/*
 * Copyright (C) 2018 Gustavo Henrique.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package util;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe do algoritmo de Dijkstra, respons�vel por descobrir o menor caminho em um grafo.
 * @author Gustavo Henrique.
 */
public class Dijkstra {

    private final List<Aresta> arestas;
    private List<List<Vertice>> listaResultados;
    private Set<Vertice> nosVisitados;
    private Set<Vertice> nosNaoVisitados;
    private Map<Vertice, ArrayList<Vertice>> antecessores;
    private Map<Vertice, Double> peso;

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Construtor da classe, recebe o grafo a ser utilizado no algoritmo.
     * @param grafo - Grafo utilizado no algoritmo.
     */
    public Dijkstra(Grafo grafo) {
            this.arestas = new ArrayList<>(grafo.getArestas());
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Gera as menores dist�ncias da raiz para cada outro v�rtice conexo do grafo.
     * @param raiz - V�rtice de origem.
     */
    public void executar(Vertice raiz) {
        listaResultados = new ArrayList<>();
        nosVisitados = new HashSet<>();
        nosNaoVisitados = new HashSet<>();
        peso = new HashMap<>();
        antecessores = new HashMap<>();
        peso.put(raiz, 0.0);
        nosNaoVisitados.add(raiz);
        while (nosNaoVisitados.size() > 0) {
            Vertice no = getVertMenorDistancia(nosNaoVisitados);
            nosVisitados.add(no);
            nosNaoVisitados.remove(no);
            acharDistanciaMinima(no);
        }
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Acha a dist�ncia m�nima at� o n�.
     * @param no - Vertice.
     */
    private void acharDistanciaMinima(Vertice no) {
        List<Vertice> nosAdjacentes = getVizinhos(no);
        ArrayList<Vertice> listaAuxiliar;
        for (Vertice target : nosAdjacentes) {
            if (getMenorDistancia(target) > getMenorDistancia(no) + getDistancia(no, target)) {
                peso.put(target, getMenorDistancia(no) + getDistancia(no, target));
                listaAuxiliar = new ArrayList<>();
                listaAuxiliar.add(no);
                antecessores.put(target, listaAuxiliar);
                nosNaoVisitados.add(target);
            } else if (getMenorDistancia(target) == getMenorDistancia(no) + getDistancia(no, target)) {
                antecessores.get(target).add(no);
            }
        }

    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para retorno do peso da aresta entre n�s adjacentes.
     * @param Vertice - N� do grafo.
     * @param Vertice - N� do grafo.
     * @return double - Peso da aresta.
     */
    private double getDistancia(Vertice no, Vertice no2) {
        for (Aresta aresta : arestas) {
            if (aresta.getVertice1().equals(no)
                    && aresta.getVertice2().equals(no2)) {
                return aresta.getPeso();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo que descobre todos os v�rtices adjacentes a certo v�rtice.
     * @param no - V�rtice do grafo.
     * @return adjacentes - Lista de v�rtices.
     */
    private List<Vertice> getVizinhos(Vertice no) {
        List<Vertice> adjacentes = new ArrayList<>();
        for (Aresta aresta : arestas) {
            if (aresta.getVertice1().equals(no)
                    && !isVisitado(aresta.getVertice2())) {
                adjacentes.add(aresta.getVertice2());
            }
        }
        return adjacentes;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para c�lculo do v�rtice com a menor dist�ncia
     * @param Set<Vertice> - Lista de V�rtices
     * @return Vertice - Peso da aresta.
     */
    private Vertice getVertMenorDistancia(Set<Vertice> vertices) {
        Vertice minimo = null;
        for (Vertice vertice : vertices) {
            if (minimo == null) {
                minimo = vertice;
            } else {
                if (getMenorDistancia(vertice) < getMenorDistancia(minimo)) {
                    minimo = vertice;
                }
            }
        }
        return minimo;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Verifica se o n� j� foi visitado
     * @return Boolean - Se j� foi visitado
     */
    private boolean isVisitado(Vertice vertice) {
            return nosVisitados.contains(vertice);
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Retorna o valor da menor dist�ncia j� calculada.
     * @param Vertice - V�rtice do grafo.
     * @return double - Peso da aresta.
     */
    private double getMenorDistancia(Vertice vertice) {
            Double d = peso.get(vertice);
            if (d == null) {
                    return Double.MAX_VALUE;
            } else {
                    return d;
            }
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo que retorna todos os caminhos m�nimos .
     * @param aux - V�rtice origem.
     * @param vertice - Caminho m�nimo at� o v�rtice origem.
     * @return listaResultados - Lista de caminhos m�nimos.
     */
    public List<List<Vertice>> getCaminho(ArrayList<Vertice> aux, Vertice vertice) {
            if(aux == null){
                    aux = new ArrayList<>();
            }
            ArrayList<Vertice> caminho = new ArrayList<>(aux);
            Vertice vertAux = vertice;
            ArrayList<Vertice> vertAuxList;

            if (antecessores.get(vertAux) == null) {
                    return null;
            }
            caminho.add(vertAux);
            while (antecessores.get(vertAux) != null) {
                    vertAuxList = antecessores.get(vertAux);
                    if(vertAuxList.size() > 1){
                            for(int i = 1; i < vertAuxList.size(); i++){
                                    vertAux = vertAuxList.get(i);
                                    getCaminho(caminho, vertAux);
                            }
                    }
                    vertAux = vertAuxList.get(0);
                    caminho.add(vertAux);
            }

            Collections.reverse(caminho);
            listaResultados.add(caminho);
            return listaResultados;
    }
    /*______________________________________________________________________________________________________________________________*/
} 
