/*
 * HelloWorldGr.java
 *
 * Created on May 29, 2007, 12:08 PM
 *
 */

package FitnessEvaluation.HelloWorld;

import FitnessEvaluation.InterpretedFitnessEvaluationBSF;
import Individuals.Phenotype;

import java.util.Properties;
import Util.Constants;

/**
 * Class for executing the HelloWorld example using BSF. Not the fastest.
 *
 * @author erikhemberg
 */
public class HelloWorldBSF extends InterpretedFitnessEvaluationBSF {
    
    /** Creates a new instance of HelloWorldBSF */
    public HelloWorldBSF() {
    }

    /**
     * Create a header and a tail for the input string. Uses the groovy language
     * Creates a class that extends the WorldWriter class. Inserts the input from
     * the phenotype. Adds a tail.
     * @param p the input
     * @return code
     */

    protected String createCode(Phenotype p) {
        StringBuffer code = new StringBuffer();
        //Head
	// Set package for code to be generated
        code.append("package FitnessEvaluation.HelloWorld;\n"); 
	// Chose the class that the code should extend
        code.append("public class Test extends WorldWriter {\n");
	// Create the constructor for the new class
        code.append("\tpublic Test() {\n");
        code.append("\t\t");
        //Input
        code.append(p.getString());
        //Tail
        code.append("\n");
	code.append("\t}\n"); // End constructor
	code.append("}\n"); //End class
	// Create an object of the newly created class
	code.append("test = new Test()\n");
	// Call calculate fitness to evaluate the object
	code.append("test.calculateFitness()");
        //System.out.println(code);
	return code.toString();
    }

    /**
     * Set properties
     * @param p properties
     */
    public void setProperties(Properties p) {
        String key = Constants.WORDMATCH_WORD;
        String value;
	value = p.getProperty(key,"geva");
	WorldWriter.s = value;
    }

    
}
