/*
Grammatical Evolution in Java
Release: GEVA-v1.0.zip
Copyright (C) 2008 Michael O'Neill, Erik Hemberg, Anthony Brabazon, Conor Gilligan 
Contributors Patrick Middleburgh, Eliott Bartley, Jonathan Hugosson, Jeff Wrigh

Separate licences for asm, bsf, antlr, groovy, jscheme, commons-logging, jsci is included in the lib folder. 
Separate licence for rieps is included in src/com folder.

This licence refers to GEVA-v1.0.

This software is distributed under the terms of the GNU General Public License.


This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * MutationOperation.java
 *
 * Created on March 15, 2007, 4:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Operator.Operations;

import Exceptions.BadParameterException;
import Util.Random.Stochastic;
import Util.Random.RandomNumberGenerator;
import Util.Constants;

import java.util.List;
import Individuals.Individual;
import java.util.Properties;

/**
 * Abstract class for mutaion operation.
 * @author Blip
 */
public abstract class MutationOperation implements Stochastic,Operation {
    
    protected double probability;
    protected RandomNumberGenerator rng;

    /** Creates a new instance of MutationOperation
     * @param prob mutation probability
     * @param rng random number generator
     */
    public MutationOperation(double prob,RandomNumberGenerator rng) {
        this.probability = prob;
        this.rng = rng;
    }
    
    /** Creates a new instance of MutationOperation
     * @param rng random number generator
     * @param p properties
     */
    public MutationOperation(RandomNumberGenerator rng, Properties p) {
        this.setProperties(p);
        this.rng = rng;
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
        double value  ;
        try {
            String key = Constants.MUTATION_PROBABILITY;
            value = Double.parseDouble(p.getProperty(key));
            if(value < 0.0 || value > 1.0) {
                throw new BadParameterException(key);
            }
        } catch(Exception e) {
            value = 0.01;
            System.out.println(e+" using default: "+value);
        }
        this.probability = value;
    }

    public abstract void doOperation(Individual opernad);
    
    public abstract void doOperation(List<Individual> opernad);
    
    public void setRNG(RandomNumberGenerator m) {
        this.rng = m;
    }
    
    public RandomNumberGenerator getRNG() {
        return this.rng;
    }
    
}
