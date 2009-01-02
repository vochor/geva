package UI;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

/**
 * Property container that shows the name value property as name left-of value.
 * Name sits left of value, and takes maximum preferred height of name or value
 *  so several name/value controls can be added to the container and each one
 *  will sit below the prevously added control
 * @author eliottbartley
 */
public class GEVAPropertyRowContainer extends GEVAPropertyContainer
{

    private JPanel guiProperties;
    private int row = 0;

    public GEVAPropertyRowContainer
    (   GEVADirtyListener dirtyListener,
        GEVAPageContainer parent,
        String            title,
        String            comment
    ){  super(dirtyListener, parent, "PropertyRowContainer", title, comment);
        guiProperties = new JPanel();
        guiProperties.setLayout(new GridBagLayout());
        guiProperties.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
        if(title != null)
            guiProperties.setBorder(BorderFactory.createTitledBorder(title));
        parent.add(this);
    }

    @Override
    public void add(GEVAControl control)
    {   super.add(control);
        guiProperties.add
        (   control.getComponent(0),
            new GridBagConstraints
            (   0, row,
                1, 1,
                0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0
            )
        );
        guiProperties.add
        (   control.getComponent(1),
            new GridBagConstraints
            (   1, row,
                1, 1,
                1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0
            )
        );
        row++;
        guiProperties.add
        (   control.getComponent(2),
            new GridBagConstraints
            (   1, row,
                1, 1,
                1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1),
                0, 0
            )
        );
        row++;
    }

    public Component getComponent(int index)
    {   return guiProperties;
    }

    public int countComponents() { return 1; }

}
