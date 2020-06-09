import java.io.InputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Arbitrage {

    public static ArrayList<Integer> arbitrage(double[][] rates) {

        // create new graph with same vertices and edges but modified weights
        int v = rates.length; // number of currencies
        double[][] graph = new double[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = i+1; j < v; j++) {
                // for each edge (u,v) change weight to -ln(rates[u][v])
                graph[i][j] = -Math.log(rates[i][j]);
                graph[j][i] = -Math.log(rates[j][i]);
            }
        }

        // use bellman ford to detect if there is a negative weight cycle
        double[] distance = new double[v]; // shortest distance from source vertex
        int[] parent = new int[v]; // parent of vertex in shortest-path tree

        // initialize single source
        for (int i = 0; i < v; i++) {
            distance[i] = Double.MAX_VALUE;
            parent[i] = Integer.MAX_VALUE;
        }
        distance[0] = 0; // source vertex - since graph is complete i.e. if a negative cycle exists it can be reached from
        // any vertex, source vertex can be any vertex

        for (int i = 0; i < v-1; i++) { // relax edges v-1 times
            for (int w = 0; w < v; w++) {
                for (int x = w+1; x < v; x++) {
                    relax(w, x, graph, distance, parent);
                    relax(x, w, graph, distance, parent); // relax each edge in the graph
                }
            }
        }

        // make a final pass to detect negative weight cycle
        for (int w = 0; w < v; w++) {
            for (int x = w+1; x < v; x++) {
                if (distance[x] > distance[w] + graph[w][x]) {
                    parent[x] = w;
                    return findCycle(x, parent);
                }
                if (distance[w] > distance[x] + graph[x][w]) {
                    parent[w] = x;
                    return findCycle(w, parent);
                }
            }
        }


        return new ArrayList<Integer>();
    }

    private static void relax(int u, int v, double[][] w, double[] distance, int[] parent) {
        if (distance[v] > distance[u] + w[u][v]) {
            distance[v] = distance[u] + w[u][v];
            parent[v] = u;
        }
    }

    private static ArrayList<Integer> findCycle(int u, int[] parent) {

        ArrayList<Integer> cycle = new ArrayList<>();
        int[] count = new int[parent.length]; // count the num of times a vertex is in the path
        u = parent[u];

        while (true) {
            cycle.add(u);
            count[u] += 1;
            if (count[u] > 1) {
                break; // stop when vertex has been encountered on the path twice - indicates cycle
            }
            u = parent[u];
        }

        int v;
        do {
            v = cycle.get(0);
            cycle.remove(0);
        } while (v != u); // modify path to exclude vertices that are not in the cycle

        Collections.reverse(cycle); // reverse path to reflect direction of edges
        
        cycle.add(cycle.get(0));

        return cycle;
    }

    /**** DO NOT EDIT ****/
    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = System.in;
        PrintStream out = System.out;

        Scanner s = new Scanner(in);
        int i, j;
        double bid, ask;
        int n = s.nextInt(); // number of currencies
        double[][] rates = new double[n][n];
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                rates[a][b] = 1.0;
            }
        }

        while (s.hasNextLine()) {
            if (!s.hasNextInt()) break;
            i = s.nextInt();
            j = s.nextInt();
            bid = s.nextDouble();
            ask = s.nextDouble();
            rates[i][j] = bid;
            rates[j][i] = 1.0 / ask;
        }

        ArrayList<Integer> cycle = arbitrage(rates);
        if (cycle.size() > 0) {
            for (int a = 0; a < cycle.size() - 1; a++) {
                out.printf("%d ", cycle.get(a));
            }
            out.printf("%d\n", cycle.get(cycle.size()-1));
        }
        else {
            out.printf("No arbitrage cycle\n");
        }
/**** DO NOT EDIT ****/
    }
}
