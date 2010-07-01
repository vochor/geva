package UI;

import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JComponent;

/**
 * Base control for all controls that are containers.
 * Does administrative tasks, such as delegating calls to all contained children
 * @author eliottbartley
 */
public abstract class GEVAContainerControl extends GEVAControl
{

    protected static class Controls extends ArrayList<GEVAControl> { }

    protected Controls controls = new Controls();

    public GEVAContainerControl
    (   GEVADirtyListener    dirtyListener,
        GEVAContainerControl parent,
        String               type,
        String               title,
        String               comment
    ){  super(dirtyListener, parent, type, title, null, comment);
    }

    /**
     * Delegate enabling to all the controls this container contains.
     * Container controls typically won't be enabled/disabled, just
     *  the property control it contains. The user will still be able
     *  to navigate through the UI, just won't be able to modify and
     *  properties
     */
    public void setEnabled(boolean enabled)
    {   for(GEVAControl control : controls)
            control.setEnabled(enabled);
    }

    /**
     * Delegate loading to all the controls this container contains
     */
    public boolean load(Properties properties)
    {   boolean valid = true;
        // valid will be true only if all load calls were true
        //  if one load is false, valid will be false
        for(GEVAControl control : controls)
            valid &= control.load(properties);
        return valid;
    }

    /**
     * Delegate saving to all the controls this container contains
     */
    public boolean save(Properties properties)
    {   boolean valid = true;
        // valid will be true only if all load calls were true
        //  if one load is false, valid will be false
        for(GEVAControl control : controls)
            valid &= control.save(properties);
        return valid;
    }

    /**
     * Add an awt component to the awt component that is this container.
     * This also adds a tool tip to the control based on the comment, currently
     *  (2008y05M26d) only adds this tooltip if the component is a swing
     *  component. This must be always called (inheriting classes that override
     *  must super.add())
     * @param control The control to add
     */
    public void add(GEVAControl control)
    {   // Add the comment as a tool tip to all components in the control being
        //  added
        if(control.getComment() != null)
            for(int i = 0; i < control.countComponents(); i++)
                if(control.getComponent() instanceof JComponent)
                    ((JComponent)control.getComponent()).setToolTipText
                    (   control.getComment()
                    );
        // Add this control to an array so it will known when calling children
        //  to load/save/validate/etc.
        controls.add(control);
    }

    public void setVisibleControlGroup(String name, boolean show)
    {   parent.setVisibleControlGroup(name, show);
    }

    public void setVisible(boolean show)
    {   getComponent().setVisible(show);
    }

    public String getText() { return null; }

    /**
     * By default, containers don't have a setText (though Book container does,
     * so it can change pages through setText)
     * @param text
     */
    public void setText(String text) { }

    /**
     * Get all the contained controls to validate themselves
     */
    public void validate()
    {   for(GEVAControl control : controls)
            control.validate();
    }

}
