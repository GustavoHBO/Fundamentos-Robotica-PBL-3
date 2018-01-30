/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Circulo;

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
    
    /* Lista de Circulos */
    private ArrayList<Circulo> listaCirculos;
    
    /* Demais variáveis de controle */
    
    public static int s = 0; // Determina o modo de interação dos circulos, 0 para inicio, 1 para fim, 2 para obstáculo e 3 para nada.
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaCirculos = new ArrayList<>();
        
        posicionarCirculos();
    }    
    
    private void posicionarCirculos(){
        Circulo c;
        int i = 0;
        int j = 0;
        
        int espacoX = 0;
        int espacoY = 10;
        
        for (i = 0; i < 10; i++) {
            espacoX = 10;
            for (j = 0; j < 10; j++) {
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
    
    @FXML
    private void eventButtonNada(){
        s = 3;
    }
    
    @FXML
    private void eventButtonObstaculo(){
        s = 2;
    }
    
}
