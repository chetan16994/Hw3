package examples.shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShapeRenderTest {
    @Test
    public void testRender() throws ShapeException, IOException {
        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(20, 50), new Point(50, 20), new Point(20, 20));
        SaveDetails sd1 = new SaveDetails();
        sd1.setLineColour(Color.BLACK);
        sd1.setFillColor(Color.RED);
        Composite composite = new Composite();
        rectangle.setSaveDetails(sd1);
        composite.add(rectangle);

        Circle circle = new Circle(30, 30, 10);
        SaveDetails sd2 = new SaveDetails();
        sd2.setLineColour(Color.WHITE);
        sd2.setFillColor(Color.RED);
        sd2.setxAxis(30);
        sd2.setyAxis(60);
        circle.setSaveDetails(sd2);
        composite.add(circle);

        Triangle triangle = new Triangle(new Point(0, 0), new Point(10, 0), new Point(0, 10));
        SaveDetails sd3 = new SaveDetails();
        sd3.setLineColour(Color.WHITE);
        sd3.setFillColor(Color.BLUE);
        triangle.setSaveDetails(sd3);
        composite.add(triangle);

        Line line = new Line(new Point(0, 30), new Point(30, 30));
        SaveDetails sd4 = new SaveDetails();
        sd4.setLineColour(Color.YELLOW);
        line.setSaveDetails(sd4);
        composite.add(line);

        BufferedImage bImg = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = bImg.createGraphics();

        EmbeddedPicture ePicture = new EmbeddedPicture("Output/usu.jpg");
        SaveDetails sd5 = new SaveDetails();
        sd5.setxAxis(0);
        sd5.setyAxis(100);
        ePicture.setSaveDetails(sd5);
        ePicture.load(g2D);
        composite.add(ePicture);

        composite.setSaveDetails(sd1);
        composite.load(g2D);

        ImageIO.write(bImg, "png", new File("Output/Composite.png"));
    }
}
