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
import java.util.List;
import model.Ponto;
import util.Aresta;
import util.Dijkstra;
import util.Grafo;
import util.Vertice;

/**
 *
 * @author gautstafr
 */
public class Controller {

    /* Definições */
    private final int TTOTALX = 20; // Tamanho total do mapa.
    private final int TTOTALY = 20; // Tamanho total do mapa.

    /* Grafo de obstáculos */
    private final Grafo grafoObstaculos;

    /* Grafo Visibilidade */
    private Grafo grafoVisibilidade;

    private static Controller controller; // Declaração do Controller.

    /* Variáveis */
    private Ponto pInicio = null;
    private Ponto pFim = null;

    /* Design Pattern Singleton */
    /**
     * The constructor is private for use the singleton
     */
    private Controller() {
        grafoObstaculos = new Grafo();
        grafoVisibilidade = new Grafo();
    }

    /**
     * Return the instance of controller.
     *
     * @return controller - An instance.
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Reset the controller.
     */
    public static void resetController() {
        controller = null;
    }

    /* End Singleton */
    public int adicionarObstaculo(int x, int y) {
        Ponto p;
        int[] arrayX = {-1, 0, 1, 1, 1, 0, -1, -1, -1};
        int[] arrayY = {-1, -1, -1, 0, 1, 1, 1, 0, -1};
        for (int i = 0; i < 8; i++) {
            p = buscarPonto(x + arrayX[i], y + arrayY[i], grafoObstaculos);
            if (p != null) {
                return 0;
            }
        }
        if (buscarPonto(x, y, grafoObstaculos) == null) {
            inserirPonto(new Ponto(x, y, true), grafoObstaculos);
            return 1;
        }
        return 0;
    }

    public void removerObstaculo(int x, int y) {
        Ponto p = buscarPonto(x, y, grafoObstaculos);
        if (p != null) {
            grafoObstaculos.removerVertice(p);
        }
    }

    private Ponto criarPonto(int x, int y, Grafo g) {
        Ponto p;
        if (x >= 0 && x < TTOTALX) {
            if (y >= 0 && y < TTOTALY) {
                p = buscarPonto(x, y, g);
                if (p != null) {
                    return p;
                }
                return new Ponto(x, y, false);
            }
        }
        return null;
    }

    private Vertice inserirPonto(Ponto p, Grafo g) {
        Vertice v;
        if (p == null || g == null) {
            return null;
        } else {
            v = buscarVertice(p.getX(), p.getY(), g);
            if(v != null){
                return v;
            }
            return g.inserir(p);
        }
    }

    private void criarArestaNaoOrientada(Vertice v1, Vertice v2, double distancia, Grafo g) {
        if (v1 != null && v2 != null && g != null) {
            g.inserirArestaNaoOrientada(v1, v2, distancia);
        }
    }

    public Grafo criarGrafoDeVisibilidade() {
        Iterator<Vertice> it;
        Vertice v1, v2, v3;
        Ponto p1, p2, p3;
        Grafo grafoEx;
        int x, y;
        int[] arrayX = {-1, 0, 1, 1, 1, 0, -1, -1, -1};
        int[] arrayY = {-1, -1, -1, 0, 1, 1, 1, 0, -1};

        if (grafoObstaculos == null) {
            return null;
        } else {
            grafoEx = new Grafo();
            it = grafoObstaculos.getVertices().iterator();
            while (it.hasNext()) {
                v1 = it.next();
                p1 = (Ponto) v1.getObjeto();
                x = p1.getX();
                y = p1.getY();
                p2 = null;
                v2 = null;
                for (int i = 0; i < 8; i++) {
                    System.out.println(i);
                    if (p2 == null) {
                        System.out.println("Ponto: (" + (x + arrayX[i]) + "," + (y + arrayY[i]) + ")");
                        p2 = criarPonto(x + arrayX[i], y + arrayY[i], grafoEx);
                        v2 = inserirPonto(p2, grafoEx);
                        if(i == 7){
                            p3 = criarPonto(x + arrayX[0], y + arrayY[0], grafoEx);
                            v3 = inserirPonto(p3, grafoEx);
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx);
                        }
                        if(p2 == null){
                            System.out.println("Ponto Nulo");
                        }
                    } else {
                        System.out.println("Verificando se existe próximo");
                        p3 = criarPonto(x + arrayX[i + 1], y + arrayY[i + 1], grafoEx);
                        if (p3 != null && (p3.getX() == p2.getX() || p3.getY() == p2.getY())) {
                            System.out.println("Existe");
                            v3 = inserirPonto(p3, grafoEx);
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx);
                            v2 = v3;
                            p2 = p3;
                            i++;
                        } else {
                            System.out.println("Não Existe");
                            p3 = criarPonto(x + arrayX[i], y + arrayY[i], grafoEx);
                            v3 = inserirPonto(p3, grafoEx);
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx);
                            v2 = v3;
                            p2 = p3;
                        }
                    }
                }
            }
            exibirPontos(grafoEx);
            exibirArestas(grafoEx);
            return grafoEx;
        }
    }

    /**
     * Busca um ponto seguindo a posição no grafo determinado.
     *
     * @param x - Coordenada X do ponto em questão.
     * @param y - Coordenada Y do ponto em questão.
     * @param grafo - Grafo no qual a busca será realizada.
     * @return ponto - O ponto encontrado, null - Caso o ponto não exista.
     */
    private Ponto buscarPonto(int x, int y, Grafo grafo) {
        Iterator<Vertice> it = grafo.getVertices().iterator();// Recebe o iterador na lista de vertices do grafo.
        Vertice v;
        Ponto p;
        while (it.hasNext()) {// Verifica se existe vertices.
            v = it.next(); // Armazena o vértice.
            p = (Ponto) v.getObjeto(); // Obtém o objeto do vértice.
            if (p.getX() == x && p.getY() == y) { // Caso os parâmetros sejam iguais, foi encontrado o ponto.
                return p; // Retorna o ponto encontrado.
            }
        }
        return null; // Retorna caso o vértice não exista.
    }

    private Vertice buscarVertice(int x, int y, Grafo grafo) {
        Vertice v;
        Ponto p;
        Iterator<Vertice> it = grafo.getVertices().iterator();
        while (it.hasNext()) {
            v = it.next();
            p = (Ponto) v.getObjeto();
            if (p.getX() == x && p.getY() == y) {
                return v;
            }
        }
        return null;
    }

    private double calcularDistancia(Ponto a, Ponto b) {
        if(a == null || b == null){
            return 0;
        }
        return (Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY())));
    }

    /**
     * @return the pInicio
     */
    public Ponto getpInicio() {
        return pInicio;
    }

    /**
     * Altera o ponto de início.
     *
     * @param x - Posição X do novo ponto.
     * @param y - Posição Y do novo ponto.
     */
    public void setpInicio(int x, int y) {
        this.pInicio = new Ponto(x, y, false);
    }

    /**
     * @return the pFim
     */
    public Ponto getpFim() {
        return pFim;
    }

    /**
     * Altera o ponto final.
     *
     * @param x - Posição X do novo ponto.
     * @param y - Posição Y do novo ponto.
     */
    public void setpFim(int x, int y) {
        this.pFim = new Ponto(x, y, false);
    }

    public void exibirPontos(Grafo grafo) {
        Vertice v;
        Ponto p;
        Iterator<Vertice> it = grafo.getVertices().iterator();
        while (it.hasNext()) {
            v = it.next();
            p = (Ponto) v.getObjeto();
            System.out.println("Ponto(" + p.getX() + "," + p.getY() + ")");
        }
    }

    public void exibirArestas(Grafo grafo) {
        Aresta a;
        Ponto p1, p2;
        Iterator<Aresta> it = grafo.getArestas().iterator();
        while (it.hasNext()) {
            a = it.next();
            p1 = (Ponto) a.getVertice1().getObjeto();
            p2 = (Ponto) a.getVertice2().getObjeto();
            System.out.println("Ponto (" + p1.getX() + "," + p1.getY() + ") - (" + p2.getX() + "," + p2.getY() + ")");
        }
    }

    private void exibirCaminho(Grafo grafo, Vertice inicio, Vertice fim) {
        Dijkstra operador = new Dijkstra(grafo);
        operador.executar(inicio);
        List<List<Vertice>> caminhos = operador.getCaminho(null, fim);
        if (caminhos == null) {
            System.out.println("Não existe caminhos");
            return;
        }
        Iterator<List<Vertice>> it = caminhos.iterator();
        Iterator<Vertice> it2;
        Ponto p;
        int i = 0;
        if (!it.hasNext()) {
            System.out.println("Não existe caminhos");
        } else {
            while (it.hasNext()) {
                List<Vertice> next = it.next();
                it2 = next.iterator();
                System.out.println("Caminho " + ++i);
                while (it2.hasNext()) {
                    Vertice next1 = it2.next();
                    p = (Ponto) next1.getObjeto();
                    System.out.print("(" + p.getX() + "," + p.getY() + ") ");
                }
                System.out.println("");
            }
        }
    }

    private void exibirProtocoloCaminho(List<Vertice> caminho) {
        String protocolo = "#" + (caminho.size() - 1);
        protocolo += "00-00-";
        Vertice v1 = null, v2 = null;
        Ponto p1, p2, p3;
        double dist = 0;
        for (Vertice vertice : caminho) {
            if (v1 == null) {
                v1 = vertice;
            } else {
                p1 = (Ponto) v1.getObjeto();
                p2 = (Ponto) vertice.getObjeto();
                p3 = new Ponto(p1.getX(), p2.getY(), false);
            }
        }
    }
}