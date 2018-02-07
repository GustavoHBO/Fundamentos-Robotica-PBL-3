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

/**
 *
 * @author gautstafr
 */
public class Ponto {
    
    private int x = 0;
    private int y = 0;
    
    private boolean obstaculo = false; // true - caso seja obstáculo, false - caso não seja.
    
    public Ponto(){}
    public Ponto(int x, int y, boolean obstaculo){
        this.x = x;
        this.y = y;
        this.obstaculo = obstaculo;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the obstaculo
     */
    public boolean isObstaculo() {
        return obstaculo;
    }

    /**
     * @param obstaculo the obstaculo to set
     */
    public void setObstaculo(boolean obstaculo) {
        this.obstaculo = obstaculo;
    }
}
