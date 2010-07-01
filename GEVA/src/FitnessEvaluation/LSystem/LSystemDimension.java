package FitnessEvaluation.LSystem;

import FitnessEvaluation.FitnessFunction;
import Fractal.LSystem2FDBoxCounting;
import Individuals.Individual;
import Util.Constants;
import java.util.Properties;

/**
 * A fitness which tends towards a specified fractal dimension 
 * @author eliott bartley
 */
public class LSystemDimension implements FitnessFunction
{

    private double targetFractalDim;
    private int boxCountDivision;
    
    public void getFitness(Individual i)
    {

        String grammar = i.getPhenotype().getString().substring(6);
        String depth = i.getPhenotype().getString().substring(0, 1);
        String angle = i.getPhenotype().getString().substring(1, 6);
        double fractalDim = new LSystem2FDBoxCounting
        (   null,
            grammar,
            Integer.parseInt(depth),
            Float.parseFloat(angle),
            boxCountDivision
        ).calcFractalDimension();

        i.getFitness().setDouble
        (   Math.abs(targetFractalDim - fractalDim)
        );
        
    }

    public boolean canCache()
    {   return true;
    }

    public void setProperties(Properties p)
    {
        
        try
        {
            targetFractalDim = Double.parseDouble
            (   p.getProperty
                (   Constants.TARGET_FRACTAL_DIMENSION,
                    "1.666"
                )
            );
            System.out.format
            (   "%s=%.3f%n",
                Constants.TARGET_FRACTAL_DIMENSION,
                targetFractalDim
            );
        }
        catch(NumberFormatException e)
        {   targetFractalDim = 1.666;
            System.out.format
            (   "%s [%s] using default: %.3f%n",
                Constants.TARGET_FRACTAL_DIMENSION,
                e,
                targetFractalDim
            );
        }

        try
        {
            boxCountDivision = Integer.parseInt
            (   p.getProperty
                (   Constants.BOX_COUNT_DIVISION,
                    "512"
                )
            );
            System.out.format
            (   "%s=%d%n",
                Constants.BOX_COUNT_DIVISION,
                boxCountDivision
            );
        }
        catch(NumberFormatException e)
        {   boxCountDivision = 512;
            System.out.format
            (   "%s [%s] using default: %d%n",
                Constants.BOX_COUNT_DIVISION,
                e,
                boxCountDivision
            );
        }

    }

}
