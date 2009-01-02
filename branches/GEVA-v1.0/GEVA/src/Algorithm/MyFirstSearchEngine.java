package Algorithm;

/**
 * Contains the module pipelines used to perform the algorithm.
 * A simple Implementation of a concrete algorithm. May be good for
 * testing module performance and stability. 
 */
public class MyFirstSearchEngine extends AbstractAlgorithm {
    
    /**
     * Constructor
     */
    public MyFirstSearchEngine() {
        
    }
    
    /**
     * Step the loop pipeline.
     */
    public void step() {
        this.loop.step();
    }
    
    /**
     * Step the init pipeline
     */
    public void init() {
        this.init.step();
    }
    
    /**
     * Iterate <CODE>step()</CODE>
     * @param steps iterations of step()
     */
    public void run(int steps) {
        for(int i=0; i<steps; i++) {
            this.step();
            //System.out.println("--Gen:"+i+"----------");
        }
    }
    
}