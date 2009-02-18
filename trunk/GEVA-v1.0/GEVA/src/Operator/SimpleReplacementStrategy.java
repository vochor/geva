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

package Operator;

import Util.Random.RandomNumberGenerator;
import Individuals.Populations.Population;
import Operator.Operations.Operation;
import Operator.Operations.ReplacementOperation;

/**
 * SimpleReplacementStrategy joins two populations. It has a ReplacementOperation
 * The ReplacementOperation is performed on the population before the incoming population is joined
 * @author Blip
 */
public class SimpleReplacementStrategy extends JoinOperator {
    
    protected ReplacementOperation replacementOperation;

    /**
     * Creates a new instance of SimpleReplacementStrategy
     * @param rng random number generator
     * @param incPop incomming population
     * @param rO replacement operation
     */
    public SimpleReplacementStrategy(RandomNumberGenerator rng, Population incPop, ReplacementOperation rO){
	super(rng, incPop);
        this.replacementOperation = rO;
    }

    /**
     * New instance
     */
    public SimpleReplacementStrategy(){
	super();
    }

    /**
     * A ReplacementOperation is performed on the original 
     * population before the incomigPopulation is joined.
     * Competition among the children if Selection Size is larger then replacement size
     */
    public void perform() {
        //System.out.println("ip:"+this.incomingPopulation);
        //System.out.println("op:"+this.population);
        if(this.incomingPopulation.size()>this.replacementOperation.getReplacementSize()) {
            int size = this.incomingPopulation.size()-this.replacementOperation.getReplacementSize();
            this.replacementOperation.doOperation(this.incomingPopulation.getAll(), size);
        }
        //System.out.println("t-ip:"+this.incomingPopulation);
        //Generational. Clear the original population
        if(this.incomingPopulation.size() == this.population.size()) {
            this.population.clear();
        } else {
            this.replacementOperation.doOperation(this.population.getAll());
        }
        //System.out.println("t-p:"+this.population);
        this.population.addAll(this.incomingPopulation);
        //System.out.println("p:"+this.population);
        this.incomingPopulation.clear();
        this.increaseAge(this.population.getAll());
    }

    public void setOperation(Operation op) {
	this.replacementOperation = (ReplacementOperation)op;
    }    

    public Operation getOperation() {
        return this.replacementOperation;
    }
    
}
