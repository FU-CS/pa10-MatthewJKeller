package pa10;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphMap implements Graph{

    public int vertices;
    public Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();

    public GraphMap(int vertices) {
        this.vertices = vertices;
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }
    @Override
    public void addEdge(int v, int w) {
        adjacencyList.putIfAbsent(v, new ArrayList<>());
        adjacencyList.get(v).add(w);
    }

    @Override
    public String topologicalSort() {
        Stack<Integer> sorted = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> curr = new HashSet<>(); 

        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                if (dfs(i, sorted, visited, curr)) {
                    return "Graph has a cycle";
                }
            }
        }

        String result = " ";
        while (!sorted.isEmpty()) {
            result = result + " " + sorted.pop();
        }
        return result.trim();
    }

    private boolean dfs(int vertex, Stack<Integer> sorted, Set<Integer> visited, Set<Integer> curr) {
        if (curr.contains(vertex)) {
            return true; 
        }
        if (visited.contains(vertex)) {
            return false; 
        }

        curr.add(vertex);
        visited.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (dfs(neighbor, sorted, visited, curr)) {
                return true;
            }
        }

        curr.remove(vertex);
        sorted.push(vertex);
        return false;
    }


    @Override
    public String kahn() {
        int[] inDegree = new int[vertices];
        List<Integer> sorted = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjacencyList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int visited = 0; 

        while (!q.isEmpty()) {
            int current = q.poll();
            sorted.add(current);
            visited++;

            for (int neighbor : adjacencyList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        if (visited == vertices) {
            return sorted.toString();
        } else {
            return "Graph has a cycle";
        }
    }


}
