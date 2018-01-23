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

	public class GrafoB {
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

			grafo.inserirAresta(a, h, 2);
			
			grafo.inserirAresta(b, h, 7);
			grafo.inserirAresta(b, e, 3);
			grafo.inserirAresta(b, f, 4);

			grafo.inserirAresta(c, h, 3);
			grafo.inserirAresta(c, e, 4);
			grafo.inserirAresta(c, g, 4);
			
			grafo.inserirAresta(d, g, 3);

			grafo.inserirAresta(e, c, 4);
			grafo.inserirAresta(e, b, 3);
			grafo.inserirAresta(e, f, 6);
			grafo.inserirAresta(e, g, 5);

			grafo.inserirAresta(f, b, 4);
			grafo.inserirAresta(f, e, 6);
			grafo.inserirAresta(f, g, 4);

			grafo.inserirAresta(g, c, 4);
			grafo.inserirAresta(g, e, 5);
			grafo.inserirAresta(g, f, 4);
			grafo.inserirAresta(g, d, 3);

			grafo.inserirAresta(h, a, 2);
			grafo.inserirAresta(h, b, 8);
			grafo.inserirAresta(h, c, 3);

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
			List<List<Vertice>> caminhos = operador.getCaminho(null, f);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(a);
			caminhoEsperado.add(h);
			caminhoEsperado.add(c);
			caminhoEsperado.add(g);
			caminhoEsperado.add(f);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho2Test() throws Exception {

			operador.executar(e);
			List<List<Vertice>> caminhos = operador.getCaminho(null, h);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(e);
			caminhoEsperado.add(c);
			caminhoEsperado.add(h);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho3Test() throws Exception {

			operador.executar(d);
			List<List<Vertice>> caminhos = operador.getCaminho(null, b);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(d);
			caminhoEsperado.add(g);
			caminhoEsperado.add(f);
			caminhoEsperado.add(b);
			
			caminhosEsperados.add(caminhoEsperado);
			
			caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(d);
			caminhoEsperado.add(g);
			caminhoEsperado.add(e);
			caminhoEsperado.add(b);
			
			caminhosEsperados.add(caminhoEsperado);
			
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		
}