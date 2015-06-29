
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
        System.out.println("dfsd"+graph.startPoint);
        graph.distance.set(graph.startPoint, 0);

        for (int i = 0; i < graph.pointCount; i++) {
            int curPoint = -1;
            
            for (int j = 0; j < graph.pointCount; j++) {
                if(!graph.isVisit.get(j)&&(curPoint == -1 || graph.distance.get(j) < graph.distance.get(curPoint))){
                    curPoint = j;
                }
            }
            System.out.println("CurPoint="+curPoint);
            if(graph.distance.get(curPoint) == Integer.MAX_VALUE){
                System.out.println("Капец");
                break;
            }
            graph.isVisit.set(curPoint, Boolean.TRUE);
            form.dropPoint(graph, copyImage, curPoint);
            
            for (int j = 0; j < graph.table.get(curPoint).size(); j++) {
                //panel.getGraphics().drawImage(copyImage, 0, 0,Color.BLACK, null);
                this.suspend();
                
                
                textArea.append("\nТекущая вершина = "+curPoint+"\n");

                int to  = graph.table.get(curPoint).get(j).getFirst();
                int cost = graph.table.get(curPoint).get(j).getSecond();
                //if(j == graph.table.get(curPoint).size() - 1 || 0 == graph.table.get(curPoint).size())form.dropPoint(graph, copyImage, curPoint);
                
                form.showEdge(copyImage, graph, curPoint, to);
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
                    textArea.append("Метки вершин:\n");
                    for (int k = 0; k < graph.distance.size();k++)
                    {
                        textArea.append("Вершина["+String.valueOf(k)+"] = "+String.valueOf(graph.distance.get(k))+"\n");
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
        String resLine ="";
        for (int i = 0; i < graph.distance.size(); i++) {
            resLine +="Вершина "+i+": "+ graph.distance.get(i)+"\n";
            
        }
        //JOptionPane.showMessageDialog(form, "Алгоритм завершил работу.\nМетки вершин:\n"+resLine);
        textArea.append("\nАлгоритм завершил работу.\nМетки вершин:\n"+resLine);
        //panel.repaint();
    }
}
