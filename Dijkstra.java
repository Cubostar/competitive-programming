import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// Uses adjacency matrix, O(n^2)
class Dijikstra {

    // Finds shortest distance between each node
    static int[][] dijkstra(int[][] graph){
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<ArrayList<Integer>>();
        
        for (int i = 0; i < graph.length; i++){
            
        }
    }
    // Finds shortest distance between two nodes
    static int dijkstra(int[][] graph, int node1, int node2){
        int[][] dijkstra = dijkstra(graph);
        return(dijkstra[node1][node2]);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		int edges = sc.nextInt();
        int[][] graph = new int[nodes + 1][edges + 1];
        
		// Adjacency matrix version (nodes start counting at 1, not zero)
		for (int i = 0; i < nodes + 1; i++) { // Initialize matrix to have -1 in each entry
			for (int j = 0; j < edges + 1; j++) {
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
        
        sc.close();
		}
	}
}
