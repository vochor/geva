/*
 * ReplacementOperation.java
 *
 * Created on March 15, 2007, 4:38 PM
 *
 */
package Operator.Operations;

import Exceptions.BadParameterException;
import Individuals.FitnessPackage.Fitness;
import Individuals.Individual;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import Util.Constants;

/**
 * EliteReplacementOperation removes the worst
 * @author Blip
 */
public class EliteReplacementOperation implements Operation {

    private int eliteSize;

    /** Creates a new instance of ReplacementOperation
     * @param size size
     */
    public EliteReplacementOperation(int size) {
        this.eliteSize = size;
    }

    /** Creates a new instance of ReplacementOperation
     * @param p properties
     */
    public EliteReplacementOperation(Properties p) {
        this.setProperties(p);
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        int value = 0;
        String key = Constants.ELITE_SIZE;
        value = Integer.parseInt(p.getProperty(key, "0"));
        if (value == -1) {//-1 indicates elites is turned off
            value = 0;
        }
        this.eliteSize = value;
    }

    public void doOperation(Individual operand) {
    }

    /**
     * Sort ascending and remove the worst individuals
     * @param operand individuals to sort
     */
    public void doOperation(List<Individual> operand) {
        Collections.sort(operand);
        int cnt = operand.size();
        while (cnt > this.eliteSize && cnt > 0) {
            cnt--;
            operand.remove(cnt);
        }
    }

    public int getEliteSize() {
        return eliteSize;
    }

    public void printHelp(Fitness[] fA) {
        String s = "";
        for (Fitness aFA : fA) {
            s += aFA.getDouble() + ",";
        }
        System.out.println("sorted elites:" + s);
    //    System.out.println("best_fit:"+fA[0].getDouble()+" "+fA[0].getIndividual().getPhenotypeString(0));
    }
}
