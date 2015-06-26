
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.FileNotFoundException;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
public class FormWorker extends javax.swing.JFrame {
    /**
     * Creates new form FormWorker
     */
    public FormWorker() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" Визуализация алгоритма Дейкстры");
        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jButton1.setText("Начать");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Следующий шаг");

        jLabel2.setText("Путь к файлу");
        jLabel2.setToolTipText("");

        jLabel3.setText("Обозначения");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(68, 68, 68))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jLabel3))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*public void paint(Graphics g) {       
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.red);
        Line2D lin = new Line2D.Float(100, 100, 250, 500);
        
        g2.draw(lin);
        repaint();
    }*/
    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:     
        GraphManager graph = new GraphManager();
        try{
            graph.readFromFile();
        }
        catch(FileNotFoundException e){System.out.println(e);}
        
        System.out.println(jPanel1.getHeight()+" "+jPanel1.getWidth());
        System.out.println(jPanel1.getX()+" "+jPanel1.getY());
        
        BufferedImage background = new BufferedImage(500,400,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = background.createGraphics();
            
        
        graphics.setFont(new Font("Serif", Font.PLAIN, 20));
        graphics.setColor(Color.green);
        graphics.fillRect(0, 0, background.getWidth(), background.getHeight());
        graphics.setColor(Color.BLACK);
        print_points(graphics);
        print_edges(graphics,graph);
        
        this.setLayout(new BorderLayout());
        
        jPanel1.getGraphics().drawImage(background, 0, 0,Color.BLACK, null);
        this.pack();
        this.setVisible(true);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){}
        
        BufferedImage copyImage = deepCopy(background);
        
        
        showEdge(copyImage,graph,1,2);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    

private void showEdge(BufferedImage copyImage,GraphManager graph,int from,int to){
        Graphics2D copyGraphics = (Graphics2D)copyImage.getGraphics();        
        double angle = 360 / graph.table.size()*Math.PI / 180;

                 int centerX = 250;
                 int centerY = 150;
                 int radius = 125;
                 int startX = 250, startY = 25;

                 double curX1, curY1, curX2, curY2;
                 double cur_angle1, cur_angle2;

                 cur_angle1 = from * angle;
                 curX1 = (startX - centerX)*cos(cur_angle1) - (startY - centerY)*sin(cur_angle1) + centerX;
                 curY1 = (startX - centerX)*sin(cur_angle1) + (startY - centerY)*cos(cur_angle1) + centerY;

                 cur_angle2 = to*angle;
                 curX2 = (startX - centerX)*cos(cur_angle2) - (startY - centerY)*sin(cur_angle2) + centerX;
                 curY2 = (startX - centerX)*sin(cur_angle2) + (startY - centerY)*cos(cur_angle2) + centerY;
                 
                 
                 copyGraphics.setColor(Color.red);
                 copyGraphics.setStroke(new BasicStroke(3));
                 copyGraphics.drawLine((int)curX1, (int)curY1, (int)curX2, (int)curY2);
        jPanel1.getGraphics().drawImage(copyImage, 0, 0, null);
        this.pack();
        this.setVisible(true);

        
    }

private void dropPoint(){
    /*
                 void drop_edge(vector<vector<pair<int, int>>> table, int to)
             {
                 double angle = 360 / pointCoun*PI / 180;

                 int centerX = 250;
                 int centerY = 150;
                 int radius = 125;
                 double cur_angle = 0;
                 int startX = 250, startY = 25;

                 double curX, curY;

                 Graphics^ graph = Graphics::FromImage(background);

                 cur_angle = to*angle;
                 curX = (startX - centerX)*cos(cur_angle) - (startY - centerY)*sin(cur_angle) + centerX;
                 curY = (startX - centerX)*sin(cur_angle) + (startY - centerY)*cos(cur_angle) + centerY;


                 System::Drawing::SolidBrush^ myBrush =
                     gcnew System::Drawing::SolidBrush(System::Drawing::Color::Red);
                 graph->FillEllipse(myBrush, Rectangle(curX - 15, curY - 15, 30, 30));

                 pictureBox1->Image = background;
                 pictureBox1->Refresh();
             }
    */
}

private void print_points(Graphics2D graphics)
             {
                 int pointCoun = 5;
                 double angle = 360 / pointCoun*Math.PI / 180;
                 ArrayList<Map<Integer,Integer>> a;
                 int centerX = 250;
                 int centerY = 150;
                 int radius = 125;
                 double cur_angle = 0;
                 int startX = 250, startY = 25;

                 double curX, curY;

                 //Graphics^ graph = Graphics::FromImage(background);

                 for (int i = 0; i < pointCoun; i++, cur_angle += angle)
                 {
                     curX = (startX - centerX)*cos(cur_angle) - (startY - centerY)*sin(cur_angle) + centerX;
                     curY = (startX - centerX)*sin(cur_angle) + (startY - centerY)*cos(cur_angle) + centerY;

                    
                    
                     graphics.drawOval((int)curX - 15, (int)curY - 15, 30, 30);
                     graphics.drawString(String.valueOf(i), (int)curX - 15, (int) curY-15);
                     //jPanel1.getGraphics().drawOval((int)curX - 15, (int)curY - 15, 30, 30);
                     //graph->DrawEllipse(pen, curX - 15, curY - 15, 30, 30);
                     //jPanel1.getGraphics().drawString(String.valueOf(i), (int)curX - 15, (int) curY-15);
                     //graph->DrawString(i.ToString(), this->Font, Brushes::Red, (float)curX - 15, (float)curY + 20);
                 }
                 
                 
                 //pictureBox1->Image = background;
             }
private void print_edges(Graphics2D graphics,GraphManager graph)
             {
                 double angle = 360 / graph.table.size()*Math.PI / 180;

                 int centerX = 250;
                 int centerY = 150;
                 int radius = 125;
                 int startX = 250, startY = 25;

                 double curX1, curY1, curX2, curY2;

                 //Graphics^ graph = Graphics::FromImage(background);

                 double cur_angle1, cur_angle2;
                 for (int i = 0; i < graph.pointCount; i++)
                {
                    for (int j = 0; j < graph.edgesNumbers.get(i); j++)
                    {
                        cur_angle1 = i * angle;
                        curX1 = (startX - centerX)*cos(cur_angle1) - (startY - centerY)*sin(cur_angle1) + centerX;
                        curY1 = (startX - centerX)*sin(cur_angle1) + (startY - centerY)*cos(cur_angle1) + centerY;

                        cur_angle2 = graph.table.get(i).get(j).getFirst()*angle;
                        curX2 = (startX - centerX)*cos(cur_angle2) - (startY - centerY)*sin(cur_angle2) + centerX;
                        curY2 = (startX - centerX)*sin(cur_angle2) + (startY - centerY)*cos(cur_angle2) + centerY;

                        //Pen^ pen = gcnew Pen(Color::Black, 3);
                        //pen->DashStyle = System::Drawing::Drawing2D::DashStyle::Custom;
                        //pen->EndCap = System::Drawing::Drawing2D::LineCap::ArrowAnchor;
                        //System.out.println(curX1+" "+curY1+" "+curX2+" "+curY2);
                        graphics.drawLine((int)curX1, (int)curY1, (int)curX2, (int)curY2);
                        //jPanel1.getGraphics().drawLine((int)curX1, (int)curY1, (int)curX2, (int)curY2);
                        //graph->DrawLine(pen, (int)curX1, (int)curY1, (int)curX2, (int)curY2);
                    }
                }
             }
private static void fill_points(Graphics2D graphics,GraphManager graph){
        double angle = 360 / graph.pointCount * Math.PI / 180;
        ArrayList<Map<Integer,Integer>> a;
        int centerX = 250;
        int centerY = 150;
        int radius = 125;
        double cur_angle = 0;
        int startX = 250, startY = 25;

        double curX, curY;

        for (int i = 0; i < graph.pointCount; i++, cur_angle += angle)
        {
            curX = (startX - centerX)*Math.cos(cur_angle) - (startY - centerY)*Math.sin(cur_angle) + centerX;
            curY = (startX - centerX)*Math.sin(cur_angle) + (startY - centerY)*Math.cos(cur_angle) + centerY;

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Tahoma", Font.ITALIC, 15));

            graphics.fillOval((int)curX, (int)curY, 28, 28);

            graphics.drawString(String.valueOf(i), (int)curX, (int) curY);
        }
    }

           /*  
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}