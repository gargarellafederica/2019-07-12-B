package it.polito.tdp.food.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private Graph<Food, DefaultWeightedEdge> grafo;
	private FoodDao dao;
	private Map<Integer, Food> mappacibi;
	private List<Food> listacibi;
	private List<Adiacenti> adiacenti;
	
	public Model() {
	dao= new FoodDao();	
	}

	public void creagrafo(int porz) {
		
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);	
		mappacibi= new HashMap<>();
		listacibi=this.dao.cibidataporzione(porz, mappacibi);
		Graphs.addAllVertices(this.grafo, mappacibi.values());
		adiacenti=this.dao.listAdiacenti();
		for (Adiacenti a: adiacenti) {
			Food cibo1 =mappacibi.get(a.getCibo1());
			Food cibo2 =mappacibi.get(a.getCibo2());
			if(cibo1 !=null && cibo2 !=null)
				if(!this.grafo.containsEdge(cibo1, cibo2))
				Graphs.addEdge(this.grafo,mappacibi.get(a.getCibo1()),mappacibi.get(a.getCibo2()), a.getMedia());
		}
		
		
	}

	public int getnvertici() {
		return this.grafo.vertexSet().size();
	}

	public int getnarchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Food> getlistavertici() {
		return this.listacibi;
	}

	//ritorna una lista vuota
	public List<Food> getgrassiminori(Food valore) {
		List<Adiacenti> successivi=new LinkedList<>();
		for( Food f:Graphs.successorListOf(this.grafo, valore))
			for(Adiacenti a: adiacenti)
				if(valore.getFood_code()==a.getCibo1() && f.getFood_code()==a.getCibo2())
					successivi.add(a);
		Collections.sort(successivi);
		List<Food> listafinale= new LinkedList<>();
		for(int i=0; i<5; i++)
			for(Adiacenti a: successivi) {
				listafinale.add(new Food((int) a.getMedia(), mappacibi.get(a.getCibo2()).getDisplay_name()));
				i++;
			}
		return listafinale;
	}
}
