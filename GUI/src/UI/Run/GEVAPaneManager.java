package UI.Run;

import java.awt.Component;

/**
 * Allow anything that inherits from GEVAPane to interact with the GEVARun
 *  window in which the pane is shown
 * @author eliott bartley
 */
public interface GEVAPaneManager
{   /**
     * Make the manager aware of this run pane
     * @param pane
     */
    public void addPane(GEVAPane pane, String title);
    /**
     * Get the pages tab that this pane is under to become selected.
     */
    public void viewPane(GEVAPane pane);
    /**
     * Get the page's tab that this pane is under to become selected. If no page
     *  tab has been added yet, it is added too. If this is called several times,
     *  only the first call is executed. All others are ignored. This allows
     *  viewPane to be called in the parsing loop that parses several similar
     *  lines, but should only cause the page to show when the first line is
     *  parsed (to show the user that parsing has begun), but not take control
     *  of viewing if another pane starts parsing something in the middle of the
     *  current pane's parsing. If a pane parses several types of data, and
     *  wants to show each one, it should specify a unique <var>reason</var> for
     *  each one. If it wants to force a show, it should call viewPane,
     *  without passing a reason (pass null). For example, GEVAOutputPane
     *  displays console messages and errors, and GEVAGraphPane shows data lines.
     *  Every line is parsed by GEVAOutputPane, but in general, it is not viewed
     *  by the user, so it calls viewPane each line it parses, but once
     *  GEVAGraphPane's data starts being read, it is more interesting, so
     *  GEVAGraphPane calls viewPane for each data line read. This is not
     *  then overridden by GEVAOutputPane's viewPane as it only actually did
     *  anything on its first call. However, if GEVAOutputPane parses an error
     *  line, the error is more important than the graph, but calling
     *  viewPane for this will not display, so instead, GEVAOutputPane can
     *  call viewPane with an "error" reason, or even just call viewPane
     *  with no reason, to force a show (as errors are probably important)
     */
    public void viewPane(GEVAPane pane, String reason);
    /**
     * Get whether this pane is currently being viewed
     */
    public boolean isViewingPane(GEVAPane pane);
    /**
     * Have this run pane add a tool to the run pane manager's toolbox. The run
     *  pane manager will only show tools for run panes that are visible to the
     *  user
     */
    public void addPaneTool(GEVAPane pane, Component component);
    /**
     * Have this run pane remove a tool from the run pane manager's toolbox
     */
    public void removePaneTool(GEVAPane pane, Component component);
    /**
     * Have this run pane add a status to the run pane manager's status bar. The
     *  run pane manager will show all status tools for all run panes regardless
     *  of whether that run pane is visible at the current time
     */
    public void addPaneStatus(GEVAPane pane, Component component);
    /**
     * Have this run pane remove a status from the run pane manager's status bar
     */
    public void removePaneStatus(GEVAPane pane, Component component);

}
