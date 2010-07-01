package UI.Run;

import Fractal.LSystem2Control;
import UI.Run.GEVALSystemStreamParser.Event;
import Util.I18N;
import java.awt.BorderLayout;

/**
 * Pane that shows final LSystem
 * @author eliott bartley
 */
public class GEVALSystemPane
     extends GEVAPane
  implements GEVALSystemStreamParser.Listener
             <GEVALSystemStreamParser.Event>

{

    public GEVALSystemPane(GEVAPaneManager paneManager)
    {   super(paneManager, I18N.get("ui.run.lsys.name"));
        super.setLayout(new BorderLayout());
    }

    public void lineParsed(Event event)
    {   LSystem2Control guiLSystem;
        super.viewMe("result");
        guiLSystem = new LSystem2Control
        (   event.getGrammar(),
            event.getDepth(),
            event.getAngle(),
            null
        );
        super.add(guiLSystem);

    }
    public void streamParsed() { }

}
