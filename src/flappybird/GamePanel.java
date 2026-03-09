package flappybird;
/**
 *
 * @author ShaheerZK
 */
import static flappybird.Menu.flappyFont;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{
    Image backgroundImage;
    Image birdImage;
    Image birdFallingImage;
    Image birdJumpingImage;
    
    final int birdWidth = 40;
    final int birdHeight = 30;
    final int FPS = 60;
    final int gravity = 2;
    
    boolean isGameOver = false;
    
    Bird bird;
    Random random = new Random();

    
    int pipeWidth = 90;
    int pipeHeight = random.nextInt(Main.HEIGHT / 2);
    int pipeStartX = Main.WIDTH + 100;
    int pipeSpeed = 1;
    static int noOfPipes = 2;
    int safeAreaSize = 5;
    int distanceBetweenPipes = 200;
    Pipe[][] pipes; // two rows of pipes, the first item of each row is the top and the second is the bottom pipe
   
    Timer timer = new Timer(1000 / FPS, this);
    
    GamePanel()
    {
        backgroundImage = new ImageIcon("src/images/flappybirdbg.png").getImage();
        
        birdImage = new ImageIcon("src/images/flappybird.png").getImage();
        birdFallingImage = new ImageIcon("src/images/flappybirdFalling.png").getImage();
        birdJumpingImage = new ImageIcon("src/images/flappybirdJump.png").getImage();
        bird = new Bird(Main.WIDTH / 8, Main.HEIGHT / 2, birdWidth, birdHeight, birdImage);
        
        Main.frame.addKeyListener(this);
        Main.frame.setFocusable(true);
        Main.frame.requestFocusInWindow();
        
        pipes = new Pipe[noOfPipes][noOfPipes];
        for (int i = 0; i < noOfPipes; i++)
        {
            pipes[i][0] = new Pipe(pipeStartX, 0, pipeWidth, pipeHeight, true);
            pipes[i][1] = new Pipe(pipeStartX, pipeHeight + bird.getHeight() * safeAreaSize, pipeWidth, Main.HEIGHT, false);
            pipeStartX += distanceBetweenPipes;
        }
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        draw(g2d);
    }
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g2d.drawImage(bird.getImage(), bird.getXPos(), bird.getYPos(), bird.getWidth(), bird.getHeight(), this);
        for (int i = 0; i < noOfPipes; i++)
        {
            g2d.drawImage(pipes[i][0].getImage(), pipes[i][0].getXPos(), pipes[i][0].getYPos(), pipes[i][0].getWidth(), pipes[i][0].getHeight(), this);
            g2d.drawImage(pipes[i][1].getImage(), pipes[i][1].getXPos(), pipes[i][1].getYPos(), pipes[i][1].getWidth(), pipes[i][1].getHeight(), this);
        }
        g2d.setFont(flappyFont.deriveFont(30f));
        g2d.setColor(Color.white);
        g2d.drawString("Score: " + bird.getScore(), 20, 30);
        g2d.drawString("High Score: " + bird.getHighScore(), 20, 70);
    }
    public void movePipe()
    {
        for (int i = 0; i < noOfPipes; i++)
        {
            pipes[i][0].changeXPos(-pipeSpeed);
            pipes[i][1].changeXPos(-pipeSpeed);
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (bird.getYPos() <= 0)
        {
            isGameOver = true;
        }
        bird.changeYPos(gravity);
        if (bird.getYPos() + bird.getHeight() >= Main.HEIGHT - 100) // subtracting a constant value assuming that it'd be the floor height
        {
            isGameOver = true;
        }
        movePipe();
        bird.incrementScore(pipes);
        bird.checkIfNewHighScore();
        for (int i = 0; i < noOfPipes; i++)
        {
            checkIfRespawn(pipes[i]);
            if (bird.isColliding(pipes[i]))
            {
                isGameOver = true;
                break;
            }
        }
        if (isGameOver)
        {
            timer.stop();
            PlaySound.playDieSound();
            try 
            {
                Main.frame.add(new GameOver());
            } 
            catch (FontFormatException | IOException ex) 
            {
                System.getLogger(GamePanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            Main.frame.remove(this);
            Main.frame.revalidate();
            Main.frame.repaint();
        }
        repaint();
    }
    void checkIfRespawn(Pipe[] pipe)
    {
        if (pipe[0].getXPos() <= 0 - pipeWidth)
        {
            int newHeight = random.nextInt(Main.HEIGHT / 2);
            
            pipe[0].setHeight(newHeight);
            
            pipe[1].setYPos(newHeight + bird.getHeight() * safeAreaSize);
            pipe[1].setHeight(Main.HEIGHT);
            
            pipe[0].changeXPos(Main.WIDTH + pipeWidth);
            pipe[1].changeXPos(Main.WIDTH + pipeWidth);
            
            pipe[0].setHasPassed(false);
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) 
    {
        
    }
    @Override
    public void keyPressed(KeyEvent ke) 
    {
        if (ke.getKeyChar() == ' ' && bird.getYPos() > 0)
        {
            bird.changeYPos(-Bird.ySpeed);
           // bird.setImage(birdJumpingImage);
            bird.setJumping(true);
            PlaySound.playFlapSound();
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) 
    {
        
    }
}
