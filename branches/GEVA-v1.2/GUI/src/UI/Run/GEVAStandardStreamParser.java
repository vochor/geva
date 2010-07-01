package UI.Run;

/**
 * Parse lines from standard out
 * @author eliott bartley
 */
public class GEVAStandardStreamParser
     extends GEVAStreamParser<GEVAStandardStreamParser.Event>
{

    protected void parseLine(Line line)
    {   GEVAGraphStreamParser.Extension extension
            = (GEVAGraphStreamParser.Extension)line.getExtension
            (   GEVAGraphStreamParser.EXT_GRAPH
            );

        // Don't output lines that are labels or lines
        if(extension == null || extension.isGraphable() == false)
            super.fireParserListener(new Event(line.getLine()));
    }

    /**
     * Unique empty type for overloading
     */
    public static class Event extends GEVAStreamParser.LineEvent
    {   public Event(String line) { super(line); }
    }

}
