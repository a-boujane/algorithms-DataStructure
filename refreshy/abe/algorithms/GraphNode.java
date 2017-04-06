package refreshy.abe.algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GraphNode <T> {
	
	T data=null;
	boolean isVisited=false;
	boolean isCurrent=false;
	Map<T,GraphNode<T>> neighbors = new HashMap<T,GraphNode<T>>();
	Map<T,Integer> distance = new HashMap<T,Integer>();
	
	public GraphNode(T data){
		this.data=data;
	}
	
	void addNeihbor(T data){
		GraphNode<T> newNeighbor = new GraphNode<T>(data);
		this.neighbors.put(data, newNeighbor);
		this.distance.put(data, 1);
	}
	
	void addNeihbor(T data, int distance){
		GraphNode<T> newNeighbor = new GraphNode<T>(data);
		this.neighbors.put(data, newNeighbor);
		this.distance.put(data, distance);
	}
	
	void addNeighbor(GraphNode<T> neighbor,int distance){
		this.neighbors.put(neighbor.data, neighbor);
		this.distance.put(neighbor.data, distance);

	}
	
	void addNeighbor(GraphNode<T> neighbor){
		this.neighbors.put(neighbor.data, neighbor);
		this.distance.put(neighbor.data, 1);

	}
	
	void removeNeighbor(GraphNode<T> node){
		this.neighbors.remove(node.data);
		this.distance.remove(node.data);
	}
	
	void removeNeighbor(T data){
		this.neighbors.remove(data);
		this.distance.remove(data);
	}
	
	void visit(){
		this.isVisited=true;
	}
	
	void unVisit(){
		this.isVisited=false;
	}
	
	Collection<GraphNode<T>> getNeighbors(){
		return this.neighbors.values();
	}
	
}
