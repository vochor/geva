package FitnessEvaluation.Sudoku;

import Util.Constants;
import Individuals.Phenotype;
import Individuals.Individual;
import FitnessEvaluation.FitnessFunction;

import java.util.StringTokenizer;
import java.util.Properties;

public class SudokuFit implements FitnessFunction{

    private static int size = 9;
    private static int subGrid = 3;
    private static int maxFit = 243;
    private static int[] solution = new int[81];
    private int[] match;

    /** 
     * Creates a new instance of SudokuFit
     */
    public SudokuFit() {}

    /**
     * @param p
     */
    public void setProperties(Properties p){
    }
    
    public boolean canCache(){
	return true;
    }

    public void getFitness(Individual i) {
	double result;
	double tempFit;
	String p = i.getPhenotype().getString();
	
	//System.out.println("Phenotype: "+p);
	parsePhen(p);

	// What do I pass as parameters to evaluate?
	tempFit = evaluate(solution);
	result = maxFit - tempFit;
	i.getFitness().setDouble(result);
    }
    
    public void parsePhen(String p){
	StringTokenizer tokenizer = new StringTokenizer(p);
	String tok;
	int cnt = 0;

	while(tokenizer.hasMoreTokens()){
	    tok = tokenizer.nextToken();
	    solution[cnt] = Integer.parseInt(tok);
	    //System.out.print(solution[cnt] + " ");
	    cnt++;
	}

	//System.out.println("\nFound " + cnt + " nums.");
    }

    public double evaluate (int[] grid) {
        size = subGrid * subGrid;
        if (match == null) {
            match = new int[size + 1];
        }
	
        // check all the rows and columns
        int tot = 0;
        for (int i = 0; i < size; i++) {
            // check a column
            tot += checkLine(grid, i, size);
            // check a row
            tot += checkLine(grid, i * size, 1);
        }

        // now check the sub-grids
        for (int x = 0; x < size; x += subGrid) {
            for (int y = 0; y < size; y += subGrid) {
                tot += checkBox(grid, x, y);
            }
        }
        return tot;
    }

    public int checkLine(int[] grid, int start, int diff) {
        int tot = 0;
        resetMatch();
        for (int i = 0; i < size; i++) {
            int val = grid[start];
            if (match[val] == 0) {
                match[val] = 1;
                tot++;
            }
            start += diff;
        }
        return tot;
    }

    public int checkBox(int[] grid, int x, int y) {
        int tot = 0;
        resetMatch();
        for (int i = x; i < x + subGrid; i++) {
            for (int j = y; j < y + subGrid; j++) {
                int val = grid[i + size * j];
                if (match[val] == 0) {
                    match[val] = 1;
                    tot++;
                }
            }
        }
        return tot;
    }

    public void resetMatch() {
        match[0] = 1;
        for (int i = 1; i < match.length; i++) {
            match[i] = 0;
        }
    }
}
