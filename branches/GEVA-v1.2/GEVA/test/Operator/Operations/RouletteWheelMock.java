/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Operator.Operations;

import Individuals.Individual;
import Util.Random.RandomNumberGenerator;
import java.util.List;

/**
 *
 * @author jbyrne
 */
public class RouletteWheelMock extends RouletteWheel {

    RouletteWheelMock() {
        super();
    }

    RouletteWheelMock(int i, RandomNumberGenerator rNG) {
        super(i, rNG);
    }

    @Override
    protected void calculateAccumulatedFitnessProbabilities(List<Individual> operands) {
        this.accProbs= new double[]{.17,.33,.5};
    }
}
