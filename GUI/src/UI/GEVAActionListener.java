package UI;

/**
 * Interface to be implemented by any class that wants to listen to
 *  GEVAControl events
 * @author eliottbartley
 */
public interface GEVAActionListener
{

    /**
     * Called when an event executes, such as LOAD/SAVE/DIRTY/VALID
     */
    boolean actionPerformed(GEVAActionEvent e);

}
