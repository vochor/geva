/*
Grammatical Evolution in Java
Release: GEVA-v1.0.zip
Copyright (C) 2008 Michael O'Neill, Erik Hemberg, Anthony Brabazon, Conor Gilligan 
Contributors Patrick Middleburgh, Eliott Bartley, Jonathan Hugosson, Jeff Wrigh

Separate licences for asm, bsf, antlr, groovy, jscheme, commons-logging, jsci is included in the lib folder. 
Separate licence for rieps is included in src/com folder.

This licence refers to GEVA-v1.0.

This software is distributed under the terms of the GNU General Public License.


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package Operator.Operations;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.*;
import Mapper.GEGrammar;
import Util.Random.MersenneTwisterFast;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;

import java.util.*;

/**
 * Roulette wheel selection based on fitness proprtional selection
 **/
public class RouletteWheelFitProp extends SelectionOperation implements Stochastic {
    
    protected RandomNumberGenerator rng;
    protected double minFit;
    protected double sumFit;
    protected double[] accProbs;

    /**
     * New instance
     * @param size size of selection
     * @param rng random number generator
     */
    public RouletteWheelFitProp(int size, RandomNumberGenerator rng) {
        super(size);
        this.rng = rng;
    }

    /**
     * New instance
     */
    public RouletteWheelFitProp() {
        super();
    }
    
    @Override
    public void setProperties(Properties p) {
        super.setProperties(p);
    }
    
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
     * until the selected populationis full.
     * @param operands Individuals to be chosen form
     **/
    private void spinRoulette(List<Individual> operands) {
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
    
    /**
     * Min fitness is the best fitness.
     * Subtracts the fitness from the fitness sum and divides by the fitness sum
     * Store the accumulated probabilities in the accProbs array
     * @param operands Individuals to take into account
     ***/
    private void calculateAccumulatedFitnessProbabilities(List<Individual> operands) {
        if(this.sumFit == 0) {
            return;
        }
        this.accProbs = new double[operands.size()];
        double[] tmpA = new double[accProbs.length];
        double tmp;
        double tmpSum = 0;
        for(int cnt=0;cnt<operands.size();cnt++) {
            tmp = operands.get(cnt).getFitness().getDouble();
            tmp = this.sumFit - tmp ;
            tmpA[cnt] = tmp;
            tmpSum += tmp;
        }
        this.sumFit = tmpSum;
        for(int cnt=0;cnt<operands.size();cnt++) {
            tmp = tmpA[cnt];
            tmp /= this.sumFit;
            //System.out.println("tmp:"+tmp);
            if(cnt>0) {
                accProbs[cnt] = accProbs[cnt-1] + tmp;
            } else {
                accProbs[cnt] = tmp;
            }
            if(accProbs[cnt] > 1) {
                accProbs[cnt] = 1;
            }
        }
    }

    /**
     * Calculate the fitness sum.
     * Get the minimum fitness.
     * If fitness is NaN or Infinite Double.MAX_VAALUE is assigned
     * @param c List of individuals which fitness is taken into account
     **/
    private void calculateFitnessSum(List<Individual> c) {
        double sum = 0;
        double tmp;
        Iterator<Individual> itI = c.iterator();
        this.minFit = Double.MAX_VALUE;
        while(itI.hasNext()) {
            tmp = itI.next().getFitness().getDouble();
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
    
    public static void main(String[] args) {
        int runs = 1;
        ArrayList<Double> aL = new ArrayList<Double>(runs*2);
        for(int ii = 0;ii<runs;ii++){
            int size = 2;
            MersenneTwisterFast rng = new MersenneTwisterFast();
            RouletteWheelFitProp rws = new RouletteWheelFitProp(size, rng);
            int popSize = 5;
            ArrayList<Individual> alI = new ArrayList<Individual>(popSize);
            GEIndividual ind;
            String grammarFile = System.getProperty("user.dir")+"/param/Grammar/sf_grammar.bnf";
            GEGrammar gram = new GEGrammar(grammarFile);
            int maxChromSize = 10000; //Arrghh. testing purpose??!! Hack Hack Hack
            Phenotype phenotype = new Phenotype();
            Genotype genotype = new Genotype(1);
            GEChromosome chrom = new GEChromosome(maxChromSize);
            int[] codons = new int[maxChromSize];
            chrom.setAll(codons);
            genotype.add(chrom);
            gram.setPhenotype(phenotype);
            BasicFitness bf;
            for(int i =1;i<popSize; i++) {
                ind = new GEIndividual();
                //bf = new BasicFitness((double)rws.getRNG().nextInt(10), ind);
                bf = new BasicFitness((double)i, ind);
                ind.setFitness(bf);
                ind.setMapper(gram);
                ind.setGenotype(genotype);
                ind.setPhenotype(phenotype);
                alI.add(ind);
            }
            rws.doOperation(alI);
            for(int k=0;k<rws.selectedPopulation.size();k++) {
                aL.add(rws.selectedPopulation.get(k).getFitness().getDouble());
            }
            rws.selectedPopulation.clear();
            
            
        }
        StringBuffer s = new StringBuffer();
        for (Double anAL : aL) {
            s.append(anAL);
            s.append(" ");
        }
        s.append("\n");
        System.out.println(s.toString());
        
    }
    
}