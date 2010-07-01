package UI.Run;

/**
 * Parse lines that relate to LSystems
 * @author eliott bartley
 */
public class GEVALSystemStreamParser
     extends GEVAStreamParser<GEVALSystemStreamParser.Event>
{

    private boolean isLSystem = false;

    protected void parseLine(Line line)
    {   int at;
        String temp;
        Event event;

        if(isLSystem == false
        && line.getLine().indexOf("fitness_function=") != -1
        && line.getLine().indexOf("LSystem") != -1)
            isLSystem = true;
        else
        if(isLSystem == true
        && line.getLine().indexOf("Rank:") != -1
        && line.getLine().indexOf("Fit:") != -1
        && (at = line.getLine().indexOf("Phenotype:")) != -1)
        {

            event = new Event();

            temp = line.getLine().substring(at + 10);

            at = temp.indexOf(" ");
            event.depth = Integer.parseInt(temp.substring(0, at));
            temp = temp.substring(at + 1);

            at = temp.indexOf(" ");
            event.angle = Float.parseFloat(temp.substring(0, at));
            event.grammar = temp.substring(at + 1);

            super.fireParserListener(event);

        }

    }

    /**
     * LSystem parsed event
     */
    public static class Event extends GEVAStreamParser.Event
    {   private String grammar;
        private int depth;
        private float angle;
        public String getGrammar()
        {   return grammar;
        }
        public int getDepth()
        {   return depth;
        }
        public float getAngle()
        {   return angle;
        }
    }

}
