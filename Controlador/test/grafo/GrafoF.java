package grafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import util.Dijkstra;
import util.Grafo;
import util.Vertice;

public class GrafoF {
	Boolean flag;
	Grafo grafo;
	Dijkstra operador;

	Vertice a;
	Vertice b;
	Vertice c;
	Vertice d;
	Vertice e;
	Vertice f;
	Vertice g;
	Vertice h;
	Vertice i;
	Vertice j;
	Vertice k;
	Vertice l;

	@Before
	public void setUp() throws Exception {
		grafo = new Grafo();
		a = grafo.inserir("A");
		b = grafo.inserir("B");
		c = grafo.inserir("C");
		d = grafo.inserir("D");
		e = grafo.inserir("E");
		f = grafo.inserir("F");
		g = grafo.inserir("G");
		h = grafo.inserir("H");
		i = grafo.inserir("I");
		j = grafo.inserir("J");
		k = grafo.inserir("K");
		l = grafo.inserir("L");

		grafo.inserirAresta(a, b, 7);
		grafo.inserirAresta(a, h, 10);

		grafo.inserirAresta(b, a, 7);
		grafo.inserirAresta(b, h, 15);
		grafo.inserirAresta(b, j, 11);
		grafo.inserirAresta(b, d, 25);
		grafo.inserirAresta(b, c, 5);

		grafo.inserirAresta(c, b, 5);
		grafo.inserirAresta(c, d, 30);

		grafo.inserirAresta(d, b, 25);
		grafo.inserirAresta(d, c, 30);
		grafo.inserirAresta(d, k, 17);
		grafo.inserirAresta(d, f, 27);
		grafo.inserirAresta(d, e, 40);

		grafo.inserirAresta(e, d, 40);
		grafo.inserirAresta(e, f, 5);

		grafo.inserirAresta(f, e, 5);
		grafo.inserirAresta(f, d, 27);
		grafo.inserirAresta(f, l, 12);
		grafo.inserirAresta(f, h, 40);
		grafo.inserirAresta(f, g, 20);

		grafo.inserirAresta(g, f, 20);
		grafo.inserirAresta(g, h, 18);

		grafo.inserirAresta(h, a, 10);
		grafo.inserirAresta(h, b, 15);
		grafo.inserirAresta(h, i, 15);
		grafo.inserirAresta(h, f, 40);
		grafo.inserirAresta(h, g, 18);

		grafo.inserirAresta(i, h, 15);
		grafo.inserirAresta(i, j, 5);
		grafo.inserirAresta(i, l, 9);

		grafo.inserirAresta(j, i, 5);
		grafo.inserirAresta(j, b, 11);
		grafo.inserirAresta(j, k, 19);

		grafo.inserirAresta(k, j, 19);
		grafo.inserirAresta(k, d, 17);
		grafo.inserirAresta(k, l, 22);

		grafo.inserirAresta(l, i, 9);
		grafo.inserirAresta(l, k, 22);
		grafo.inserirAresta(l, f, 12);

		operador = new Dijkstra(grafo);

	}
	
	private void comparar(ArrayList<ArrayList<Vertice>> caminhosEsperados, List<List<Vertice>> caminhos) {
		assertEquals(caminhosEsperados.size(), caminhos.size());
		for(List<Vertice> caminho : caminhos){
			assertNotNull(caminho);
			assertTrue(caminho.size() > 0);
			assertEquals(caminhosEsperados.size(), caminhos.size());
			
			flag = false;
			for(List<Vertice> camEsperado : caminhosEsperados){
				if(!flag && camEsperado.equals(caminho))flag = true;
			}
			assertTrue(flag);
			
		}
		
	}
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho1_1Test() throws Exception {

		operador.executar(a);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(a);
		caminhoEsperado.add(b);
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(l);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/

	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho1_2Test() throws Exception {

		operador.executar(e);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(e);
		caminhoEsperado.add(f);
		caminhoEsperado.add(l);
		caminhoEsperado.add(i);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho2_1Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(b);
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(l);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho2_2Test() throws Exception {

		operador.executar(e);
		List<List<Vertice>> caminhos = operador.getCaminho(null, j);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(e);
		caminhoEsperado.add(f);
		caminhoEsperado.add(l);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho3_1Test() throws Exception {

		operador.executar(d);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(d);
		caminhoEsperado.add(b);
		caminhoEsperado.add(h);
				
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho3_2Test() throws Exception {

		operador.executar(h);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(h);
		caminhoEsperado.add(i);
		caminhoEsperado.add(l);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho4_1Test() throws Exception {

		operador.executar(f);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(f);
		caminhoEsperado.add(l);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho4_2Test() throws Exception {

		operador.executar(b);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(b);
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(l);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho5_1Test() throws Exception {

		operador.executar(g);
		List<List<Vertice>> caminhos = operador.getCaminho(null, k);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(l);
		caminhoEsperado.add(k);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho5_2Test() throws Exception {

		operador.executar(k);
		List<List<Vertice>> caminhos = operador.getCaminho(null, a);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(k);
		caminhoEsperado.add(j);
		caminhoEsperado.add(b);
		caminhoEsperado.add(a);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/

}





