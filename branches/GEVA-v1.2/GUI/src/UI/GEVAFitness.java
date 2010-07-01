package UI;

import Util.GEVAHelper;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Store stuff about each fitness function. This class is populated by
 *  initialiseFFConfig* in GEVAPropertiesGUI and stored in
 *  GEVAPropertiesGUI.fitnessDetails which is a hash map that maps the fitness
 *  function (by class name) to these details
 * @author eliott bartley
 */
public class GEVAFitness
{

    public static class Names extends ArrayList<String> { }
    public static class FitnessCommands extends HashMap<String, String> { }

    /**
     * Default grammar file associated with this fitness function
     */
    public String grammarFile;
    /**
     * Names of the jar class paths that are associated with this fitness
     *  function
     */
    public Names names = new Names();
    /**
     * Store list of additional commands to pass on the command line when
     *  running GEVA using this fitness function
     */
    public static FitnessCommands fitnessCommands = new FitnessCommands();

    /**
     * Get all the jar files in the collection of jars by name, but also
     *  the collection of names, and also the global class path specified on the
     *  command line. This can be cancelled if the list of jars is still being
     *  populated by the GEVAJarHunter
     * @return null if cancelled, else string containing all jar files or empty
     *  string if there are no jar files
     */
    public String getJar()
    {   StringBuilder jarFiles = null;
        String jarFile;
        if(GEVAConfig.getClassRelPath() != null
        && GEVAConfig.getClassRelPath().length() != 0)
        {   jarFiles = new StringBuilder();
            jarFiles.append(GEVAHelper.quote(GEVAConfig.getClassRelPath()));
        }
        for(String name : names)
            if((jarFile = GEVAJarHunter.get(name)) == null)
                return null;
            else
                if(jarFile.length() != 0)
                    if(jarFiles == null)
                    {   jarFiles = new StringBuilder();
                        jarFiles.append(jarFile);
                    }
                    else
                        jarFiles.append
                        (   System.getProperty("path.separator")
                          + jarFile
                        );
        if(jarFiles == null)
            return "";
        return jarFiles.toString();
    }
    public String getCmd()
    {   StringBuilder cmdExtras = new StringBuilder();
        String cmd;
        for(String name : names)
            if((cmd = fitnessCommands.get(name)) != null && cmd.length() != 0)
                cmdExtras.append(" " + cmd);
        cmdExtras.append(' ');
        return cmdExtras.toString();
    }
}
