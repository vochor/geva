/*
 * This class is used to generate constants for symbolic regression and the Binomial 3 problem. 
 *
 */

package Util;

import Util.Random.MersenneTwisterFast;


/**
 *
 * @author jbyrne
 */
public class ConstantGenerator 
{
    
    private static int modulo = 1000;
    private static int decimalPlaces = 2; 
    private static int divisor = 100;
    
    protected MersenneTwisterFast m;
            
    public ConstantGenerator() 
    { 
        m = new MersenneTwisterFast();
    }
    /**
     * this sets the Modulo value to limit the range
     * @param mod
     */
    public static void setMod(int mod)
    {
        if(mod > 1)
        {
            modulo = mod;
            System.out.println("The new mod value is "+modulo );
        }
    }
    /**
     * this displays the current set modulo value (default is 1000);
     * @return int
     */
    public static int getMod()
    {
        return modulo;
    }
    
     /**
     * this sets the decimal places 
     * @param mod
     */
    public static void setDecimalPlaces(int dp)
    {
        if(dp >= 0)
        {
           decimalPlaces = dp;
           double tmp = Math.pow(10,decimalPlaces);
           divisor = (int)tmp;
           
        }
        else
        {
            System.out.println("decimalPlaces value was not valid");
        }
    System.out.println("decimalPlaces set to "+decimalPlaces );
    System.out.println("divisor set to "+divisor );
    }
    /**
     * this displays the current decimal places (default is 2);
     * @return int
     */
    public static int getDecimalPlaces()
    {
        return decimalPlaces;
    }
     
    /**
     * This generates a float constant value 
     * @return float
     */
    public float getConstant()
    {
        
       float constant = Math.abs((float)m.nextInt());
       constant = constant %(modulo*divisor);    //set the Mod first
       constant = (constant /divisor);           //check to 2 decimal places
                           
       return constant;
    }
    
    /**
     * This generates a float constant value in a string format
     * @return String
     */
    public String getStringConstant()
    {
       float tmpConst = getConstant();       
       StringBuffer strConst =new StringBuffer();
       strConst.append(tmpConst);
       return strConst.toString();
    }
    
    public static void main(String[] args) 
    {
        ConstantGenerator cg = new ConstantGenerator();
        ConstantGenerator.setDecimalPlaces(2);
        System.out.println(cg.getStringConstant());
    }
    
}
