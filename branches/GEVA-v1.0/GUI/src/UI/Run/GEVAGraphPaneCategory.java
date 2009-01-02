package UI.Run;

import java.awt.Container;
import java.util.HashMap;

/**
 * Store a grouping of GEVAGraphItems that relate to each other, usually in
 *  their measurements in the graph. Each GEVAGraphPaneCategory given to
 *  GEVAGraph will produce a new tab, and each GEVAGraphPaneItem in the
 *  GEAVGraphCategory will produce a statistics and configuration entry under
 *  that tab
 * @author eliott bartley
 */
public class GEVAGraphPaneCategory
{

    private static class Items extends HashMap<String, GEVAGraphPaneItem> { };

    private String name;
    private Items items = new Items();
    private Container container;

    /**
     * Create a new category (statistics tab in graph pane) and give it a user-
     *  friendly name
     * @param name Name displayed on tab
     */
    public GEVAGraphPaneCategory(String name)
    {   this.name = name;
    }

    /**
     * Get the name that is displayed on the tab for this category
     */
    public String getName()
    {   return name;
    }

    /**
     * Add an item to this category. Each added item is displayed under the tab
     *  as a statistical data entry
     * @param item
     */
    public void addItem(GEVAGraphPaneItem item)
    {   items.put(item.getName(), item);
    }

    /**
     * Get an item by its name. this name is the name used by GEVA, not the
     *  user-friendly title
     * @param itemName
     */
    public GEVAGraphPaneItem getItem(String itemName)
    {   return items.get(itemName);
    }

    /**
     * Set the container where all items under this category are to be added.
     *  This is the awt (or swing) component
     * @param container
     */
    public void setContainer(Container container)
    {   this.container = container;
    }

    /**
     * Get the container where all items under this category are to be added.
     *  This is the awt (or swing) component
     */
    public Container getContainer()
    {   return container;
    }

}
