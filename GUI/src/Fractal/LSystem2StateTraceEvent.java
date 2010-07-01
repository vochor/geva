package Fractal;

/**
 * Listener event details
 * @author eliott bartley
 */
class LSystem2StateTraceEvent
{   /**
     * The current state after a move is made
     */
    public LSystem2State state;
    /**
     * The previous state after a move is made. This is null if the move
     *  shouldn't render (f) and is non-null if the move should render (F)
     */
    public LSystem2State trace;
    /**
     * User specified data passed in when the listener was set up
     */
    public Object user;
}
