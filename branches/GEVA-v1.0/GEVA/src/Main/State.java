package Main;

import Algorithm.Algorithm;
import Util.Random.RandomNumberGenerator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * State allows loading, saving, and setup of the algorithm's state.
 **/
public abstract class State implements Serializable {
    
    protected Algorithm algorithm;
    protected RandomNumberGenerator rng;
    
    public State() {
    }

    public abstract void setup(String[] args);

    public Algorithm getAlgorithm() {
        return algorithm;
    }
    
    /**
     * Save the State
     */
    @SuppressWarnings({"IOResourceOpenedButNotSafelyClosed"})
    public void save() {
        FileOutputStream fOut ;
        ObjectOutputStream oOut;
        try{
            fOut= new FileOutputStream("saveFile");
            oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Load a State
     * @param fileName Name of file to load
     **/
    @SuppressWarnings({"IOResourceOpenedButNotSafelyClosed"})
    public void load(String fileName) {
        FileInputStream fIn;
        ObjectInputStream oIn;
        try{
            fIn= new FileInputStream(fileName);
            oIn = new ObjectInputStream(fIn);
            
            State emp = (State) oIn.readObject();
            System.out.println(emp);
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
}