/*
 * SantaFeAntTrailGr.java
 *
 * Created on May 29, 2007, 3:51 PM
 *
 */

package FitnessEvaluation.SantaFeAntTrail;

import FitnessEvaluation.InterpretedFitnessEvaluationBSF;
import Individuals.Phenotype;

import java.util.Properties;

/**
 * Class for running Santa Fe ant trail interpreted via BSF
 * @author erikhemberg
 */
public class SantaFeAntTrailBSF extends InterpretedFitnessEvaluationBSF {
    
    /** Creates a new instance of SantaFeAntTrailBSF */
    public SantaFeAntTrailBSF() {
    }

    /**
         * Create a header and a tail for the input string. Uses the groovy language
         * Creates a class that extends the Trail class. Inserts the input from
         * the phenotype. Adds a tail.
         * @param p the input to be evaluated
         * @return code
         */
        public String createCode(Phenotype p) {
            StringBuffer code = new StringBuffer();
            //Head
            code.append("package FitnessEvaluation.SantaFeAntTrail;\n");
            code.append("public class Test extends Trail {\n");
            code.append("\tpublic Test() {\n");
            code.append("\t\twhile(get_Energy() > 0) {");
            //Input
            code.append(p.getString());
            //Tail
            code.append("}\n\t}\n}\ntest = new Test();\ntest.getFitness();");
            //Evaluate
            return code.toString();
        }

    /**
     * Set properties
     * @param p properties
     */
    public void setProperties(Properties p) {
    }
    
}
