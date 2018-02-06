/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Circulo;
import util.Grafo;
import util.Vertice;

/**
 *
 * @author gautstafr
 */
public class FXMLDocumentController implements Initializable {
    
    /* Declaração dos Panes */
   
    @FXML
    private Pane paneControle = null;
    @FXML
    private Pane paneMapa = null;

    /* Declaração dos Botões */
    @FXML
    private Button botaoNada = null;
    @FXML
    private Button botaoObstaculo = null;
    @FXML
    private Button botaoCalcular = null;
    
    /* Lista de Circulos */
    private ArrayList<Circulo> listaCirculos;
    
    /* Demais variáveis de controle */
    
    /* Grafo de visibilidade */
    
    private Grafo grafo;
    
    /* Váriáveis finais, para controle do mapa */
    
    private final int QTNVV = 10; // Quantidade de vértices na vertical.
    private final int QTNVH = 10; // Quantidade de vértices na horizontal.
    private final int PESO = 1; // Peso entre os vértices.
    
    public static int s = 0; // Determina o modo de interação dos circulos, 0 para inicio, 1 para fim, 2 para obstáculo e 3 para nada.
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaCirculos = new ArrayList<>();
        grafo = new Grafo();
        posicionarCirculos();
    }    
    
    /* Métodos de eventos */
    
    @FXML
    private void eventButtonNada(){
        s = 3;
    }
    
    @FXML
    private void eventButtonObstaculo(){
        s = 2;
    }
    
    @FXML
    private void eventCalcularCaminho(){
        Vertice v = null;
        Vertice vInicio = null;
        Vertice vFim = null;
        Circulo c = null;
        Circulo cInicio = null;
        Circulo cFim = null;
        Iterator<Circulo> it = listaCirculos.iterator();
        Iterator<Vertice> itV = grafo.getVertices().iterator();
        
        while(it.hasNext()){
            c = it.next();
            if(c.getNivel() == 1){
                cInicio = c;
            } else if (c.getNivel() == 2){
                cFim = c;
            }
            if(cInicio != null && cFim != null){
                break;
            }
        }
        
        while(itV.hasNext()){
            v = itV.next();
            c = (Circulo) v.getObjeto();
            if(c.getX() == cInicio.getX() && c.getY() == cInicio.getY()){
                vInicio = v;
            } else if (c.getX() == cFim.getX() && c.getY() == cFim.getY()){
                vFim = v;
            }
            if(vInicio != null && vFim != null){
                break;
            }
        }
        
        retirarVertices();
        // Prints de controle. Para exibir os dados do grafo.
        System.out.println("Quantidade de arestas: " + grafo.getArestas().size());
        System.out.println("Quantidade de Vertices: " + grafo.getVertices().size());
    }
    
    /* Métodos de Controle */
    
    /**
     * Retira os vértices que são parte de um obstáculo.
     */
    private void retirarVertices(){
        Vertice v;
        Circulo c1;
        Circulo c2;
        Iterator<Vertice> itV = grafo.getVertices().iterator();
        Iterator<Circulo> itC = listaCirculos.iterator();
        
//        while(itC.hasNext()){
//            c1 = itC.next();
//            if(c1.getNivel() == 3){
                while(itV.hasNext()){
                    v = itV.next();
                    c2 = v.getObjeto();
//                    if(c1.getX() == c1.getX() && c1.getY() == c2.getY()){
//                        grafo.removerVertice(v);
//                    }
System.out.println(c2.getNivel());
                    if(c2.getNivel() == 3){
                        grafo.removerVertice(c2);
                    }
                }
//            }
//        }
    }
    
    /**
     * Posiciona os circulos nas posições iniciais.
     */
    private void posicionarCirculos(){
        Circulo c;
        int i = 0;
        int j = 0;
        
        int espacoX = 0;
        int espacoY = QTNVV;
        
        for (i = 0; i < QTNVV; i++) {
            espacoX = QTNVH;
            for (j = 0; j < QTNVH; j++) {
                c = new Circulo();
                listaCirculos.add(c);
                grafo.inserir(c);
                c.setPosX(j);
                c.setPosY(i);
                c.setLayoutX(espacoX);
                c.setLayoutY(espacoY);
                paneMapa.getChildren().addAll(c);
                espacoX += 40;
            }
            espacoY += 40;
        }
        criarCaminhos();
    }
    
    /**
     * Cria os caminhos entre os vértices vizinhos. Este método deve ser chamado após o grafo ter sido preenchido.
     */
    private void criarCaminhos() {
        int i = 0;
        int j = 0;
        
        List<Vertice> fila = new LinkedList();
        Iterator<Vertice> it = grafo.getVertices().iterator();
        Vertice v1 = null;
        Vertice v2 = null;

        for (i = 0; i < QTNVV; i++) {
            for (j = 0; j < QTNVH; j++) {
                if(v1 == null){
                    v1 = it.next();
                    fila.add(v1);
                    if(i != 0){
                        grafo.inserirArestaNaoOrientada(v1, fila.remove(0), PESO);
                    }
                } else {
                    v2 = it.next();
                    fila.add(v2);
                    grafo.inserirArestaNaoOrientada(v1, v2, PESO);
                    if (i != 0) {
                        grafo.inserirArestaNaoOrientada(v1, fila.remove(0), PESO);
                    }
                    v1 = v2;
                }
            }
            v1 = null;
        }
        // Prints de controle. Para exibir os dados do grafo.
        System.out.println("Quantidade de arestas: " + grafo.getArestas().size());
        System.out.println("Quantidade de Vertices: " + grafo.getVertices().size());
        System.out.println("Quantidade de itens na fila: " + fila.size());
    }
}
