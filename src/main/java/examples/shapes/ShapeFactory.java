package examples.shapes;

import java.util.Base64;

public class ShapeFactory {
    public static Shape create(String shapeDetails) {
        String[] splits = shapeDetails.split(",");
        String shapeName = splits[0];
        Shape shape = null;
        try {
            switch (shapeName) {
                case "Circle": {
                    double x = Double.parseDouble(splits[1]);
                    double y = Double.parseDouble(splits[2]);
                    double radius = Double.parseDouble(splits[3]);
                    shape = new Circle(x, y, radius);
                    break;
                }

                case "Line": {
                    double x1 = Double.parseDouble(splits[1]);
                    double y1 = Double.parseDouble(splits[2]);
                    double x2 = Double.parseDouble(splits[3]);
                    double y2 = Double.parseDouble(splits[4]);
                    shape = new Line(x1, y1, x2, y2);
                    break;
                }
                case "Rectangle": {
                    double x1 = Double.parseDouble(splits[1]);
                    double y1 = Double.parseDouble(splits[2]);
                    double x2 = Double.parseDouble(splits[3]);
                    double y2 = Double.parseDouble(splits[4]);
                    double x3 = Double.parseDouble(splits[5]);
                    double y3 = Double.parseDouble(splits[6]);
                    double x4 = Double.parseDouble(splits[7]);
                    double y4 = Double.parseDouble(splits[8]);
                    shape = new Rectangle(x1, y1, x2, y2, x3, y3, x4, y4);
                    break;
                }

                case "Point": {
                    double x = Double.parseDouble(splits[1]);
                    double y = Double.parseDouble(splits[2]);
                    shape = new Point(x, y);
                    break;
                }
                case "Triangle": {
                    double x1 = Double.parseDouble(splits[1]);
                    double y1 = Double.parseDouble(splits[2]);
                    double x2 = Double.parseDouble(splits[3]);
                    double y2 = Double.parseDouble(splits[4]);
                    double x3 = Double.parseDouble(splits[5]);
                    double y3 = Double.parseDouble(splits[6]);
                    shape = new Triangle(x1, y1, x2, y2, x3, y3);
                    break;
                }

                case "EmbeddedPicture": {
                    String embeddedPictureBytes = splits[1];
                    byte[] byteArray = Base64.getDecoder().decode(embeddedPictureBytes);
                    shape = new EmbeddedPicture(byteArray);
                    break;
                }
                default:
                    System.out.println("Wrong format to create shape " + shapeDetails);
                    break;
            }
        } catch (ShapeException e) {
            e.printStackTrace();
        }
        return shape;
    }
}
