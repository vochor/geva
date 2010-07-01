/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator;

import Helpers.IndividualMaker;
import Individuals.Individual;
import Individuals.Populations.Population;
import Individuals.Populations.SimplePopulation;
import Operator.Operations.EliteReplacementOperation;
import Operator.Operations.Operation;
import Operator.Operations.ReplacementOperation;
import Operator.Operations.TournamentSelect;
import Util.Random.MersenneTwisterFast;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class SimpleReplacementStrategyTest {

    public SimpleReplacementStrategyTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of perform method, of class SimpleReplacementStrategy.
     */
    @Test
    public void testPerform() {
        System.out.println("* SimpleReplacementStrategy: perform");
        //population < incomingPopulation
        int populationSize = 10;
        int incomingpopulationSize = 15;
        List<Individual> operands = new ArrayList<Individual>();
        for (int i = incomingpopulationSize; i > 0; i--) {
            operands.add(IndividualMaker.makeIndividual());
            operands.get(incomingpopulationSize-i).getFitness().setDouble(i+populationSize/2);
        }
        Population incommingPopulation = new SimplePopulation();
        incommingPopulation.addAll(operands);
        List<Individual> operands2 = new ArrayList<Individual>();
        for (int i = populationSize; i > 0; i--) {
            operands2.add(IndividualMaker.makeIndividual());
            operands2.get(populationSize-i).getFitness().setDouble(i);
        }
        Population population = new SimplePopulation();
        population.addAll(operands2);
        SimpleReplacementStrategy instance = new SimpleReplacementStrategy();
        instance.setOperation(new ReplacementOperation(populationSize));
        instance.setPopulation(population);
        instance.setIncomingPopulation(incommingPopulation);
        instance.perform();
        assertEquals(instance.population.size(), populationSize);
        assertEquals(instance.incomingPopulation.size(), 0);
        assertEquals(instance.population.get(0).getAge(),1);
        Individual[] pA = instance.population.getAll().toArray(new Individual[0]);
        Individual[] ipA = operands.subList(incomingpopulationSize-populationSize,incomingpopulationSize).toArray(new Individual[0]);
        assertArrayEquals(pA, ipA);

        //population = incommingPopulation
        populationSize = 10;
        incomingpopulationSize = 10;
        operands = new ArrayList<Individual>();
        for (int i = incomingpopulationSize; i > 0; i--) {
            operands.add(IndividualMaker.makeIndividual());
            operands.get(incomingpopulationSize-i).getFitness().setDouble(i+populationSize/2);
        }
        incommingPopulation = new SimplePopulation();
        incommingPopulation.addAll(operands);
        operands2 = new ArrayList<Individual>();
        for (int i = populationSize; i > 0; i--) {
            operands2.add(IndividualMaker.makeIndividual());
            operands2.get(populationSize-i).getFitness().setDouble(i);
        }
        population = new SimplePopulation();
        population.addAll(operands2);
        instance = new SimpleReplacementStrategy();
        instance.setOperation(new ReplacementOperation(populationSize));
        instance.setPopulation(population);
        instance.setIncomingPopulation(incommingPopulation);
        instance.perform();
        assertEquals(instance.population.size(), populationSize);
        assertEquals(instance.incomingPopulation.size(), 0);
        assertEquals(instance.population.get(0).getAge(),1);
        pA = instance.population.getAll().toArray(new Individual[0]);
        ipA = operands.subList(incomingpopulationSize-populationSize,incomingpopulationSize).toArray(new Individual[0]);
        assertArrayEquals(pA, ipA);

        //population > incommingPopulation
        populationSize = 15;
        incomingpopulationSize = 10;
        operands = new ArrayList<Individual>();
        for (int i = incomingpopulationSize; i > 0; i--) {
            operands.add(IndividualMaker.makeIndividual());
            operands.get(incomingpopulationSize-i).getFitness().setDouble(i+populationSize/2);
        }
        incommingPopulation = new SimplePopulation();
        incommingPopulation.addAll(operands);
        operands2 = new ArrayList<Individual>();
        for (int i = populationSize; i > 0; i--) {
            operands2.add(IndividualMaker.makeIndividual());
            operands2.get(populationSize-i).getFitness().setDouble(i);
        }
        population = new SimplePopulation();
        population.addAll(operands2);
        instance = new SimpleReplacementStrategy();
        instance.setOperation(new ReplacementOperation(populationSize));
        instance.setPopulation(population);
        instance.setIncomingPopulation(incommingPopulation);
        instance.perform();
        assertEquals(instance.population.size(), incomingpopulationSize);
        assertEquals(instance.incomingPopulation.size(), 0);
        assertEquals(instance.population.get(0).getAge(),1);
        pA = instance.population.getAll().toArray(new Individual[0]);
        ipA = operands.subList(0,incomingpopulationSize).toArray(new Individual[0]);
        assertArrayEquals(pA, ipA);
    }

    /**
     * Test of setOperation method, of class SimpleReplacementStrategy.
     */
    @Test
    public void testSetOperation() {
        System.out.println("* SimpleReplacementStrategy: setOperation");
        Operation op = new ReplacementOperation(1);
        SimpleReplacementStrategy instance = new SimpleReplacementStrategy();
        instance.setOperation(op);
        assertEquals(true, instance.replacementOperation instanceof ReplacementOperation);
    }

}