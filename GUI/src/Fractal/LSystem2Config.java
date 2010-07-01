package Fractal;

import java.util.ArrayList;

/**
 * Track information about each LSystem displayed to the user in guiComp
 * @author eliott bartley
 */
public class LSystem2Config
{

    public static class Indexes extends ArrayList<Integer> { }

    /**
     * Because all LSystems are coalesced before display, when the user selects
     *  one, it must relate back to all the orignal LSystems that were
     *  coalsesced. This keeps a list of all the LSystems (with their original
     *  index) that are the same as this one
     */
    public Indexes indexes = new Indexes();
    /**
     * The phenotype of this LSystem's grammar
     */
    public String grammar;
    /**
     * The phenotype of this LSystem's depth
     */
    public int depth;
    /**
     * The phenotype of this LSystem's angle
     */
    public float angle;
    /**
     * The fitness of this LSystem, multiplied by .99 each time it is selected
     *  in LSystemSelect
     */
    public double fitness;
    /**
     * Set to true if this LSystem is picked as a parent in guiComp
     */
    public boolean select;
    /**
     * Set to true if this LSystem has been selected to die
     */
    public boolean purge;

    public LSystem2Config
    (   int      index,
        String grammar,
        int      depth,
        float    angle,
        double fitness
    ){  this.indexes.add(index);
        this.grammar = grammar;
        this.depth   = depth;
        this.angle   = angle;
        this.fitness = fitness;
        this.select  = false;
        this.purge   = false;
    }

    public interface SelectAction
    {   public void selectPerformed();
    }

}
