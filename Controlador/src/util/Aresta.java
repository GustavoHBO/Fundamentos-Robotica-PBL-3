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

/**
 * Classe aresta.
 * @author Gustavo Henrique.
 * @author Leonardo Melo.
 */
public class Aresta {

	private Vertice vertice1 = null;
	private Vertice vertice2 = null;
	private int peso = 0;
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Construtor padrão da classe.
	 * @param vertice1 - Vertice1 da aresta.
	 * @param vertice2 - Vertice2 da aresta.
	 */
	public Aresta(Vertice vertice1, Vertice vertice2){
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
	}
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Retorna o vértice de destino da aresta.
	 * @return Vertice - Vértice de destino
	 */
	public Vertice getVertice2() {
		return vertice2;
	}
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Altera o vértice de destino da aresta.
	 * @param Vertice - Novo vértice de destino da aresta.
	 */
	public void setVertice2(Vertice vertice2) {
		this.vertice2 = vertice2;
	}

	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Retorna o vértice de origem da aresta.
	 * @return Vertice - Vértice de origem
	 */
	public Vertice getVertice1() {
		return vertice1;
	}
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Altera o vértice de origem da aresta.
	 * @param Vertice - Novo vértice de origem da aresta.
	 */
	public void setVertice1(Vertice vertice1) {
		this.vertice1 = vertice1;
	}
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Retorna o valor peso da aresta.
	 * @return int - Peso da aresta.
	 */
	public int getPeso() {
		return peso;
	}
	
	/*______________________________________________________________________________________________________________________________*/
	/**
	 * Altera o valor peso da aresta.
	 * @param int - Novo valor peso da aresta.
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	/*______________________________________________________________________________________________________________________________*/
}
