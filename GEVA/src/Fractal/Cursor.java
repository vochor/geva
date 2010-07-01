/*
 * Cursor.java
 *
 * Created on October 18, 2007, 4:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Fractal;

/** Logo Style cursor
 * for use with 2-D L-Systems Applet
 *
 * 
 * @author J Scott Cameron
 */
public class Cursor{
    double x,y,angle;
    
/** Default Construtor
 */
    public Cursor()
    {
        x=0;y=0;angle=-Math.PI/2;
    }
    
/** Copy Constructor
 * @param c Cursor to be copied
 */
    public Cursor(Cursor c)
    {
        x=c.x;
        y=c.y;
        angle=c.angle;
    }
    
}