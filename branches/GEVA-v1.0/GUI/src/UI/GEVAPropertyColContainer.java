package UI;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Property container that shows the name value property as name above value.
 *  Name becomes a heading to value, and value takes the whole space of the
 *  container so only one name/value control can be added
 * @author eliottbartley
 */
public class GEVAPropertyColContainer extends GEVAPropertyContainer
{

    private JPanel guiProperties;

    public GEVAPropertyColContainer
    (   GEVADirtyListener dirtyListener,
        GEVAPageContainer parent
    ){  super(dirtyListener, parent, "PropertyColContainer", null, null);
        guiProperties = new JPanel();
        guiProperties.setLayout(new BorderLayout());
        parent.add(this);
    }

    @Override
    public void add(GEVAControl control)
    {   super.add(control);
        guiProperties.add(control.getComponent(0), BorderLayout.NORTH);
        guiProperties.add(control.getComponent(1), BorderLayout.CENTER);
    }

    public Component getComponent(int index)
    {   return guiProperties;
    }

    public int countComponents() { return 1; }

}

