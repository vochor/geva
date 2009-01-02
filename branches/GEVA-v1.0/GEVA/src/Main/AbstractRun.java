/*
 * AbstractRun.java
 *
 * Created as ParameterizedState on June 12, 2007, 2:58 PM
 * Renamed to AbstractRun Nov 27 2008.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Main;

import Algorithm.AbstractAlgorithm;
import Exceptions.BadParameterException;
import FitnessEvaluation.FitnessFunction;
import Individuals.Populations.Population;
import Mapper.GEGrammar;
import Operator.*;
import Operator.Operations.*;
import Util.Random.RandomNumberGenerator;
import Util.Random.Stochastic;
import Util.Statistics.StatCatcher;
import Util.Constants;

import java.io.*;
import java.util.*;
import java.text.MessageFormat;

/**
 * AbstractRun class. Read the parameters. Some classes are dynamically loaded.
 * @author erik
 */
public abstract class AbstractRun extends State {

    /**
     * Creates a new instance of AbstractRun
     */
    public AbstractRun() {
    }

    protected String propertiesFilePath;
    protected Initialiser initialiser;
    protected Properties properties;
    protected String stdOut;
    protected String stdErr;
    Collector collector;

    /**
     * Setup the algorithm. Read the properties. Create the modules(Operators) and operations
     * @param args arguments
     */
    public abstract void setup(String[] args);
    
    
    /**
     * Load the fitness class according to the parameters
     * @param p Properties
     * @return FitnessFunction
     */
    protected FitnessFunction getFitnessFunction(Properties p) {
        FitnessFunction fitnessFunction = null;
        String className;
        String key = Constants.FITNESS_FUNCTION;
        try {
            className = p.getProperty(key);
            if (className == null) {
                throw new BadParameterException(key);
            }
            Class clazz = Class.forName(className);
            fitnessFunction = (FitnessFunction) clazz.newInstance();
            fitnessFunction.setProperties(p);
	    Class[] interfaces = clazz.getInterfaces();
	    for(int i = 0; i<interfaces.length; i++) {
		if(interfaces[i].getName().equals(Stochastic.class.getName())) {
		    ((Stochastic)fitnessFunction).setRNG(this.rng);
		}
	    }
        }  catch (Exception e) {
            System.out.println("Exception: "+e);
        }
        return fitnessFunction;
    }
    
    
    /**
     * Load and initialise the initialiser class according to the parameters
     * Defualt initialiser is the RandomInitialiser.
     * To add other initialisers expand the if-statement with another clause
     * @param g GEGrammar
     * @param rng RandomNumberGenerator
     * @param p Properties
     * @return Intialiser
     */
    protected Initialiser getInitialiser(GEGrammar g, RandomNumberGenerator rng, Properties p) {
        String className;
        String key = Constants.INITIALISER;
        try {
            className = p.getProperty(key);
            if (className == null) {
                throw new BadParameterException(key);
            }
            Class clazz = Class.forName(className);
            initialiser = (Initialiser) clazz.newInstance();
            // For RampedFullGrowInitialiser
            if (clazz.getName().equals(RampedFullGrowInitialiser.class.getName())) {
                CreationOperation fullInitialiser = new FullInitialiser(rng, g, p);
                CreationOperation growInitialiser = new GrowInitialiser(rng, g, p);
                ArrayList<CreationOperation> opL = new ArrayList<CreationOperation>();
                opL.add(fullInitialiser);
                opL.add(growInitialiser);
                ((RampedFullGrowInitialiser) initialiser).setOperations(opL);
            } else {
                // The default initialiser
                CreationOperation randomInitialiser;
                randomInitialiser = new RandomInitialiser(rng, g, p);
                initialiser.setOperation(randomInitialiser);
                
            }
            initialiser.setProperties(p);
            initialiser.setRNG(rng);
            initialiser.init();
        }  catch (Exception e) {
            System.out.println("Exception: "+e);
        }
        return initialiser;
    }

    /**
     * Load the fitness class according to the parameters
     * @param rng RandomNumberGenerator
     * @param p Properties
     * @return SelectionOperation
     */
    protected SelectionOperation getSelectionOperation(Properties p, RandomNumberGenerator rng) {
        SelectionOperation selectionOperation = null;
        String className;
        String key;
	key = Constants.SELECTION_OPERATION;
        try {
            className = p.getProperty(key);
            if (className == null) {
                throw new BadParameterException(key);
            }
            Class clazz = Class.forName(className);
            selectionOperation = (SelectionOperation) clazz.newInstance();
            selectionOperation.setProperties(p);
            ((Stochastic) selectionOperation).setRNG(rng);
        }  catch (Exception e) {
            System.out.println("Exception: "+e);
        }
        return selectionOperation;
    }
    
    /**
     * Load the fitness class according to the parameters
     * @param p Properties
     * @param rng RandomNumberGenerator
     * @param incPop incomming population
     * @param rO replacement operation
     * @return SelectionOperation
     */
    protected JoinOperator getJoinOperator(Properties p, RandomNumberGenerator rng, Population incPop, ReplacementOperation rO) {
        JoinOperator joinOperator = null;
        String className = "";
        String key;
	key = Constants.REPLACEMENT_TYPE;
	if(p.getProperty(key).equals(Constants.STEADY_STATE)) {
	    className = "Operator.MeritReplacementStrategy";
	} else {
	    if(p.getProperty(key).equals(Constants.GENERATIONAL)) {
		className = "Operator.SimpleReplacementStrategy";
	    }
	}
	key = Constants.REPLACEMENT_OPERATOR;
        try {
            if (p.getProperty(key)!= null) {
		className = p.getProperty(key);
            }
            Class clazz = Class.forName(className);
            joinOperator = (JoinOperator) clazz.newInstance();
            joinOperator.setIncomingPopulation(incPop);
            joinOperator.setOperation(rO);
            joinOperator.setRNG(rng);
        }  catch (Exception e) {
            System.out.println("Exception: "+e);
        }
        return joinOperator;
    }
    
    /**
     * Runs the init pipeline.
     * Prints statistics from the init pipeline
     **/
    protected void init() {
        long st = System.currentTimeMillis();
        //StatCatcher stats = getStatisticsCollectionOperation().getStats();
        // Call init() in the algorithm
        // Header for the column output
        this.algorithm.init();
        long et = System.currentTimeMillis();
        this.collector = this.getCollector();
        if(this.collector != null) {
            ((StatisticsCollectionOperation)this.collector.getOperation()).printHeader();
            ((StatisticsCollectionOperation)this.collector.getOperation()).printStatistics(et - st);

        }
                
    }


    /**
     * Prints the collected statistics
     **/
    @SuppressWarnings({"ConstantConditions"})
    protected void printStuff() {
        boolean printToFile = false;
        try{
            if(this.properties.getProperty(Constants.OUTPUT)!=null) {
                if(!this.properties.getProperty(Constants.OUTPUT).equals("")  && !this.properties.getProperty(Constants.OUTPUT).equals("false")) {
                    printToFile = true;
                }
            }
        } catch(Exception e){
            System.out.println("No output option specified! Not printing to file");
        }
        this.collector.print(printToFile);

    }
    
    /**
     * Run the loop pipeline.
     * @return int number of iterations
     **/
    protected int run() {
        long st = System.currentTimeMillis();
        // Get the number of generations
        int its =100;// Default
        boolean stopWhenSolved = false;

	/* 
	 * How many fitness evaluations should be done in order to 
	 * count a generation, in the generational sense.
	 */
	int fitnessEvaluationsPerGeneration = 1;
        try{
            String key = Constants.GENERATION;
            String value = this.properties.getProperty(key);
            if(value!=null) {
                if(!value.equals("")) {
                    its = Integer.parseInt(value);
                }
            }
            key = Constants.STOP_WHEN_SOLVED;
            value = this.properties.getProperty(key);
            if(value!=null) {
                if(!value.equals("")) {
                    if(value.equals(Constants.TRUE)) {
                        stopWhenSolved = true;
                    }
                }
            }
	    /*
	     * If Steady state is used out each generation in a
	     * generational sense only has 2 fitness evaluations. Set
	     * the iterations to GENERATIONS*POPULATION_SIZE/2
	     */
	    if(this.properties.getProperty(Constants.REPLACEMENT_TYPE,"").equals(Constants.STEADY_STATE)) {
		fitnessEvaluationsPerGeneration = Integer.parseInt(this.properties.getProperty(Constants.POPULATION_SIZE, Constants.DEFAULT_POPULATION_SIZE))/2;
		its = its*fitnessEvaluationsPerGeneration;
	    }
        }  catch(Exception e) {
            System.out.println(e+" default generations:"+its);
        }
        for (int i = 1; i<=its; i++) {
            if(this.collector != null) {
                if(!foundOptimum(stopWhenSolved, ((StatisticsCollectionOperation)this.collector.getOperation()).getStats())) {
                    this.algorithm.step();
                    long et = System.currentTimeMillis();
		    /* Only collect output after enough fitness
		     * evaluations to repalce the population
		     */
		    if(i%fitnessEvaluationsPerGeneration == 0) {
			((StatisticsCollectionOperation)this.collector.getOperation()).printStatistics(et - st);
			st = System.currentTimeMillis();
		    }
                }
            }
        }
        return its;
    }

    /**
     * Check if the global optimum value has been found
     * @param stopWhenSolved if algorithm should terminate when the global optimum is found
     * @param stats stataistics collection
     * @return if global optimum has been found
     */
    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    protected boolean foundOptimum(boolean stopWhenSolved, StatCatcher stats) {
        boolean ret = false;
        if(stopWhenSolved) {
            if(stats.getCurrentBestFitness() == 0.0) {
                ret = true;
            }
        }
        return ret;
    }
    
    /**
     * helper method for getting the StatisticsCollectionOperation
     * @return Collector collector module
     **/
    protected Collector getCollector() {
        if(((AbstractAlgorithm)this.getAlgorithm()).getLoopPipeline() != null) {
            Collection<Module> m = ((AbstractAlgorithm)this.getAlgorithm()).getLoopPipeline().getModules();
            Iterator<Module> iM = m.iterator();
            Module mo;
            while (iM.hasNext()) {
                mo = iM.next();
                if (mo.getClass().getName().equals(Collector.class.getName())) {
                    return (Collector)mo;
                }
            }
        }
        return null;
    }
    
    /**
     * Read the commandline arguments. Find if -h is called. Find if properties field is given
     * Prints the usage help
     * @param args Command-line arguments
     * @return booelan value of success
     **/
    @SuppressWarnings({"IOResourceOpenedButNotSafelyClosed"})
    protected boolean commandLineArgs(String[] args) {
        boolean ret = true;
        for(int i =0;i<args.length;i++) {
            if (args[i].startsWith("-")) {
                if (args[i].equals(Constants.HELP_FLAG)) { //print program help
                    printProgramHelp();
                    ret = false;
                } else {
                    if(args[i].equals("-"+ Constants.PROPERTIES_FILE) && ((i+1)<args.length)) { //properties file
                        this.propertiesFilePath = args[i+1];
                    } else {
                        if(args[i].equals(Constants.VERSION_FLAG)) {
                            printVersion();
                            if(args.length == 1) {
                                ret = false;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    void printVersion() {
        StringBuffer sb = new StringBuffer();
        sb.append("GEVA - Grammatical evolution in JAVA");
        sb.append(System.getProperty("line.separator") );
        sb.append("Version: 1");
        sb.append(System.getProperty("line.separator") );
        sb.append("Authors: Erik Hemberg, Conor Gilligan");
        sb.append(System.getProperty("line.separator") );
        sb.append("Developed by UCD Natural Computing Research & Applications group");
        sb.append(System.getProperty("line.separator") );
        sb.append("http://ncra.ucd.ie");
        System.out.println(sb.toString());
    }

    void printProgramHelp() {
        InputStream is;
        try {
            File f = new File(this.propertiesFilePath);
            is = new FileInputStream(f);
            this.properties = new Properties();
            this.properties.load(is);
            System.out.println("Commandline arguments");
            System.out.println("	"+ Constants.HELP_FLAG+" for help");
            System.out.println("	"+ Constants.VERSION_FLAG+" for version");
        } catch (IOException e) {
            System.out.println(e+" no default properties file at "+this.propertiesFilePath);
        }
        for (Enumeration e = properties.keys(); e.hasMoreElements();) {
            System.out.println("	-" + e.nextElement());
        }
    }

    /**
     * Read the default properties. Replace or add properties from the command line.
     * If the file is not found on the file system class loading from the jar is tried
     * @param args arguments
     */
    @SuppressWarnings({"IOResourceOpenedButNotSafelyClosed"})
    protected void readProperties(String[] args) {
        ClassLoader loader;
        InputStream in;
        try {
            this.properties = new Properties();
            File f = new File(this.propertiesFilePath);
            if(!f.exists()) { // try classloading
                loader = ClassLoader.getSystemClassLoader();
                in = loader.getResourceAsStream(this.propertiesFilePath);
                this.properties.load(in);
                System.out.println("Loading properties from ClassLoader and: "+this.propertiesFilePath);
            } else {
                FileInputStream is = new FileInputStream(f);
                this.properties.load(is);
                System.out.println("Loading properties from file system: "+this.propertiesFilePath);
            }
            if (args != null) {
                for (int i = 0; i<args.length; i++) {
                    if(args[i].startsWith("-") && args.length > (i+1) && !args[i].equals(Constants.VERSION_FLAG)) {
                        this.properties.setProperty(args[i].substring(1),args[i+1]);
                        i++;
                    }
                }
            }
	    for (Enumeration e = properties.keys(); e.hasMoreElements();) {
		String key = (String)e.nextElement();
		System.out.println(key+"="+properties.getProperty(key,"Not defined"));
	    }
        }  catch (IOException e) {
            loader = ClassLoader.getSystemClassLoader();
            in = loader.getResourceAsStream(this.propertiesFilePath);
            try{
                this.properties.load(in);
            } catch (Exception ex) {
                System.err.println("Properties reading output caught:"+ex);
            }
            System.err.println(MessageFormat.format("Using default: {0} Bad:{1} Could not load:{2}", this.propertiesFilePath, this.propertiesFilePath, e));
        }  catch (Exception e) {
            System.err.println("Could not commandline argument:"+e+" properties path:"+this.propertiesFilePath);
        }
    }

}
