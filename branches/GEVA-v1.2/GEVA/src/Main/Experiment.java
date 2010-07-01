package Main;

import Util.Constants;

/**
 * Wrapper class for loading different Main.Run files
 */
public class Experiment {

    public Experiment(){}

    /**
     * Get the class name to load from the commandline and load via reflection
     * @param args commandline arguments
     * @return loaded class
     */
    private State getMain(String[] args) {
        State s = null;
        try {
	    String key = Constants.EXPERIMENT;
	    String className = null;
	    //Get the name of the file to run from the command line
            for(int i=0;i<args.length;i++) {                
                if(key.equals(args[i].substring(1))) {
                    className = args[i+1];
                }
            }
            Class clazz = Class.forName(className);
            s = (State) clazz.newInstance();
            return s;
        }  catch (Exception e) {
            System.out.println(this.getClass().getName()+".getMain():Exception loading experiment: "+e);
            System.out.println("Main class not specified. -main_class flag needed.");
            return s;
        }
    }

    /**
     * Create a new Experiment instance and call the getMain function
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Experiment exp = new Experiment();
        State s = exp.getMain(args);
        s.experiment(args);
	System.exit(0);
    }
}
