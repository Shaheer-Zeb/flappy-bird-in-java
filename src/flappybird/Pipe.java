package flappybird;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ShaheerZK
 */
public class Pipe 
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private boolean isTop;
    private boolean hasPassed;
    private Image pipeImage;
    
    Pipe(int xPos, int yPos, int width, int height, boolean isTop)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.isTop = isTop;
        hasPassed = false;
        pipeImage = isTop ? new ImageIcon("src/images/toppipe.png").getImage() : new ImageIcon("src/images/bottompipe.png").getImage();
    }
    int getXPos()
    {
        return xPos;
    }
    void changeXPos(int i)
    {
        xPos += i;
    }
    int getYPos()
    {
        return yPos;
    }
    void changeYPos(int i)
    {
        yPos += i;
    }
    void setYPos(int i)
    {
        yPos = i;
    }
    int getWidth()
    {
        return width;
    }
    int getHeight()
    {
        return height;
    }
    void setHeight(int i)
    {
        height = i;
    }
    boolean isTop()
    {
        return isTop;
    }
    Image getImage()
    {
        return pipeImage;
    }
    boolean getHasPassed()
    {
        return hasPassed;
    }
    void setHasPassed(boolean b)
    {
        hasPassed = b;
    }
}