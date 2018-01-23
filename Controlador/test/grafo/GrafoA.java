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

	public class GrafoA {
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

			grafo.inserirAresta(a, e, 4);
			grafo.inserirAresta(a, h, 5);

			grafo.inserirAresta(b, d, 7);
			grafo.inserirAresta(b, f, 6);
			grafo.inserirAresta(b, e, 3);

			grafo.inserirAresta(c, g, 7);
			
			grafo.inserirAresta(d, h, 7);
			grafo.inserirAresta(d, b, 7);
			grafo.inserirAresta(d, f, 6);

			grafo.inserirAresta(e, a, 4);
			grafo.inserirAresta(e, b, 3);
			grafo.inserirAresta(e, g, 3);

			grafo.inserirAresta(f, b, 6);
			grafo.inserirAresta(f, d, 6);

			grafo.inserirAresta(g, e, 3);
			grafo.inserirAresta(g, c, 7);

			grafo.inserirAresta(h, a, 5);
			grafo.inserirAresta(h, d, 7);

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
			caminhoEsperado.add(e);
			caminhoEsperado.add(b);
			caminhoEsperado.add(f);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho2Test() throws Exception {

			operador.executar(f);
			List<List<Vertice>> caminhos = operador.getCaminho(null, g);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(f);
			caminhoEsperado.add(b);
			caminhoEsperado.add(e);
			caminhoEsperado.add(g);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/
		/*---------------------------------------------------------------------------------*/
		@Test
		public void Caminho3Test() throws Exception {

			operador.executar(c);
			List<List<Vertice>> caminhos = operador.getCaminho(null, h);
			ArrayList<ArrayList<Vertice>> caminhosEsperados = new ArrayList<ArrayList<Vertice>>();
			
			ArrayList<Vertice> caminhoEsperado = new ArrayList<Vertice>();
			caminhoEsperado.add(c);
			caminhoEsperado.add(g);
			caminhoEsperado.add(e);
			caminhoEsperado.add(a);
			caminhoEsperado.add(h);
			
			caminhosEsperados.add(caminhoEsperado);
			comparar(caminhosEsperados, caminhos);
		}
		/*---------------------------------------------------------------------------------*/

}
