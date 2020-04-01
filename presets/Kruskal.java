package presets;
import java.util.PriorityQueue;
import java.util.Scanner;

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

    // Disjoint set data structure for union-find. Very abstractly made for Kruskal's algorithm.
    static class DisjointSet{
        private int[] set;
        private int[] unions;

        public DisjointSet(int n){
            set = new int[n+1];
            unions = new int[n+1];

            for (int i = 1; i < set.length; i++){
                set[i] = i;
                unions[i] = i;
            }
        }

        public int find(int i){
            return unions[i];
        }

        public void union(int a, int b){
            int check = unions[b];
            for (int i = 1; i < unions.length; i++){
                if (unions[i] == check) unions[i] = unions[a];
            }
        }

        public boolean isFullUnioned(){
            int first = unions[1];
            for (int i = 1; i < unions.length; i++){
                if (first != unions[i]) return false;
            }

            return true;
        }

        public void printUnions(){
            System.out.println("");
            for (int i = 1; i < unions.length; i++){
                System.out.print(unions[i] + ", ");
            }
        }
    }
    
    static int kruskal(int vertices, PriorityQueue<Edge> pq){
        int kruskal = 0;

        DisjointSet ds = new DisjointSet(vertices);

        while (!ds.isFullUnioned()){
            Edge e = pq.poll();
            if (ds.find(e.vertex1) == ds.find(e.vertex2)){
                continue;
            } else {
                kruskal += e.length;
                ds.union(e.vertex1, e.vertex2);
            }
        }

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