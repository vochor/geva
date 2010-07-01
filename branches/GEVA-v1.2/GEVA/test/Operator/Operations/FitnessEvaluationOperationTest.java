/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import FitnessEvaluation.PatternMatch.WordMatch;
import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Individuals.GEIndividual;
import Mapper.GEGrammar;
import Util.Constants;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class FitnessEvaluationOperationTest {

    public FitnessEvaluationOperationTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of doOperation method, of class FitnessEvaluationOperation.
     */
    @Test
    public void testDoOperation_Individual() {
        System.out.println("* FitnessEvaluationOperation: doOperation");
        GEIndividual operand = (GEIndividual)IndividualMaker.makeIndividual();
        FitnessEvaluationOperation instance = new FitnessEvaluationOperation(new WordMatch("aa"));
        instance.setPopulationSize(10);
        instance.doOperation(operand);
        assertEquals(operand.isEvaluated(), true);
        assertEquals(operand.isValid(), true);
        assertEquals(operand.isMapped(), true);
        assertEquals(instance.getFitnessCache().containsKey(((GEGrammar)operand.getMapper()).getName()), true);
//TODO Test private class CachItem and the fitness Caching
    }

    /**
     * Test of setProperties method, of class FitnessEvaluationOperation.
     */
    @Test
    public void testSetProperties() {
        System.out.println("* FitnessEvaluationOperation: setProperties");
//POPULATION_SIZE = 10
        Properties p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "10");
        FitnessEvaluationOperation instance = new FitnessEvaluationOperation(null);
        instance.setProperties(p);
        assertEquals(instance.getPopulationSize(), 10);
        assertEquals(instance.getOriginalPopulationSize(), 10);

    //POPULATION_SIZE = a
        p = GrammarCreator.getProperties();
        p.setProperty(Constants.POPULATION_SIZE, "a");
        instance = new FitnessEvaluationOperation(null);
        instance.setProperties(p);
        assertEquals(instance.getPopulationSize(), Integer.parseInt(Constants.DEFAULT_POPULATION_SIZE));
        assertEquals(instance.getOriginalPopulationSize(), 0);
}

}