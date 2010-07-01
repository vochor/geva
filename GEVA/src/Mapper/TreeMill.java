/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapper;

import Util.Constants;

/**
 *
 * @author erikhemberg
 */
public class TreeMill {

    public static DerivationTree getDerivationTree(final GEGrammar gegrammar) {
        final DerivationTree derivationTree;
        if(gegrammar.getDerivationString() == null) {
            throw new NullPointerException("GEGrammar.derivationTreeType is null. Use key -"+Constants.DERIVATION_TREE+" and value is the class name");
        }
        if(gegrammar.getDerivationString().equals(DerivationTree.class.getName())) {
            derivationTree = new DerivationTree(gegrammar, gegrammar.getGenotype());
        } 
        else if (gegrammar.getDerivationString().equals(ContextualDerivationTree.class.getName()))
        {
           derivationTree = new ContextualDerivationTree(gegrammar, gegrammar.getGenotype());
        }
        else
        {
           derivationTree = null;
           throw new IllegalArgumentException("Illegal derivation tree:"+gegrammar.getDerivationString());
        }
        
        return derivationTree;
    }

}
