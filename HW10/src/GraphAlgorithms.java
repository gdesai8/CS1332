import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("The inputs may be null or the "
                    + "starting vertex doesn't exist in the graph.");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();
        Queue<Vertex<T>> explore = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> result = new ArrayList<>();
        explore.add(start);
        Vertex<T> current;
        while (!(explore.isEmpty())) {
            current = explore.remove();
            List<VertexDistancePair<T>> vertices = adjList.get(current);
            for (VertexDistancePair<T> vx : vertices) {
                if (!(visited.contains(vx.getVertex()))) {
                    explore.add(vx.getVertex());
                }
            }
            if (!(visited.contains(current))) {
                result.add(current);
            }
            visited.add(current);
        }
        return result;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("The inputs may be null or the "
                    + "starting vertex doesn't exist in the graph.");
        }
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> result = new ArrayList<>();
        result.add(start);
        dfsHelper(start, adjList, visited, result);
        return result;
    }

    /**
     * Helper method for depth first search algorithm.
     * This helper method implements the recursive call functionality.
     *
     * @param start the Vertex you start at for each recursive call
     * @param adjList the adjacency list used for the graph
     * @param visited the set of all the visited vertices
     * @param result the list that keeps track of the order in which vertices
     *               are visited
     * @param <T> the data type representing the vertices in the graph
     */
    private static <T> void dfsHelper(Vertex<T> start, Map<Vertex<T>,
            List<VertexDistancePair<T>>> adjList, Set<Vertex<T>> visited,
                                      List<Vertex<T>> result) {
        visited.add(start);
        List<VertexDistancePair<T>> vertices = adjList.get(start);
        for (VertexDistancePair<T> vx : vertices) {
            if (!(visited.contains(vx.getVertex()))) {
                result.add(vx.getVertex());
                dfsHelper(vx.getVertex(), adjList, visited, result);
            }
        }
    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("The inputs may be null or the "
                    + "starting vertex doesn't exist in the graph.");
        }
        Map<Vertex<T>, Integer> shortDist = new HashMap<>();
        Map<Vertex<T>, Boolean> lookedAt = new HashMap<>();
        PriorityQueue<VertexDistancePair<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();
        VertexDistancePair<T> starter = new VertexDistancePair<T>(start, 0);
        for (Vertex<T> vx : adjList.keySet()) {
            if (vx.equals(start)) {
                shortDist.put(vx, 0);
            } else {
                shortDist.put(vx, Integer.MAX_VALUE);
            }
            lookedAt.put(vx, false);
        }
        pq.add(starter);
        while (!pq.isEmpty()) {
            VertexDistancePair<T> v = pq.poll();
            lookedAt.put(v.getVertex(), true);
            for (VertexDistancePair<T> neighbor : adjList.get(v.getVertex())) {
                shortDist.put(neighbor.getVertex(),
                        Math.min(shortDist.get(neighbor.getVertex()),
                                (shortDist.get(v.getVertex())
                                        + neighbor.getDistance())));
                if (!lookedAt.get(neighbor.getVertex())) {
                    VertexDistancePair<T> newNeighbor =
                            new VertexDistancePair<T>(neighbor.getVertex(),
                                    shortDist.get(neighbor.getVertex()));
                    pq.add(newNeighbor);
                }
            }
        }
        return shortDist;
    }

    /**
     * Run Kruskal's algorithm on the given graph and return the minimum
     * spanning tree in the form of a set of Edges. If the graph is
     * disconnected, and therefore there is no valid MST, return null.
     *
     * You may assume that there will only be one valid MST that can be formed.
     * In addition, only an undirected graph will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, {@code java.util.Map} and any class from java.util
     * that implements the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("The graph should not be null, "
                    + "cannot form an MST.");
        }
        Set<Edge<T>> mst = new HashSet<>();
        Map<Vertex<T>, DisjointSet> clusters = new HashMap<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        for (Edge<T> edge : graph.getEdgeList()) {
            pq.add(edge);
        }
        for (Vertex<T> vx : graph.getAdjacencyList().keySet()) {
            DisjointSet cluster = new DisjointSet();
            clusters.put(vx, cluster);
        }
        int sizeVxs = graph.getAdjacencyList().keySet().size();
        while (mst.size() < sizeVxs - 1) {
            if (pq.isEmpty()) {
                return null;
            }
            Edge<T> minWeight = pq.poll();
            DisjointSet uCluster = clusters.get(minWeight.getU());
            DisjointSet vCluster = clusters.get(minWeight.getV());
            if (!(uCluster.find() == vCluster.find())) {
                mst.add(minWeight);
                uCluster.union(vCluster);
            }
        }
        return mst;
    }

}
