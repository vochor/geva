package Fractal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * View LSystems in a JPanel
 * @author eliott bartley
 */
public class LSystem2Panel extends JPanel
{

    private LSystem2Renderer lSystem;
    private String           axiom;
    private String           grammar;
    private int              depth;
    private float            angle;
    private int              centerX = 0;
    private int              centerY = 0;
    private float            scale = 1;

    public LSystem2Panel(String grammar, int depth, float angle)
    {   this(null, grammar, depth, angle);
    }

    public LSystem2Panel(String axiom, String grammar, int depth, float angle)
    {   this.axiom = axiom;
        this.grammar = grammar;
        this.depth = depth;
        this.angle = angle;
        createLSystem();
    }

    public void setAxiom(String axiom)
    {   this.axiom = axiom;
        createLSystem();
        super.repaint();
    }

    public void setGrammar(String grammar)
    {   this.grammar = grammar;
        createLSystem();
        super.repaint();
    }

    public void setDepth(int depth)
    {   this.depth = depth;
        createLSystem();
        super.repaint();
    }

    public void setAngle(float angle)
    {   this.angle = angle;
        createLSystem();
        super.repaint();
    }

    protected void setScale(float scale)
    {   this.scale = scale;
        super.repaint();
    }

    public String getDerivedGrammar()
    {   return lSystem.getDerivedGrammar();
    }

    @Override
    public void paint(Graphics graphics)
    {   super.paint(graphics);
        int size = Math.min(this.getWidth(), this.getHeight());
        size *= scale;
        centerX = (this.getWidth() - size) / 2;
        centerY = (this.getHeight() - size) / 2;
        lSystem.setDimension(size - 1, size - 1);
        lSystem.render(graphics);
    }

    private void createLSystem()
    {   lSystem = new LSystem2Renderer
        (   axiom,
            grammar,
            depth,
            angle
        ){  protected void drawLine(Object user, int x, int y, int u, int v)
            {   assert user instanceof Graphics : user.getClass().getName();
                Graphics graphics = (Graphics)user;
                graphics.setColor(Color.green);
                graphics.drawLine
                (   x + centerX,
                    y + centerY,
                    u + centerX,
                    v + centerY
                );
            }
        };

    }

}
