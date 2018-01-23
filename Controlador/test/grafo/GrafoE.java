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

public class GrafoE {
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
	Vertice l;
	Vertice m;
	Vertice n;
	Vertice o;
	Vertice p;
	Vertice q;
	Vertice r;
	Vertice s;
	Vertice t;

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
		l = grafo.inserir("L");
		m = grafo.inserir("M");
		n = grafo.inserir("N");
		o = grafo.inserir("O");
		p = grafo.inserir("P");
		q = grafo.inserir("Q");
		r = grafo.inserir("R");
		s = grafo.inserir("S");
		t = grafo.inserir("T");
		

		grafo.inserirAresta(a, b, 16);
		grafo.inserirAresta(a, c, 9);
		
		grafo.inserirAresta(b, a, 16);
		grafo.inserirAresta(b, c, 12);
		grafo.inserirAresta(b, f, 11);
		grafo.inserirAresta(b, e, 11);
		
		grafo.inserirAresta(c, a, 9);
		grafo.inserirAresta(c, b, 12);
		grafo.inserirAresta(c, f, 1);
		grafo.inserirAresta(c, d, 4);
		
		grafo.inserirAresta(d, c, 4);
		grafo.inserirAresta(d, g, 1);
		grafo.inserirAresta(d, h, 2);
		grafo.inserirAresta(d, l, 8);
		
		grafo.inserirAresta(e, b, 11);
		grafo.inserirAresta(e, f, 10);
		grafo.inserirAresta(e, m, 15);
		grafo.inserirAresta(e, q, 2);
		grafo.inserirAresta(e, s, 4);
		grafo.inserirAresta(e, r, 8);
		
		grafo.inserirAresta(f, b, 11);
		grafo.inserirAresta(f, c, 1);
		grafo.inserirAresta(f, g, 2);
		grafo.inserirAresta(f, n, 50);
		grafo.inserirAresta(f, m, 5);
		grafo.inserirAresta(f, q, 40);
		grafo.inserirAresta(f, e, 10);
		
		grafo.inserirAresta(g, f, 2);
		grafo.inserirAresta(g, d, 1);
		grafo.inserirAresta(g, h, 3);
		
		grafo.inserirAresta(h, g, 3);
		grafo.inserirAresta(h, d, 2);
		grafo.inserirAresta(h, i, 4);
		grafo.inserirAresta(h, o, 5);
		
		grafo.inserirAresta(i, h, 4);
		grafo.inserirAresta(i, o, 2);
		grafo.inserirAresta(i, p, 3);
		grafo.inserirAresta(i, j, 2);
		
		grafo.inserirAresta(j, i, 2);
		grafo.inserirAresta(j, l, 2);
		
		grafo.inserirAresta(l, d, 8);
		grafo.inserirAresta(l, j, 2);
		
		grafo.inserirAresta(m, q, 10);
		grafo.inserirAresta(m, e, 15);
		grafo.inserirAresta(m, f, 5);
		grafo.inserirAresta(m, n, 21);
		
		grafo.inserirAresta(n, f, 50);
		grafo.inserirAresta(n, m, 21);
		
		grafo.inserirAresta(o, h, 5);
		grafo.inserirAresta(o, i, 2);
		
		grafo.inserirAresta(p, i, 3);
		
		grafo.inserirAresta(q, s, 15);
		grafo.inserirAresta(q, r, 3);
		grafo.inserirAresta(q, e, 2);
		grafo.inserirAresta(q, f, 40);
		grafo.inserirAresta(q, m, 10);
		
		grafo.inserirAresta(r, e, 8);
		grafo.inserirAresta(r, q, 3);
		grafo.inserirAresta(r, s, 5);
		
		grafo.inserirAresta(s, r, 5);
		grafo.inserirAresta(s, e, 4);
		grafo.inserirAresta(s, q, 15);
		

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
	public void Caminho1Test() throws Exception {

		operador.executar(a);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(a);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho2Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, q);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		caminhoEsperado.add(q);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho3Test() throws Exception {

		operador.executar(a);
		List<List<Vertice>> caminhos = operador.getCaminho(null, l);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(a);
		caminhoEsperado.add(c);
		caminhoEsperado.add(d);
		caminhoEsperado.add(l);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(a);
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		caminhoEsperado.add(d);
		caminhoEsperado.add(l);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho4Test() throws Exception {

		operador.executar(n);
		List<List<Vertice>> caminhos = operador.getCaminho(null, e);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(n);
		caminhoEsperado.add(m);
		caminhoEsperado.add(q);
		caminhoEsperado.add(e);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho5Test() throws Exception {

		operador.executar(g);
		List<List<Vertice>> caminhos = operador.getCaminho(null, h);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(g);
		caminhoEsperado.add(d);
		caminhoEsperado.add(h);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho6Test() throws Exception {

		operador.executar(p);
		List<List<Vertice>> caminhos = operador.getCaminho(null, q);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(p);
		caminhoEsperado.add(i);
		caminhoEsperado.add(h);
		caminhoEsperado.add(d);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		caminhoEsperado.add(q);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(p);
		caminhoEsperado.add(i);
		caminhoEsperado.add(h);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(e);
		caminhoEsperado.add(q);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho7Test() throws Exception {

		operador.executar(c);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(c);
		caminhoEsperado.add(f);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho8Test() throws Exception {

		operador.executar(r);
		List<List<Vertice>> caminhos = operador.getCaminho(null, j);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(r);
		caminhoEsperado.add(q);
		caminhoEsperado.add(e);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		caminhoEsperado.add(d);
		caminhoEsperado.add(h);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(r);
		caminhoEsperado.add(q);
		caminhoEsperado.add(e);
		caminhoEsperado.add(f);
		caminhoEsperado.add(g);
		caminhoEsperado.add(h);
		caminhoEsperado.add(i);
		caminhoEsperado.add(j);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho9Test() throws Exception {

		operador.executar(s);
		List<List<Vertice>> caminhos = operador.getCaminho(null, n);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(s);
		caminhoEsperado.add(e);
		caminhoEsperado.add(q);
		caminhoEsperado.add(m);
		caminhoEsperado.add(n);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------*/
	@Test
	public void Caminho10Test() throws Exception {

		operador.executar(j);
		List<List<Vertice>> caminhos = operador.getCaminho(null, b);
		ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
		
		ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(h);
		caminhoEsperado.add(d);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		
		caminhoEsperado = new ArrayList<Vertice>();
		caminhoEsperado.add(j);
		caminhoEsperado.add(i);
		caminhoEsperado.add(h);
		caminhoEsperado.add(g);
		caminhoEsperado.add(f);
		caminhoEsperado.add(b);
		
		caminhosEsperados.add(caminhoEsperado);
		comparar(caminhosEsperados, caminhos);
	}
	/*---------------------------------------------------------------------------------*/
}