/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controller.Controller;
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
    
    /* Declara��o dos Panes */
   
    @FXML
    private Pane paneControle = null;
    @FXML
    private Pane paneMapa = null;

    /* Declara��o dos Bot�es */
    @FXML
    private Button botaoNada = null;
    @FXML
    private Button botaoObstaculo = null;
    @FXML
    private Button botaoCalcular = null;
    
    /* Lista de Circulos */
    private ArrayList<Circulo> listaCirculos;
    
    /* Demais vari�veis de controle */
    
    /* Grafo de visibilidade */
    
    private Grafo grafo;
    
    /* V�ri�veis finais, para controle do mapa */
    
    private final int QTNVV = 10; // Quantidade de v�rtices na vertical.
    private final int QTNVH = 10; // Quantidade de v�rtices na horizontal.
    private final int PESO = 1; // Peso entre os v�rtices.
    
    public static int s = 0; // Determina o modo de intera��o dos circulos, 0 para inicio, 1 para fim, 2 para obst�culo e 3 para nada.
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaCirculos = new ArrayList<>();
        grafo = new Grafo();
        posicionarCirculos();
    }    
    
    /* M�todos de eventos */
    
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
        Controller.getInstance().expandirObstaculos();
    }
    
    /* M�todos de Controle */
    
    
    /**
     * Posiciona os circulos nas posi��es iniciais.
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
                c.setPosX(j);
                c.setPosY(i);
                c.setLayoutX(espacoX);
                c.setLayoutY(espacoY);
                paneMapa.getChildren().addAll(c);
                espacoX += 40;
            }
            espacoY += 40;
        }
    }
}
