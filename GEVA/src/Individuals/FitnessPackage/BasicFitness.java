/*
 * BasicFitness.java
 *
 * Created on 06 March 2007, 16:14
 *
 */

package Individuals.FitnessPackage;

import Individuals.Individual;


/**
 * BasicFitness. Class holding a basic fitness measurement. Has a fitness value
 * as well as a reference to the individual the fitness refers to.
 * @author Conor
 */
public class BasicFitness implements Fitness {
    
    public static final double DEFAULT_FITNESS = 100000000.0;
    private static double Min_Double = 0.0;
    private static double Max_Double = DEFAULT_FITNESS;
    private static int Min_Int = 0;
    private static int Max_Int = (int)DEFAULT_FITNESS;
    private double value;
    private Individual individual;
    
    /** Creates a new instance of BasicFitness */
    public BasicFitness() {
        this.value = 0;
    }

    /**
     * Creates new instance of BasicFitness
     * @param f fitness value
     * @param i reference to an individual
     */
    public BasicFitness(double f, Individual i) {
        this.value = f;
        this.individual = i;
    }

    /**
     * Creates new instance of BasicFitness
     * @param i reference to an individual
     */
    public BasicFitness(Individual i) {
        this.individual = i;
    }

    /**
     * Compare the Fitness ascending
     *
     * @param o fitness to compare to
     * @return value (-1,0,1) of comparison
     */
    public int compareTo(Fitness o) {
        double dO = o.getDouble();
        double dT = this.value;
        if(Double.isNaN(dO) || Double.isInfinite(dO)) {
            dO = BasicFitness.Max_Double;
        }
        if(Double.isNaN(dT) || Double.isInfinite(dT)) {
            dT = BasicFitness.Max_Double;
        }
        if(dT < dO) {
            return -1;
        }
        if(dT > dO) {
            return 1;
        } else {
            return 0;
        }
    }

    public double getMaxDoubleFitness() {
        return BasicFitness.Max_Double;
    }

    public void setMaxDoubleFitness(double d) {
        BasicFitness.Max_Double = d;
    }

    public double getMinDoubleFitness() {
        return BasicFitness.Min_Double;
    }

    public void setMinDoubleFitness(double d) {
        BasicFitness.Min_Double = d;
    }

    public int getMaxIntFitness() {
        return BasicFitness.Max_Int;
    }

    public void setMaxIntFitness(int d) {
        BasicFitness.Max_Int = d;
    }

    public int getMinIntFitness() {
        return BasicFitness.Min_Int;
    }

    public void setMinIntFitness(int d) {
        BasicFitness.Min_Int = d;
    }
    
    public void setDefault() {
        value = BasicFitness.DEFAULT_FITNESS;
    }
    
    public double getDefaultFitness() {
        return BasicFitness.DEFAULT_FITNESS;
    }
    
    public double getDouble() {
        return this.value;
    }
    
    public double getDistance(DoubleFitness f) {
        return this.value - f.getDouble();
    }
    
    public Individual getIndividual() {
        return this.individual;
    }
    
    public void setIndividual(Individual i) {
        this.individual  = i;
    }
    
    public void setDouble(double f) {
	if(Double.isNaN(f) || Double.isInfinite(f)) {
	    f = BasicFitness.Max_Double;
	}
        this.value = f;
    }
    
    public int getInt() {
        return (int)this.value;
    }
    
    public void setInt(int f) {
        this.value = (float)f;
    }
       
}
