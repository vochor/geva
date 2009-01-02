package UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

/**
 * Pages that appear in the Book control. Each page added to book add a new tab
 * Includes functionallity for showing scroll bars if the page is too big for
 * the UI
 * @author eliottbartley
 */
public class GEVAPageContainer extends GEVAContainerControl
{

    private JPanel guiPage;
    private JScrollPane guiPane;

    public GEVAPageContainer
    (   GEVADirtyListener dirtyListener,
        GEVABookContainer parent,
        String            title,
        String            comment
    ){  super(dirtyListener, parent, "page", title, comment);
        guiPane = new JScrollPane();
        guiPage = new Panel();
        guiPage.setLayout(new BoxLayout(guiPage, BoxLayout.Y_AXIS));
        guiPane.getViewport().add(guiPage);
        parent.add(this);
    }

    @Override
    public void add(GEVAControl control)
    {   super.add(control);
        guiPage.add(control.getComponent());
    }

    public Component getComponent(int index)
    {   return guiPane;
    }

    public int countComponents() { return 1; }

}

/**
 * Panel that sits in a ScrollPane and won't show horizontal scrollbars, always
 *  sizing to fit width, but will show vertical scrollbars when height is too
 *  small
 * @author eliott bartley
 */
class Panel extends JPanel implements Scrollable
{

    public Dimension getPreferredScrollableViewportSize()
    {   return super.getPreferredSize();
    }

    public int getScrollableBlockIncrement
    (   Rectangle visibleRect,
        int orientation,
        int direction
    ){  return (int)(getScrollableIncrement(visibleRect, orientation) * 0.9);
    }

    public boolean getScrollableTracksViewportHeight()
    {   return false;
    }

    public boolean getScrollableTracksViewportWidth()
    {   return true;
    }

    public int getScrollableUnitIncrement
    (   Rectangle visibleRect,
        int orientation,
        int direction
    ){  return getScrollableIncrement(visibleRect, orientation) / 10;
    }

    private int getScrollableIncrement
    (   Rectangle visibleRect,
        int orientation
    ){  if(orientation == SwingConstants.VERTICAL)
            return visibleRect.height;
        else
            return visibleRect.width;
    }

}
