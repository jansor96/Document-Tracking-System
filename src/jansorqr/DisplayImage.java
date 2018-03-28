/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jansorqr;

/**
 *
 * @author jansoriano acer
 */
/*This class displays an image provided with image address and dimensions*/
import java.awt.*;
import javax.swing.*;

class DisplayImage extends JPanel {

    ImageIcon imageIcon;
    Image image;
    String address;
    int xPos, yPos, width, height;

    public DisplayImage(String address, int xPos, int yPos, int width, int height) {
        this.address = address;
        imageIcon = new ImageIcon(this.address);
        image = imageIcon.getImage();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }//end class constructor

    public DisplayImage(String address, int width, int height) {
        this.address = address;
        imageIcon = new ImageIcon(this.address);
        image = imageIcon.getImage();
        this.xPos = 0;
        this.yPos = 0;
        this.width = width;
        this.height = height;
    }//end class constructor

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Main.report("Displaying image \'" + this.address + "\'. @ (" +this.width+", "+this.height+ ")");
            g.drawImage(image, xPos, yPos, width, height, this);
        } else {
            System.out.println("File not found");
        }
    }//end paintComponent
}//end SideImageRight
