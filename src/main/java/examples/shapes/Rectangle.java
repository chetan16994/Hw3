package examples.shapes;

import java.awt.*;

public class Rectangle extends AbstractShape{

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private Point vertex4;
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;

    public Rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws ShapeException {

        vertex1 = new Point(x1, y1);
        vertex2 = new Point(x2, y2);
        vertex3 = new Point(x3, y3);
        vertex4 = new Point(x4, y4);
        line1 = new Line(vertex1, vertex2);
        line2 = new Line(vertex2, vertex3);
        line3 = new Line(vertex3, vertex4);
        line4 = new Line(vertex4, vertex1);

        if (line1.computeLength() < 0.000001 || line2.computeLength() < 0.000001 || line3.computeLength() < 0.000001 || line4.computeLength() < 0.00000001)
            throw new ShapeException("All edge must have a length greater than zero");
        if (line1.computeSlope() * line2.computeSlope() == -1 || line2.computeSlope() * line3.computeSlope() == -1 || line3.computeSlope() * line4.computeSlope() == -1 || line4.computeSlope() * line1.computeSlope() == -1)
            throw new ShapeException("Two adjacent edge must form right angle");

    }

    public Rectangle(Point vertex1, Point vertex2, Point vertex3, Point vertex4) throws ShapeException {
        if (vertex1 == null || vertex2 == null || vertex3 == null || vertex4 == null)
            throw new ShapeException("Invalid Point");

        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.vertex4 = vertex4;
        line1 = new Line(vertex1, vertex2);
        line2 = new Line(vertex2, vertex3);
        line3 = new Line(vertex3, vertex4);
        line4 = new Line(vertex4, vertex1);
        if (line1.computeLength() < 0.00000001 || line2.computeLength() < 0.00000001 || line3.computeLength() < 0.00000001 || line4.computeLength() < 0.00000001)
            throw new ShapeException("All edge must have a length greater than  0");

        if (line1.computeSlope() * line2.computeSlope() == -1 || line2.computeSlope() * line3.computeSlope() == -1 || line3.computeSlope() * line4.computeSlope() == -1 || line4.computeSlope() * line1.computeSlope() == -1)
            throw new ShapeException("In Rectangle 2 edges must make 90 degree angle");
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        vertex1.move(deltaX, deltaY);
        vertex2.move(deltaX, deltaY);
        vertex3.move(deltaX, deltaY);
        vertex4.move(deltaX, deltaY);
    }


    /**
     * @return The first vertice
     */

    public Point getvertice1() {
        return vertex1;
    }

    /**
     * @return The second vertice
     */

    public Point getvertice2() {
        return vertex2;
    }

    /**
     * @return The third vertice
     */

    public Point getvertice3() {
        return vertex3;
    }

    /**
     * @return The fourth vertice
     */

    public Point getvertice4() {
        return vertex4;
    }

    /**
     * @return The Lenght
     */

    public double getLength() {
        return line1.computeLength();
    }

    /**
     * @return The Width
     */

    public double getWidth() {
        return line2.computeLength();
    }


   @Override
    public void load(Graphics2D graphics2D) {

        int xAxis = (int) vertex1.getX();
        int yAxis = (int) vertex1.getY();

        if (saveDetails == null) {
            graphics2D.setColor(Color.RED);
            graphics2D.drawRect(xAxis, yAxis, (int) getWidth(), (int) getLength());
            return;
        }

       graphics2D.setColor(saveDetails.getLineColour());
       graphics2D.drawRect((int) vertex1.getX(), (int) vertex1.getY(), (int)getWidth(), (int) getLength());

       if (saveDetails.getFillColor() != null) {
           graphics2D.setColor(saveDetails.getFillColor());
           graphics2D.fillRect((int) vertex1.getX(), (int) vertex1.getY(), (int) getWidth(), (int) getLength());
       }
    }

    public double computeArea() {
        return line1.computeLength() * line2.computeLength();
    }

    public String toString() {
        return "Rectangle," + vertex1.getX() + "," + vertex1.getY() + "," + vertex2.getX() + "," + vertex2.getY() + "," + vertex3.getX() + "," + vertex3.getY() + "," + vertex4.getX() + "," + vertex4.getY();
    }
}




