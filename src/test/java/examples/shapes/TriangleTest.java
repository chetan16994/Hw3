package examples.shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class TriangleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(4, 10);
        Point p3 = new Point(9, 6);

        Line myline1 = new Line(p1, p2);
        assertSame(p1,myline1.getPoint1());
        assertSame(p2,myline1.getPoint2());

        Line myline2 = new Line(p2, p3);
        assertSame(p2,myline2.getPoint1());
        assertSame(p3,myline2.getPoint2());

        Line myline3 = new Line(p3, p1);
        assertSame(p3,myline3.getPoint1());
        assertSame(p1,myline3.getPoint2());

        Triangle myTriangle = new Triangle(p1, p2, p3);
        assertSame(p1, myTriangle.getPoint1());
        assertSame(p2, myTriangle.getPoint2());
        assertSame(p3, myTriangle.getPoint3());

        p1 = new Point(1.4, 2.5);
        p2 = new Point(4.6, 10.7);
        p3 = new Point(9.6, 6.8);
        myTriangle = new Triangle(p1, p2, p3);
        assertSame(p1, myTriangle.getPoint1());
        assertSame(p2, myTriangle.getPoint2());
        assertSame(p3, myTriangle.getPoint3());

        myTriangle = new Triangle(1, 2.33, 4.444, 10.5555, 9.76, 6.854);
        assertEquals(1, myTriangle.getPoint1().getX(), 0);
        assertEquals(2.33, myTriangle.getPoint1().getY(), 0);
        assertEquals(4.444, myTriangle.getPoint2().getX(), 0);
        assertEquals(10.5555, myTriangle.getPoint2().getY(), 0);
        assertEquals(9.76, myTriangle.getPoint3().getX(), 0);
        assertEquals(6.854, myTriangle.getPoint3().getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws Exception {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(4, 10);
        Point p3 = new Point(1, 2);

        try {
            new Triangle(p1, p2, null);
            fail("Expected exception not thrown for when the third parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Triangle(null, p2, p3);
            fail("Expected exception not thrown for when the first parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Triangle(p1, null, p3);
            fail("Expected exception not thrown for when the second parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Triangle(Double.POSITIVE_INFINITY, 2, 3, 4, 5, 6);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Triangle(1, Double.POSITIVE_INFINITY, 3, 4, 5, 6);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Triangle(1, 2, Double.POSITIVE_INFINITY, 4, 5, 6);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Triangle(1, 2, 3, Double.POSITIVE_INFINITY, 5, 6);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());

        }
        try {
            new Triangle(1, 2, 3, 4, Double.POSITIVE_INFINITY, 6);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());

        }

        try {
            new Triangle(1, 2, 3, 4, 5, Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }


    }

    @Test
    public void testMove() throws ShapeException {
        Triangle myTriangle = new Triangle(1, 2, 4, 10, 11, 12);

        myTriangle.move(3, 4);
        assertEquals(4, myTriangle.getPoint1().getX(), 0);
        assertEquals(6, myTriangle.getPoint1().getY(), 0);
        assertEquals(7, myTriangle.getPoint2().getX(), 0);
        assertEquals(14, myTriangle.getPoint2().getY(), 0);
        assertEquals(14, myTriangle.getPoint3().getX(), 0);
        assertEquals(16, myTriangle.getPoint3().getY(), 0);

        myTriangle.move(.4321, .7654);
        assertEquals(4.4321, myTriangle.getPoint1().getX(), 0);
        assertEquals(6.7654, myTriangle.getPoint1().getY(), 0);
        assertEquals(7.4321, myTriangle.getPoint2().getX(), 0);
        assertEquals(14.7654, myTriangle.getPoint2().getY(), 0);
        assertEquals(14.4321, myTriangle.getPoint3().getX(), 0);
        assertEquals(16.7654, myTriangle.getPoint3().getY(), 0);

        myTriangle.move(-0.4321, -0.7654);
        assertEquals(4, myTriangle.getPoint1().getX(), 0);
        assertEquals(6, myTriangle.getPoint1().getY(), 0);
        assertEquals(7, myTriangle.getPoint2().getX(), 0);
        assertEquals(14, myTriangle.getPoint2().getY(), 0);
        assertEquals(14, myTriangle.getPoint3().getX(), 0);
        assertEquals(16, myTriangle.getPoint3().getY(), 0);
    }

    public void testComputeArea() throws ShapeException {

        Triangle myTriangle = new Triangle(0, 1, 4, 0, 3, 2);
        assertEquals(4, myTriangle.computeArea(), 0.001);
    }
    @Test
    public void load() throws Exception {

        String outputFilename = "Output/triangle.png";

        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.white);

            Point p1 = new Point(0, 0);
            Point p2 = new Point(0, 40);
            Point p3 = new Point(60, 40);

            Triangle triangle= new Triangle(p1, p2, p3);
            triangle.load(graphics);

        File file = new File(outputFilename);
        ImageIO.write(bufferedImage, "png", file);
        graphics.dispose();
    }
}