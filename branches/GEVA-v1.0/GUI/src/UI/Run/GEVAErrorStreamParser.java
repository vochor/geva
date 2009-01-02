package UI.Run;

/**
 * Parse lines from error out
 * @author eliott bartley
 */
public class GEVAErrorStreamParser
     extends GEVAStreamParser<GEVAErrorStreamParser.Event>
{

    /**
     * Fake a stream reader event causing all listeners to be notified of the
     *  <var>line</var> passed in as if it came from ID_ERR_OUT. Overridden
     *  to generate ID_ERR_OUT instead of ID_STD_OUT
     */
    @Override
    public void injectLine(String text)
    {   parseLine(new Line(new GEVAStreamReader.Event
        (   text,
            GEVAStreamReader.ID_ERR_OUT
        )));
    }

    protected void parseLine(Line line)
    {   assert line.getStreamId() == GEVAStreamParser.ID_ERR_OUT
             : "GEVAErrorStreamParser!=" + line.getStreamId();
        super.fireParserListener(new Event(line.getLine()));
    }

    /**
     * Unique empty type for overloading
     */
    public static class Event extends GEVAStreamParser.LineEvent
    {   public Event(String line) { super(line); }
    }

}
