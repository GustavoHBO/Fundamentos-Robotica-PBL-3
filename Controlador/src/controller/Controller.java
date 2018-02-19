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
package controller;

import java.util.Iterator;
import model.Ponto;
import util.Aresta;
import util.Grafo;
import util.Vertice;

/**
 *
 * @author gautstafr
 */
public class Controller {
    
    /* Definições */
    private final int ESPACO = 1; // Espaço entre os vertices.
    private final int TTOTALX = 20; // Tamanho total do mapa.
    private final int TTOTALY = 20; // Tamanho total do mapa.
    
    /* Grafo de obstáculos */
    private Grafo grafoObstaculos;
    
    /* Grafo Expandido */
    private Grafo grafoExpandido;
    
    /* Grafo Visibilidade */
    private Grafo grafoVisibilidade;
    
    private static Controller controller; // Declaração do Controller.
    
    /* Design Pattern Singleton */
    
    /**
     * The constructor is private for use the singleton
     */
    private Controller(){
        grafoObstaculos = new Grafo();
        grafoExpandido = new Grafo();
        grafoVisibilidade = new Grafo();
    }
    
    /**
     * Return the instance of controller.
     * @return controller - An instance.
     */
    public static Controller getInstance(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }
    
    /**
     * Reset the controller.
     */
    public static void resetController(){
        controller = null;
    }
                            /* End Singleton */
    
    public int adicionarObstaculo(int x, int y) {
        if (buscarPonto(x - 1, y - 1, grafoObstaculos) != null) {
            return 0;
        } else if(buscarPonto(x, y - 1, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x + 1, y - 1, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x - 1, y, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x + 1, y, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x - 1, y + 1, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x, y + 1, grafoObstaculos) != null){
            return 0;
        } else if(buscarPonto(x + 1, y + 1, grafoObstaculos) != null){
            return 0;
        } else if (buscarPonto(x, y, grafoObstaculos) == null) {
            grafoObstaculos.inserir(new Ponto(x, y, true));
        }
        return 1;
    }
    
    public void removerObstaculo(int x, int y){
        Ponto p = buscarPonto(x, y, grafoObstaculos);
        if(p == null){
            return;
        } else {
            grafoObstaculos.removerVertice(p);
        }
    }
    
    public void expandirObstaculos(){
        if(grafoObstaculos.getVertices().isEmpty()){
            return;
        } else {
            grafoExpandido = new Grafo();
            Iterator<Vertice> it = grafoObstaculos.getVertices().iterator();
            Vertice v;
            Ponto p;
            while(it.hasNext()){
                v = it.next();
                p = (Ponto) v.getObjeto();
                System.out.println(p.getX() + "  " + p.getY());
                if(p.isObstaculo()){
                    criarPontosEmVolta(p.getX(), p.getY());
                }
            }
        }
        System.out.println("Quantidade de pontos: " + grafoExpandido.getVertices().size());
        exibirPontos(grafoExpandido);
        montarGrafoVisibilidade(grafoExpandido);
    }
    
    /**
     * Busca um ponto seguindo a posição no grafo determinado.
     * @param x - Coordenada X do ponto em questão.
     * @param y - Coordenada Y do ponto em questão.
     * @param grafo - Grafo no qual a busca será realizada.
     * @return ponto - O ponto encontrado, null - Caso o ponto não exista.
     */
    private Ponto buscarPonto(int x, int y, Grafo grafo){
        Iterator<Vertice> it = grafo.getVertices().iterator();// Recebe o iterador na lista de vertices do grafo.
        Vertice v;
        Ponto p;
        while(it.hasNext()){// Verifica se existe vertices.
            v = it.next(); // Armazena o vértice.
            p = (Ponto) v.getObjeto(); // Obtém o objeto do vértice.
            if(p.getX() == x && p.getY() == y){ // Caso os parâmetros sejam iguais, foi encontrado o ponto.
                return p; // Retorna o ponto encontrado.
            }
        }
        return null; // Retorna caso o vértice não exista.
    }
    
    /**
     * Cria pontos em volta do obstáculo no grafo expnadido.
     * @param x - Coordenada X do ponto em questão.
     * @param y - Coordenada Y do ponto em questão.
     */
    private void criarPontosEmVolta(int x, int y){
        Ponto p;
        // Expande os pontos em volta dos obstáculos.
        for (int i = 0; i < 3; i++) { // Quantidade de pontos na vertical.
            for (int j = 0; j < 3; j++) { // Quantidade de pontos na horizontal.
                p = buscarPonto(x-1 + j, y-1 + i, grafoExpandido); // Verifica se o ponto já foi criado anteriormente.(Evitar pontos iguais).
                if( p == null && x-1 + j >= 0 && y-1 + i >= 0){ // Verifica se existe o ponto e se ele ultrapassa a margem superior ou esquerda.
                    if (x-1 + j < TTOTALX && y-1 + i < TTOTALY) { // Verifica se o ponto ultrapassa a margem inferior ou direita.
                        if ((buscarPonto(x - 1 + j, y - 1 + i, grafoObstaculos) == null)) { // Verifica se o ponto vai ser criado em cima de um obstáculo.
                            grafoExpandido.inserir(new Ponto(x - 1 + j, y - 1 + i, false)); // Cria o ponto.
                        }
                    }
                }
            }
        }
    }
    
    private void montarGrafoVisibilidade(Grafo grafo) {
        Ponto p;
        Ponto pD, pE, pA, pA2; // Ponto a direita, ponto a esquerda, ponto auxiliar.
        Ponto pC, pB; // Ponto acima e ponto abaixo.
        Vertice v1, v2, v3;
        grafoVisibilidade = new Grafo();
        Iterator<Vertice> it = grafo.getVertices().iterator();
        while (it.hasNext()) {
            v1 = it.next();
            p = (Ponto) v1.getObjeto();
            pE = buscarVisinhoEsquerda(p.getX(), p.getY(), grafo);
            if (pE == null) {
                pE = p;
            }
            if (buscarPonto(pE.getX(), pE.getY(), grafoVisibilidade) == null) {
                grafoVisibilidade.inserir(pE);
            }
            v2 = buscarVertice(pE.getX(), pE.getY(), grafoVisibilidade);
            pD = buscarVisinhoDireita(p.getX(), p.getY(), grafo);
            if (pD == null) {
                pD = p;
            }
            if (buscarPonto(pD.getX(), pD.getY(), grafoVisibilidade) == null) {
                grafoVisibilidade.inserir(pD);
            }
            if (pE.getX() == pD.getX() && pE.getY() == pD.getY()) {
                pA = buscarVisinhoCima(pE.getX(), pE.getY(), grafo);
                if (pA == null) {
                    pA = pE;
                    pA2 = buscarVisinhoBaixo(pE.getX(), pE.getY(), grafo);
                    if(pA2 == null){
                        pA = null;
                    }
                } else {
                    pA2 = buscarVisinhoBaixo(pE.getX(), pE.getY(), grafo);
                    if(pA2 == null){
                        pA2 = pE;
                    }
                }
                if (pA == null) {// Só entra aqui caso o ponto não possua nenhum vizinho.
                    if (buscarPonto(pE.getX(), pE.getY(), grafoVisibilidade) == null) {
                        grafoVisibilidade.inserir(pE);
                    }
                } else {
                    v2 = buscarVertice(pA.getX(), pA.getY(), grafoVisibilidade);
                    v3 = buscarVertice(pA2.getX(), pA2.getY(), grafoVisibilidade);
                    grafoVisibilidade.inserirAresta(v2, v3, ESPACO);
                }
            } else {
                if (pD != null) {
                    v3 = buscarVertice(pD.getX(), pD.getY(), grafoVisibilidade);
                    grafoVisibilidade.inserirAresta(v2, v3, ESPACO);
                }
                pC = buscarVisinhoCima(p.getX(), p.getY(), grafo);
                if (pC == null) {
                    pC = p;
                }
                v2 = buscarVertice(pC.getX(), pC.getY(), grafoVisibilidade);
                pB = buscarVisinhoBaixo(p.getX(), p.getY(), grafo);
                if (pB == null) {
                    pB = p;
                }
                if (buscarPonto(pB.getX(), pB.getY(), grafoVisibilidade) == null) {
                    grafoVisibilidade.inserir(pB);
                }
                if (pC.getX() != pB.getX() || pC.getY() != pB.getY()) {
                    v3 = buscarVertice(pB.getX(), pB.getY(), grafoVisibilidade);
                    grafoVisibilidade.inserirAresta(v2, v3, ESPACO);
                }
            }
        }
        System.out.println("Quantidade de Vertices do grafo de visibilidade: " + grafoVisibilidade.getVertices().size());
        System.out.println("Quantidade de arestas do grafo de visibilidade: " + grafoVisibilidade.getArestas().size());
        exibirPontos(grafoVisibilidade);
        exibirArestas(grafoVisibilidade);
    }
    
    private Ponto buscarVisinhoDireita(int x, int y, Grafo grafo) {
        Ponto p = buscarPonto(x + ESPACO, y, grafo);
        if (p != null) {
            if (buscarPonto(p.getX(), p.getY() + ESPACO, grafo) != null || buscarPonto(p.getX(), p.getY() - ESPACO, grafo) != null) {
                return p;
            } else if(buscarPonto(p.getX() + ESPACO, p.getY(), grafo) != null){
                return buscarVisinhoDireita(p.getX(), p.getY(), grafo);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private Ponto buscarVisinhoEsquerda(int x, int y, Grafo grafo) {
        Ponto p = buscarPonto(x - ESPACO, y, grafo);
        if (p != null) {
            if (buscarPonto(p.getX(), p.getY() + ESPACO, grafo) != null || buscarPonto(p.getX(), p.getY() - ESPACO, grafo) != null) {
                return p;
            } else if (buscarPonto(p.getX() - ESPACO, p.getY(), grafo) != null) {
                return buscarVisinhoEsquerda(p.getX(), p.getY(), grafo);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private Ponto buscarVisinhoCima(int x, int y, Grafo grafo) {
        Ponto p = buscarPonto(x, y - ESPACO, grafo);
        if (p != null) {
            if (buscarPonto(p.getX() + ESPACO, p.getY(), grafo) != null || buscarPonto(p.getX() - ESPACO, p.getY(), grafo) != null) {
                return p;
            } else if (buscarPonto(p.getX(), p.getY() - ESPACO, grafo) != null) {
                return buscarVisinhoCima(p.getX(), p.getY(), grafo);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    private Ponto buscarVisinhoBaixo(int x, int y, Grafo grafo) {
        Ponto p = buscarPonto(x, y + ESPACO, grafo);
        if (p != null) {
            if (buscarPonto(p.getX() + ESPACO, p.getY(), grafo) != null || buscarPonto(p.getX() - ESPACO, p.getY(), grafo) != null) {
                return p;
            } else if (buscarPonto(p.getX(), p.getY() + ESPACO, grafo) != null) {
                return buscarVisinhoBaixo(p.getX(), p.getY(), grafo);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    private Vertice buscarVertice(int x, int y, Grafo grafo){
        Vertice v = null;
        Ponto p = null;
        Iterator<Vertice> it = grafo.getVertices().iterator();
        while(it.hasNext()){
            v = it.next();
            p = (Ponto) v.getObjeto();
            if(p.getX() == x && p.getY() == y){
                return v;
            }
        }
        return null;
    }
    
    public void exibirPontos(Grafo grafo){
        Vertice v = null;
        Ponto p = null;
        Iterator<Vertice> it = grafo.getVertices().iterator();
        while(it.hasNext()){
            v = it.next();
            p = (Ponto) v.getObjeto();
            System.out.println("Ponto(" + p.getX() + "," + p.getY() + ")");
        }
    }
    
    public void exibirArestas(Grafo grafo){
        Aresta a = null;
        Ponto p1, p2 = null;
        Iterator<Aresta> it = grafo.getArestas().iterator();
        while(it.hasNext()){
            a = it.next();
            p1 = (Ponto) a.getVertice1().getObjeto();
            p2 = (Ponto) a.getVertice2().getObjeto();
            System.out.println("Ponto (" + p1.getX() + "," + p1.getY() + ") - (" + p2.getX() + "," + p2.getY() + ")");
        }
    }
}
