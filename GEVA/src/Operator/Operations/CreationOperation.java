/*
 * Operation.java
 *
 * Created on 08 March 2007, 02:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Operator.Operations;

import Individuals.Individual;

/**
 * Interface for creation of individuals
 * @author Conor
 */
public interface CreationOperation extends Operation
{
    /**
     * Creates an individual
     * @return created individual
     */
    public Individual createIndividual();
}
