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
import java.util.Iterator;
import java.util.List;

public class Grafo{

    private int numVertices;
    private int numArestas;
    private final List<Vertice> listaVertices;
    private final List<Aresta> listaArestas;

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Construtor, inicializa os atributos da classe.
     */
    public Grafo(){
            numVertices = 0;
            numArestas = 0;
            listaVertices = new ArrayList<>();
            listaArestas = new ArrayList<>();
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para retorno da quantidade de v�rtices do grafo
     * @return int - N�mero de v�rtices do grafo
     */
    public int numVertices() {
            return numVertices;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para retorno da quantidade de arestas do grafo
     * @return int - N�mero de arestas do grafo
     */
    public int numArestas() {
            return numArestas;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para retorno dos v�rtices do grafo
     * @return listaVertices - Lista de todos os v�rtices do grafo
     */
    public List<Vertice> getVertices(){
            return listaVertices;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para retorno das arestas do grafo
     * @return listaArestas - Lista de todas as arestas do grafo
     */
    public List<Aresta> getArestas(){
            return listaArestas;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Insere o v�rtice a partir do objeto do v�rtice.
     * @param o - Objeto inserido
     * @return Vertice - O v�rtice com o objeto inserido
     */
    public Vertice inserir(Object o) {
            if(o == null){// Caso o objeto recebido seja nulo.
                    return null;
            }
            Vertice vertice = new Vertice(o);
            listaVertices.add(vertice);
            numVertices++;

            return vertice;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * M�todo para inserir uma aresta orientada
     * @param v - V�rtice de origem
     * @param w - V�rtice de destino
     * @param peso - Peso da aresta
     */
    public void inserirAresta(Vertice v, Vertice w, double peso) {
        Iterator<Aresta> it = listaArestas.iterator();
        Aresta a;
        if (v == null || w == null) {
            return;
        }
        
        while (it.hasNext()) {            
            a = it.next();
            if(a.getVertice1().equals(v) && a.getVertice2().equals(w) && a.getPeso() == peso){
                return;
            }
        }
        Aresta aresta = new Aresta(v, w);
        aresta.setPeso(peso);
        numArestas++;
        listaArestas.add(aresta);
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Inserir no grafouma aresta n�o orientada
     * @param v - Vertice 1.
     * @param w - V�rtice 2.
     * @param peso - Peso do caminho entre os dois.
     */
    public void inserirArestaNaoOrientada(Vertice v, Vertice w, double peso) {
            inserirAresta(v , w , peso);
            inserirAresta(w , v , peso);
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Remove a aresta.
     * @param a - Aresta do grafo.
     */
    public void removerAresta(Aresta a) {
        Aresta aresta = buscarAresta(a.getVertice1(), a.getVertice2());
        if (aresta != null) {
            listaArestas.remove(aresta);
        }

    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Remove o v�rtice a partir do objeto do v�rtice.
     * @param o - Objeto do v�rtice a ser removido.
     * @return vertice - V�rtice removido || null - Caso o v�rtice n�o seja encontrado.
     */
    public Vertice removerVertice(Object o){
            Iterator<Aresta> it = listaArestas.iterator();

            Vertice vertice = buscarVertice(o);

            if(vertice == null){
                    return null;
            }
            Aresta aresta;
            while(it.hasNext()){
                    aresta = it.next();
                    if(aresta.getVertice1().equals(vertice) || aresta.getVertice2().equals(vertice)){
                            listaArestas.remove(aresta);
                    }
            }
            listaVertices.remove(vertice);
            return vertice;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Busca o v�rtice a partir do objeto armazenado nele.
     * @param objeto - Objeto do v�rtice procurado.
     * @return vertice - V�rtice procurado | null - Caso o v�rtice n�o seja encontrado.
     */
    public Vertice buscarVertice(Object objeto){
            Iterator<Vertice> it = listaVertices.iterator();
            Vertice vertice;
            while(it.hasNext()){
                    vertice = it.next();
                    if(vertice.getObjeto().equals(objeto)){
                            return vertice;
                    }
            }
            return null;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Busca a aresta a partir dos v�rtices que determinam o inicio e fim da aresta.
     * @param vertice1 - V�rtice origem da aresta.
     * @param vertice2 - V�rtice destino da aresta.
     * @return Aresta - Aresta procurada || null - Caso a aresta n�o seja encontrada.
     */
    public Aresta buscarAresta(Vertice vertice1, Vertice vertice2) {
        Iterator<Aresta> it = listaArestas.iterator();
        Aresta aresta;
        while (it.hasNext()) {
            aresta = it.next();
            if (aresta.getVertice1().equals(vertice1) && aresta.getVertice2().equals(vertice2)) {
                return aresta;
            }
        }
        return null;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Retorna uma lista de todas as aresta incidentes no v�rtice encontrado.
     * @param nome - Nome do v�rtice.
     * @return arestasIncidentes - Todas as arestas incidentes no v�rtice encontrado.
     */
    public List<Aresta> arestasIncidentes(String nome) {
        List<Aresta> arestasIncidentes = new ArrayList<>();
        Iterator<Aresta> it = listaArestas.iterator();
        Aresta aresta;

        while (it.hasNext()) {
            aresta = it.next();
            if (aresta.getVertice1().getObjeto().equals(nome) || aresta.getVertice2().getObjeto().equals(nome)) {
                arestasIncidentes.add(aresta);
            }
        }
        if (arestasIncidentes.isEmpty()) {
            return null;
        }
        return arestasIncidentes;
    }

    /*______________________________________________________________________________________________________________________________*/
    /**
     * Retorna um operador Dijkstra do grafo recebido.
     * @return Dijkstra - Operador dijkstra do grafo.
     */
    public Dijkstra menorCaminho(){
            return new Dijkstra(this);
    }
    /*______________________________________________________________________________________________________________________________*/
}
