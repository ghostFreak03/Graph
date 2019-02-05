/**
 * @author Ghost Freak
 *
 */
public class SampleGraph {
	public static void main(String args[]) {
		//Creating a directed graph
		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 8);
		graph.addEdge(8, 11);
		
		System.out.print("AdjacentVertices 1");
		for(Long id : graph.getAdjacentVertices(1)){
			System.out.print(" --> "+id);
		}
	}
}
