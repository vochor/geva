package Operator.Operations;

import Individuals.FitnessPackage.Fitness;
import Individuals.GEIndividual;
import Individuals.Individual;
import Individuals.Populations.SimplePopulation;
import Util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Class for selection of elites.
 **/
public class EliteOperationSelection extends SelectionOperation  {
    
    private boolean evaluate_elites;

    /**
     * New instance
     * @param size size of elites
     */
    public EliteOperationSelection(int size){
        super(size);
    }

    /**
     * New instance
     * @param p properties
     */
    public EliteOperationSelection(Properties p){
        super();
        setProperties(p);
    }
    
    public void setProperties(Properties p) {
        int value =0;
        String key = Constants.ELITE_SIZE;
	value = Integer.parseInt(p.getProperty(key,"0"));
        this.size = value;
        String valueS;
        key = Constants.EVALUATE_ELITES;
        try {
            valueS = p.getProperty(key);
            if(valueS == null ) {
		valueS = Constants.FALSE;
            }
        } catch(Exception e) {
            valueS = Constants.FALSE;
            System.out.println(e+" using default: "+valueS);
        }
        this.evaluate_elites = valueS.equals(Constants.TRUE);
        this.selectedPopulation = new SimplePopulation(this.size);
    }
    
    public void doOperation(Individual operand) {
    }
    
    /**
     * Ranks the population. Takes out size number of individuals and adds
     * to the selectedPopulation.
     * @param operands Individuals to select from
     **/
    public void doOperation(List<Individual> operands) {
        Fitness[] fA=rankPopulation(operands);
        int cnt = 0;
        while(cnt < this.size && cnt < operands.size()){
            //Avoid duplicates
            if(!this.selectedPopulation.contains(fA[cnt].getIndividual()) && fA[cnt].getIndividual().isValid()) {
                Individual ind = fA[cnt].getIndividual().clone();
                //		System.out.println("org:\t"+fA[cnt].getIndividual().getGenotype().hashCode());
                //		System.out.println("new:\t"+ind.getGenotype().hashCode());
                //Set individual as valid
                if(!this.evaluate_elites) {
                    ind.setEvaluated(fA[cnt].getIndividual().isEvaluated());
                    ind.setValid(fA[cnt].getIndividual().isValid());
                    ind.setAge(fA[cnt].getIndividual().getAge());
                    ((GEIndividual)ind).setMapped(((GEIndividual)(fA[cnt].getIndividual())).isMapped());
                    ((GEIndividual)ind).setUsedCodons(((GEIndividual)(fA[cnt].getIndividual())).getUsedCodons());
                }
                this.selectedPopulation.add(ind);
            }
            cnt++;
        }
        //System.out.println("E:"+this.selectedPopulation);

    }

    /**
     * Helper function to rank the poulation in ascending order.
     * @param operands List of Individuals to rank
     * @return An ordered Fitness array
     **/
    Fitness[] rankPopulation(List<Individual> operands){
        Fitness[] fAt = new Fitness[operands.size()];
        
        //System.out.print("EliteRank org:");
        for(int i=0;i<fAt.length;i++) {
            fAt[i] = operands.get(i).getFitness();
            //System.out.print(fAt[i].getDouble()+",");
        }
        //System.out.println();
        //Sort ascending
        Arrays.sort(fAt);
        return fAt;
    }
    
}
