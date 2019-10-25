package examples.shapes;

import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Circle  extends AbstractShape {
    private Point center;
    private double radius;

    public Circle(double x, double y, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        center = new Point(x, y);
        this.radius = radius;
    }

    public Circle(Point center, double radius) throws ShapeException {
        Validator.validatePositiveDouble(radius, "Invalid radius");
        if (center==null)
            throw new ShapeException("Invalid center point");

        this.center = center.copy();
        this.radius = radius;
    }

    public Point getCenter() throws ShapeException { return center.copy(); }

    public double getRadius() { return radius; }

    public void move(double deltaX, double deltaY) throws ShapeException {
        center.move(deltaX, deltaY);
    }

    public void scale(double scaleFactor) throws ShapeException {
        Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
        radius *= scaleFactor;
    }

    public double computeArea() {
        return Math.PI * Math.pow(radius, 2);
    }


    public void load(Graphics2D graphics2D) {
        int xCordinate = (int) Math.round(center.getX());
        int yCordinate = (int) Math.round(center.getY());
        int diameter = (int) Math.round(radius * 2);

        if (saveDetails == null) {
            createAndFill(graphics2D, xCordinate, yCordinate, diameter, Color.PINK);
            return;
        }
        createAndFill(graphics2D, saveDetails.getxAxis(), saveDetails.getyAxis(), diameter,
                saveDetails.getFillColor() != null ? saveDetails.getFillColor() : saveDetails.getLineColour());
    }

    private void createAndFill(Graphics2D graphics2D, int xCordinate, int yCordinate, int diameter, Color c) {
        graphics2D.setColor(c);
        graphics2D.drawOval(xCordinate, yCordinate, diameter, diameter);
        graphics2D.fillOval(xCordinate, yCordinate, diameter, diameter);
    }

    public String toString() {
        return "Circle," + center.getX() + "," + center.getY() + "," + getRadius();
    }
}
