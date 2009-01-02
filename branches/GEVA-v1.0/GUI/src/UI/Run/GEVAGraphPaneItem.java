package UI.Run;

import java.awt.Color;

/**
 * Store the configuration properties of an item in the graph. GEVAGraphItems
 *  are stored in a GEVAGraphCategory
 * @author eliott bartley
 */
public class GEVAGraphPaneItem
{

    /**
     * Hide this item on the graph, but still visible on the options
     */
    public static int VISIBLE_HIDE = 0;
    /**
     * Show this item on the graph
     */
    public static int VISIBLE_SHOW = 1;
    /**
     * Show this item on the graph, plus mark it as highlighted
     */
    public static int VISIBLE_BOLD = 2;

    private String name;
    private int visible;
    private Color colour;
    private String title;
    private String measure;
    private String errorName;

    public GEVAGraphPaneItem
    (   String      name,
        int      visible,
        Color     colour,
        String     title,
        String   measure,
        String errorName
    ){  assert clean(name) != null : "name required";
        this.visible = visible;
        this.colour = colour;
        this.name = clean(name);
        this.title = clean(title);
        this.measure = clean(measure);
        this.errorName = clean(errorName);
    }

    public int getVisible()
    {   return visible;
    }

    public Color getColour()
    {   return colour;
    }

    public String getName()
    {   return name;
    }

    public String getTitle()
    {   if(title == null)
            return name;
        return title;
    }

    public String getMeasure()
    {   return measure;
    }

    public String getErrorName()
    {   return errorName;
    }

    /**
     * Helper to ensure all read values are clean. No white-space padding, no
     *  empty strings (uses null instead)
     * @param value
     * @return
     */
    private String clean(String value)
    {   value = value.trim();
        if(value.length() == 0)
            return null;
        return value;
    }

}
