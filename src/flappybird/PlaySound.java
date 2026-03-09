package flappybird;

/**
 *
 * @author ShaheerZK
 */

import javax.sound.sampled.*;
import java.io.*;

public class PlaySound 
{
    static File birdDie = new File("src/sounds/die.wav");
    static File birdFlap = new File("src/sounds/flap.wav");
    static File birdPoint = new File("src/sounds/point.wav");
    
    public static void playDieSound()
    {
        if (birdDie.exists())
        {
            try
            {
                AudioInputStream dieSoundStream = AudioSystem.getAudioInputStream(birdDie);
                Clip dieClip = AudioSystem.getClip();
                dieClip.open(dieSoundStream);
                dieClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the sound mate.");
            }
        }
        else
            System.out.println("Unable to locate the dying sound.");   
    }
    public static void playFlapSound()
    {
        if (birdFlap.exists())
        {
            try
            {
                AudioInputStream flapSoundStream = AudioSystem.getAudioInputStream(birdFlap);
                Clip flapClip = AudioSystem.getClip();
                flapClip.open(flapSoundStream);
                flapClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the sound mate.");
            }
        }
        else
            System.out.println("Unable to locate the dying sound.");   
    }
    public static void playPointSound()
    {
        if (birdPoint.exists())
        {
            try
            {
                AudioInputStream pointSoundStream = AudioSystem.getAudioInputStream(birdPoint);
                Clip pointClip = AudioSystem.getClip();
                pointClip.open(pointSoundStream);
                pointClip.start();
            }
            catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
            {
                System.out.println("Something bad occured while playing the sound mate.");
            }
        }
        else
            System.out.println("Unable to locate the dying sound.");   
    }
}
