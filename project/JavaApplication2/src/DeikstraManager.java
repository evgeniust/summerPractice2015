
import java.awt.Color;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhenya
 */
public class DeikstraManager extends Thread{

    private GraphManager graph;
    public BufferedImage copyImage = null;
    private FormWorker form;
    private TextArea textArea;
    private JPanel panel;
    
    public DeikstraManager(GraphManager graph, BufferedImage copyImage, FormWorker form,TextArea area,JPanel panel) {
        this.graph = graph;
        this.copyImage = copyImage;
        this.form = form;
        textArea = area;
        this.panel = panel;
    }
    
    public void run(){
        graph.distance = new ArrayList<>(Collections.nCopies(graph.pointCount,Integer.MAX_VALUE));
        graph.distance.set(graph.startPoint, 0);

        for (int i = 0; i < graph.pointCount; i++) {
            int curPoint = -1;
            
            for (int j = 0; j < graph.pointCount; j++) {
                if(!graph.isVisit.get(j)&&(curPoint == -1 || graph.distance.get(j) < graph.distance.get(curPoint))){
                    curPoint = j;
                }
            }
            if(graph.distance.get(curPoint) == Integer.MAX_VALUE){
                break;
            }
            
            graph.isVisit.set(curPoint, Boolean.TRUE);
            
            if(graph.table.get(curPoint).size() == 0) form.dropPoint(graph, copyImage, curPoint);
            for (int j = 0; j < graph.table.get(curPoint).size(); j++) {
                this.suspend();      
                textArea.append("\nТекущая вершина = "+curPoint+"\n");

                int to  = graph.table.get(curPoint).get(j).getFirst();
                int cost = graph.table.get(curPoint).get(j).getSecond();
                if(j == graph.table.get(curPoint).size() - 1) form.dropPoint(graph, copyImage, curPoint);
                textArea.append("\nТекущее ребро = "+curPoint+"->"+to+" Стоимость ="+cost+"\n");
  
                if(graph.distance.get(curPoint) + cost < graph.distance.get(to)){
                    textArea.append("Cтоимость ребра оптимальна, так как верно: ");
                    textArea.append(String.valueOf(graph.distance.get(curPoint)) + " + " + String.valueOf(cost) + " < " + String.valueOf(graph.distance.get(to))+"\n");
                    graph.distance.set(to, graph.distance.get(curPoint) + cost);
                    graph.prevPoints.set(to,curPoint);
                }
                else{
                        textArea.append("Cтоимость ребра не оптимальна, так как неверно: ");
                        textArea.append(String.valueOf(graph.distance.get(curPoint)) + " + " + String.valueOf(cost) + " < " + String.valueOf(graph.distance.get(to))+"\n");
                    }
                    form.showEdge(copyImage, graph, curPoint, to);

                    textArea.append("Метки вершин:\n");
                    for (int k = 0; k < graph.distance.size();k++)
                    {
                        textArea.append("Вершина["+String.valueOf(k)+"] = "+String.valueOf(graph.distance.get(k))+"\n");
                    }
            }
            
            
        }
        //form.dropPoint(graph, copyImage, g);

        ArrayList<Integer> path = new ArrayList<>();
        for (int v = graph.endPoint; v != graph.startPoint; v = graph.prevPoints.get(v))
        {
            path.add(v);
        }
        path.add(graph.startPoint);
    }
}
