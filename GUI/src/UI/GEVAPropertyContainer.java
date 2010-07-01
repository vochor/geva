package UI;

/**
 * Base class for property containers. These are added to Page controls to give
 *  the page one or more logical groups of controls. Controls cannot be added
 *  directly to a page, and must be in a property control. To simulate adding
 *  controls directly to the page, the property container can be created with
 *  a null title. This will create the container, but not display it with any
 *  visible border. If the GEVAPropertyRowContainer is used, passing title of
 *  null shows no border, "" shows a small border, and "..." shows a title
 *  border
 * @author eliottbartly
 */
public abstract class GEVAPropertyContainer extends GEVAContainerControl
{

    public GEVAPropertyContainer
    (   GEVADirtyListener dirtyListener,
        GEVAPageContainer parent,
        String            type,
        String            title,
        String            comment
    ){  super(dirtyListener, parent, type, title, comment);
    }

}
