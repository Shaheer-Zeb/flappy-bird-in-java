package flappybird;
/**
 *
 * @author ShaheerZK
 */
import java.awt.Image;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Bird 
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Image birdImage;
    private int score;
    private int highScore;
    private boolean isJumping;
    
    static int ySpeed = 50;
    
    Bird(int xPos, int yPos, int width, int height, Image birdImage)
    {
        try (RandomAccessFile highScoreFile = new RandomAccessFile("src/scores/highScore.txt", "rw"))
        {            
            highScore = Integer.parseInt(highScoreFile.readLine());
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.birdImage = birdImage;
        this.score = 0;
        isJumping = false;
    }
    public int getXPos()
    {
        return xPos;
    }
    public int getYPos()
    {
        return yPos;
    }
    public void changeYPos(int i)
    {
        yPos += i;
    }
    public void changeYSpeed(int i)
    {
        ySpeed += i;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Image getImage()
    {
        return birdImage;
    }
    public void setImage(Image newBirdImage)
    {
        birdImage = newBirdImage;
    }
    public void incrementScore()
    {
        score++;
    }
    public boolean isJumping()
    {
        return isJumping;
    }
    public void setJumping(boolean b)
    {
        isJumping = b;
    }
    public int getScore()
    {
        return score;
    }
    public int getHighScore()
    {
        return highScore;
    }
    public void checkIfNewHighScore()
    {
        if (score > highScore)
        {
            highScore = score;
        }
    }
    public boolean isColliding(Pipe[] pipes)
    {
//        int safeAreaStart = pipes[0].getYPos() + pipes[0].getHeight() - pipes[1].getYPos();
//        int safeAreaEnd = pipes[1].getYPos();
        return (yPos < pipes[0].getYPos() + pipes[0].getHeight() || yPos + height > pipes[1].getYPos()) &&
               xPos >= pipes[0].getXPos();
               
    }
    public void incrementScore(Pipe[][] pipes)
    {
        for (int i = 0; i < GamePanel.noOfPipes; i++)
        {
            if (pipes[i][0].getXPos() < xPos && pipes[i][0].getHasPassed() == false)
            {
                PlaySound.playPointSound();
                pipes[i][0].setHasPassed(true);
                score++;
            }
        }
    }
}