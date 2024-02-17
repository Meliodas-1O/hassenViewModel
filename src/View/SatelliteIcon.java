package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Satellite;

public class SatelliteIcon extends JPanel {
    
    private Satellite satellite;
    private Image satelliteImage;
    public JPanel parentPanel;

    public SatelliteIcon(Satellite _satellite) {
        String imageName = "satellite.png";
        String currentDirectory = System.getProperty("user.dir");
        String siblingDirectoryPath = currentDirectory + File.separator + "assets"+ File.separator + "images";
        String imagePath = siblingDirectoryPath + File.separator + imageName;
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            satelliteImage = icon.getImage();
            satellite = _satellite;
        } else {
            System.err.println("Failed to load image: " + imagePath);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int satelliteX = (int) satellite.getX(); 
        int satelliteY = (int) satellite.getY(); 
        g.drawImage(satelliteImage, satelliteX+5, satelliteY+3, Satellite.SATELLITE_WIDTH, Satellite.SATELLITE_HEIGHT, this);
    }

    public Satellite getSatellite(){
        return satellite;
    }
}
