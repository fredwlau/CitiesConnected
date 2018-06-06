import java.util.*;
import java.lang.*;
import java.io.*;
 
public class Connected
{
    private Map<String, LinkedHashSet<String>> map = new HashMap();
 
    public void addEdge(String node1, String node2)
    {
        LinkedHashSet<String> adjacent = map.get(node1);
        if (adjacent == null)
        {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }
 
    public void addUndirected(String node1, String node2)
    {
        addEdge(node1, node2);
        addEdge(node2, node1);
    }
 
    public LinkedList<String> adjacentNodes(String last)
    {
        LinkedHashSet<String> adjacent = map.get(last);
        if (adjacent == null)
        {
            return new LinkedList();
        }
        return new LinkedList<String>(adjacent);
    }
 
    private static String  START;
    private static String  END;
 
    public static void main(String[] args)
    {
        // this graph is undirectional
        Connected graph = new Connected();
		String fileName = args[0];
		String line = null;
		START = args[1];
		END = args[2];
		LinkedList<String> visited = new LinkedList();
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(file);
			while((line = buffer.readLine())!=null) {
				String [] city = line.split(", ");
				graph.addUndirected(city[0], city[1]);
			}
			buffer.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Unable to read from file: " + fileName);
		}
		visited.add(START);
		Connected.bfs(graph, visited);
		System.out.println("no");
    }
 
    public static void bfs(Connected graph,
            LinkedList<String> visited)
    {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());
 
        for (String node : nodes)
        {
            if (visited.contains(node))
            {
                continue;
            }
            if (node.equals(END))
            {
                System.out.println("yes");
                System.exit(0);
            }
        }
 
        for (String node : nodes)
        {
            if (visited.contains(node) || node.equals(END))
            {
                continue;
            }
            visited.addLast(node);
            bfs(graph, visited);
            visited.removeLast();
        }
    }
}
