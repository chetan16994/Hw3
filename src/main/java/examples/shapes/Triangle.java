package examples.shapes;


import java.awt.*;

@SuppressWarnings("WeakerAccess")
public class Triangle extends AbstractShape {

    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private Line line1;
    private Line line2;
    private Line line3;


    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) throws ShapeException {
        vertex1 = new Point(x1, y1);
        vertex2 = new Point(x2, y2);
        vertex3 = new Point(x3, y3);
        line1 = new Line(vertex1, vertex2);
        line2 = new Line(vertex2, vertex3);
        line3 = new Line(vertex3, vertex1);

        if (line1.computeSlope() == line2.computeSlope())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line1.computeLength() > line2.computeLength() + line3.computeLength())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line2.computeLength() > line1.computeLength() + line3.computeLength())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line3.computeLength() > line2.computeLength() + line1.computeLength())
            throw new ShapeException("In a traiangle sum of two sides cant be less than 1 side");
    }


    public Triangle(Point vertex1, Point vertex2, Point vertex3) throws ShapeException {
        if (vertex1 == null || vertex2 == null || vertex3 == null)
            throw new ShapeException("Invalid Point");

        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        line1 = new Line(vertex1, vertex2);
        line2 = new Line(vertex2, vertex3);
        line3 = new Line(vertex3, vertex1);


        if (line1.computeSlope() == line2.computeSlope())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line1.computeLength() > line2.computeLength() + line3.computeLength())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line2.computeLength() > line1.computeLength() + line3.computeLength())
            throw new ShapeException("A Triangle cant have all vertices in same line");
        if (line3.computeLength() > line2.computeLength() + line1.computeLength())
            throw new ShapeException("In a traiangle sum of two sides cant be less than 1 side");
    }


    public Point getPoint1() {
        return vertex1;
    }

    public Point getPoint2() {
        return vertex2;
    }

    public Point getPoint3() {
        return vertex3;
    }


    public void move(double deltaX, double deltaY) throws ShapeException {
        vertex1.move(deltaX, deltaY);
        vertex2.move(deltaX, deltaY);
        vertex3.move(deltaX, deltaY);
    }


    public void load(Graphics2D graphics2D) {
        int x = (int) vertex1.getX();
        int y = (int) vertex1.getY();
        int xCordinates[] = {(int) vertex1.getX(), (int) vertex2.getX(), (int) vertex3.getX()};
        int yCordinates[] = {(int) vertex1.getY(), (int) vertex2.getY(), (int) vertex3.getY()};
        if (saveDetails == null) {
            graphics2D.setColor(Color.white);
            graphics2D.drawPolygon(xCordinates, yCordinates, 3);
            return;
        }

        graphics2D.setColor(saveDetails.getLineColour());
        graphics2D.drawPolygon(xCordinates, yCordinates, 3);

        if (saveDetails.getFillColor() != null) {
            graphics2D.setColor(saveDetails.getFillColor());
            graphics2D.fillPolygon(xCordinates, yCordinates, 3);
        }
    }

    public double computeArea() {
        double s;
        s = getPerimeter() / 2;
        return Math.sqrt(s * (s - line1.computeLength()) * (s - line2.computeLength()) * (s - line3.computeLength()));
    }

    public double getPerimeter() {
        double perimeter;
        perimeter = line1.computeLength() + line2.computeLength() + line3.computeLength();
        return perimeter;
    }

    public String toString() {
        return "Triangle," + vertex1.getX() + "," + vertex1.getY() + "," + vertex2.getX() + "," + vertex2.getY() + "," + vertex3.getX() + "," + vertex3.getY();
    }
}

