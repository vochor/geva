package UI;

import Util.Constants;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Window;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JWindow;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Display splash screen with GEVA logo
 * @author eliottbartley
 */
public class GEVASplash extends JWindow
{

    public GEVASplash(Window owner)
    {   super(owner);
        Dimension screen;

        try
        {

            screen = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds
            (   (screen.width - 400) / 2,
                (screen.height - 300) / 2,
                400,
                300
            );
            add(new JLabel(new ImageIcon(ClassLoader.getSystemResource
            (   Constants.IMG_SPLASH
            ))));
            new Timer().schedule(this.new GEVASplashTimer(), 2000);
            setVisible(true);

        }
        catch(HeadlessException e)
        {   /* If splash cannot be loaded, simply don't show it */
        }

    }

    public void bye()
    {   dispose();
    }

    private class GEVASplashTimer extends TimerTask
    {   public void run()
        {   bye();
        }
    }

}
