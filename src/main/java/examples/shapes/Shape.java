package examples.shapes;

import java.awt.*;
import java.util.stream.Stream;

public interface Shape {


    Stream save();
    void load(Graphics2D graphics) throws ShapeException;
    double computeArea() throws ShapeException;
}