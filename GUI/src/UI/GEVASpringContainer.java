package UI;

import java.awt.Component;
import javax.swing.Box;

/**
 * Creates a container that fills empty space on the page.
 * Controls before will be pressed against the top of the page,
 *  controls after will be pressed against the bottom.
 * @author eliottbartley
 */
public class GEVASpringContainer extends GEVAContainerControl
{

    private Component boxSpring;

    public GEVASpringContainer
    (   GEVADirtyListener dirtyListener,
        GEVAPageContainer parent
    ){  super(dirtyListener, parent, "PropertyContainer", null, null);
        boxSpring = Box.createVerticalGlue();
        parent.add(this);
    }

    @Override
    public void add(GEVAControl control)
    {   super.add(control);
    }

    public Component getComponent(int index)
    {   return boxSpring;
    }

    public int countComponents() { return 1; }

}
