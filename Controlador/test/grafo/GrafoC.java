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

	public class GrafoC {
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
		Vertice m;
		Vertice n;
		Vertice o;
		Vertice p;
		Vertice q;
		Vertice r;

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
			m = grafo.inserir("M");
			n = grafo.inserir("N");
			o = grafo.inserir("O");
			p = grafo.inserir("P");
			q = grafo.inserir("Q");
			r = grafo.inserir("R");

			grafo.inserirArestaNaoOrientada(a, b, 1);
			grafo.inserirArestaNaoOrientada(b, c, 1);
			grafo.inserirArestaNaoOrientada(a, e, 1);
			grafo.inserirArestaNaoOrientada(b, f, 1);
			grafo.inserirArestaNaoOrientada(b, e, 1);
			grafo.inserirArestaNaoOrientada(c, f, 1);
			grafo.inserirArestaNaoOrientada(c, g, 1);
			grafo.inserirArestaNaoOrientada(g, d, 1);
			grafo.inserirArestaNaoOrientada(d, k, 1);
			grafo.inserirArestaNaoOrientada(e, i, 1);
			grafo.inserirArestaNaoOrientada(i, f, 1);
			grafo.inserirArestaNaoOrientada(j, g, 1);
			grafo.inserirArestaNaoOrientada(i, j, 1);
			grafo.inserirArestaNaoOrientada(j, k, 1);
			grafo.inserirArestaNaoOrientada(h, o, 1);
			grafo.inserirArestaNaoOrientada(h, l, 1);
			grafo.inserirArestaNaoOrientada(i, l, 1);
			grafo.inserirArestaNaoOrientada(j, m, 1);
			grafo.inserirArestaNaoOrientada(k, n, 1);
			grafo.inserirArestaNaoOrientada(m, n, 1);
			grafo.inserirArestaNaoOrientada(l, o, 1);
			grafo.inserirArestaNaoOrientada(l, p, 1);
			grafo.inserirArestaNaoOrientada(n, q, 1);
			grafo.inserirArestaNaoOrientada(q, r, 1);

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
			List<List<Vertice>> caminhos = operador.getCaminho(null, n);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(a);
			caminhoEsperado.add(e);
			caminhoEsperado.add(i);
			caminhoEsperado.add(j);
			caminhoEsperado.add(k);
			caminhoEsperado.add(n);
			
			caminhosEsperados.add(caminhoEsperado);
			
			caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(a);
			caminhoEsperado.add(e);
			caminhoEsperado.add(i);
			caminhoEsperado.add(j);
			caminhoEsperado.add(m);
			caminhoEsperado.add(n);
			
			caminhosEsperados.add(caminhoEsperado);
			
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho2Test() throws Exception {

			operador.executar(a);
			List<List<Vertice>> caminhos = operador.getCaminho(null, h);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(a);
			caminhoEsperado.add(e);
			caminhoEsperado.add(i);
			caminhoEsperado.add(l);
			caminhoEsperado.add(h);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho3Test() throws Exception {

			operador.executar(a);
			List<List<Vertice>> caminhos = operador.getCaminho(null, d);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(a);
			caminhoEsperado.add(b);
			caminhoEsperado.add(c);
			caminhoEsperado.add(g);
			caminhoEsperado.add(d);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		
}