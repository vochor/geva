/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Individuals.Individual;
import Helpers.IndividualMaker;
import Helpers.JUnitHelper;
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
public class ProportionalRouletteWheelTest {

    Individual ind1 = IndividualMaker.makeIndividual();
    Individual ind2 = IndividualMaker.makeIndividual();
    Individual ind3 = IndividualMaker.makeIndividual();
    Individual ind4 = IndividualMaker.makeIndividual();
    Individual ind5 = IndividualMaker.makeIndividual();
    Individual ind6 = IndividualMaker.makeIndividual();
    List<Individual> individuals = new ArrayList();
    List<Individual> individuals2 = new ArrayList();

    public ProportionalRouletteWheelTest() {
    }
 

    @Before
    public void setUp() {
        ind1.getFitness().setDouble(5);
        individuals.add(ind1);
        ind2.getFitness().setDouble(10);
        individuals.add(ind2);
        ind3.getFitness().setDouble(15);
        individuals.add(ind3);
        ind4.getFitness().setDouble(.2);
        individuals2.add(ind4);
        ind5.getFitness().setDouble(.3);
        individuals2.add(ind5);
        ind6.getFitness().setDouble(.5);
        individuals2.add(ind6);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateAccumulatedFitnessProbabilities method, of class ProportionalRouletteWheel.
     */
    @Test
    public void testCalculateAccumulatedFitnessProbabilities() {
        System.out.println("calculateAccumulatedFitnessProbabilities");
        ProportionalRouletteWheel instance = new ProportionalRouletteWheel();
        double[] expResults = new double[]{0.5207100591715976,0.8047337278106509,1.0};
        instance.rankPopulation(individuals);
        instance.calculateFitnessSum(individuals);
        assertEquals(false,instance.smallFit);
        assertEquals(30,instance.sumFit,9e-16);

        instance.calculateAccumulatedFitnessProbabilities(individuals);
        JUnitHelper.checkArrays(expResults,instance.accProbs);

        ProportionalRouletteWheel instance2 = new ProportionalRouletteWheel();
        instance2.rankPopulation(individuals2);
        instance2.calculateFitnessSum(individuals2);
        expResults = new double[] {0.48333189103950924,0.806088402996125,1.0};
        assertEquals(1,instance2.sumFit,9e-16);
        assertEquals(true,instance2.smallFit);
        instance2.calculateAccumulatedFitnessProbabilities(individuals2);
        JUnitHelper.checkArrays(expResults,instance2.accProbs);

    }
}