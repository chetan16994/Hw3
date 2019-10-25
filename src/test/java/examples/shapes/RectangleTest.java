package examples.shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void testValidConstruction() throws ShapeException {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);
        Point p3 = new Point(2,4);
        Point p4 = new Point(3,6);

        Rectangle myRectangle = new Rectangle(p1, p2, p3, p4);
        assertSame(p1, myRectangle.getvertice1());
        assertSame(p2, myRectangle.getvertice2());
        assertSame(p3, myRectangle.getvertice3());
        assertSame(p4, myRectangle.getvertice4());

        p1 = new Point(1.4,2.5);
        p2 = new Point(4.6, 10.7);
        p3 = new Point(1.6,12);
        p4 = new Point(2.4,2.5);
        myRectangle = new Rectangle(p1, p2, p3, p4);
        assertSame(p1, myRectangle.getvertice1());
        assertSame(p2, myRectangle.getvertice2());
        assertSame(p3, myRectangle.getvertice3());
        assertSame(p4, myRectangle.getvertice4());

        myRectangle = new Rectangle(1,2,4,10,2,4,3,6);
        assertEquals(1, myRectangle.getvertice1().getX(), 0);
        assertEquals(2, myRectangle.getvertice1().getY(), 0);
        assertEquals(4, myRectangle.getvertice2().getX(), 0);
        assertEquals(10, myRectangle.getvertice2().getY(), 0);
        assertEquals(2, myRectangle.getvertice3().getX(), 0);
        assertEquals(4, myRectangle.getvertice3().getY(), 0);
        assertEquals(3, myRectangle.getvertice4().getX(), 0);
        assertEquals(6, myRectangle.getvertice4().getY(), 0);
    }

    @Test
    public void testInvalidConstruction() throws Exception {
        Point p1 = new Point(1,2);
        Point p2 = new Point(4, 10);
        Point p3 = new Point(1,2);
        Point p4 = new Point(1,2);

        try {
            new Rectangle( null,p2,p3,p4);
            fail("Expected exception not thrown for when the first parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Rectangle(p1, null,p3,p4);
            fail("Expected exception not thrown for when the second parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Rectangle(p1, p2,null,p4);
            fail("Expected exception not thrown for when the third parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }
        try {
            new Rectangle(p1, p2,p3,null);
            fail("Expected exception not thrown for when the fourth parameter is null");
        } catch (ShapeException e) {
            assertEquals("Invalid Point", e.getMessage());
        }

        try {
            new Rectangle(Double.POSITIVE_INFINITY, 2, 3, 4,5,6,7,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Rectangle(1, Double.POSITIVE_INFINITY, 3, 4,5,6,7,8);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Rectangle(1,  Double.POSITIVE_INFINITY,3, 4,5,6,7,8);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("Invalid y-location", e.getMessage());
        }

        try {
            new Rectangle(1, 2,  Double.POSITIVE_INFINITY,4,5,6,7,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }


        try {
            new Rectangle(1, 2, 3, Double.POSITIVE_INFINITY,5,6,7,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(1, 2, 3, 4,Double.POSITIVE_INFINITY,6,7,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }
        try {
            new Rectangle(1, 2, 3, 4,5,Double.POSITIVE_INFINITY,7,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }
        try {
            new Rectangle(1, 2, 3, 4,5,6,Double.POSITIVE_INFINITY,8);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid x-location", e.getMessage());
        }

        try {
            new Rectangle(1,2,3,4,5,6,7,Double.POSITIVE_INFINITY);
            fail("Expected exception not thrown");
        } catch (ShapeException e) {
            assertEquals("Invalid y-location", e.getMessage());
        }



    }

    @Test
    public void testMove() throws ShapeException {
        Rectangle myRectangle = new Rectangle(1, 1, 3, 3,6,6,9,9);

        myRectangle.move(3, 4);
        assertEquals(4, myRectangle.getvertice1().getX(), 0);
        assertEquals(5, myRectangle.getvertice1().getY(), 0);
        assertEquals(6, myRectangle.getvertice2().getX(), 0);
        assertEquals(7, myRectangle.getvertice2().getY(), 0);
        assertEquals(9, myRectangle.getvertice3().getX(), 0);
        assertEquals(10, myRectangle.getvertice3().getY(), 0);
        assertEquals(12, myRectangle.getvertice4().getX(), 0);
        assertEquals(13, myRectangle.getvertice4().getY(), 0);

        myRectangle.move(.4321, .7654);
        assertEquals(4.4321, myRectangle.getvertice1().getX(), 0);
        assertEquals(5.7654, myRectangle.getvertice1().getY(), 0);
        assertEquals(6.4321, myRectangle.getvertice2().getX(), 0);
        assertEquals(7.7654, myRectangle.getvertice2().getY(), 0);
        assertEquals(9.4321, myRectangle.getvertice3().getX(), 0);
        assertEquals(10.7654, myRectangle.getvertice3().getY(), 0);
        assertEquals(12.4321, myRectangle.getvertice4().getX(), 0);
        assertEquals(13.7654, myRectangle.getvertice4().getY(), 0);

        myRectangle.move(-0.4321, -0.7654);
        assertEquals(4, myRectangle.getvertice1().getX(), 0);
        assertEquals(5, myRectangle.getvertice1().getY(), 0);
        assertEquals(6, myRectangle.getvertice2().getX(), 0);
        assertEquals(7, myRectangle.getvertice2().getY(), 0);
        assertEquals(9, myRectangle.getvertice3().getX(), 0);
        assertEquals(10, myRectangle.getvertice3().getY(), 0);
        assertEquals(12, myRectangle.getvertice4().getX(), 0);
        assertEquals(13, myRectangle.getvertice4().getY(), 0);
    }

    @Test
    public void testComputeArea() throws ShapeException {

        Rectangle myRectangle = new Rectangle(1,1,1,4,4,1,4,4);
        assertEquals(12.727, myRectangle.computeArea(), 0.001);

    }
    @Test
    public void load() throws Exception {
        BufferedImage bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.white);

        Point p1 = new Point(0, 0);
            Point p2 = new Point(20, 0);
            Point p3 = new Point(50, 56);
            Point p4 = new Point(0, 56);

            Rectangle rectangle = new Rectangle(p1, p2, p3, p4);
            rectangle.load(graphics);

        ImageIO.write(bufferedImage, "png", new File("Output/rectangle.png"));
        graphics.dispose();
    }
}