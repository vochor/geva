package Util;

/**
 * This class has all the constants.
 * The names for properties.
 */
public final class Constants {

    //Property file fields. Works as command line args as well
    /**
     * Class name of fitness function. Reflection used to load
     */
    public static final String FITNESS_FUNCTION = "fitness_function";

    /**
     * Class name of selection operation. Reflection used to load
     */
    public static final String SELECTION_OPERATION = "selection_operation";

    /**
     * Class name of replacement operator. Reflection used to load
     */
    public static final String REPLACEMENT_OPERATOR = "replacement_operator";

    /**
     * Path to properties file
     */
    public static final String PROPERTIES_FILE = "properties_file";

    /**
     * Class name of initialiser operation. Reflection used to load
     */
    public static final String INITIALISER = "initialiser";

    /**
     * Path for output file. Starts in user.dir
     * Output files are appended a timestamp and *.dat
     * If output is set to false no output is written
     */
    public static final String OUTPUT = "output";

    /**
     * Number of iterations of algorithm
     */
    public static final String GENERATION = "generations";

    /**
     * Stop algorithm if global optimum is found before max iterations
     */
    public static final String STOP_WHEN_SOLVED = "stopWhenSolved";

    /**
     * Path to grammar file *.bnf
     */
    public static final String GRAMMAR_FILE = "grammar_file";

    /**
     * Number of times the input is reread from start.
     */
    public static final String MAX_WRAPS = "max_wraps";

    /**
     * Probability of choosing a to grow a full depth tree when rampde full grow
     * initialisation is used.
     */
    public static final String GROW_PROBABILITY = "grow_probability";

    /**
     * Max depth of tree growth for full and grow initialisation
     */
    public static final String MAX_DEPTH = "max_depth";

    /**
     * Number of elites.
     */
    public static final String ELITE_SIZE = "elite_size";

    /**
     * If the elites should be evaluated each iteration
     */
    public static final String EVALUATE_ELITES = "evaluate_elites";

    /**
     * Length of input for random initialisation
     */
    public static final
        String INITIAL_CHROMOSOME_SIZE = "initial_chromosome_size";

    /**
     * Selection size proportion of population used selected.
     * E.g 1.0 generates a selection of population size
     */
    public static final String SELECTION_SIZE = "selection_size";

    /**
     * Size of individual solutions
     */
    public static final String POPULATION_SIZE = "population_size";

    /**
     * Generational or steady state replacment. If mor control is needed use
     * the generation gap and selection size parameters.
     */
    public static final String REPLACEMENT_TYPE = "replacement_type";

    /**
     * If the crossover point is fixed
     */
    public static final String FIXED_POINT_CROSSOVER = "fixed_point_crossover";

    /**
     * Size of tournament for tournament selection
     */
    public static final String TOURNAMENT_SIZE = "tournament_size";
    
    /**
     * Size of population for user selection
     */
    public static final String USERPICK_SIZE = "userpick_size";

    /**
     * Interval between saved individuals in the population
     */
    public static final
        String INDIVIDUAL_CATCH_INTERVAL = "individual_catch_interval";

    /**
     * Probability of mutating an input
     */
    public static final String MUTATION_PROBABILITY = "mutation_probability";

    /**
     * Probability of crossing over inputs
     */
    public static final String CROSSOVER_PROBABILITY = "crossover_probability";

    /**
     * Probability of duplicating inputs
     */
    public static final
        String DUPLICATION_PROBABILITY = "duplication_probability";

    /**
     * Porportion of new solutions (population) that will be inserted among the
     *  old solutions (population)
     */
    public static final String GENERATION_GAP = "generation_gap";

    /**
     * Word to match when using the wordmatch fitness function
     */
    public static final String WORDMATCH_WORD = "word";

    /**
     * Default population size
     */
    public static final String DEFAULT_POPULATION_SIZE = "10";

    /**
     * Default chromosome size
     */
    public static final String DEFAULT_CHROMOSOME_SIZE = "10";

    /**
     * The seed for the random number generator.
     */
    public static final String RNG_SEED = "rng_seed";
    //Property file values
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * Sets generation gap to 1/population size.
     * Sets selection size to 2/population size.
     */
    public static final String STEADY_STATE = "steady_state";

    /**
     * Sets generation gap to population size.
     * Sets selection size to population size.
     */
    public static final String GENERATIONAL = "generational";
    //Other values
    public static final String DEFAULT_PARAM_ROOT = "../param/";
    /**
     * Path to default properties file
     */
    public static final
        String DEFAULT_PROPERTIES = DEFAULT_PARAM_ROOT
                                 + "Parameters/HelloWorld.properties";

    /**
     * Signature of a GE Codon. Indicates special treatment in the parsing
     */
    public static final CharSequence GE_CODON_VALUE = "GECodonValue";

    /**
     * Identifying a GE codon when parsing
     */
    public static final String GE_CODON_VALUE_PARSING = "<"+GE_CODON_VALUE;

    public static final String MAX_DERIVATION_TREE_DEPTH = "max_dt_depth";
    
    public final static String DEFAULT_MAX_DERIVATION_TREE_DEPTH = String.valueOf(Integer.MAX_VALUE);
    /**
     * Sets number of step interactions to perform (higher is more accurate)
     */
    public static final String ODE_INTERACTIONS = "ode_interactions";

    /**
     * Sets size of a single step (number of seconds of action per step, lower
     *  is more accurate)
     */
    public static final String ODE_STEPSIZE = "ode_step_size";

    /**
     * Sets number of simulation seconds to run for
     */
    public static final String ODE_STEPS = "ode_steps";

    /**
     * Sets wall width in blocks in ode DamageCalc
     */
    public static final String ODE_DAMAGE_WALL_WIDTH = "ode_damage_wall_width";

    /**
     * Sets wall height in blocks in ode DamageCalc
     */
    public static final String ODE_DAMAGE_WALL_HEIGHT = "ode_damage_wall_height";
    
    public static final String TARGET_FRACTAL_DIMENSION = "targetFractalDimension";
    public static final String BOX_COUNT_DIVISION = "boxCountDivision";

    //Command line args
    public static final String VERSION_FLAG = "-v";
    public static final String HELP_FLAG = "-h";


    private Constants() {}

}
