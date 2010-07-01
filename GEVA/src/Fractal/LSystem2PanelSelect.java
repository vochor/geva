package Fractal;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author eliott bartley
 */
public class LSystem2PanelSelect extends LSystem2Panel
{

    private double fitness;
    
    public LSystem2PanelSelect
    (   String axiom,
        String grammar,
        int depth,
        float angle,
        double fitness
    ){  super(axiom, grammar, depth, angle);
        super.setScale(0.9f);
        this.fitness = fitness;
    }

    public LSystem2PanelSelect
    (   String grammar,
        int depth,
        float angle,
        double fitness
    ){  super(grammar, depth, angle);
        super.setScale(0.9f);
        this.fitness = fitness;
    }

    @Override
    public void paint(Graphics g)
    {   super.paint(g);

        /*// Render fitness as a red bar
        g.setColor(Color.red);
        g.fillRect
        (   2, this.getHeight() - (int)(this.getHeight() * fitness) + 2,
            5, (int)(this.getHeight() * fitness) - 4
        );
        */

        g.setColor(Color.gray);
        g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 5, 5);
        
    }

}
