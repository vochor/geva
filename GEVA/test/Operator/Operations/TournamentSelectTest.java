/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Helpers.GrammarCreator;
import Helpers.IndividualMaker;
import Individuals.Individual;
import Util.Constants;
import Util.Random.MersenneTwisterFast;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class TournamentSelectTest {

    List<Individual> operands;
    public TournamentSelectTest() {
    }

    @Before
    public void setUp() {
        operands = new ArrayList<Individual>();
        for (int i = 10; i > 0; i--) {
            operands.add(IndividualMaker.makeIndividual());
            operands.get(10-i).getFitness().setDouble(i);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setProperties method, of class TournamentSelect.
     */
    @Test
    public void testSetProperties() {
        System.out.println("* TournamentSelect: setProperties");
        Properties p = GrammarCreator.getProperties();
        p.setProperty(Constants.TOURNAMENT_SIZE, "4");
        TournamentSelect instance = new TournamentSelect();
        instance.setProperties(p);
        assertEquals(instance.tournamentSize, 4);

        //TOURNAMENT_SIZE <1
        p.setProperty(Constants.TOURNAMENT_SIZE, "0");
        instance = new TournamentSelect();
        instance.setProperties(p);
        assertEquals(instance.tournamentSize, 3);
    }

    /**
     * Test of doOperation method, of class TournamentSelect.
     */
    @Test
    public void testDoOperation_List() {
        System.out.println("* TournamentSelect: doOperation");
        TournamentSelect instance = new TournamentSelect(2,2,new MersenneTwisterFast(0));
        instance.doOperation(operands);
        assertEquals(instance.getSelectedPopulation().size(), 2);
        assertEquals(instance.getSelectedPopulation().get(0).getFitness().getDouble(),1.0,0.001);
        assertEquals(instance.getSelectedPopulation().get(1).getFitness().getDouble(), 4.0, 0.001);
        assertNotSame(instance.getSelectedPopulation().get(0),operands.get(9));
        assertNotSame(instance.getSelectedPopulation().get(1),operands.get(6));
    }

    /**
     * Test of getTour method, of class TournamentSelect.
     */
    @Test
    public void testGetTour() {
        System.out.println("* TournamentSelect: getTour");
        TournamentSelect instance = new TournamentSelect(2,2,new MersenneTwisterFast(0));
        instance.getTour(operands);
        assertEquals(instance.tour.size(), 2);
        assertEquals(instance.tour.get(0).getDouble(),8.0,0.001);
        assertEquals(instance.tour.get(1).getDouble(), 1.0, 0.001);
        assertSame(instance.tour.get(0),operands.get(2).getFitness());
        assertSame(instance.tour.get(1),operands.get(9).getFitness());
    }

    /**
     * Test of selectFromTour method, of class TournamentSelect.
     */
    @Test
    public void testSelectFromTour() {
        System.out.println("* TournamentSelect: selectFromTour");
        TournamentSelect instance = new TournamentSelect(2,2,new MersenneTwisterFast(0));
        instance.getTour(operands);
        instance.selectFromTour();
        assertEquals(instance.getSelectedPopulation().size(), 1);
        assertEquals(instance.getSelectedPopulation().get(0).getFitness().getDouble(), 1.0, 0.001);
        assertNotSame(instance.getSelectedPopulation().get(0),operands.get(9));
    }

}