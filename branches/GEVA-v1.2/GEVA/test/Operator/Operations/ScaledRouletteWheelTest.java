/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Helpers.IndividualMaker;
import Helpers.JUnitHelper;
import Individuals.Individual;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbyrne
 */
public class ScaledRouletteWheelTest {

    Individual ind1 = IndividualMaker.makeIndividual();
    Individual ind2 = IndividualMaker.makeIndividual();
    Individual ind3 = IndividualMaker.makeIndividual();
    List<Individual> individuals = new ArrayList();



    public ScaledRouletteWheelTest() {

    }

    @Before
    public void setUp() {
        ind1.getFitness().setDouble(5);
        individuals.add(ind1);
        ind2.getFitness().setDouble(10);
        individuals.add(ind2);
        ind3.getFitness().setDouble(15);
        individuals.add(ind3);


    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateAccumulatedFitnessProbabilities method, of class ScaledRouletteWheel.
     */
    @Test
    public void testCalculateAccumulatedFitnessProbabilities() {
        System.out.println("calculateAccumulatedFitnessProbabilities");
        ScaledRouletteWheel instance = new ScaledRouletteWheel();
        instance.rankPopulation(individuals);
        instance.calculateFitnessSum(individuals);
        double[] expResults = new double[] {0.4166666666666667,0.75,1.0};
        assertEquals(30,instance.sumFit,9e-16);

        instance.calculateAccumulatedFitnessProbabilities(individuals);      
        JUnitHelper.checkArrays(expResults,instance.accProbs);
        assertEquals(30,instance.sumFit,9e-16);


    }

}