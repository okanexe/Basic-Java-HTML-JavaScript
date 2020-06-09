
import java.util.*;

public class MST {
	
	/**
	 * This function takes in a graph, with weights classified as light, medium or heavy
	 * It should output a list of all edges in the graph that must be a part of
	 * any minimum spanning tree
	 * 
	 * You should use the Tuple class provided to output edges.  For instance, to output
	 * the edge (u,v), you would add "new Tuple(u,v)" to your list
	 * 
	 * @param g
	 * @return
	 */
	public static Set<Tuple> mustExist(WeightedUndirectedGraph<Weight> g) {
        PriorityQueue<Object> pq = new PriorityQueue<>();
        Object[][] weights;
        weights=g.weights;
        ArrayList<Tuple> edges = new ArrayList<Tuple>();
        for(int i = 0 ; i<weights.length;i++){
            for(int j = 0 ; j<weights.length;j++){
                if(weights[i][j]!=null){
                    Tuple tuples= new Tuple(0,0);
                    tuples.u=i;
                    tuples.v=j;
                    edges.add(tuples);
                    pq.add(g.weights[i][j]);
                }
            }
        }
        
        int [] parent = new int[g.n];
        for(int i = 0 ; i<g.n;i++){
            parent[i]=i;
        }
        //weights[tuple.u][tuple.v] þekline baðlýntý kurulacak 
        ArrayList<Tuple> priorityQueue = new ArrayList<Tuple>();
        
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.LIGHT){
                priorityQueue.add(edges.get(i));
            }
        }
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.MEDIUM){
                priorityQueue.add(edges.get(i));
            }
        }
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.HEAVY){
                priorityQueue.add(edges.get(i));
            }
        }
        /*for (int i = 0 ;i<g.weights.length*2;i++){
            System.out.println(priorityQueue.get(i).u+" "+priorityQueue.get(i).v);
        }*/
        
        ArrayList<Tuple> mst = new ArrayList<>();
        
        int index=0;
        while(index<g.n-1){
            Tuple tuple = priorityQueue.remove(0);
            
            int x_set = find(parent, tuple.u);
            int y_set = find(parent, tuple.v);

            if(x_set==y_set){
                    //ignore, will create cycle
            }else {
                    //add it to our final result
                    mst.add(tuple);
                    index++;
                    union(parent,x_set,y_set);
                }
        }
        /*for(int i = 0;i<mst.size();i++){
            System.out.println(mst.get(i).u+" "+mst.get(i).v);
        }*/
        
        Set<Tuple> result = new TreeSet<Tuple>();
        for(int i = 0;i<mst.size();i++){
            if(g.weights[mst.get(i).u][mst.get(i).v]==Weight.LIGHT){
                result.add(mst.get(i));
            }
        }


		return result;
	}
    
    static int find(int [] parent, int vertex){
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if(parent[vertex]!=vertex)
                return find(parent, parent[vertex]);;
            return vertex;
        }

    static void union(int [] parent, int x, int y){
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        }
    
	
    /**
	 * This function takes in a graph, with weights classified as light, medium or heavy
	 * It should output a list of all edges in the graph that must NOT be a part of
     * any minimum spanning tree
	 * 
	 * @param g
	 * @return the set of edges (unordered tuples of vertices) that must not
	 * exist in any MST
	 */
    public static Set<Tuple> mustNotExist(WeightedUndirectedGraph<Weight> g) {
        //TODO
        PriorityQueue<Object> pq = new PriorityQueue<>();
        Object[][] weights;
        weights=g.weights;
        ArrayList<Tuple> edges = new ArrayList<Tuple>();
        for(int i = 0 ; i<weights.length;i++){
            for(int j = 0 ; j<weights.length;j++){
                if(weights[i][j]!=null){
                    Tuple tuples= new Tuple(0,0);
                    tuples.u=i;
                    tuples.v=j;
                    edges.add(tuples);
                    pq.add(g.weights[i][j]);
                }
            }
        }
        
        int [] parent = new int[g.n];
        for(int i = 0 ; i<g.n;i++){
            parent[i]=i;
        }
        //weights[tuple.u][tuple.v] þekline baðlýntý kurulacak 
        ArrayList<Tuple> priorityQueue = new ArrayList<Tuple>();
        
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.LIGHT){
                priorityQueue.add(edges.get(i));
            }
        }
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.MEDIUM){
                priorityQueue.add(edges.get(i));
            }
        }
        for (int i = 0 ;i<edges.size();i++){
            if(g.weights[edges.get(i).u][edges.get(i).v]==Weight.HEAVY){
                priorityQueue.add(edges.get(i));
            }
        }
        /*for (int i = 0 ;i<g.weights.length*2;i++){
            System.out.println(priorityQueue.get(i).u+" "+priorityQueue.get(i).v);
        }*/
        
        ArrayList<Tuple> mst = new ArrayList<>();
        
        int index=0;
        while(index<g.n-1){
            Tuple tuple = priorityQueue.remove(0);
            
            int x_set = find(parent, tuple.u);
            int y_set = find(parent, tuple.v);

            if(x_set==y_set){
                    //ignore, will create cycle
            }else {
                    //add it to our final result
                    mst.add(tuple);
                    index++;
                    union(parent,x_set,y_set);
                }
        }
        /*for(int i = 0;i<mst.size();i++){
            System.out.println(mst.get(i).u+" "+mst.get(i).v);
        }*/
        Set<Tuple> result = new TreeSet<Tuple>();
        for ( int i = 0 ; i<mst.size();i++){
            if(!edges.contains(mst.get(i))&&g.weights[mst.get(i).u][mst.get(i).v]!=Weight.LIGHT){
                result.add(mst.get(i));
            }
        }
        
        /*Set<Tuple> result = new TreeSet<Tuple>();
        for(int i = 0;i<mst.size();i++){
            if(g.weights[mst.get(i).u][mst.get(i).v]!=Weight.LIGHT){
                result.add(mst.get(i));
            }
        }*/
        return result;
    }

	/**
	 * Edges in this MST are classified as light, medium or heavy
	 * (their actual edge weights are not known)
	 */
	public enum Weight {
		LIGHT, MEDIUM, HEAVY
	}
	

}
