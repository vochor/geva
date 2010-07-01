/*
 * panelComp.java
 *
 * Created on January 25, 2008, 2:24 PM
 */

package Fractal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author  patrickmiddleburgh
 */
public class protoComp extends JPanel {
    
    /** Creates new form protoComp */
    public protoComp(LSystem2Config.SelectAction select, LSystem2Config config, int width) {
        grammar_new = config.grammar;
        depth_new = config.depth;
        angle_new = config.angle;
        this.config = config;
        this.select = select;
        //System.out.println("PROTOCOMP; grammar: "+grammar+", depth: "+depth+", angle: "+angle);
        initComponents(width);
        
         
    }

    private void initComponents(int width) {
        
        jPanel1 = new LSystem2PanelSelect
        (   grammar_new,
            depth_new,
            angle_new,
            this.config.fitness
        );
        chkParent = new JCheckBox();
        chkPurge = new JCheckBox();
        
        jPanel1.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e)
            {
                //new enlarged(grammar_new, depth_new, angle_new).setSize(400, 500);
                new UI.LSystemViewer(grammar_new, depth_new, angle_new);
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
        jPanel1.setToolTipText
        (   "g:[" + grammar_new
          + "] d:[" + depth_new
          + "] a:[" + angle_new
          + "] f:[" + this.config.fitness
          + "]"
        );
        chkParent.setText("Parent");
        
        this.setPreferredSize(new Dimension(width, width + 40));
        
        setLayout(null);
        
        jPanel1.setBounds(0,0,width,width);
        chkParent.setBounds(0,width,width,20);
        chkParent.addActionListener(new ActionListener()
        {   public void actionPerformed(ActionEvent e)
            {   chkParent_onClick();
            }
        });

        chkPurge.setText("Purge");
        chkPurge.setBounds(0, width + 20, width, 20);
        chkPurge.addActionListener(new ActionListener()
        {   public void actionPerformed(ActionEvent e)
            {   chkPurge_onClick();
            }
        });

        add(jPanel1);
        add(chkParent);
        add(chkPurge);
        
        
    }// </editor-fold> 

    void chkParent_onClick()
    {   config.select = chkParent.isSelected();
        if(chkParent.isSelected() == true)
        {   chkPurge.setSelected(false);
            chkPurge_onClick();
        }
        select.selectPerformed();
    }
    
    void chkPurge_onClick()
    {   config.purge = chkPurge.isSelected();
        if(chkPurge.isSelected() == true)
        {   chkParent.setSelected(false);
            chkParent_onClick();
        }
    }
    
    // Variables declaration - do not modify                     
    private JPanel jPanel1;
    private JCheckBox chkParent;
    private JCheckBox chkPurge;
    BufferedImage test;
    
    String grammar_new;
    int depth_new;
    float angle_new;
    LSystem2Config config;
    LSystem2Config.SelectAction select;
    // End of variables declaration                   
    
}

