package presets;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Arrays;

class Kruskal {

    // Class holds both vertices unlike Dijkstra and Prim
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
    
    static int kruskal(int vertices, PriorityQueue<Edge> pq){
        Boolean[] visited = new Boolean[vertices + 1];
        visited[0] = true;
        for (int i = 1; i < visited.length; i++){
            visited[i] = false;
        }
        int kruskal = 0;

        while (Arrays.asList(visited).contains(false)){
            Edge e = pq.poll();
            if (visited[e.vertex1] && visited[e.vertex2]){
                continue;
            } else {
                kruskal += e.length;
                visited[e.vertex1] = true;
                visited[e.vertex2] = true;
            }
        }

        kruskal += pq.poll().length;
        return(kruskal);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

        for (int i = 0; i < edges; i++){
            int vertex1 = sc.nextInt();
            int vertex2 = sc.nextInt();
            int length = sc.nextInt();

            pq.add(new Edge(vertex1, vertex2, length));
        }

        System.out.println(kruskal(vertices, pq));
        sc.close();
    }
}