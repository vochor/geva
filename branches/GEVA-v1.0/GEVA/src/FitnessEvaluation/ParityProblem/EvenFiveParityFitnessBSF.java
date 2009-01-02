/*
 * EvenFiveParityFitness.java
 *
 * Created on den 12 mars 2007, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package FitnessEvaluation.ParityProblem;

import FitnessEvaluation.InterpretedFitnessEvaluationBSF;
import Individuals.Phenotype;

import java.util.Properties;

/**
 * Evaluates the fitness for the EvenFiveParityExperiment.java.
 * Uses the help class EvenFiveParity.java
 * Interprets the code using BSF
 * @author jonatan
 */
public class EvenFiveParityFitnessBSF extends InterpretedFitnessEvaluationBSF {
    
    /** Creates a new instance of EvenFiveParityFitness */
    public EvenFiveParityFitnessBSF() {
        
    }

    /**
     * Set properties
     * @param p properties
     */
    public void setProperties(Properties p) {
    }

    /**
     * Create a header and a tail for the input string. Uses the groovy language
     * Creates a class that extends the EvenFiveParBSF class. Inserts the input from
     * the phenotype. Adds a tail.
     * @param p the input to be evaluated
     * @return fitness of the input
     */
    public String createCode(Phenotype p) {
        StringBuffer code = new StringBuffer();
        //Head
        code.append("package FitnessEvaluation.ParityProblem;\n");
        code.append("public class Test extends EvenFiveParBSF {\n");
        code.append("\tpublic Test() {}\n");
        code.append("\tpublic int expr(int d0, int d1, int d2, int d3, int d4) {\n\t\treturn ");
        //Input
        code.append(p.getString());
        //Tail
        code.append("\n\t}\n}\ntest = new Test();\ntest.getFitness();");
        //Evaluate
        return code.toString();
    }
}
