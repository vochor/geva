package Individuals.Populations;

import Individuals.GEIndividual;
import Helpers.IndividualMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * SimplePopulation is a wrapoper method for ArrayList that implements the
 * Population interface.
 * @author erikhemberg
 */
public class SimplePopulationTest {

    Population population;

    public SimplePopulationTest() {
    }

    @Before
    public void setUp() {
        population = new SimplePopulation();
        final int populationSize = 4;
        for (int i = 0; i < populationSize; i++) {
            GEIndividual individual = IndividualMaker.makeIndividual();
            individual.getFitness().setDouble(populationSize - i);
            population.add(individual);
        }
    }

    /**
     * Test of sort method, of class SimplePopulation.
     */
    @Test
    public void testSort() {
        System.out.println("*SimplePopulationTest: sort");
        double[] expected = new double[population.size()];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i + 1;
        }
        population.sort();
        for (int i = 0; i < population.size(); i++) {
            assertEquals(population.get(i).getFitness().getDouble(), expected[i], 0.00001);
        }
    }

    /**
     * Test of toString method, of class SimplePopulation.
     */
    @Test
    public void testToString() {
        System.out.println("*SimplePopulationTest: toString");
        String expResult = "4.00,3.00,2.00,1.00";
        String result = population.toString();
        assertEquals(true, expResult.equals(result));
    }
}