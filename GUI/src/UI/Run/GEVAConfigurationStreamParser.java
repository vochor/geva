package UI.Run;

import Util.Constants;

/**
 * Parse lines that GEVA output about the configuration it is using
 * @author eliott bartley
 */
public class GEVAConfigurationStreamParser
     extends GEVAStreamParser<GEVAConfigurationStreamParser.Event>
{

    private static Event event = new Event();

    protected void parseLine(Line line)
    {   assert line.getStreamId() == GEVAStreamParser.ID_STD_OUT
             : "GEVAConfigurationStreamParser!=" + line.getStreamId();

        // Accumulate all configuration details without generating events for
        //  them, and then, after all are dealt with, generate one event passing
        //  them all together
        if(line.getLine().indexOf(Constants.GRAPH_DATA_GENERATIONS) != -1)
            event.setGenerations(Integer.parseInt(line.getLine().substring(12)));
        else
        // Only generate one event after all configuration details have been
        //  parsed. Unlike other parsers which generate events every time it
        //  reads something it understands. This means <var>event</var> is a
        //  class-global always-allocated value that is updated as details are
        //  parsed and should contain all the values by the time graph data
        //  begins to be output
        if(line.getLine().trim().equals(Constants.GRAPH_DATA_BEGIN) == true)
            super.fireParserListener(event);

    }

    /**
     * Store all the configuration details in one class. Only when all are
     *  parsed is the event sent on to the listeners
     */
    public static class Event extends GEVAStreamParser.Event
    {

        // TODO - add more configuration details as they're needed
        private int generations;

        /**
         * Get the number of generations GEVA will run for
         */
        public int getGenerations()
        {   return generations;
        }

        /**
         * Record the number of generations GEVA will run for
         */
        protected void setGenerations(int generations)
        {   this.generations = generations;
        }

    }

}
