package examples.shapes;

@SuppressWarnings("WeakerAccess")
public class Rectangle{
private Point point1;
private Point point2;
private Point point3;
private Point point4;

public Rectangle(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4) throws ShapeException {
        point1=new Point(x1,y1);
        point2=new Point(x2,y2);
        point3=new Point(x3,y3);
        point4=new Point(x4,y4);
        }

public Rectangle(Point point1,Point point2,Point point3,Point point4) throws ShapeException {

        if(point1==null||point2==null)
        throw new ShapeException("Invalid Point");

        this.point1=point1.copy();
        this.point2=point2.copy();
        this.point3=point3.copy();
        this.point4=point4.copy();
        }

public Point getPoint1() throws ShapeException { return point1.copy(); }

public Point getPoint2() throws ShapeException { return point2.copy(); }

public Point getPoint3() throws ShapeException { return point3.copy(); }

public Point getPoint4() throws ShapeException { return point4.copy(); }

//public void move(double deltaX, double deltaY) throws ShapeException {
//        point1.move(deltaX, deltaY);
//        point2.move(deltaX, deltaY);
//        }


        }