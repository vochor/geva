/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Operator.Operations;

import Individuals.Individual;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * This is an abstract class for creating Roulette wheel selection
 * methods with different distributions
 * @author jbyrne
 */
public abstract class RouletteWheel extends SelectionOperation implements Stochastic {

    protected RandomNumberGenerator rng;
    protected double minFit;
    protected double sumFit;
    protected boolean smallFit = true;
    protected double[] accProbs;

    public RouletteWheel(int size, RandomNumberGenerator rng) {
    super(size);
    this.rng = rng;
    }

    /**
    * New instance
    */
    public RouletteWheel() {
        super();
    }

    @Override
    public void setProperties(Properties p) {
        super.setProperties(p);
    }

    @Override
    public void doOperation(Individual operand) {
    }


    public void doOperation(List<Individual> operands) {
        rankPopulation(operands);
        calculateFitnessSum(operands);
        calculateAccumulatedFitnessProbabilities(operands);
        spinRoulette(operands);
    }

    /**
     * Rank the population
     * @param operands population
     */
    public void rankPopulation(List<Individual> operands) {
        Collections.sort(operands);
    }

    /**
     * Selects Indivudals from operand and adds to the selected population
     * until the selected population is full.
     * @param operands Individuals to be chosen form
     **/
    protected void spinRoulette(List<Individual> operands) {
        double prob;
        Individual selected;
        this.selectedPopulation.clear();

        while(this.selectedPopulation.size()<super.getSize()) {
            prob = rng.nextDouble();

            int cnt = 0;        
            while(cnt < operands.size() && this.accProbs[cnt] < prob) {
                cnt++;
            }
            if(cnt >= operands.size()) {
		//                System.out.println("Doh:"+cnt);
                cnt = operands.size() - 1; //If the selction with the roulette fails, take the last individual
            }
            selected = operands.get(cnt);   
            this.selectedPopulation.add(selected.clone());

        }
    }

    protected abstract void calculateAccumulatedFitnessProbabilities(List<Individual> operands);

     /**
     * Calculate the fitness sum.
     * Get the minimum fitness.
     * If fitness is NaN or Infinite Double.MAX_VAALUE is assigned
     * @param c List of individuals which fitness is taken into account
     **/
    protected void calculateFitnessSum(List<Individual> c) {
        double sum = 0;
        double tmp;
        Iterator<Individual> itI = c.iterator();
        this.minFit = Double.MAX_VALUE;
        while(itI.hasNext()) {
            tmp = itI.next().getFitness().getDouble();
            if (tmp > 1)
            {
                this.smallFit = false;
            }

            sum += tmp;
            if(this.minFit < tmp) {
                this.minFit = tmp;
            }
        }
        this.sumFit = sum;
    }

    public RandomNumberGenerator getRNG() {
        return this.rng;
    }

    public void setRNG(RandomNumberGenerator m) {
        this.rng = m;
    }


}
