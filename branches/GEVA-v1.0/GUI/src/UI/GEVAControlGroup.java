package UI;

import java.util.ArrayList;

/**
 * Class for storing list of controls in a group.
 * Is just an ArrayList, but could be more
 * @author eliottbartley
 */
public class GEVAControlGroup extends ArrayList<GEVAControl>
{

    private boolean hiding = false;
    
    public GEVAControlGroup() {}

    public boolean isHiding() {
	return this.hiding;
    }

    public void setHiding(boolean b) {
	this.hiding = b;
    }

}
