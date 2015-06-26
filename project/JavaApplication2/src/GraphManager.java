import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhenya
 */
public class GraphManager {
    public int startPoint;
    public int endPoint = 5;
    private int edgeCount;
    public int pointCount;
    public LinkedList<Boolean> isVisit;
    public ArrayList<Integer> distance;
    public ArrayList<Integer> prevPoints;
    public ArrayList<ArrayList<Pair<Integer,Integer>>> table;
    public ArrayList<Integer> edgesNumbers;

    public void readFromFile() throws FileNotFoundException{
        
        Scanner scanner = new Scanner(new File("D://input.txt"));

        startPoint = Integer.parseInt(scanner.next());
        edgeCount = Integer.parseInt(scanner.next());
        pointCount = Integer.parseInt(scanner.next());

        table = new ArrayList<ArrayList<Pair<Integer,Integer>>>(pointCount);
        isVisit = new LinkedList<>(Collections.nCopies(pointCount,Boolean.FALSE));
        prevPoints = new ArrayList<>(Collections.nCopies(pointCount,0));
        distance = new ArrayList<>(Collections.nCopies(pointCount,Integer.MAX_VALUE));
        edgesNumbers = new ArrayList<>(Collections.nCopies(pointCount,0));

        for (int i = 0; i < pointCount - 1; i++) {
            ArrayList<Pair<Integer,Integer>> sublist = new ArrayList<Pair<Integer,Integer>>();
            int edges = Integer.parseInt(scanner.next());
            edgesNumbers.add(i,edges);

            for (int j = 0; j < edges; j++) {
                int fromPoint = Integer.parseInt(scanner.next());
                int toPoint = Integer.parseInt(scanner.next());
                int cost = Integer.parseInt(scanner.next());

                sublist.add(j, new Pair<Integer, Integer>(toPoint, cost));
            }
            table.add(sublist);

            System.out.println("Список"+i);
            for (Pair<Integer, Integer> integerIntegerPair : sublist) {
                System.out.println(integerIntegerPair.getFirst()+" "+integerIntegerPair.getSecond());
            }
        }
    }
}