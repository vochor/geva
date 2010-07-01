/*
 * InterpretedFitnessEvaluationGr.java
 *
 * Created on May 29, 2007, 12:09 PM
 *
 */

package FitnessEvaluation;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.Phenotype;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

/**
 * Interprets code using the BSF framework (http://jakarta.apache.org/bsf/)
 * @author erikhemberg
 */
public abstract class InterpretedFitnessEvaluationBSF extends InterpretedFitnessEvaluation {
    
    protected final BSFManager mngr;

    /** Creates a new instance of InterpretedFitnessEvaluationGr */
    public InterpretedFitnessEvaluationBSF() {
        mngr = new BSFManager();
    }

    /**
     * Create code by adding header and tail to the evolved input
     * @param p input
     * @return code
     */
    protected abstract String createCode(Phenotype p);

    /**
     * Create a header and a tail for the input string. Uses the groovy language
     * Inserts the input from
     * the phenotype. Adds a tail. Call eval in the BSF manager to interpret the class.
     * @param p the input to be evaluated
     * @return fitness of the input
     */
    public double runFile(Phenotype p) {
        double fit = BasicFitness.DEFAULT_FITNESS;
        final String lng = "groovy";
        final String src = "Ind";
        final int lineNo = 0;
        final int columnNo = 0;
        final String code = this.createCode(p);
        //Evaluate
        try {
            Object o = this.mngr.eval(lng,src,lineNo,columnNo,code);
            if(o instanceof Double) {
                fit = (Double)o;
            } else {
                if(o instanceof Integer) {
                    fit = (Integer)o;
                }
            }
        } catch (BSFException ex) {
            System.err.println("Exception evaluating code using bsf:"+ex);
            ex.printStackTrace();
        }
        return fit;
    }
}
