package UI;

import Fractal.LSystem2Control;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * View LSystems in a stand-alone project
 * @author eliott bartley
 */
public class LSystemViewer extends JFrame
{

    private LSystem2Control guiLSystem;
    private JButton      cmdClose = new JButton("Close");

    public LSystemViewer(String grammar, int depth, float angle)
    {   guiLSystem = new LSystem2Control(grammar, depth, angle, cmdClose);
        initialiseWindow();
    }
    
    private void initialiseWindow()
    {   super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setSize(400, 500);
        super.setLayout(new BorderLayout());

        cmdClose.addActionListener(new ActionListener()
        {   public void actionPerformed(ActionEvent e)
            {   dispose();
            }
        });

        super.add(guiLSystem, BorderLayout.CENTER);

        super.setVisible(true);
        
    }
    
}
