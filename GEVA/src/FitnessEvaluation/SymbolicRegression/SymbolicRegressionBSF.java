/*
 * SymbolicRegression.java
 *
 * Created on den 12 februari 2007, 13:02
 *
 */

package FitnessEvaluation.SymbolicRegression;

import FitnessEvaluation.InterpretedFitnessEvaluationBSF;
import Individuals.Phenotype;

import java.util.Properties;

/**
 * Evaluates the fitness for the SymbolicRegressionExperiment class. The help class SymRegFunk
 * is used to evaluate the arithmetic expressions.
 * @author jonatan
 */
public class SymbolicRegressionBSF extends InterpretedFitnessEvaluationBSF {
    
    /** Creates a new instance of SymbolicRegression */
    public SymbolicRegressionBSF() {
    }
    
    public void setProperties(Properties p) {
    }
    
    public String createCode(Phenotype p) {
        StringBuffer code = new StringBuffer();
        //Header
        code.append("package FitnessEvaluation.SymbolicRegression;\n");
        code.append("public class Test extends SymRegFunkBSF {\n");
        code.append("\tpublic Test() {}\n");
        code.append("\tpublic double expr(double X) {\n\t\treturn ");
        //Input
        code.append(p.getString());
        //Tail
        code.append("\n\t}\n}\n");
	code.append("test = new Test()\ntest.getFitness()");
        return code.toString();
    }
    
}
