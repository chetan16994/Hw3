package examples.shapes;

import java.awt.*;


@SuppressWarnings("WeakerAccess")
public class Line extends AbstractShape {
    private Point point1;
    private Point point2;

    public Line(double x1, double y1, double x2, double y2) throws ShapeException {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
        if (computeLength() < 0.000001)
            throw new ShapeException("A line must have a length > 0");
    }

    public Line(Point point1, Point point2) throws ShapeException {
        if (point1==null || point2==null)
            throw new ShapeException("Invalid Point");

        this.point1 = point1.copy();
        this.point2 = point2.copy();

        if (computeLength() < 0.000001)
            throw new ShapeException("A line must have a length > 0");
    }

    public Point getPoint1() throws ShapeException { return point1.copy(); }

    public Point getPoint2() throws ShapeException { return point2.copy(); }

    public double computeArea() {
        return 0;
    }

    public void move(double deltaX, double deltaY) throws ShapeException {
        point1.move(deltaX, deltaY);
        point2.move(deltaX, deltaY);
    }

    public double computeLength() {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) +
                Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double computeSlope() {
        return (point2.getX() - point1.getX())/(point2.getY() - point1.getY());
    }

    public void load(Graphics2D graphics2D) throws ShapeException {

        int xCordinate1 = (int) point1.getX();
        int yCordinate1 = (int) point1.getY();
        int xCordinate2 = (int) point2.getX();
        int yCordinate2 = (int) point2.getY();

        if (saveDetails == null) {
            createAndFill(graphics2D, xCordinate1, yCordinate1, xCordinate2, yCordinate2, Color.BLUE);
            return;
        }

        createAndFill(graphics2D, saveDetails.getxAxis(), saveDetails.getyAxis(), xCordinate2, yCordinate2, Color.BLUE);
        graphics2D.setColor(saveDetails.getLineColour());
        graphics2D.drawLine((int) getPoint1().getX(), (int) getPoint1().getY(), (int) getPoint2().getX(), (int) getPoint2().getY());

    }

    private void createAndFill(Graphics2D graphics2D, int xCordinate1, int yCordinate1, int xCordinate2, int yCordinate2, Color c) {

        graphics2D.setColor(c);
        graphics2D.drawLine(xCordinate1, yCordinate1, xCordinate2, yCordinate2);

    }

    public String toString() {
        return "Line," + point1.getX() + "," + point1.getY() + "," + point2.getX() + "," + point2.getY();
    }
}

