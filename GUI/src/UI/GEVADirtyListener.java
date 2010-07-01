package UI;

/**
 * Allow a GUI to listen to all its properties when they are modified and become
 *  dirty allowing the GUI to enable a save button or whatnot
 * @author eliott bartley
 */
public interface GEVADirtyListener
{   /**
     * Called when modifications were made that need to be saved
     */
    public void setDirty();
    /**
     * Called when modifications will be made, but will or will not need to be
     *  saved. Although, just not calling setDirty is a solution to prevent a
     *  change needing saving, this is more general, saying that not only are
     *  future changes by the caller not be saved, but also, all other controls
     *  that can make modifications as a reaction to the caller changing.
     *  setDirtyable(false/true) calls should wrap as small a section of code as
     *  possible, as everything that can make modifications will lose their
     *  ability to flag that they need to save. e.g.<br/>
     *  setDirtyable(false);<br/>
     *  // Making a change that doesn't alter the meaning is a good time<br/>
     *  //  to disable flagging a change that needs to save.<br/>
     *  formatValue();<br/>
     *  setDirtyable(true);
     * @param dirtyable pass true if future calls to setDirty are because of a
     *  modification that needs to be saved. pass false if future calls to
     *  setDirty are because of a modification that doesn't need to be saved
     */
    public void setDirtyable(boolean dirtyable);
    /**
     * Get whether calling setDirty() will actually set the state as dirty (true)
     *  or not (false)
     * @return whether calling setDirty() will actually set the state as dirty
     */
    public boolean canDirty();
}
