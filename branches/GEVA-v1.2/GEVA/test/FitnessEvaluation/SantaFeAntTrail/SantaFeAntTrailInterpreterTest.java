package FitnessEvaluation.SantaFeAntTrail;

import Individuals.GEIndividual;
import Individuals.Phenotype;
import Mapper.Symbol;
import Util.Enums;
import Helpers.IndividualMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class SantaFeAntTrailInterpreterTest {

    public SantaFeAntTrailInterpreterTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testGetTrail() {
        System.out.println("* SantaFeAntTrailInterpreter: getTrail");
//SFA
        SantaFeAntTrailInterpreter instance = new SantaFeAntTrailInterpreter();
        String trail_type = "FitnessEvaluation.SantaFeAntTrail.Trail";
        Trail trail = instance.getTrail(trail_type);
        assertEquals(true, trail.getClass().getName().equals(trail_type));

     //SM
        instance = new SantaFeAntTrailInterpreter();
        trail_type = "FitnessEvaluation.SantaFeAntTrail.SanMateoTrail";
        trail = instance.getTrail(trail_type);
        assertEquals(true, trail.getClass().getName().equals(trail_type));

     //LA
        instance = new SantaFeAntTrailInterpreter();
        trail_type = "FitnessEvaluation.SantaFeAntTrail.LosAltosTrail";
        trail = instance.getTrail(trail_type);
        assertEquals(true, trail.getClass().getName().equals(trail_type));

        //Exception
        instance = new SantaFeAntTrailInterpreter();
        trail_type = "FitnessEvaluation.SantaFeAntTrail.Grr";
        trail = null;
        try {
            trail = instance.getTrail(trail_type);
        } catch(IllegalArgumentException e) {
            assertTrue(true);
        }
        assertEquals(true, trail == null);
    }

    /**
     * Test of getFitness method, of class SantaFeAntTrailInterpreter.
     */
    @Test
    public void testGetFitness() {
        System.out.println("*SantaFeAntTrailInterpreter: getFitness");
        GEIndividual individual = IndividualMaker.makeIndividual();
        Phenotype p = IndividualMaker.getPhenotype("move();");
        individual.setPhenotype(p);
        SantaFeAntTrailInterpreter instance = new SantaFeAntTrailInterpreter();
        instance.setTrail_type("FitnessEvaluation.SantaFeAntTrail.Trail");
        instance.getFitness(individual);
        assertEquals((int) individual.getFitness().getDouble(), 86);

        individual = IndividualMaker.makeIndividual();
        p.clear();
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF,Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.MOVE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.LEFT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.RIGHT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        individual.setPhenotype(p);
        instance = new SantaFeAntTrailInterpreter();
        instance.setTrail_type("FitnessEvaluation.SantaFeAntTrail.Trail");
        instance.getFitness(individual);
        assertEquals((int) individual.getFitness().getDouble(), 78);

        individual = IndividualMaker.makeIndividual();
        p.clear();
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF,Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.MOVE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.LEFT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.MOVE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.RIGHT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        individual.setPhenotype(p);
        instance = new SantaFeAntTrailInterpreter();
        instance.setTrail_type("FitnessEvaluation.SantaFeAntTrail.Trail");
        instance.getFitness(individual);
        assertEquals((int) individual.getFitness().getDouble(), 72);

individual = IndividualMaker.makeIndividual();
        p.clear();
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF,Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.MOVE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF,Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.RIGHT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.LEFT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.RIGHT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.IF,Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.RIGHT, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.ELSE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.MOVE, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        p.add(new Symbol(SantaFeAntTrailInterpreter.END_IF, Enums.SymbolType.TSymbol));
        individual.setPhenotype(p);
        instance = new SantaFeAntTrailInterpreter();
        instance.setTrail_type("FitnessEvaluation.SantaFeAntTrail.Trail");
        instance.getFitness(individual);
        assertEquals((int) individual.getFitness().getDouble(), 85);

    }
}