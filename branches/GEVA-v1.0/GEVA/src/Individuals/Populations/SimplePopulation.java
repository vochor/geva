/*
 * SimplePopulation.java
 *
 * Created on 08 March 2007, 03:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Individuals.Populations;

import Individuals.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Population using an array list structure for storing the
 * individuals. The functions are wrappers for the ArrayList<Individual> structure.
 * @author Conor
 */
public class SimplePopulation implements Population {
    ArrayList<Individual> indies;
    //Used for testing some classes
    /**
     * Creates a new instance of SimplePopulation
     */
    public SimplePopulation() {
        indies = new ArrayList<Individual>();
    }
    
    public SimplePopulation(int size) {
        indies = new ArrayList<Individual>(size);
    }
    
    public void add(Individual i) {
        indies.add(i);
    }
    
    public void sort() {
    }
    
    public Iterator<Individual> iterator() {
        return indies.iterator();
    }
    
    public int size() {
        return indies.size();
    }
    
    public void addAll(Collection<Individual> immigrants) {
        for (Individual immigrant : immigrants) {
            indies.add(immigrant);
        }
        
    }
    
    public void addAll(Population immigrants) {
        Iterator<Individual> indIt = immigrants.iterator();
        while(indIt.hasNext()) {
            indies.add(indIt.next());
        }
        
    }
    
    public List<Individual> getAll() {
        return indies;
    }
    
    public boolean contains(AbstractIndividual individual) {
        return indies.contains(individual);
    }
    
    public String toString() {
        String s = "";
        Individual ind;
        Iterator<Individual> iIt = indies.iterator();
        DecimalFormat df = new DecimalFormat("#0.00");
        while(iIt.hasNext()) {
            ind = iIt.next();
            String f = df.format(ind.getFitness().getDouble());
            s += f+",";
        }
        return s;
    }
    
    public boolean contains(Individual individual) {
        return this.indies.contains(individual);
    }
    
    public Individual get(int index) {
        return this.indies.get(index);
    }
    
    public void clear() {
        this.indies.clear();
    }
    
    public void remove(Individual ind) {
        this.indies.remove(ind);
    }
    
}
