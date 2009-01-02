/*
 * panelComp.java
 *
 * Created on January 25, 2008, 2:24 PM
 */

package Fractal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author  patrickmiddleburgh
 */
public class finalProtoComp extends JPanel implements ActionListener{
    
    /** Creates new form protoComp */
    public finalProtoComp(String grammar, int depth, float angle) {
        //System.out.println("finalProtoComp: "+depth+angle+grammar);
        grammar_new = grammar;
        depth_new = depth;
        angle_new = angle;
        //System.out.println("PROTOCOMP; grammar: "+grammar+", depth: "+depth+", angle: "+angle);
        initComponents();
        
         
    }
    
    
                        
    private void initComponents() {
        
        jPanel1 = new LSystem(grammar_new, depth_new, angle_new, 460, 0);
        jButton_enlarge = new JButton();
        jButton_enlarge.setText("Enlarge");
        jButton_enlarge.addActionListener(this);

        

        jPanel1.setBackground(new Color(255, 255, 255));

        
        size = new Dimension(200,260);
        this.setSize(size);
        
        setLayout(null);
        
        jPanel1.setBounds(10,10,170,170);
        jButton_enlarge.setBounds(50,210,90,20);
        
        add(jPanel1);
        add(jButton_enlarge);
        
        
    }// </editor-fold> 

    public void actionPerformed(ActionEvent actionEvent) {
        //System.out.println("the enlarge button works!");
        Dimension size = new Dimension(500,625);
        enlarged bigger = new enlarged(grammar_new, depth_new, angle_new);
        bigger.setSize(size);
        
        //keep running untill generate button on gui is pressed.
        //while(enlarged.shouldContinue()==false){
          //  System.out.print("");
        //}
        //bigger.setVisible(false);
        //bigger = null;
    }

    // Variables declaration - do not modify                     
    private JPanel jPanel1;
    private JButton jButton_enlarge;
    Dimension size;
    BufferedImage test;
    
    String grammar_new;
    int depth_new;
    float angle_new;
    // End of variables declaration                   
    
}

