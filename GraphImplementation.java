/*

Victoria Nerecina
CS245 - practice assignment 9
GraphImplementation.java


public GraphImplementation(int vertices)
    constructs and returns a graph with the number of the vertices passed as the argument
    vertices have IDs
        numbered 0, 1, ..., vertices - 1
    no edges exist between vertices at instantiation

public void addEdge(int source, int target)
    adds a directed edge between two vertices from source to target

public List<Integer> neighbors(int vertex)
    returns a List of vertex IDs
        with each ID representing a vertex
            which is the destination of the edge originating from the source vertex
    passed as the argument

public List<Integer> topologicalSort()
    prints (to console) one ordering of vertices such that each directed edge(v1, v2) from vertex v2
    v1 comes before v2 in the ordering
    if such an ordering sort is not possible (due to cycles, for example)
        this fucntion must indicate so
        though it may print a partial solution in so doing


help from
https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-list-better-set-2/
//https://www.geeksforgeeks.org/topological-sorting/
 */

import java.util.List;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.ArrayList;


public class GraphImplementation implements Graph
{
    public int vertices;
    public ArrayList<ArrayList<Integer>> adjacencyList;


    //GraphImplementation constructor
    public GraphImplementation(int vertices)
    {
        this.vertices = vertices;
        adjacencyList = new ArrayList<ArrayList<Integer>>(vertices);
        for(int i = 0; i < vertices; i++)
        {
            adjacencyList.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int v1, int v2) throws Exception
    {
        //v1 = source, v2 = target
        //directed = one way
        adjacencyList.get(v1).add(v2);
    }

    /* only for directed graph
    a linear ordering of vertices v1, v2, .., vn in such a way that
    if there is an edge directed towards vertex vj from vertex vi
    then vi comes before vj
    */
    //help from
    //https://algorithms.tutorialhorizon.com/topological-sort/
    //https://www.geeksforgeeks.org/topological-sorting/
    public List<Integer> topologicalSort()
    {
        Stack<Integer> stack = new Stack<Integer>();
        boolean visited[] = new boolean [vertices];
        //marking vertices as not visited
        for(int i = 0; i < vertices; i++)
        {
            visited[i] = false;
        }
        //call a recuresive helper function to store topoplogical sort
        for(int i = 0; i < vertices; i++)
        {
            //starting from all vertices one by one
            if(visited[i] == false)
            {
                topologicalSortUtil(i, visited, stack);
            }
//            topologicalSortUtil(i, visited, stack);
        }
        return stack;


    }

    public List<Integer> neighbors(int vertex) throws Exception
    {
//        with each ID representing a vertex
//        which is the destination of the edge originating from the source vertex
//        passed as the argument
        return adjacencyList.get(vertex); //returns a List of vertex IDs

    }


    //helper for topological sort
    public void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack)
    {
        //mark current node as visited
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adjacencyList.get(v).iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
            {
                topologicalSortUtil(i, visited, stack);
            }
        }
        // Push current vertex to stack which stores result
        stack.push(new Integer(v));

    }

}
