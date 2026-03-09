package flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements ActionListener
{
    Image backgroundImage;
    
    ImageIcon startButtonImage = new ImageIcon("src/images/startButton.png");
    JButton startAgain = new JButton(new ImageIcon(startButtonImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    
    ImageIcon exitButtonImage = new ImageIcon("src/images/exitButton.png");
    JButton exitBtn = new JButton(new ImageIcon(exitButtonImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    
    JLabel gameOverLabel = new JLabel("My Flappy Bird");
    
    static Font flappyFont;
    Menu() throws FontFormatException, IOException
    {
        flappyFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/flappy-font.ttf"));
        
        backgroundImage = new ImageIcon("src/images/flappybirdbg.png").getImage();
        
        startAgain.addActionListener(this);
        startAgain.setBorderPainted(false);
        startAgain.setContentAreaFilled(false);
        startAgain.setFocusPainted(false);
        startAgain.setMargin(new Insets(0, 0, 0, 0));
        
        exitBtn.addActionListener(this);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setMargin(new Insets(0, 0, 0, 0));
        
        gameOverLabel.setFont(flappyFont.deriveFont(40f));
        gameOverLabel.setForeground(Color.white);
        
        this.add(gameOverLabel);
        this.add(startAgain);
        this.add(exitBtn);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == startAgain)
        {
            Main.frame.add(new GamePanel());
            Main.frame.remove(this);
            
            Main.frame.revalidate();
            Main.frame.repaint();
        }
        if (ae.getSource() == exitBtn)
        {
            System.exit(0);
        }
    }   
}