package pa10;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GraphTest {

    @Test
    public void testTopologicalSort() {
        GraphMap graph = new GraphMap(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        String result = graph.topologicalSort();
        assertEquals("5 0 2 1 3 4", result);
    }

    @Test
    public void testKahnAlgorithm() {
        GraphMap graph = new GraphMap(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        String result = graph.kahn();
        assertEquals("[0, 1, 2, 3, 4, 5]", result);
    }

    @Test
    public void testKahnAlgorithmWithCycle() {
        GraphMap graph = new GraphMap(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        String result = graph.kahn();
        assertEquals("Graph has a cycle", result);
    }

    @Test
    public void testEmptyGraph() {
        GraphMap emptyGraph = new GraphMap(0);

        assertEquals("[]", emptyGraph.kahn());
        assertEquals("", emptyGraph.topologicalSort());
    }

    @Test
    public void testDisconnectedGraph() {
        GraphMap graph = new GraphMap(6);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        String result = graph.topologicalSort();
        assertEquals("4 5 2 3 0 1", result);
    }

    @Test
    public void testSingleNodeGraph() {
        GraphMap graph = new GraphMap(1);

        String result = graph.topologicalSort();
        assertEquals("0", result);

        assertEquals("[0]", graph.kahn());
    }


    @Test
    public void testSimpleCycle() {
        GraphMap graph = new GraphMap(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        String result = graph.topologicalSort();
        assertEquals("Graph has a cycle", result);

        assertEquals("Graph has a cycle", graph.kahn());
    }


    @Test
    public void testGraphWithMultipleEdges() {
        GraphMap graph = new GraphMap(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 1); 
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String result = graph.topologicalSort();
        assertEquals("0 1 2 3", result);

        assertEquals("[0, 1, 2, 3]", graph.kahn());
    }

    @Test
    public void testSelfLoop() {
        GraphMap graph = new GraphMap(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 2); 

        String result = graph.topologicalSort();
        assertEquals("Graph has a cycle", result);

        assertEquals("Graph has a cycle", graph.kahn());
    }

}
