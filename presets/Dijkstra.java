package presets;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Scanner;

// Uses adjacency list (ArrayList of ArrayList of Edges), O(M*log(n)) where n is # of vertices and M is # of edges
// Assumes each edge is unique (only one edge between two nodes)
class Dijikstra {

	static class Edge implements Comparable<Edge> {
		public int vertex;
		public int length;

		public Edge(int vertex, int length){
			this.vertex = vertex;
			this.length = length;
		}

		public int compareTo(Edge other){
			return(this.length - other.length);
		}

		public String toString(){
			return(vertex + ", " + length);
		}
	}

    // Finds shortest distance from one node to each node
    static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int vertex1){
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] dijkstra = new int[graph.size()];
		for (int i = 0; i < graph.size(); i++){
			dijkstra[i] = -1;
		}

        for (int i = 0; i < graph.get(vertex1).size(); i++){
			pq.add(graph.get(vertex1).get(i));
		}

		while (!pq.isEmpty()){
			Edge e = pq.poll();
			ArrayList<Edge> al = graph.get(e.vertex);

			// For debugging:
			// System.out.println(e);

			if (!IntStream.of(dijkstra).anyMatch(x -> x == -1)){
				break;
			} else if (dijkstra[e.vertex] != -1){
				continue;
			} else {
				dijkstra[e.vertex] = e.length;
				for (int i = 0; i < al.size(); i++){
						pq.add(new Edge(al.get(i).vertex, al.get(i).length + e.length));
					}
			}
		}

		return(dijkstra);
	}

    // Finds shortest distance between two vertices
    static int dijkstra(ArrayList<ArrayList<Edge>> graph, int vertex1, int vertex2){
        int[] dijkstra = dijkstra(graph, vertex1);
        return(dijkstra[vertex2]);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// I/O is as follows:
		// {number of nodes} {number of edges}
		// {first endpoint of edge 1} {second endpoint of edge 1} {weight of edge 1}
		// {first endpoint of edge 2} {second endpoint of edge 2} {weight of edge 2}
		// ...                        ...                         ...
		// {source node} {destination node}
		int vertices = sc.nextInt();
		int edges = sc.nextInt();
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();

		for (int vertex = 0; vertex < vertices + 1; vertex++){
			graph.add(new ArrayList<Edge>());
		}

		for (int lengths = 0; lengths < edges; lengths++) { //Takes inputs and adds them to list
			int vertex1 = sc.nextInt();
			int vertex2 = sc.nextInt();
			int length = sc.nextInt();
			
			// Assmes graph is undirected
			graph.get(vertex1).add(new Edge(vertex2, length));
			graph.get(vertex2).add(new Edge(vertex1, length));
		}

		int vertex1 = sc.nextInt();
		int vertex2 = sc.nextInt();
		System.out.println(dijkstra(graph, vertex1, vertex2));

		sc.close();
	}
}