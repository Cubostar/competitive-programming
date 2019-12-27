import java.util.PriorityQueue;
import java.util.Scanner;

// Uses adjacency matrix, O(n^2)
class Dijikstra {

	static class Edge implements Comparable<Edge> {
		public int vertex1;
		public int vertex2;
		public int length;

		public Edge(int vertex1, int vertex2, int length){
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.length = length;
		}

		public int compareTo(Edge other){
			return(this.length - other.length);
		}

		public String toString(){
			return(vertex1 + ", " + vertex2 + ", " + length);
		}
	}

    // Finds shortest distance from one node to each node
    static int[] dijkstra(int[][] graph, int vertex1){
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] dijkstra = new int[graph.length];
		for (int i = 0; i < graph.length; i++){
			dijkstra[i] = -1;
		}
        
        for (int i = 0; i < graph.length; i++){
			if (graph[vertex1][i] != -1){
				pq.add(new Edge(1, i, graph[vertex1][i]));
			}
		}
		
		while (!pq.isEmpty()){
			Edge e = pq.poll();

			// For debugging:
			// System.out.println(e);
			
			if (dijkstra[e.vertex2] != -1){
				;
			} else {
				dijkstra[e.vertex2] = e.length;
				for (int i = 0; i < graph.length; i++){
					if (graph[e.vertex2][i] != -1){
						pq.add(new Edge(e.vertex2, i, graph[e.vertex2][i] + e.length));
					}
				}
			}
		}

		return(dijkstra);
	}

    // Finds shortest distance between two vertices
    static int dijkstra(int[][] graph, int vertex1, int vertex2){
        int[] dijkstra = dijkstra(graph, vertex1);
        return(dijkstra[vertex2]);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		int edges = sc.nextInt();
        int[][] graph = new int[vertices + 1][vertices + 1];
        
		// Adjacency matrix version (vertices start counting at 1, not zero)
		for (int i = 0; i < vertices + 1; i++) { // Initialize matrix to have -1 in each entry
			for (int j = 0; j < vertices + 1; j++) {
				if (i == j){
					graph[i][j] = 0;
				}
				graph[i][j] = -1;
			}
		}
		for (int lengths = 0; lengths < edges; lengths++) { //Takes inputs and adds them to matrix
			int vertex1 = sc.nextInt();
			int vertex2 = sc.nextInt();
			int length = sc.nextInt();
			
			// If there's recurring paths, you may want to check if the preexisting entry is lower (but not -1)
			graph[vertex1][vertex2] = length;
			graph[vertex2][vertex1] = length;
		}

		int vertex1 = sc.nextInt();
		int vertex2 = sc.nextInt();
		System.out.println(dijkstra(graph, vertex1, vertex2));

		sc.close();
	}
}