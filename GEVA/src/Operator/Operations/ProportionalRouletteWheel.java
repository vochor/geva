package Operator.Operations;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.*;
import Mapper.GEGrammar;
import Util.Random.MersenneTwisterFast;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;

import java.util.*;

/**
 * Roulette wheel selection based on fitness proprtional selection.
 * it uses assigns probabilities as 1/(1-fitness) where the smaller the
 * value the more likely it is to be selected
 **/
public class ProportionalRouletteWheel extends RouletteWheel implements Stochastic {

    /**
     * New instance
     * @param size size of selection
     * @param rng random number generator
     */
    public ProportionalRouletteWheel(int size, RandomNumberGenerator rng) {
        super(size, rng);
    }

    /**
     * New instance
     */
    public ProportionalRouletteWheel() {
        super();
    }

    /**
     * Min fitness is the best fitness.
     * Subtracts the fitness from the fitness sum and divides by the fitness sum
     * Store the accumulated probabilities in the accProbs array
     * @param operands Individuals to take into account
     ***/
    protected void calculateAccumulatedFitnessProbabilities(List<Individual> operands) {
        if (this.sumFit == 0) {
            return;
        }
        this.accProbs = new double[operands.size()];
        double[] tmpA = new double[accProbs.length];
        double tmp;
        double tmpSum = 0;
        for (int cnt = 0; cnt < operands.size(); cnt++) {
            tmp = operands.get(cnt).getFitness().getDouble();
            //this is to lessen any distortions for very small fitness values
            if(this.smallFit == true)
            {
                tmp= tmp * 1000;
            }
            tmp = 1/(tmp+1);
            tmpA[cnt] = tmp;
            tmpSum += tmp;
        }
  
        for (int cnt = 0; cnt < operands.size(); cnt++) {
            tmp = tmpA[cnt];
            tmp /= tmpSum;
            //System.out.println("tmp:"+tmp);
            if (cnt > 0) {
                accProbs[cnt] = accProbs[cnt - 1] + tmp;
            } else {
                accProbs[cnt] = tmp;
            }
            if (accProbs[cnt] > 1) {
                accProbs[cnt] = 1;
            }
        }
    }

    public static void main(String[] args) {
        int runs = 1;
        ArrayList<Double> aL = new ArrayList<Double>(runs * 2);
        for (int ii = 0; ii < runs; ii++) {
            int size = 2;
            MersenneTwisterFast rng = new MersenneTwisterFast();
            ProportionalRouletteWheel rws = new ProportionalRouletteWheel(size, rng);
            int popSize = 5;
            ArrayList<Individual> alI = new ArrayList<Individual>(popSize);
            GEIndividual ind;
            String grammarFile = System.getProperty("user.dir") + "/param/Grammar/sf_grammar.bnf";
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
            for (int i = 1; i < popSize; i++) {
                ind = new GEIndividual();
                //bf = new BasicFitness((double)rws.getRNG().nextInt(10), ind);
                bf = new BasicFitness((double) i, ind);
                ind.setFitness(bf);
                ind.setMapper(gram);
                ind.setGenotype(genotype);
                ind.setPhenotype(phenotype);
                alI.add(ind);
            }
            rws.doOperation(alI);
            for (int k = 0; k < rws.selectedPopulation.size(); k++) {
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