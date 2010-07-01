/**
 * Mock class to test ContextFreeGrammar
 **/
package Mapper;

import java.io.*;
import Util.Enums;

public class ContextFreeGrammarMock extends ContextFreeGrammar {

    public ContextFreeGrammarMock() {
    }

    public ContextFreeGrammarMock(ContextFreeGrammar g) {
        super(g);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean genotype2Phenotype() {
        return true;
    }

    public int getUsedWraps() {
        return -1;
    }

    public int getUsedCodons() {
        return -1;
    }

    public String readBNFFileToString(final String file_name) {
        StringBuffer contents = new StringBuffer();
        final int bufferSize = 1024;
        String line;
        try {
            final File f = new File(file_name);
            final FileReader fr = new FileReader(f);
            final BufferedReader br = new BufferedReader(fr, bufferSize);
            while ((line = br.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
            br.close();
        } catch (IOException e) {
            System.err.println("IOException when opening grammar file: " + file_name + " " + e);
        }
        contents.append("\n");
        return contents.toString();
    }

    public Object getGenotype() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getPhenotype() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean phenotype2Genotype() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setGenotype(Object g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPhenotype(Object p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String getDerivationString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Probably not the right place for this method
     * A mock rule would be better
     * @return rule
     */
    public static Rule getTestRule() {
        Rule r = new Rule();
        Production p = new Production();
        Symbol lhs = new Symbol("<string>", Enums.SymbolType.NTSymbol);
        Symbol s1 = new Symbol("<letter>", Enums.SymbolType.NTSymbol);
        Symbol s2 = new Symbol("<string>", Enums.SymbolType.NTSymbol);
        Production p2 = new Production();
        //This should be an empty symbol according to the constructor
        r.setLHS(lhs);
        p.add(new Symbol(s1));
        p2.add(s1);
        p2.add(s2);
        r.add(p);
        r.add(p2);
        return r;
    }
}
