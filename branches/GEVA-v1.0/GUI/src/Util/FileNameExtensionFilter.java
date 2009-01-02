package Util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Implementation for 1.5 compatibility of the 1.6 Java class of the same name,
 *  which I didn't realise at the time of using, wasn't part of a 1.5
 * @author eliott bartley
 */
public class FileNameExtensionFilter extends FileFilter
{

    private String description;
    private String[] extensions;

    public FileNameExtensionFilter(String description, String... extensions)
    {   this.description = description;
        this.extensions = GEVAHelper.trim(extensions);
        undotExtensions();
    }

    /**
     * Remove the dot at the start of the extension name, if any,
     *  .txt becomes txt
     */
    private void undotExtensions()
    {   for(int i = 0; i < extensions.length; i++)
            if(extensions[i].length() > 1 && extensions[i].charAt(0) == '.')
                extensions[i] = extensions[i].substring(1);
    }

    @Override
    public boolean accept(File f)
    {   if(extensions == null)
            return true;
        if(f.isDirectory() == true)
            return true;
        String fileExtension = f.getName();
        int dot = fileExtension.lastIndexOf('.') + 1;
        if(dot == 0)
            return false;
        fileExtension = fileExtension.substring(dot);
        for(String extension : extensions)
            if(extension.length() == 0
            || extension.equals(fileExtension) == true)
                return true;
        return false;
    }

    @Override
    public String getDescription()
    {   StringBuilder extensionList = null;
        if(extensions != null)
            for(String extension : extensions)
                if(extension.trim().length() != 0)
                    if(extensionList == null)
                    {   extensionList = new StringBuilder();
                        extensionList.append(extension);
                    }
                    else
                        extensionList.append("|" + extension);
        if(extensionList != null)
        {   extensionList.insert(0, " (");
            extensionList.append(')');
            return description + extensionList.toString();
        }
        return description;
    }

    public String[] getExtensions()
    {   return extensions;
    }

    @Override
    public String toString()
    {   if(extensions != null)
            return extensions[0];
        return null;
    }

}
