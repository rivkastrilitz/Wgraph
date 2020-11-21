
package ex1.src;

import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {


    private int modificationCounter = 0;
    private int numOfEdges = 0;
    private HashMap<Integer, node_info> graph = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, Double>> edges = new HashMap<>();

    public WGraph_DS() {

    }

    @Override
    public node_info getNode(int key) {

        return graph.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {


        if (edges.get(node1) != null && edges.get(node2) != null) {
            if (edges.get(node1).get(node2) != null && edges.get(node2).get(node1) != null) {

                    return true;

            }
        }
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {

        if (hasEdge(node1, node2)) {
            if (edges.get(node1) != null) {
                return edges.get(node1).get(node2);
            }
        }
        return -1;
    }

    @Override
    public void addNode(int key) {
        node_info node = new Node_Info(key);
        HashMap<Integer, Double> ni = new HashMap<>();

        if(!graph.containsKey(key)&&!edges.containsKey(key)) {
            graph.put(key, node);
            edges.put(key, ni);

            modificationCounter++;
        }
    }

    @Override
    public void connect(int node1, int node2, double w) {

        if(node1==node2)
            return;

        if (graph.containsKey(node1) && graph.containsKey(node2)) {

            if (hasEdge(node1, node2)) {

                removeEdge(node1, node2);
                if (edges.get(node1) != null) {
                    edges.get(node1).put(node2, w);
                    edges.get(node2).put(node1, w);
                    numOfEdges++;
                    modificationCounter++;

                }
            }
            else {
                if (edges.get(node1) != null ) {
                    edges.get(node1).put(node2, w);
                    edges.get(node2).put(node1, w);
                    numOfEdges++;
                    modificationCounter++;
                }
            }
        }
    }

    @Override
    public Collection<node_info> getV() {
        return this.graph.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {

        HashSet<node_info> neighbors = new HashSet<>();
        node_info nodeToSet;

        if (edges.get(node_id) != null) {
            for (int ni : edges.get(node_id).keySet()) {
                if (hasEdge(node_id, ni)) {
                    nodeToSet = getNode(ni);
                    neighbors.add(nodeToSet);
                }
            }
        }

        return neighbors;
    }

    @Override
    public node_info removeNode(int key) {

        for (node_info niNode : getV(key)) {
            int sizeOfni = getV(key).size();

            this.edges.remove(key);

            numOfEdges -= sizeOfni;
            modificationCounter += sizeOfni;
        }

        modificationCounter++;
        return graph.remove(key);
    }

    @Override
    public void removeEdge(int node1, int node2) {

        if (hasEdge(node1, node2)) {
            this.edges.get(node1).remove(node2);
            this.edges.get(node2).remove(node1);

            numOfEdges--;
            modificationCounter++;

        }
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof weighted_graph))
            return false;
        weighted_graph wg=(WGraph_DS)o;

        if(this.nodeSize()!=wg.nodeSize()||this.edgeSize()!=wg.edgeSize())
            return false;

          boolean flag=false;

        for (int cuurNode:this.edges.keySet()) {


            for (int currNi:this.edges.get(cuurNode).keySet()) {
               if( this.getEdge(cuurNode,currNi)==wg.getEdge(cuurNode,currNi)){
                   flag=true;
               }

            }

        }
        return  flag;
    }



    @Override
    public int nodeSize() {
        return this.graph.size();
    }

    @Override
    public int edgeSize() {
        return this.numOfEdges;
    }

    @Override
    public int getMC() {
        return this.modificationCounter;
    }


    private class Node_Info implements node_info, Serializable, Comparable{

        int key = 0;

        String info = "";
        double tag = Integer.MAX_VALUE;

        public Node_Info(int k) {
            this.key = k;
        }

        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        @Override
        public double getTag() {
            return this.tag;
        }

        @Override
        public void setTag(double t) {
            this.tag = t;

        }


        @Override
        public int compareTo(Object n1) {
            node_info node1 = (node_info) n1;

            if (node1.getTag() == this.getTag()) return 0;

            if (node1.getTag() < this.getTag()) {
                return 1;
            } else {
                return -1;
            }

        }

        @Override
        public boolean equals(Object o){
            if(!(o instanceof node_info))
                return false;
            node_info node= (Node_Info)o;

            if(this.getKey()!=node.getKey())
                return  false;


             return true;

        }





    }
}
