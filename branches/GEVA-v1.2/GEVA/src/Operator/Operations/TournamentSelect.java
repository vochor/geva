package Operator.Operations;

import Exceptions.BadParameterException;
import Individuals.FitnessPackage.Fitness;
import Individuals.Individual;
import Util.Constants;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * The operation of this class is tournament selection.
 * The individual with the best fitness from a randomly
 * selected tournament of size tournamentSize is cloned to the selected population.
 **/
public class TournamentSelect extends SelectionOperation implements Stochastic {
    
    protected RandomNumberGenerator rng;
    protected int tournamentSize;
    protected double pressureModifier; // p=1 for deterministic selection
    protected ArrayList<Fitness> tour;
    
    /** Creates a new instance of TournamentSelect
     * @param size size of selected population
     * @param tourSize size of tournament
     * @param rand random number generator
     */
    public TournamentSelect(int size, int tourSize, RandomNumberGenerator rand) {
        super(size);
        this.rng = rand;
        this.tournamentSize = tourSize;
        tour = new ArrayList<Fitness>(tourSize);
    }
    
    /** Creates a new instance of TournamentSelect
     * @param rand random number generator
     * @param p properties
     */
    public TournamentSelect(RandomNumberGenerator rand, Properties p) {
        super(p);
        setProperties(p);
        this.rng = rand;
        tour = new ArrayList<Fitness>(this.tournamentSize);
    }

    /**
     * New instantion
     */
    public TournamentSelect() {
        super();
        tour = new ArrayList<Fitness>(this.tournamentSize);
    }
    
    public void setProperties(Properties p) {
        super.setProperties(p);
        int value;
                
        String key;
        try {
            key = Constants.TOURNAMENT_SIZE;
            value = Integer.parseInt(p.getProperty(key));
            if(value < 1) {
                throw new BadParameterException(key);
            }
        } catch(Exception e) {
            value = 3;
            System.out.println(e+" using default: "+value);
        }
        this.tournamentSize = value;
    }
    
    public void doOperation(Individual operand) {}
    
    /**
     * Individuals from operands will be added to the selected population
     * if the win their tournament.
     * @param operands Individuals to be selected from
     **/
    public void doOperation(List<Individual> operands) {
        this.selectedPopulation.clear();
        while(this.selectedPopulation.size()<this.size){
            getTour(operands);
            selectFromTour();
        }
    }

    /**
     * Adds individual to the tournament by randomly selecting from
     * the operands untill the tounramentSize is reached.
     * @param operands Individuals that can be selected to the tournament
     **/
    public void getTour(List<Individual> operands) {
        tour.clear();
        int contestant;
        for(int i = 0;i<this.tournamentSize;i++) {
            contestant = this.rng.nextInt(operands.size());
            tour.add(operands.get(contestant).getFitness());
        }
    }
    
    /**
     * Select a winner from the tournament and add to the selected population.
     **/
    public void selectFromTour() {
	    Collections.sort(tour);
	    this.selectedPopulation.add(tour.get(0).getIndividual().clone());

    }

    public void setRNG(RandomNumberGenerator m) {
            this.rng =m;
    }

    public RandomNumberGenerator getRNG() {
        return this.rng;
    }

}

