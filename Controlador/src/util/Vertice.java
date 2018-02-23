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
 * Classe Vértice do Grafo.
 * @author Gustavo Henrique.
 * @since 16 abr. 2016.
 */

public class Vertice {

	private int grau;
	private boolean foiVisitado = false;
	private Object objeto;

	/*___________________________________________________________________________________________*/
	/**
	 * Verifica se o vértice foi visitado.
	 * @return true - Caso tenha sido visitado | false - Caso não tenha sido visitado.
	 */
	public boolean foiVisitado() {
		return foiVisitado;
	}

	/*___________________________________________________________________________________________*/
	/**
	 * Seta se o vértice foi visitado.
	 * @param foiVisitado.
	 */
	public void setFoiVisitado(boolean foiVisitado) {
		this.foiVisitado = foiVisitado;
	}
	
	/*___________________________________________________________________________________________*/
	/**
	 * Construtor da classe.	
	 * @param objeto - Objeto do vértice.
	 */
	public Vertice(Object objeto){
		this.setObjeto(objeto);
		grau = 0;
		//listaArestas = new ArrayList<Aresta>();
	}

	/*___________________________________________________________________________________________*/
	/**
	 * Retorna o grau do vértice.
	 * @return int - Grau do vértice.
	 */
	public int getGrau() {
		return grau;
	}
	/*___________________________________________________________________________________________*/
	/**
	 * Altera o grau do vértice.
	 * @param grau - Novo grau do vértice.
	 */
	public void setGrau(int grau) {
		this.grau = grau;
	}
	
	/*___________________________________________________________________________________________*/

	/**
	 * Retorna o objeto do vértice.
	 * @return Object - Objeto do vértice.
	 */
	public Object getObjeto() {
		return objeto;
	}
	/*___________________________________________________________________________________________*/
	/**
	 * Altera o objeto do vértice.
	 * @param objeto - Novo objeto do vértice.
	 */
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	/*___________________________________________________________________________________________*/
}
