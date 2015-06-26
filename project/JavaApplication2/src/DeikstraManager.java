
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhenya
 */
public class DeikstraManager {


    public void startDeikstra(GraphManager graph){
        graph.distance = new ArrayList<>(Collections.nCopies(graph.pointCount,Integer.MAX_VALUE));
        graph.distance.set(graph.startPoint, 0);
        
        for (int i = 0; i < graph.table.size(); i++) {

            int curPoint = -1;

            for (int j = 0; j < graph.pointCount; j++) {
                if(!graph.isVisit.get(j)&&(curPoint == -1 || graph.distance.get(i) < graph.distance.get(curPoint))){
                    curPoint = j;
                }
            }

            if(graph.distance.get(curPoint) == Integer.MAX_VALUE){
                break;
            }

            graph.isVisit.set(curPoint, Boolean.TRUE);

            for (int j = 0; j < graph.table.get(curPoint).size(); j++) {
                int to  = graph.table.get(curPoint).get(j).getFirst();
                int cost = graph.table.get(curPoint).get(j).getSecond();
                //if(j == graph.table.get(curPoint).size() - 1) ;//обвести вершину
                //подсветить ребро

                if(graph.distance.get(curPoint) + cost < graph.distance.get(to)){
                    graph.distance.set(to, graph.distance.get(curPoint) + cost);
                    graph.prevPoints.set(to,curPoint);
                }
            }

        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int v = graph.endPoint; v != graph.startPoint; v = graph.prevPoints.get(v))
        {
            path.add(v);
        }
        path.add(graph.startPoint);

        System.out.println("Путь");
        for (int j = path.size() - 1; j >= 0; j--) {
            System.out.print(path.get(j) + " ");
        }
    }
}
