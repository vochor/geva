/*
 * SantaFeAntTrailGr.java
 *
 * Created on May 29, 2007, 3:51 PM
 *
 */
package FitnessEvaluation.SantaFeAntTrail;

import FitnessEvaluation.InterpretedFitnessEvaluationBSF;
import Individuals.Phenotype;
import Mapper.Symbol;
import java.util.Properties;

/**
 * Class for running Santa Fe ant trail interpreted via BSF
 * @author erikhemberg
 */
public class SantaFeAntTrailBSF extends InterpretedFitnessEvaluationBSF {

    boolean setLineBreaks;
    private String trailType;

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
        String phenotypeString;
        if (setLineBreaks) {
            phenotypeString = setLineBreaks(p);
        } else {
            phenotypeString = p.getString();
        }
        //Head
        code.append("package FitnessEvaluation.SantaFeAntTrail;\n");
        code.append("public class Test extends Trail {\n");
        code.append("\tpublic Test() {\n");
        code.append("\t\twhile(get_Energy() > 0) {\n");
        //Input
        code.append(phenotypeString);
        //Tail
        code.append("\n}\n\t}\n}\ntest = new Test();\ntest.getFitness();");
        //Evaluate
        return code.toString();
    }

    private String setLineBreaks(Phenotype p) {
        StringBuffer sb = new StringBuffer();
        for (Symbol sym : p) {
            String str = sym.getSymbolString();
            if (str.startsWith("}")) {
                sb.append("\n");
            }
            sb.append(str);
            if (str.endsWith("{") || str.startsWith("}")) {
                sb.append("\n");
            }
        }
        //	System.out.println(this.getClass().getName()+".setLineBreaks(.) phen:\n"+p+"\n sb:\n"+sb);
        return sb.toString();
    }

    public void setProperties(Properties p) {
    }

}
