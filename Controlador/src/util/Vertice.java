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
 * Classe V�rtice do Grafo.
 * @author Gustavo Henrique.
 * @since 16 abr. 2016.
 */

public class Vertice {

	private int grau;
	private boolean foiVisitado = false;
	private Object objeto;

	/*___________________________________________________________________________________________*/
	/**
	 * Verifica se o v�rtice foi visitado.
	 * @return true - Caso tenha sido visitado | false - Caso n�o tenha sido visitado.
	 */
	public boolean foiVisitado() {
		return foiVisitado;
	}

	/*___________________________________________________________________________________________*/
	/**
	 * Seta se o v�rtice foi visitado.
	 * @param foiVisitado.
	 */
	public void setFoiVisitado(boolean foiVisitado) {
		this.foiVisitado = foiVisitado;
	}
	
	/*___________________________________________________________________________________________*/
	/**
	 * Construtor da classe.	
	 * @param objeto - Objeto do v�rtice.
	 */
	public Vertice(Object objeto){
		this.setObjeto(objeto);
		grau = 0;
		//listaArestas = new ArrayList<Aresta>();
	}

	/*___________________________________________________________________________________________*/
	/**
	 * Retorna o grau do v�rtice.
	 * @return int - Grau do v�rtice.
	 */
	public int getGrau() {
		return grau;
	}
	/*___________________________________________________________________________________________*/
	/**
	 * Altera o grau do v�rtice.
	 * @param grau - Novo grau do v�rtice.
	 */
	public void setGrau(int grau) {
		this.grau = grau;
	}
	
	/*___________________________________________________________________________________________*/

	/**
	 * Retorna o objeto do v�rtice.
	 * @return Object - Objeto do v�rtice.
	 */
	public Object getObjeto() {
		return objeto;
	}
	/*___________________________________________________________________________________________*/
	/**
	 * Altera o objeto do v�rtice.
	 * @param objeto - Novo objeto do v�rtice.
	 */
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	/*___________________________________________________________________________________________*/
}
