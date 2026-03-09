package flappybird;
/**
 *
 * @author ShaheerZK
 */
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.*;

public class Main 
{
    static final int WIDTH = 360;
    static final int HEIGHT = 640;
    static JFrame frame;
    
    public static void main(String[] args) 
    {
        frame = new JFrame("Flappy Bird");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("src/images/flappybird.png").getImage());
        
        try 
        {
            frame.add(new Menu());
        } 
        catch (FontFormatException | IOException ex) 
        {
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        frame.setVisible(true);
    }   
}

