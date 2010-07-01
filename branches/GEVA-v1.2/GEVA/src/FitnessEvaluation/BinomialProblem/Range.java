package FitnessEvaluation.BinomialProblem;

public class Range {

    private double[] start;
    private double[] stop;
    private double[] step;
    private boolean random;

    public Range(String s) {
	this.stringToRange(s);
    }

    /**
     * String as x = [start:step:stop]; or x = rnd(start, cases ,stop)
     */
    public void stringToRange(String s) {
	try {
	    String[] sA = s.split(";");
	    this.start = new double[sA.length];
	    this.stop = new double[sA.length];
	    this.step = new double[sA.length];	
	    for(int j = 0; j<sA.length; j++) {
		String[] sA_2 = sA[j].split("\\s*eq\\s*");
		for(int i = 0; i<sA_2.length; i++) {
		    if(sA_2[i].contains("["))  {
			String sA_3[] = sA_2[i].split(":");
			this.start[j] = Double.parseDouble(sA_3[0].substring(1));
			this.step[j] = Double.parseDouble(sA_3[1]);
			this.stop[j] = Double.parseDouble(sA_3[2].substring(0,(sA_3[2].length()-1)));
			this.random = false;
		    } else {
			if(sA_2[i].contains("(")) {
			    String sA_3[] = sA_2[i].split(",");
			    this.start[j] = Double.parseDouble(sA_3[0].substring(4));
			    this.step[j] = Double.parseDouble(sA_3[1]);
			    this.stop[j] = Double.parseDouble(sA_3[2].substring(0,(sA_3[2].length()-1)));
			    this.random = true;
			}
		    }
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	    System.err.println("Range");
	}
    }
 
    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public double getStart(int dim) {
        return start[dim];
    }

    public double getStep(int dim) {
        return step[dim];
    }

    public double getStop(int dim) {
        return stop[dim];
    }

    public int getDimensions() {
	return this.start.length;
    }

    public String toString() {
	String s = "";
	for(int i=0; i<start.length; i++) {
	    s += this.start[i]+"\n";
	    s += this.step[i]+"\n";
	    s += this.stop[i]+"\n";
	}
	s += this.random;
	return s;
    }

    public static void main(String[] args) {
	Range r = new Range("x= [1.2:2.3:5.6]; y = rnd(1,2,3)");
	System.out.println(r);
    }

    double getStep() {
        return this.getStep(0);
    }
    
    double getStop() {
        return this.getStop(0);
    }
    
    double getStart() {
        return this.getStart(0);
    }
}