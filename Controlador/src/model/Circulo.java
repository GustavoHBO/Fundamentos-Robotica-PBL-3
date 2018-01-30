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
package model;

import controlador.FXMLDocumentController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Classe para desenho do mapa.
 * @author Gustavo Henrique.
 */
public class Circulo extends ImageView{
    
    /* Declaração da posição da imagem */
    private int posX = 0;
    private int posY = 0;
    
    /* url das imagens */
    private final String imgPreta = "/img/circuloPreto.jpg";
    private final String imgCinza = "/img/circuloCinza.jpg";
    private final String imgVerde = "/img/circuloVerde.jpg";
    private final String imgAmarelo = "/img/circuloAmarelo.jpg";
    private final String imgVermelho = "/img/circuloVermelho.jpg";
    
    /* Url Atual */
    private String url = imgPreta;
    
    /**
     * Construtor padrão, define a imagem inicial e o tamanho padrão.(25x20)
     */
    public Circulo() {
        super();
        this.setImage(new Image(url));
        this.setFitWidth(25);
        this.setFitHeight(20);
        this.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(url.equals(imgPreta))
                   setImgCinza();
                if (!url.equals(imgVermelho)) {
                    setFitWidth(30);
                    setFitHeight(25);
                }
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (!url.equals(imgVermelho)) {
                    setImg(url);
                    setFitWidth(25);
                    setFitHeight(20);
                    setLayoutX(10 + getPosX() * 40);
                    setLayoutY(10 + getPosY() * 40);
                }
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (FXMLDocumentController.s == 0) {
                    setImgVerde();
                    FXMLDocumentController.s++;
                } else if (FXMLDocumentController.s == 1) {
                    if (!url.equals(imgVerde)) {
                        setImgAmarelo();
                        FXMLDocumentController.s++;
                    }
                } else if (FXMLDocumentController.s == 2) {
                    if (!url.equals(imgVerde) && !url.equals(imgAmarelo)) {
                        setImgVermelho();
                        setLayoutX(getPosX() * 40);
                        setLayoutY(getPosY() * 40);
                        setFitWidth(40);
                        setFitHeight(40);
                    }
                } else if (FXMLDocumentController.s == 3) {
                    if (!url.equals(imgVerde) && !url.equals(imgAmarelo)) {
                        setImgPreta();
                    }
                }
            }
        });
    }
    
    /**
     * Altera a imagem do circulo para a definida atualmente.
     */
    private void setImg(String url){
        this.setImage(new Image(url));
    }
    
    public void setImgPreta(){
        url = imgPreta;
        setImg(url);
    }
    
    public void setImgCinza(){
        setImg(imgCinza);
    }
    
    public void setImgVerde(){
        url = imgVerde;
        setImg(url);
    }
    
    public void setImgAmarelo(){
        url = imgAmarelo;
        setImg(url);
    }
    
    public void setImgVermelho(){
        url = imgVermelho;
        setImg(url);
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
