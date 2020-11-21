package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Test;


import java.text.DecimalFormat;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {

    public weighted_graph graphCreate(){
        weighted_graph gr=new WGraph_DS();
        gr.addNode(1);
        gr.addNode(2);
        gr.addNode(3);
        gr.addNode(4);
        gr.addNode(5);
        gr.addNode(6);
        gr.addNode(7);
        gr.addNode(8);
        gr.addNode(9);
        gr.addNode(10);
        gr.connect(1,2,3);
        gr.connect(1,3,3);
        gr.connect(3,6,5);
        gr.connect(2,4,1);
        gr.connect(1,5,1);
        gr.connect(4,5,1);
        gr.connect(5,7,1);
        gr.connect(5,8,8);
        gr.connect(8,7,1);
        gr.connect(8,9,1);
        gr.connect(8,10,0);
        gr.connect(9,10,1);


    return gr ;
    }


    @Test
    void init() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        ga.init(gr);
        assertEquals(gr,ga.getGraph());
        gr.removeEdge(8,10);
        assertEquals(gr,ga.getGraph());
        assertTrue(gr==ga.getGraph());

        gr.connect(8,10,0);




    }

    @Test
    void getGraph() {
        weighted_graph_algorithms galgo= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        galgo.init(gr);
       assertEquals(galgo.getGraph(),gr);


    }

    @Test
    void copy() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        ga.init(gr);
        toStringWithWeight(ga.getGraph());
        weighted_graph copy=ga.copy();

        toStringWithWeight(copy);

       assertEquals(ga.copy(),ga.getGraph());

      assertFalse(ga.copy()==ga.getGraph());


    }

    @Test
    void isConnected() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        ga.init(gr);

        assertTrue(ga.isConnected());
        gr.removeEdge(1,5);
        gr.removeEdge(4,5);
        assertFalse(ga.isConnected());
        gr.connect(1,5,1);
        gr.connect(4,5,1);
    }

    @Test
    void shortestPathDist() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        ga.init(gr);

        assertEquals(3, ga.shortestPathDist(1,10));
        assertEquals(-1,ga.shortestPathDist(1,13));
        assertEquals(0,ga.shortestPathDist(1,1));

    }

    @Test
    void shortestPath() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph gr =graphCreate();
        ga.init(gr);

        LinkedList<node_info>wantedPath=new LinkedList<>();
        wantedPath.add(gr.getNode(1));
        wantedPath.add(gr.getNode(5));
        wantedPath.add(gr.getNode(7));
        wantedPath.add(gr.getNode(8));
        wantedPath.add(gr.getNode(10));
        assertEquals(ga.shortestPath(1,10),wantedPath);
        assertEquals(ga.shortestPath(1,12),null);

        gr.removeEdge(8,9);
        gr.removeEdge(8,10);
        assertEquals(ga.shortestPath(1,10),null);

        LinkedList<node_info>wantedPath2=new LinkedList<>();
        wantedPath2.add(gr.getNode(1));
        assertEquals(ga.shortestPath(1,1),wantedPath2);





    }

    @Test
    void save() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph g1=graphCreate();
        ga.init(g1);
        weighted_graph_algorithms ga2= new WGraph_Algo();
        weighted_graph g2=new WGraph_DS();
        ga2.init(g2);
        ga.save("my file");
        ga2.load("my file");
        assertEquals(ga.getGraph(),ga2.getGraph());

        ga.save("my file");
        g1.removeNode(1);
        ga2.load("my file");
        assertNotEquals(ga.getGraph(),ga2.getGraph());

    }

    @Test
    void load() {
        weighted_graph_algorithms ga= new WGraph_Algo();
        weighted_graph g1=graphCreate();
        ga.init(g1);
        weighted_graph_algorithms ga2= new WGraph_Algo();
        weighted_graph g2=new WGraph_DS();
        ga2.init(g2);
        ga.save("my file");
        ga2.load("my file");
        assertEquals(ga.getGraph(),ga2.getGraph());

        ga.save("my file");
        g1.removeNode(1);
        ga2.load("my file");
        assertNotEquals(ga.getGraph(),ga2.getGraph());

    }

    public static void toStringWithWeight(weighted_graph g){
        String niString;
        DecimalFormat df = new DecimalFormat("0.00");
        int nodeSize= g.nodeSize();
        for (node_info node : g.getV()) {
            niString = "[";
            for(node_info ni : g.getV(node.getKey())) {
                niString += ni.getKey() + " |" + df.format(g.getEdge(ni.getKey(), node.getKey()))+"|, ";
            }

            System.out.println("node := "+ node.getKey() + ", Ni := " +  niString + "]");

        }
        System.out.println("###############################################################");
    }


}