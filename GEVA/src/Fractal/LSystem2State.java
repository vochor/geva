package Fractal;

/**
 * Track and update the state of the LSystem. This is Cursor in the other
 *  four implemtations
 * @author eliott bartley
 */
public class LSystem2State
{

    private float x;
    private float y;
    /**
     * The angle that this state currently points, in radians
     */
    private float angle;

    /**
     * Create a new state at position (0, 0) and at angle 270 degrees, or
     *  pointing vertically up, in screen co-ordinates, i.e. calling move
     *  would move up the screen
     */
    public LSystem2State()
    {   x = 0;
        y = 0;
        angle = -(float)Math.PI / 2;
    }

    /**
     * Create a new state cloned from another
     * @param state The state to clone
     */
    public LSystem2State(LSystem2State state)
    {   x = state.getX();
        y = state.getY();
        angle = state.getAngle();
    }

    /**
     * Move the state's co-ordinates one step along the facing angle
     */
    void move()
    {   x += Math.cos(angle);
        y += Math.sin(angle);
    }

    /**
     * Turn the state's angle by the distance amount
     * @param distance The angle by which to turn the state, in radians
     */
    void turn(float distance)
    {   angle += distance;
        if(distance > 0)
            while(angle >= 2 * Math.PI)
                angle -= 2 * Math.PI;
        else
            while(angle < 0)
                angle += 2 * Math.PI;
    }

    /**
     * Get the x co-ordinate value of the state
     * @return the x co-ordinate value of the state
     */
    public float getX()
    {   return x;
    }

    /**
     * Get the y co-ordinate value of the state
     * @return the y co-ordinate value of the state
     */
    public float getY()
    {   return y;
    }

    /**
     * Get the angle of the state in radians. This value will always be in
     *  the range [0..2pi)
     * @return the angle of the state in radians
     */
    public float getAngle()
    {   return angle;
    }

}
