package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
  
	private PremierLeagueDAO dao;
	private Graph<Player, DefaultWeightedEdge> graph;
	private Map<Integer, Player> idMap;
    private List<Player> allPlayer;
    private TopPlayer player;
    private List<Player> dreamTeam;
    private double bestDegree;
    
    public Model() {
    	this.dao = new PremierLeagueDAO();
        this.idMap = new HashMap<Integer, Player>();
        this.allPlayer = this.dao.listAllPlayers(idMap);
        
    }
    
    
    public void creaGrafo(double goal) {
    	
    	this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
    	

    	Graphs.addAllVertices(this.graph, this.dao.getVertici(idMap, goal));
    	
    	
    	for(Adiacenze a : this.dao.getArchi(idMap)) {
    		if(this.graph.containsVertex(a.getP1()) && this.graph.containsVertex(a.getP2())) {
    			if(a.getPeso() > 0) {
    				Graphs.addEdgeWithVertices(this.graph, a.getP1(), a.getP2(), a.getPeso());
    			}
    			
    			if(a.getPeso() < 0) {
    				Graphs.addEdgeWithVertices(this.graph, a.getP2(), a.getP1(), ((double)-1)*a.getPeso());
    			}
    		}
       	
    	}
    		
    	
    	System.out.println("Grafo creato con "+this.graph.vertexSet().size()+" vertici e "+this.graph.edgeSet().size()+" archi");
    }
    
    public Player topPlayer() {
    	Integer maxBattuti = Integer.MIN_VALUE;
    	Player topPlayer = null;
    	for(Player p : this.graph.vertexSet()) {
    		if(graph.outDegreeOf(p) > maxBattuti) {
    			topPlayer = p;
    			maxBattuti = graph.outDegreeOf(p);
    		}
    	}
    	
    	this.player = new TopPlayer(topPlayer);
    	return topPlayer;
    }
    
    public List<Avversari> getListBattuti(Player topPlayer){
    	List<Avversari> battuti = new ArrayList<>();
        for(DefaultWeightedEdge e : graph.outgoingEdgesOf(topPlayer)) {
        
        	battuti.add(new Avversari(graph.getEdgeTarget(e), graph.getEdgeWeight(e)));
        }
        Collections.sort(battuti);
        this.player.setBattuti(battuti);
        return battuti;
    	
    }
    
    public List<Player> getDreamTeam(int k){
    	this.dreamTeam = new ArrayList<>();
    	this.bestDegree = 0.0;
    	List<Player> vertici = new ArrayList<Player>();
    	
    	for(Player p : graph.vertexSet()) {
    		vertici.add(p);
    	}
    	
    	recursive(new ArrayList<Player>(), vertici, k);
    	
    	return this.dreamTeam;
    }
    
    
    
    private void recursive(List<Player> parziale, List<Player> vertici,  int k) {
	     
    	if(parziale.size() == k) {
    		if(getDegree(parziale) > bestDegree) {
    			bestDegree = getDegree(parziale);
    			this.dreamTeam = new ArrayList<>(parziale);
    		}
    	}
    	
    	for(Player p : graph.vertexSet()) {
    		if(!parziale.contains(p)) {
    			parziale.add(p);
    			List<Player> battuti = new ArrayList<Player>(vertici);
    			battuti.removeAll(Graphs.successorListOf(this.graph, p));
    			recursive(parziale, battuti, k);
    			parziale.remove(p);
    		
    		}
    	}
		
	}


	private double getDegree(List<Player> parziale) {
		double degree = 0.0;
		double out = 0.0;
		double in = 0.0;
		for(Player p : parziale) {
			for(DefaultWeightedEdge e : graph.outgoingEdgesOf(p)) {
				out += graph.getEdgeWeight(e);
			}
			
			for(DefaultWeightedEdge e : graph.incomingEdgesOf(p)) {
				in += graph.getEdgeWeight(e);
			}
			
			degree += out-in;
		}
		
		return degree;	
	}
	
	public double getBestDegree() {
		return this.bestDegree;
	}


	public Graph<Player, DefaultWeightedEdge> getGraph(){
    	return this.graph;
    }
    
    public int getVerticiSize() {
    	return this.graph.vertexSet().size();
    }
    
    public int getArchiSize() {
    	return this.graph.edgeSet().size();
    }
    
}
