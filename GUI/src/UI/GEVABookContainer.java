package UI;

import java.awt.Component;
import javax.swing.JTabbedPane;
import java.util.Hashtable;

/**
 * The root container for all properties. Displayed as a tabbed control, allows
 *  the properties to be grouped into logical pages
 * @author eliottbartley
 */
public class GEVABookContainer extends GEVAContainerControl
{

    private static class ControlGroups
                 extends Hashtable<String, GEVAControlGroup> { }

    /**
     * The swing component used to render this control
     */
    private JTabbedPane guiBook;
    /**
     * Keeps a list of control groups. When a control group is to be modified,
     *  the control that issues the modification calls on its parent's
     *  *ControlGroup action command. This call is passed up the parent chain
     *  until it reaches the Book (root) container, which then goes through the
     *  controls in the group and calls on them to perform the action
     */
    private ControlGroups controlGroups = new ControlGroups();

    public GEVABookContainer
    (   GEVADirtyListener    dirtyListener,
        String               comment
    ){  super(dirtyListener, null, "book", null, comment);
        guiBook = new JTabbedPane();
    }

    /**
     * Helper - programmatically change the selected tabbed page to the
     *  specified page by title.
     * @param title The title of the page used for the title argument set when
     *  creating the GEVAPageControl
     */
    public void setPage(String title)
    {   int i = 0;
        for(GEVAControl control : controls)
        {   if(control.getTitle().equalsIgnoreCase(title) == true)
            {   guiBook.setSelectedIndex(i);
                break;
            }
            i++;
        }
    }

    @Override
    public void add(GEVAControl page)
    {   super.add(page);
        guiBook.addTab(page.getTitle(), page.getComponent());
    }

    public Component getComponent(int index)
    {   return guiBook;
    }

    public int countComponents() { return 1; }

    /**
     * Add a control group to an object, typically the Book object.
     * A control group is a collection of controls that all act in a similar way
     *  for a given event, such as all being shown/hidden depending on the
     *  selection of a drop-down
     * @param name The name of the control group. All controls in the group are
     *  referenced by this name
     * @param controlGroup A collection of all the controls in the group
     */
    public void addControlGroup(String name, GEVAControlGroup controlGroup)
    {   this.controlGroups.put(name, controlGroup);
    }

    @Override
    public void setVisibleControlGroup(String name, boolean show)
    {   GEVAControlGroup controlGroup = this.controlGroups.get(name);
        if(name != null && name.length() != 0) {
            if(controlGroup != null) {
		//HACK for hiding instead of showing elements
		// inverting the values
		if(controlGroup.isHiding()) {
		    show = !show;
		}
                for(GEVAControl control : controlGroup) {
                    control.setVisible(show);
		}
            } else {
                assert false : "Group " + name + " does not exist";
	    }
	}
    }

    @Override
    public void setVisible(boolean show)
    {   guiBook.setVisible(show);
    }

    @Override
    public String getText()
    {   return guiBook.getTitleAt(guiBook.getSelectedIndex());
    }

    @Override
    public void setText(String text)
    {   setPage(text);
    }

}
