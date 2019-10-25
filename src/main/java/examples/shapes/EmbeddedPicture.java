package examples.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class EmbeddedPicture extends AbstractShape {
    private BufferedImage bufferedImg;
    private final String imageFormat = "jpg";

    public EmbeddedPicture(byte[] byteArray) {
        try {
            ByteArrayInputStream bm = new ByteArrayInputStream(byteArray);
            bufferedImg = ImageIO.read(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmbeddedPicture(String fName) {
        try {
            File f = new File(fName);
            bufferedImg = ImageIO.read(f);
        } catch (IOException e) {
        }
    }

    @Override
    public void load(Graphics2D g2D) {
        if (saveDetails == null) {
            g2D.drawImage(bufferedImg, 0, 0, bufferedImg.getWidth(), bufferedImg.getHeight(), Color.BLUE, null);
            return;
        }

        g2D.drawImage(bufferedImg, saveDetails.getxAxis(), saveDetails.getyAxis(),
                bufferedImg.getWidth(), bufferedImg.getHeight(), saveDetails.getFillColor(), null);
    }

    @Override
    public String toString() {
        String output = "EmbeddedPicture";
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(this.bufferedImg, this.imageFormat, byteArrayOutputStream);
            output = "EmbeddedPicture," + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    @Override
    public double computeArea() {
        return 0;
    }
}
