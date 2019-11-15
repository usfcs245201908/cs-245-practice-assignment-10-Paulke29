import java.util.Stack;
import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph{
    private int size;
    private LinkedList<Integer>[] Sort;

    //initial constructor
    GraphImplementation(int size){
        this.size = size;
        this.Sort = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            Sort[i] = new LinkedList<>();
        }
    }
    // adding edge
    @Override
    public void addEdge(int v1, int v2) throws Exception {
        //Checking whether idex over size
        if(v1>=size || v2>=size){
            throw new Exception();
        }
        Sort[v1].addLast(v2);
    }



    // Start to sort
    @Override
    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        LinkedList<Integer> newSort = new LinkedList<>();
        //visit from each node if not already visited
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        int size = stack.size();
        for (int i = 0; i <size ; i++) {
            newSort.add(stack.pop());
        }
        return newSort;
    }
    //interator sorting
    public void topologicalSortUtil(int start, boolean[] visited, Stack<Integer> stack) {
        visited[start] = true;
        for (int i = 0; i < Sort[start].size(); i++) {
            int vertex = Sort[start].get(i);
            if (!visited[vertex])
                topologicalSortUtil(vertex, visited, stack);
        }
        stack.push(start);
    }
    //Checking neighbor
    @Override
    public List<Integer> neighbors(int vertex) throws Exception {
        List<Integer> q = new LinkedList<>();
        int node[] = new int[size];
        // Traverse adjacency lists to fill node of vertices.
        for(int i = 0; i < Sort.length; i++)
        {
            for(int c = 0; c < Sort[i].size();c++){
                //Adding number to list
                q.add(Sort[i].get(c));
            }
        }
        return q;
    }
}
