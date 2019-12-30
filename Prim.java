import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Scanner;

// Uses adjacency list (ArrayList of ArrayList of Edges), O(M*log(n)) where n is # of vertices and M is # of edges
// Assumes each edge is unique (only one edge between two nodes)
class Prim {

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

    // Finds sum of edges in minimum spanning tree
    static int prim(ArrayList<ArrayList<Edge>> graph){
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] prim = new int[graph.size()];
		for (int i = 0; i < graph.size(); i++){
			prim[i] = -1;
		}

        for (int i = 0; i < graph.get(1).size(); i++){
			pq.add(graph.get(1).get(i));
		}

		while (!pq.isEmpty()){
			Edge e = pq.poll();
			ArrayList<Edge> al = graph.get(e.vertex);

			// For debugging:
			// System.out.println(e);

			if (!IntStream.of(prim).anyMatch(x -> x == -1)){
				break;
			} else if (prim[e.vertex] != -1){
				continue;
			} else {
				prim[e.vertex] = e.length;
				for (int i = 0; i < al.size(); i++){
						pq.add(new Edge(al.get(i).vertex, al.get(i).length)); // The only difference from Dijkstra is removing "+ e.length"
					}
			}
		}

        int sum = 0;
        for (int i = 0; i < prim.length; i++){
            sum += prim[i];
        }

        return(sum);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
			
			graph.get(vertex1).add(new Edge(vertex2, length));
			graph.get(vertex2).add(new Edge(vertex1, length));
		}

		System.out.println(prim(graph));

		sc.close();
	}
}