package Fractal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Extended LSystem renderer with box-counting fractal dimension calculating
 * @author eliott bartley
 */
public class LSystem2FDBoxCounting
     extends LSystem2Renderer
  implements FractalDimension
{

    private BufferedImage image;

    public LSystem2FDBoxCounting
    (   String   axiom,
        String grammar,
        int      depth,
        float    angle,
        int       size
    ){  super(axiom, grammar, depth, angle);
        setBoxSize(size);
    }

    /**
     * Change the box-size. The area of the fractal will be divided evenly by
     *  the squared box-size value - i.e. 2 will divide the fractal into 4
     *  squares, 2 across, 2 down - 4 will divide into 16 squares, 4 across, 4
     *  down
     */
    public void setBoxSize(int size)
    {   if(size <= 1)
            throw new IllegalArgumentException(">1");
        image = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
    }

    /**
     * Approximate the fractal dimension using box-counting. This is done by
     *  rendering the fractal unto an image of dimensions specified by the box-
     *  size, and counting the pixels rendered
     */
    public double calcFractalDimension()
    {   assert image != null : "setBoxSize must have failed!";
        int size = image.getWidth();
        int count = 0;

        // Clear the image
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, size, size);

        // Render the fractal
        super.setDimension(size, size);
        super.render(null);

        // Count the pixels affected by the render
        byte[] pixels = ((DataBufferByte)image
            .getRaster()
            .getDataBuffer())
            .getData();
        for(byte pixel : pixels)
            if(pixel != 0)
                count++;

        // Return the approximate fractal dimension
        return Math.log(count) / Math.log(size);

    }

    @Override
    protected void drawLine(Object user, int x, int y, int u, int v)
    {   Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.drawLine(x, y, u, v);
    }

}
