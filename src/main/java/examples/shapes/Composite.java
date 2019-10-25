package examples.shapes;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class Composite extends AbstractShape {
    private List<AbstractShape> abstractShapes = new ArrayList<>();
    public void add(AbstractShape s) {
        abstractShapes.add(s);
    }

    public void remove(AbstractShape s) {
        abstractShapes.remove(s);
    }

    public void clear() {
        abstractShapes.clear();
    }

    @Override
    public void load(Graphics2D graphics) throws ShapeException {
        for (Shape s : abstractShapes) {
            s.load(graphics);
        }
    }

    @Override
    public double computeArea() throws ShapeException {
        double totalArea = 0;
        for (Shape s : abstractShapes) {
            totalArea += s.computeArea();
        }
        return totalArea;
    }
}

