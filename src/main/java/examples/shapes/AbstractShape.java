package examples.shapes;

import java.util.stream.Stream;

public abstract class AbstractShape implements Shape{
    protected SaveDetails saveDetails;

    /*public SaveDetails getSaveDetails() {
        return saveDetails;
    }*/

    public void setSaveDetails(SaveDetails saveDetails) {
        this.saveDetails = saveDetails;
    }

    public Stream save() {
        return this.toString().codePoints().mapToObj(ch -> String.valueOf((char) ch));
    }
}
