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

import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;
import model.Ponto;
import util.Aresta;
import util.Dijkstra;
import util.Grafo;
import util.Vertice;

/**
 * Classe controller, possui os m�todos necess�rios para um bom funcionamento do sistema.
 * @author Gustavo Henrique.
 * @since 22 de Fevereiro de 2018.
 */
public class Controller {
//circunfer�ncia
                                /* Defini��es */
    private final int TTOTALX = 20; // Tamanho total do mapa.
    private final int TTOTALY = 20; // Tamanho total do mapa.
    
    private final int CLICKSENCODER = 360; // Quantidade de click's de uma volta completa da roda do rob�;
    
    private final double RAIORODA = 2.8; // Em cent�metros.
    private final double CIRCUNFERENCIARODA = Math.PI * 2 * RAIORODA; // Circufer�ncia do circulo formado por um giro de 360� da roda.
                                                                 // Ou seja, a dist�ncia percorrida com um giro completo.
    private final double RAIOEIXO = 8.85; // Em cent�metros. Medidas feitas com uma r�gua com erro de +- 0.05 cm.
    private final double CIRCUNFERENCIAROBO = Math.PI * 2 * RAIOEIXO; // Circufer�ncia do circulo formado por um giro de 360� do rob�.
                                                                 // Ou seja, a dist�ncia percorrida com um giro completo.

    /* Grafo de obst�culos */
    private final Grafo grafoObstaculos;

    /* Grafo Visibilidade */
    private Grafo grafoVisibilidade;

    /* Singleton */
    private static Controller controller; // Declara��o do Controller.

    /* Vari�veis */
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
    
    /*============================================================ M�TODOS P�BLICOS ==========================================================*/
    
    /**
     * M�todo de adicionar obst�culo. Adiciona um obst�culo no grafo de obst�culos(grafoObstaculos). Por�m, antes � verificado se existe
     * algum obst�culo por perto(1 unidade de deslocamento). Caso n�o exista obst�culos pr�ximos o obst�culo � criado e o m�todo retorna 1,
     * caso contr�rio, o m�todo retorna 0.
     * @param x - Posi��o no eixo X no qual ser� criado o obst�culo.
     * @param y - Posi��o no eixo Y no qual ser� criado o obst�culo.
     * @return 0 - Caso o obst�culo n�o seja criado, 1 - Caso o obst�culo seja criado.
     */
    public int adicionarObstaculo(int x, int y) {
        Ponto p;
        int[] arrayX = {-1, 0, 1, 1, 1, 0, -1, -1, -1};// Array que controla a forma que o "for" vai varrer em torno do ponto(coordenadas x).
        int[] arrayY = {-1, -1, -1, 0, 1, 1, 1, 0, -1};// Array que controla a forma que o "for" vai varrer em torno do ponto(coordenadas x).
        for (int i = 0; i < 8; i++) {// Percorre em volta do ponto.
            p = buscarPonto(x + arrayX[i], y + arrayY[i], grafoObstaculos);// Busca o ponto.
            if (p != null) {// Verifica se j� existe um ponto. Se existir retorna 0 e n�o adiciona o obst�culo.
                return 0;
            }
        }
        /* Caso n�o exista obst�culos por perto, verifica-se ent�o se existe um obst�culo no ponto determinado */
        if (buscarPonto(x, y, grafoObstaculos) == null) {// Se n�o existir cria o ponto.
            inserirPonto(new Ponto(x, y, true), grafoObstaculos);// Cria o ponto e o insere no grafo de obst�culos(grafoObstaculos).
            return 1;
        }
        return 0;
    }

    /**
     * M�todo para remo��o de obst�culos, utilizando os par�metros recebidos � removido o obst�culo correspondente, se existir.
     * @param x - Posi��o no eixo X no qual ser� removido o obst�culo.
     * @param y - Posi��o no eixo Y no qual ser� removido o obst�culo.
     */
    public void removerObstaculo(int x, int y) {
        Ponto p = buscarPonto(x, y, grafoObstaculos);// Busca o ponto.
        if (p != null) {// Verifica se o ponto existe.
            grafoObstaculos.removerVertice(p); // Caso exista o ponto � removido.
        }
    }
    
    public boolean calcularCaminho(){
        Grafo grafoExpandido = expandirObstaculos();
        Grafo gObstaculos = new Grafo(grafoExpandido);
        Ponto pontoInicio, pontoFim;
        Vertice v1, v2;
        
        pontoInicio = getpInicio();
        pontoFim = getpFim();
        
        if(pontoInicio == null || pontoFim == null){// Caso o ponto de in�cio ou fim n�o tenha sido definido.
            return false;
        } else {
            if(grafoExpandido == null){
                grafoExpandido = new Grafo();
                gObstaculos = new Grafo();
            }
            v1 = inserirPonto(pontoInicio, grafoExpandido);
            v2 = inserirPonto(pontoFim, grafoExpandido);
            criarGrafoVisibilidade(grafoExpandido, gObstaculos);
            exibirPontos(grafoExpandido);
            exibirArestas(grafoExpandido);
            exibirCaminho(grafoExpandido, v1, v2);
        }
        return true;
    }
    
    /**
     * @return the pInicio
     */
    public Ponto getpInicio() {
        return pInicio;
    }

    /**
     * Altera o ponto de in�cio.
     *
     * @param x - Posi��o X do novo ponto.
     * @param y - Posi��o Y do novo ponto.
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
     * @param x - Posi��o X do novo ponto.
     * @param y - Posi��o Y do novo ponto.
     */
    public void setpFim(int x, int y) {
        this.pFim = new Ponto(x, y, false);
    }

    public void exibirPontos(Grafo grafo) {
        if(grafo == null){
            return;
        }
        Vertice v;
        Ponto p;
        Iterator<Vertice> it = grafo.getVertices().iterator();
        System.out.println("Tem " + grafo.getVertices().size() + "v�rtice(s)");
        while (it.hasNext()) {
            v = it.next();
            p = (Ponto) v.getObjeto();
            System.out.println("Ponto(" + p.getX() + "," + p.getY() + ")");
        }
    }

    public void exibirArestas(Grafo grafo) {
        if (grafo == null) {
            return;
        }
        Aresta a;
        Ponto p1, p2;
        Iterator<Aresta> it = grafo.getArestas().iterator();
        System.out.println("Tem " + grafo.getArestas().size() + "aresta(s)");
        while (it.hasNext()) {
            a = it.next();
            p1 = (Ponto) a.getVertice1().getObjeto();
            p2 = (Ponto) a.getVertice2().getObjeto();
            System.out.println("Ponto (" + p1.getX() + "," + p1.getY() + ") - (" + p2.getX() + "," + p2.getY() + ")");
        }
    }
    
    /*============================================================ M�TODOS PRIVADOS ==========================================================*/
    
    /**
     * M�todo para cria��o de pontos no grafo determinado. Cria o ponto utilizando os par�metros recebidos e retorna-o. Caso o ponto
     * j� exista ele � retornado, se o ponto exceder os limites do plano ele n�o � adicionado.
     * @param x - Posi��o no eixo X no qual ser� criado o ponto.
     * @param y - Posi��o no eixo Y no qual ser� criado o ponto.
     * @param g - Grafo no qual o ponto vai ser criado.
     * @return null - Caso o ponto exceda os limites do plano, ou se o grafo seja nulo, p - Caso o ponto seja criado.
     */
    private Ponto criarPonto(int x, int y, Grafo g) {
        Ponto p;
        if (g == null) {// Verifica se o grafo � nulo.
            return null;
        } else if (x >= 0 && x < TTOTALX) {// Verifica se o ponto excede o limite do eixo X.
            if (y >= 0 && y < TTOTALY) {// Verifica se o ponto excede o limite do eixo Y.
                p = buscarPonto(x, y, g); // Verifica se o ponto j� existe no grafo determinado.
                if (p != null) {
                    return p;
                }
                return new Ponto(x, y, false); // Caso n�o exista o ponto e ele no ultrapasse os limites, o ponto � criado.
            }
        }
        return null;
    }

    /**
     * M�todo de inser��o de ponto. Insere o ponto determinado no grafo escolhido. O ponto s� � inserido se o ponto e o grafo recebidos n�o
     * forem nulos. Caso o ponto j� exista o v�rtice que o cont�m � retornado.
     * @param p - Ponto a ser inserido.
     * @param g - Grafo no qual o ponto vai ser inserido.
     * @return null - Caso o ponto e o grafo sejam nulos, v - V�rtice que cont�m o ponto.
     */
    private Vertice inserirPonto(Ponto p, Grafo g) {
        Vertice v;
        if (p == null || g == null) {
            return null;
        } else {
            v = buscarVertice(p.getX(), p.getY(), g);// Busca o v�rtice que cont�m o ponto.
            if(v != null){ // Verifica se existe o v�rtice.
                return v;// Caso exista � retornado.
            }
            return g.inserir(p);//Insere o ponto e retorna o seu v�rtice.
        }
    }

    /**
     * Cria aresta n�o orientada entre os v�rtices determinados, com o peso igual a dist�ncia recebida, no grafo passado.
     * @param v1 - V�rtice 1.
     * @param v2 - V�rtice 2.
     * @param distancia - Peso da aresta a ser criada.
     * @param g - Grafo no qual a aresta vai ser criada.
     */
    private void criarArestaNaoOrientada(Vertice v1, Vertice v2, double distancia, Grafo g) {
        if (v1 != null && v2 != null && g != null) {// Verifica se os par�metros s�o v�lidos.
            g.inserirArestaNaoOrientada(v1, v2, distancia);
        }
    }

    /**
     * Cria o grafo de expans�o. Utilizando o grafo de obst�culos, � criado o grafo de expans�o. Este m�todo expande os obst�culos
     * e cria as arestas entre os v�rtices dos mesmos.
     * @return null - Caso o grafo de obst�culos seja inv�lido, grafoEx - Grafo de expans�o.
     */
    private Grafo expandirObstaculos() {
        
        /*
        Este m�todo analisa os pontos em volta do ponto obst�culo do grafo de obst�culos(grafoObstaculos). Come�ando do primeiro ponto na diagonal
        secund�ria, depois o ponto a direita, primeiro ponto na diagonal principal, em seguida o ponto abaixo, �ltimo ponto da diagonal secund�ria,
        ponto a esquerda, �ltimo ponto da diagonal principal e por fim o ponto acima. O ponto no meio n�o � verificado, o mesmo n�o deve existir
        pois segundo o grafo de obst�culos, esse ponto � um obst�culo.
        */
        
        Iterator<Vertice> it;
        Vertice v1, v2, v3;
        Ponto p1, p2, p3;
        Grafo grafoEx;
        int x, y;
        int[] arrayX = {-1, 0, 1, 1, 1, 0, -1, -1, -1}; // Array de controle da forma de varredura em volta do ponto no eixo X.
        int[] arrayY = {-1, -1, -1, 0, 1, 1, 1, 0, -1}; // Array de controle da forma de varredura em volta do ponto no eixo X.

        if (grafoObstaculos == null || grafoObstaculos.getVertices().isEmpty()) {// Verifica se o grafo � v�lido, caso 
            return null;                                                         //seja nulo o segundo par�metro n�o � executado.
        } else {
            grafoEx = new Grafo(); // Intancia o grafo de expans�o.
            it = grafoObstaculos.getVertices().iterator();
            while (it.hasNext()) {// La�o para percorrer todos os v�rtices.
                v1 = it.next();
                p1 = (Ponto) v1.getObjeto();
                x = p1.getX();
                y = p1.getY();
                p2 = null;
                v2 = null;
                for (int i = 0; i < 8; i++) {// Percorre os pontos em volta do ponto p1.
                    if (p2 == null) { // Verifica se p2 � nulo, significa que n�o foi definido nenhum dos v�rtices da aresta.
                        p2 = criarPonto(x + arrayX[i], y + arrayY[i], grafoEx);// Cria o ponto no grafo e armazen�-o.
                        v2 = inserirPonto(p2, grafoEx);// Insere o ponto no grafo e armazen�-o.
                        if(i == 7){ // Verifica se est� na �ltima execu��o.
                            p3 = criarPonto(x + arrayX[0], y + arrayY[0], grafoEx);
                            v3 = inserirPonto(p3, grafoEx);
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx);// Cria aresta entre o �ltimo ponto
                        }                                                                       // encontrado.
                    } else { // Caso p2 n�o seja nulo, � necess�rio encontrar o pr�ximo ponto para pode criar a aresta.
                        p3 = criarPonto(x + arrayX[i + 1], y + arrayY[i + 1], grafoEx); // Busca o pr�ximo ponto a ser analizado.
                        if (p3 != null && (p3.getX() == p2.getX() || p3.getY() == p2.getY())) { // Verifica se o ponto existe, caso exista
                            v3 = inserirPonto(p3, grafoEx);                         // verifica se � colinear com o ponto anterior(p2).
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx);// Caso sejam colineares, a aresta � criada.
                            v2 = v3; // Este v�rtice passa a ser o vertice anterior d� pr�xima an�lise.
                            p2 = p3;
                            i++; // Como o pr�ximo ponto j� foi analisado, ele � pulado.
                        } else { // Caso o ponto exista.
                            p3 = criarPonto(x + arrayX[i], y + arrayY[i], grafoEx);
                            v3 = inserirPonto(p3, grafoEx);
                            criarArestaNaoOrientada(v2, v3, calcularDistancia(p2, p3), grafoEx); // Cria a aresta entre os pontos.
                            v2 = v3;// Este v�rtice passa a ser o vertice anterior d� pr�xima an�lise.
                            p2 = p3;
                        }
                    }
                }
            }
            //exibirPontos(grafoEx);
            //exibirArestas(grafoEx);
            return grafoEx;
        }
    }
    
    private void criarGrafoVisibilidade(Grafo g, Grafo gObstaculos) {
        Iterator<Vertice> itV1, itV2;
        Iterator<Aresta> itA1;
        Vertice v1, v2, vP1, vP2; // V�rtice do ponto 1 e ponto 2.
        Ponto p1, p2, p3, p4;
        if (g != null) {// Caso o grafo seja v�lido.
            itV1 = g.getVertices().iterator();
            while (itV1.hasNext()) {
                v1 = itV1.next();
                itV2 = g.getVertices().iterator();
                while (itV2.hasNext()) {
                    v2 = itV2.next();
                    if (!v1.equals(v2)) { // Se forem iguais pulo para o pr�ximo.
                        p1 = (Ponto) v1.getObjeto();
                        p2 = (Ponto) v2.getObjeto();
                        p3 = null;
                        p4 = null;
                        for (Aresta a1 : gObstaculos.getArestas()) {
                            p3 = (Ponto) a1.getVertice1().getObjeto();
                            p4 = (Ponto) a1.getVertice2().getObjeto();
                            if (temIntersecao(p1, p2, p3, p4)) {
                                System.out.println("Break");
                                break;
                            }
                            System.out.println("Sem interse��o");
                        }
                        
                        System.out.println("Pr�ximo Ponto");
                        if (!temIntersecao(p1, p2, p3, p4) && !g.getArestas().isEmpty()) { // Verifica se finalizou o la�o com uma interse��o e verifica se s� tem o ponto de in�cio e o de fim.
                            v1 = buscarVertice(p1.getX(), p1.getY(), g);// Caso n�o tenha sido finalizado com uma interse��o, ent�o podemos adicionar a aresta
                            v2 = buscarVertice(p2.getX(), p2.getY(), g);// entre os pontos no grafo g.
                            g.inserirArestaNaoOrientada(v1, v2, calcularDistancia(p1, p2));
                        } else if (g.getArestas().isEmpty()) {
                            g.inserirArestaNaoOrientada(v1, v2, calcularDistancia(p1, p2));
                        }
                    }
                }

            }
        }
    }

    /**
     * Busca um ponto seguindo a posi��o no grafo determinado.
     *
     * @param x - Coordenada X do ponto em quest�o.
     * @param y - Coordenada Y do ponto em quest�o.
     * @param grafo - Grafo no qual a busca ser� realizada.
     * @return ponto - O ponto encontrado, null - Caso o ponto n�o exista.
     */
    private Ponto buscarPonto(int x, int y, Grafo grafo) {
        Iterator<Vertice> it = grafo.getVertices().iterator();// Recebe o iterador na lista de vertices do grafo.
        Vertice v;
        Ponto p;
        while (it.hasNext()) {// Verifica se existe vertices.
            v = it.next(); // Armazena o v�rtice.
            p = (Ponto) v.getObjeto(); // Obt�m o objeto do v�rtice.
            if (p.getX() == x && p.getY() == y) { // Caso os par�metros sejam iguais, foi encontrado o ponto.
                return p; // Retorna o ponto encontrado.
            }
        }
        return null; // Retorna caso o v�rtice n�o exista.
    }

    /**
     * Busca o v�rtice que possua o ponto com as coordenadas especificadas no grafo definido.
     * @param x - Coordenada no eixo X do ponto.
     * @param y - Coordenada no eixo Y do ponto.
     * @param grafo - Grafo no qual o v�rtice ser� procurado.
     * @return null - Caso o v�rtice n�o exista, v - Caso o v�rtice exista.
     */
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

    /**
     * Calcula a dist�ncia entre os pontos recebidos. Cria-se um vetor, e dele � retirado o m�dulo.
     * @param a - Ponto 1.
     * @param b - Ponto 2.
     * @return 0 - Caso algum par�metro seja inv�lido, double - Dist�ncia entre os pontos.
     */
    private double calcularDistancia(Ponto a, Ponto b) {
        if(a == null || b == null){
            return 0;
        }
        return (Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY())));// Calcula o m�dulo.
    }
    
    private double produtoEscalar(Ponto p1, Ponto p2, Ponto p3, Ponto p4){
        double dx1 = p1.getX() - p2.getX();
        double dy1 = p1.getY() - p2.getY();
        double dx2 = p3.getX() - p4.getX();
        double dy2 = p3.getY() - p4.getY();
        return dx1*dx2+dy1*dy2;
    }
    
    private double modulo(Ponto p1, Ponto p2){
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    /**
     * Transforma uma dist�ncia em cent�metros em clicks do encoder do motor do rob�.
     * @param dist - Dist�ncia em cent�metros.
     * @return qtn - Quantidade de clicks do encoder para chegar a dist�ncia.
     */
    private int transCmCl(double dist){ // Transforma cent�metros em clicks da roda do rob�.
        return (int) Math.round(dist/CIRCUNFERENCIARODA);
    }
    
    /**
     * Transforma a quantidade de graus, em rela��o ao rob�, em clicks do encoder do motor. Ou seja, transforma os graus na quantidade
     * necess�ria de clicks que o encoder dever locomover-se para realizar o giro esperado.
     * @param graus - Quantidade de graus que o rob� deve girar.
     * @return int - Quantidade de clicks que o rob� deve girar.
     */
    private int transGrCl(double graus){
        return transCmCl(graus*(CIRCUNFERENCIAROBO/360));
    }

    /**
     * Verifica se existe intersec��o entre os seguimento de reta A(p1,p2) e B(p3, p4).
     * @param p1 - Ponto de origem do segmento de reta A.
     * @param p2 - Ponto final do segmento de reta A.
     * @param p3 - Ponto de origem do segmento de reta B.
     * @param p4 - Ponto final do segmento de reta B.
     * @return false - Caso os segmentos de reta n�o possam uma intersec��o, true - Caso exista intersec��o entre os dois seguimento de reta.
     */
    private boolean temIntersecao(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        Ponto p;
        if (p1 == null || p2 == null || p3 == null || p4 == null) {// Caso algum ponto seja inv�lido.
            return false;
        } else {
            for (Vertice vertice : grafoObstaculos.getVertices()) {
                p = (Ponto) vertice.getObjeto();
                if (Line2D.linesIntersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p.getX(), p.getY(), p.getX(), p.getY())) {
                    return true;
                }
            }
            if (Line2D.linesIntersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY())) {
                if ((p1.getX() == p3.getX() && p1.getY() == p3.getY()) || (p1.getX() == p4.getX() && p1.getY() == p4.getY())) {
                    return false;
                }
                if ((p2.getX() == p3.getX() && p2.getY() == p3.getY()) || (p2.getX() == p4.getX() && p2.getY() == p4.getY())) {
                    return false;
                }
            }
            return Line2D.linesIntersect(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY());
        }
    }

    private void exibirCaminho(Grafo grafo, Vertice inicio, Vertice fim) {
        Dijkstra operador = new Dijkstra(grafo);
        operador.executar(inicio);
        List<List<Vertice>> caminhos = operador.getCaminho(null, fim);
        if (caminhos == null) {
            System.out.println("N�o existe caminhos");
            return;
        }
        Iterator<List<Vertice>> it = caminhos.iterator();
        Iterator<Vertice> it2;
        Ponto p;
        int i = 0;
        if (caminhos.isEmpty()) {
            System.out.println("N�o existe caminhos");
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
                exibirProtocoloCaminho(next);
            }
        }
    }

    private void exibirProtocoloCaminho(List<Vertice> caminho) {
        byte[] protocolo = new byte[128];
        protocolo[0] = 23;
        Iterator<Vertice> itV = caminho.iterator();
        Vertice v1 = null, vertice;
        int i = 0;
        Ponto p1, p2, p3 = null;
        double dist = 0, ang = 0, escalar, modV1, modV2;
        while (itV.hasNext()) {
            vertice = itV.next();
            if (v1 == null) {
                v1 = vertice;
                p1 = (Ponto) v1.getObjeto();
                p3 = new Ponto(p1.getX()+1, p1.getY(), false);
            } else {
                p1 = (Ponto) v1.getObjeto();
                p2 = (Ponto) vertice.getObjeto();
                escalar = Math.abs(produtoEscalar(p1, p2, p1, p3));
                System.out.println(escalar);
                modV1 = modulo(p1, p2);
                modV2 = modulo(p1, p3);
                ang = Math.atan(escalar/(modV1*modV2));
                System.out.println(ang);
                if(escalar > 0){
                    protocolo[i++] = 1;
                } else if(escalar < 0){
                    protocolo[i++] = 0;
                } else {
                    protocolo[i++] = 0;
                    protocolo[i++] = 0;
                }
                v1 = vertice;
                p3 = p1;
            }
        }
    }
}