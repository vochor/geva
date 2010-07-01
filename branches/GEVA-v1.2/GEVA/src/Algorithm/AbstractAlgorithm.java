package Algorithm;

/**
 * An Abstract algorithm class that should be useful for most algorithms.
 * Contains two pipelines:  
 * One pipeline for initialization. 
 * One pipeline for looping.
 */
public abstract class AbstractAlgorithm implements Algorithm {

    Pipeline init;
    Pipeline loop;

    /**
     * Constructor
     */
    AbstractAlgorithm() {
    }

    public abstract void step();
    
    public abstract void init();
    
    public abstract void run(int steps);

    public void setLoopPipeline(Pipeline loop) {
	this.loop = loop;
    }
    
    public Pipeline getLoopPipeline() {
	return this.loop;
    }

    public void setInitPipeline(Pipeline init) {
	this.init = init;
    }

    public Pipeline getInitPipeline() {
	return this.init;
    }
}