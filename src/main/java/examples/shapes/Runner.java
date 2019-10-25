package examples.shapes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Runner {
   {
        ArrayList shapes = new ArrayList<>();

        try {
            Circle circle = new Circle(new Point(1.1, 1), 5);
            shapes.add(circle);


            Triangle triangle = new Triangle(new Point(1, 1), new Point(1, 2), new Point(0, 1));
            shapes.add(triangle);

            Rectangle rectangle = new Rectangle(new Point(0, 1), new Point(1, 1), new Point(0, 0), new Point(1, 0));
            shapes.add(rectangle);


            Line line = new Line(new Point(0, 1), new Point(1, 1));
            shapes.add(line);

            EmbeddedPicture ePicture = new EmbeddedPicture("Output/usu.jpg");
            shapes.add(ePicture);
            writeAllShapesToFile(shapes, "Output/shapes.txt");
            List<Shape> allShapes = readShapesFromFile("Output/shapes.txt");

            renderEmbeddedImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeAllShapesToFile(List<Shape> shapeList, String shapeFileName) {
        OutputStream outputStream = null;
        File shapeFile;

        try {
            shapeFile = new File(shapeFileName);
            if (!shapeFile.exists()) {
                shapeFile.createNewFile();
            }
            outputStream = new FileOutputStream(shapeFile);

            for (Shape shape : shapeList) {
                outputStream.write(shape.toString().getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List readShapesFromFile(String shapeFileName) {
        List shapeList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(shapeFileName));
            String stringShape;
            while ((stringShape = br.readLine()) != null) {
                shapeList.add(ShapeFactory.create(stringShape));
            }
        } catch (FileNotFoundException e) {
           // System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shapeList;
    }

    public static void renderEmbeddedImage() throws ShapeException, IOException {
        Composite composite = new Composite();

        Rectangle rectangle = new Rectangle(new Point(50, 50), new Point(20, 50), new Point(50, 20), new Point(20, 20));
        SaveDetails sd1 = new SaveDetails();
        sd1.setLineColour(Color.BLACK);
        sd1.setFillColor(Color.WHITE);
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

        Line line = new Line(new Point(0, 300), new Point(30, 30));
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

        ImageIO.write(bImg, "png", new File("Output/Composite1.jpg"));
    }
}
