package FitnessEvaluation.LSystem;

import FitnessEvaluation.FitnessFunction;
import Individuals.Individual;
import java.util.Properties;

/**
 *
 * @author eliott bartley
 */
public class LSystemInitialiser implements FitnessFunction
{

    public void getFitness(Individual i)
    {
        // Only set the fitness if it has not been set before. Every time one
        //  is picked, it's fitness is reduced, so one that is picked often is
        //  more likely to win
        if(i.getFitness().getDouble() == 0)
            i.getFitness().setDouble(1);
    }

    public boolean canCache()
    {   return false;
    }

    public void setProperties(Properties p)
    {

    }

}
