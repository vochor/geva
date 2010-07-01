/*
 * Operation.java
 *
 * Created on 08 March 2007, 02:34
 *
 */

package Operator.Operations;

import Individuals.Individual;
import Parameter.ParameterI;

import java.util.List;

/**
 * Operation performs actions on a List<Individual> or a single Individual 
 * @author Conor
 */
public interface Operation extends ParameterI {

    /**
     * Performs the operation on the list passed
     * @param operands operands to be operated on
     */
    public void doOperation(List<Individual> operands);

    /**
     * Performs the operation on an operand
     * @param operand operand to perform operation on
     */
    public void doOperation(Individual operand);    
}
