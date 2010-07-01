package FitnessEvaluation.MultiSquares;

import java.awt.Color;

public class ShapeGrammarSwingPainterState{

    public double rot;        // angle of rotation
    public int x, y;          // shape x and y coordinates
    public int startX, startY;// shape initial x and y coordinates
    public int w, h;          // shape width and height
    public double sclX, sclY; // shape scaling factors
    public double shrX, shrY; // shape shearing factors
    public int trltX, trltY;  // shape translating factors
    public Color color;       // color of the drawings

    public ShapeGrammarSwingPainterState(double initRot,
					 int initX, int initY,
					 int initStartX, int initStartY,
					 int initW, int initH,
					 double initSclX, double initSclY,
					 double initShrX, double initShrY,
					 int initTrltX, int initTrltY,
					 Color clr){
	
	x = initX;            // x coordinate
	y = initY;            // y coordinate
	rot = initRot;        // angle of rotation
	startX = initStartX;  // initial shape x coordinate
	startY = initStartY;  // initial shape y coordinate
	w = initW;            // shape width
	h = initH;            // shape height
	sclX = initSclX;      // scaling x factor
	sclY = initSclY;      // scaling y factor
	shrX = initShrX;      // shearing x factor
	shrY = initShrY;      // shearing y factor
	trltX = initTrltX;    // translating x factor
	trltY = initTrltY;    // translating y factor
	color = clr;
    }

    /**
     * Create a new state cloned from another
     * @param state The state to clone
     */
    public ShapeGrammarSwingPainterState(ShapeGrammarSwingPainterState state){   
	x = state.x;
	y = state.y;
	rot = state.rot;
	startX = state.startX;
	startY = state.startY;
	w = state.w;
	h = state.h;
	sclX = state.sclX;
	sclY = state.sclY;
	shrX = state.shrX;
	shrY = state.shrY;
	trltX = state.trltX;
	trltY = state.trltY;
	color = state.color;
    }
}
