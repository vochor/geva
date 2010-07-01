/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Individuals.FitnessPackage;

import Individuals.Individual;

/**
 *
 * @author erikhemberg
 */
public class DoubleFitnessMock implements DoubleFitness{

    double value;

    public DoubleFitnessMock(double d) {
        this.setDouble(d);
    }

    public int compareTo(Fitness o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDistance(DoubleFitness f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getDouble() {
        return this.value;
    }

    public Individual getIndividual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getInt() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getMaxDoubleFitness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMaxIntFitness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getMinDoubleFitness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getMinIntFitness() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDefault() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDouble(double f) {
        this.value = f;
    }

    public void setIndividual(Individual i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setInt(int f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMaxDoubleFitness(double d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMaxIntFitness(int d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMinDoubleFitness(double d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setMinIntFitness(int d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
