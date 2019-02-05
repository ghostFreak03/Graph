/**
 * Created by GhostFreak
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {

	// Contains all the edges in the graph
	private List<Edge<T>> allEdges;
	// Contains all the vertices in the form of vertex data and vertex object
	// Vertex object contains all the edges and the adjacent vertices of the
	// vertex
	private Map<Long, Vertex<T>> allVertices;
	// Boolean for directed or undirected graph
	boolean isDirected = false;

	// Constructor
	public Graph(boolean isDirected) {
		allEdges = new ArrayList<Edge<T>>();
		allVertices = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	// Func for adding unweighted edge
	public void addEdge(long id1, long id2) {
		addEdge(id1, id2, 0);
	}

	// Func for adding Weighted edge
	public void addEdge(long id1, long id2, int weight) {
		// Check if vertex is present in allvertices
		Vertex<T> vertex1 = null;
		if (allVertices.containsKey(id1)) {
			vertex1 = allVertices.get(id1);
		} else {
			vertex1 = new Vertex<T>(id1);
			allVertices.put(id1, vertex1);
		}
		Vertex<T> vertex2 = null;
		if (allVertices.containsKey(id2)) {
			vertex2 = allVertices.get(id2);
		} else {
			vertex2 = new Vertex<T>(id2);
			allVertices.put(id2, vertex2);
		}
		// Create and add edge to alledges
		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected) {
			vertex2.addAdjacentVertex(edge, vertex1);
		}
	}

	// Getters
	public Vertex<T> getVertex(long id) {
		return allVertices.get(id);
	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex() {
		return allVertices.values();
	}
	
	public List<Long> getAdjacentVertices(long id){
		List<Long> adjacentVertices = new ArrayList<Long>();
		if(allVertices.containsKey(id)){
			List<Vertex<T>> list = allVertices.get(id).getAdjacentVertexes();
			for(Vertex<T> v : list){
				adjacentVertices.add(v.getId());
			}
			return adjacentVertices;
		}
		return null;
	}

	// Setter for optional data of the vertex
	public void setDataForVertex(long id, T data) {
		if (allVertices.containsKey(id)) {
			Vertex<T> vertex = allVertices.get(id);
			vertex.setData(data);
		}
	}
}

class Edge<T> {

	private boolean isDirected = false;
	private Vertex<T> vertex1;
	private Vertex<T> vertex2;
	private int weight;

	Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.isDirected = isDirected;
	}

	Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.isDirected = isDirected;
	}

	// getters
	Vertex<T> getVertex1() {
		return vertex1;
	}

	Vertex<T> getVertex2() {
		return vertex2;
	}

	int getWeight() {
		return weight;
	}

	public boolean isDirected() {
		return isDirected;
	}

}

class Vertex<T> {

	long id;
	private T data;// optional data
	private List<Edge<T>> edges = new ArrayList<>();
	private List<Vertex<T>> adjacentVertex = new ArrayList<>();

	public Vertex(long id) {
		this.id = id;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void addAdjacentVertex(Edge<T> e, Vertex<T> v) {
		edges.add(e);
		adjacentVertex.add(v);
	}

	public List<Vertex<T>> getAdjacentVertexes() {
		return adjacentVertex;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

}
