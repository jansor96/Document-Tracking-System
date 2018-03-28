/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jansorqr;

import com.google.zxing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

/**
 *
 * @author jansoriano acer
 */
public class Reader {

    public static String readQRCode(String fileName) {
        Main.report("Reading QR Code from " + fileName + "...");
        File file = new File(fileName);
        BufferedImage image = null;
        BinaryBitmap bitmap = null;
        Result result = null;

        try {
            image = ImageIO.read(file);
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
            bitmap = new BinaryBitmap(new HybridBinarizer(source));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Main.report("Failed to read QR Image. " +e.getMessage());
        }

        if (bitmap == null) {
            return null;
        }

        QRCodeReader reader = new QRCodeReader();
        try {
            result = reader.decode(bitmap);
            Main.report("QR Image successfully read.");
            return result.getText();
        } catch (NotFoundException | ChecksumException | FormatException e) {
            // TODO Auto-generated catch block
            Main.report("Failed to read QR Image. "+ e.getMessage());
        }
        // TODO Auto-generated catch block
        // TODO Auto-generated catch block

        return null;
    }
}
