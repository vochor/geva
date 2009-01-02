package Fractal;

import com.rie.rieps.engine.Document;
import com.rie.rieps.engine.Job;
import com.rie.rieps.engine.Page;
import com.rie.rieps.engine.color.Gray;
import com.rie.rieps.exception.RiepsException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Write an LSystem grammar to a PostScript file
 * @author eliott bartley
 */
public class PSLSystem2 extends LSystem2Renderer
{

    /**
     * Create a new LSystem that can be written to a PS file with axiom 'F'
     * @param grammar The grammar of the LSystem
     * @param depth The recursive depth of the grammar
     * @param angle The angle distance each turn should make, in degrees
     */
    public PSLSystem2(String grammar, int depth, float angle)
    {   this(null, grammar, depth, angle);
    }

    /**
     * Create a new LSystem that can be written to a PS file
     * @param axiom The axiom to apply the fractal to
     * @param grammar The grammar of the LSystem
     * @param depth The recursive depth of the grammar
     * @param angle The angle distance each turn should make, in degrees
     */
    public PSLSystem2(String axiom, String grammar, int depth, float angle)
    {   super(axiom, grammar, depth, angle);
    }

    /**
     * Save the LSystem to the named file at dimensions (500, 500)
     * @param filename Filepath to save to
     */
    public void save(String filename)
    {   save(filename, 500, 500);
    }

    /**
     * Save the LSystem to the named file at the specified dimensions
     * @param filename Filepath to save to
     * @param width Width of PostScript (in centimeters?)
     * @param height Height of PostScript (in centimeters?)
     */
    public void save(String filename, float width, float height)
    {   OutputStream stream;
        Job job;
        Document document;
        Page page;

        try
        {

            stream = new FileOutputStream(filename);

            // Create a one page document
            job = Job.createPSTopLeftOriginJob(stream, width, height);
            job.open();
            document = job.createDocument(1);
            document.open();
            page = document.createPage();
            page.setColor(Gray.BLACK);

            super.setDimension(width, height);
            super.render(page);

            // Shut-down
            document.showPage(page);
            document.close();
            job.close();
            stream.close();

        }
        catch(IOException e) { }
        catch(RiepsException e) { }

    }

    /**
     * Execute the LSystem and write its movements to the PS file, scaled to fit
     *  the page
     * @param user Any object the caller wants to pass. This is forwarded on to
     *  the drawLine callback
     */
    protected void drawLine(Object user, int x, int y, int u, int v)
    {   assert user instanceof Page : user.getClass().getName();
        Page page = (Page)user;
        // The PS library's drawLine takes, as the first two parameters,
        //  the (x, y) co-ordinate from the top-left corner incrementing
        //  to the bottom-right. As the second two parameters, it takes
        //  the width and height of the line's bounding-box, but
        //  increments towards the top-right! So the v is negated where
        //  y is not
        page.drawLine(x, y, u - x, -(v - y));
    }

}
