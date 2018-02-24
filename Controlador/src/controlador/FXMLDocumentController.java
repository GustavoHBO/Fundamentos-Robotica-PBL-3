/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controller.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.Circulo;
import util.Grafo;

/**
 *  Classe controller, possue os métodos necessários para o bom funcionamento da interface gráfica.
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
    
    /* Grafo de pontos */
    private Grafo grafo;
    
    /* Váriáveis finais, para controle do mapa */
    
    private final int QTNVV = 20; // Quantidade de vértices na vertical.
    private final int QTNVH = 20; // Quantidade de vértices na horizontal.
    private final int ESPACOBORDA = 10; // Espaço entre a borda de os circulos..
    private final int PESO = 1; // Peso entre os vértices.
    
    public static int s = 0; // Determina o modo de interação dos circulos, 0 para inicio, 1 para fim, 2 para obstáculo e 3 para nada.
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaCirculos = new ArrayList<>();
        grafo = new Grafo();
        posicionarCirculos();
    }    
    
    /* Métodos de eventos */
    
    /**
     * Evento para ativar a possibilidade de retirar um obstáculo.
     */
    @FXML
    private void eventButtonNada(){
        s = 3;
    }
    
    /**
     * Evento para ativar a possibilidade de tornar um ponto um obstáculo.
     */
    @FXML
    private void eventButtonObstaculo(){
        s = 2;
    }
    
    /**
     * Evento no qual calculo o melhor caminho entre o ponto de início e de fim.
     */
    @FXML
    private void eventCalcularCaminho() {
        if (!Controller.getInstance().calcularCaminho()) {
            System.out.println("Não Existe Caminhos");
        }
    }
    
    /* Métodos de Controle */
    
    
    /**
     * Posiciona os circulos nas posições iniciais.
     */
    private void posicionarCirculos(){
        Circulo c;
        int i = 0;
        int j = 0;
        
        int espacoX = 0;
        int espacoY = ESPACOBORDA;
        
        /* Posiciona os circulos espaçadamente a mesma distância, além de um pequeno espaço entre a borda */
        for (i = 0; i < QTNVV; i++) {
            espacoX = ESPACOBORDA;
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
