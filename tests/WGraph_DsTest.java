package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import org.junit.Test;



import static org.junit.jupiter.api.Assertions.*;

public class WGraph_DsTest {

    @Test
    public void getEdgeTest(){
        weighted_graph g=new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(3);
        g.addNode(4);

        g.connect(1,2,2.0);
        g.connect(1,3,10.0);
        g.connect(1,5,1.0);

        g.connect(2,4,0.0);

        assertEquals(10,g.getEdge(1,3));
        assertEquals(-1,g.getEdge(3,4));



    }

    @Test
    public void connectTest(){
      weighted_graph g=new WGraph_DS();
      g.addNode(1);
      g.addNode(2);
      g.addNode(5);
      g.addNode(3);
      g.addNode(4);

        g.connect(1,2,2.0);
        g.connect(1,3,10.0);
        g.connect(1,5,1.0);
        g.connect(3,4,6.0);
        g.connect(2,4,0.0);

        g.connect(2,2,5);

        g.connect(1,2,3);
        double edg= g.getEdge(1,2);
        assertEquals(3.0,edg);
    }
    @Test
    public void removeEdge(){
        weighted_graph g=new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(3);
        g.addNode(4);

        g.connect(1,2,2.0);
        g.connect(1,3,10.0);
        g.connect(1,5,1.0);
        g.connect(3,4,6.0);
        g.connect(2,4,0.0);

        g.removeEdge(1,6);
        g.removeEdge(1,1);

        g.removeEdge(1,2);
        g.removeEdge(1,2);
        assertFalse(g.hasEdge(1,2));
        assertEquals(4,g.edgeSize());


    }
     @Test
     public void addNodeTest(){
         weighted_graph g=new WGraph_DS();
         g.addNode(1);
         g.addNode(2);
         g.addNode(5);
         g.connect(1,2,6);
         g.connect(5,3,4);
         assertEquals(3,g.nodeSize());
         g.removeEdge(1,2);
         assertEquals(3,g.nodeSize());
         g.removeNode(5);
         assertEquals(2,g.nodeSize());

}
      @Test
      public void removeNodeTest(){
          weighted_graph g=new WGraph_DS();
          g.addNode(1);
          g.addNode(2);
          g.addNode(5);
          g.addNode(3);
          g.addNode(4);

          g.connect(1,2,2.0);
          g.connect(1,3,10.0);
          g.connect(1,5,1.0);
          g.connect(3,4,6.0);
          g.connect(2,4,0.0);

          g.removeNode(6);
          g.removeNode(1);
          g.removeNode(-5);
          assertEquals(4,g.nodeSize());
          assertEquals(2,g.edgeSize());



     }
@Test
public void getVTest(){
    weighted_graph g=new WGraph_DS();
    g.addNode(1);
    g.addNode(2);
    g.addNode(5);
    g.addNode(3);
    g.addNode(4);

    g.connect(1,2,2.0);
    g.connect(1,3,10.0);
    g.connect(1,5,1.0);
    g.connect(3,4,6.0);
    g.connect(2,4,0.0);
    assertEquals(5,g.getV().size());
   g.removeNode(2);
    assertEquals(4,g.getV().size());

}
    @Test
    public void getV_NiTest(){
        weighted_graph g=new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(3);
        g.addNode(4);

        g.connect(1,2,2.0);
        g.connect(1,3,10.0);
        g.connect(1,5,1.0);
        g.connect(3,4,6.0);
        g.connect(2,4,0.0);
        assertEquals(3,g.getV(1).size());
        g.removeNode(1);
        assertEquals(0,g.getV(1).size());

    }
    @Test
    public void hasEdgeTest(){
        weighted_graph g=new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(3);
        g.addNode(4);

        g.connect(1,2,2.0);
        g.connect(1,3,10.0);
        assertTrue(g.hasEdge(1,2));
        g.removeEdge(1,2);
        assertFalse(g.hasEdge(1,2));
        assertFalse(g.hasEdge(1,1));

    }
    @Test
    public void getNodeTest(){
        weighted_graph g=new WGraph_DS();
        g.addNode(1);
        g.addNode(2);
        g.addNode(5);
        g.addNode(3);

        assertNotNull(g.getNode(1));
        assertNotSame(g.getNode(1),g.getNode(2));
        assertNotEquals(g.getNode(1),g.getNode(2));
        assertEquals(g.getNode(1),g.getNode(1));
    }







}
