package ex1.src;


import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {

    private weighted_graph graph;


    public WGraph_Algo() {
        graph = new WGraph_DS();
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     *
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this.graph = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return graph
     */
    @Override
    public weighted_graph getGraph() {
        return this.graph;
    }


    /**
     * Compute a deep copy of this weighted graph.
     */
    @Override
    public weighted_graph copy() {
        weighted_graph coppedGraph = new WGraph_DS();

        if (graph != null) {
            for (node_info currNode : graph.getV()) {
                coppedGraph.addNode(currNode.getKey());

                for (node_info currNi : graph.getV(currNode.getKey())) {

                    coppedGraph.addNode(currNi.getKey());

                    if (currNode != null && currNi != null) {
                        double edg = graph.getEdge(currNode.getKey(), currNi.getKey());
                        coppedGraph.connect(currNode.getKey(), currNi.getKey(), edg);

                    }
                }
            }
        }

        return coppedGraph;
    }

    /**
     * Returns true if  there is a valid path from every node to each other node.
     */
    @Override
    public boolean isConnected() {
        if (graph.nodeSize() == 0 || graph.nodeSize() == 1) return true;

        Iterator<node_info> first = graph.getV().iterator();
        node_info firstVertex = first.next();
        ;

        BFS(firstVertex.getKey());
        for (node_info currNode : graph.getV()) {
            if (currNode.getTag() == Integer.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param src  - start node
     * @param dest - target node
     * @return the length of the shortest path between src to dest
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (graph.getNode(src) == null || graph.getNode(dest) == null) return -1;
        if (src == dest) return 0;
        DSF(src, dest);
        return graph.getNode(dest).getTag();
    }


    /**
     * @param src  - start node
     * @param dest - end (target) node
     * @return the the shortest path between src to dest  as an ordered List of nodes
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {
        if (graph.getNode(src) == null || graph.getNode(dest) == null) return null;
        if (graph.getNode(dest).getInfo()!="" && !graph.hasEdge(dest,Integer.parseInt(graph.getNode(dest).getInfo())) )return null;


        LinkedList<node_info> ans = new LinkedList<>();

        ans= pathrCeate(src,dest);
        return ansPath(ans);
    }

    /**
     * Saves this weighted  graph to the given
     * file name
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(graph);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        WGraph_DS Graphload;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                Graphload = (WGraph_DS) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            fis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        this.graph=Graphload;
        return true;

    }


    /**
     * this method crates a reversed version of the shortest path (according to the edge wight ) between two given nodes
     **/
    private void DSF(int src,int dest) {
        initializeNode(graph);

        PriorityQueue<node_info> q = new PriorityQueue<>();
        HashMap<Integer, Integer> father = new HashMap<>();//collection that saves each node father.

        node_info srcNode = graph.getNode(src);
        q.add(srcNode);



        srcNode.setTag(0);

        node_info currNode = null;

        while (!q.isEmpty()) {

            currNode = q.poll();

            for (node_info currNi : graph.getV(currNode.getKey())) {

                double edgeValue = graph.getEdge(currNode.getKey(), currNi.getKey());

                if (currNode.getTag() + edgeValue < currNi.getTag()) {
                    currNi.setTag(currNode.getTag() + edgeValue);


                        q.add(currNi);

                   currNi.setInfo(String.valueOf(currNode.getKey()));
                }

            }
        }

    }







    private LinkedList<node_info> pathrCeate(int src, int dest ){
        DSF(src,dest);

        node_info currnode = graph.getNode(dest);

        LinkedList<node_info> path = new LinkedList<>();

        while (currnode.getInfo() != "") {

            path.add(currnode);
            currnode = graph.getNode(Integer.parseInt(currnode.getInfo()));

        }
        path.add(graph.getNode(src));

        return path;


    }

    /**
     * this function reverses the shortest path
     **/
    private LinkedList<node_info> ansPath(LinkedList<node_info> reversedPath) {
        LinkedList<node_info> ans = new LinkedList<>();
        if(reversedPath!=null) {
            Iterator<node_info> iter = reversedPath.descendingIterator();
            while (iter.hasNext()) {
                ans.add(iter.next());
            }
        }
        return ans;

    }

    /**
     * this function marks all the vertex that has been visited
     * -to be used in is connection function
     **/

    private void BFS(int src) {
        initializeNode(graph);

        LinkedList<node_info> queue = new LinkedList<>();

        queue.add(graph.getNode(src));

        node_info currentNode = null;
        while (!queue.isEmpty()) {
            currentNode = queue.removeFirst();
            Collection<node_info> neighbors = graph.getV(currentNode.getKey());

            for (node_info next : neighbors) {
                if (next.getTag() == Integer.MAX_VALUE) {
                    queue.add(next);
                    next.setTag(0);

                }
            }
        }
    }

    /**
     * this method initialize the "info" and  "tag" on each node in the graph
     **/
    private void initializeNode(weighted_graph g) {
        for (node_info curr : g.getV()) {
            curr.setTag(Integer.MAX_VALUE);
            curr.setInfo("");

        }
    }


}

