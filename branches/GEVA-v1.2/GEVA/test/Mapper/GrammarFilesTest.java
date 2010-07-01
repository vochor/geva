package Mapper;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test all grammar files in the param/Grammar directory
 * @author erikhemberg
 */
public class GrammarFilesTest extends ContextFreeGrammarTest {

    public GrammarFilesTest() {
        super();
    }

    /**
     * Test the Grammar Files in the param/Grammar directory
     **/
    @Test
    public void testGrammarDirectory() {
        System.out.println("* GrammarFilesTest: testGrammarDirectory");
        String dirName = System.getProperty("user.dir") + File.separator + ".." + File.separator + "param" + File.separator + "Grammar";
        File dir = new File(dirName);
        this.visitDirs(dir);
    }

    private void visitDirs(File file) {
        System.out.println("+" + file.getName());
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String c : children) {
                File f = new File(c);
                visitDirs(f);
            }
        } else {
            if (file.exists()) {
                System.out.println("-" + file.getName());
                if (file.getName().endsWith("bnf")) {
                    super.file_name = file.getName();
                    testReadBNFString();
                }
            }
        }
    }

    /**
     * Test of readBNFString method, of class ContextFreeGrammar.
     * @param file name
     */
    @Test
    @Override
    public void testReadBNFString() {
        System.out.println("* GrammarFilesTest: readBNFString");
        System.out.println("* GrammarFilesTest: " + super.file_name);
        ContextFreeGrammarMock instance = new ContextFreeGrammarMock();
        String bnfString = instance.readBNFFileToString(super.file_name);
        boolean result = instance.readBNFString(bnfString);
        assertEquals(true, result);
    }
}