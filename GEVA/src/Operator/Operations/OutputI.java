package Operator.Operations;

import java.util.List;
import Individuals.Individual;
import Util.Statistics.IndividualCatcher;

/**
 * Interface for printing
 * @author Conor
 */
public interface OutputI {

    /**
     * Print information about operands. Chose if the should be to file.
     * @param operand list of individuals
     * @param toFile if printing should be done to file
     */
    public void print(List<Individual> operand, boolean toFile);

    public IndividualCatcher getBest(List<Individual> operand);
    
}
