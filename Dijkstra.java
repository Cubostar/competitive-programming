import java.util.PriorityQueue;
import java.util.Scanner;

// Uses adjacency matrix, O(n^2)
class Dijikstra {

	static class Edge implements Comparable<Edge> {
		public int node1;
		public int node2;
		public int length;

		public Edge(int node1, int node2, int length){
			this.node1 = node1;
			this.node2 = node2;
			this.length = length;
		}

		public int compareTo(Edge other){
			return(this.length - other.length);
		}

		public String toString(){
			return(node1 + ", " + node2 + ", " + length);
		}
	}

    // Finds shortest distance to each node from one node
    static int[] dijkstra(int[][] graph, int node1){
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] dijkstra = new int[graph.length];
		for (int i = 0; i < graph.length; i++){
			dijkstra[i] = -1;
		}
        
        for (int i = 0; i < graph.length; i++){
			if (graph[node1][i] != -1){
				pq.add(new Edge(1, i, graph[node1][i]));
			}
		}
		
		while (!pq.isEmpty()){
			Edge e = pq.poll();
			System.out.println(e);
			if (dijkstra[e.node2] != -1){
				;
			} else {
				dijkstra[e.node2] = e.length;
				for (int i = 0; i < graph.length; i++){
					if (graph[e.node2][i] != -1){
						pq.add(new Edge(e.node2, i, graph[e.node2][i] + e.length));
					}
				}
			}
		}

		return(dijkstra);
    }
    // Finds shortest distance between two nodes
    static int dijkstra(int[][] graph, int node1, int node2){
        int[] dijkstra = dijkstra(graph, node1);
        return(dijkstra[node2]);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		int edges = sc.nextInt();
        int[][] graph = new int[nodes + 1][nodes + 1];
        
		// Adjacency matrix version (nodes start counting at 1, not zero)
		for (int i = 0; i < nodes + 1; i++) { // Initialize matrix to have -1 in each entry
			for (int j = 0; j < nodes + 1; j++) {
				if (i == j){
					graph[i][j] = 0;
				}
				graph[i][j] = -1;
			}
		}
		for (int lengths = 0; lengths < edges; lengths++) { //Takes inputs and abstracts them
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			int length = sc.nextInt();
			// If there's recurring paths, you may want to check if the preexisting entry is lower (but not -1)
			graph[node1][node2] = length;
			graph[node2][node1] = length;
		}

		int node1 = sc.nextInt();
		int node2 = sc.nextInt();
		System.out.println(dijkstra(graph, node1, node2));
		sc.close();
	}
}