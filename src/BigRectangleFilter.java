import java.awt.*;

public class BigRectangleFilter implements Filter {

    @Override
    public boolean accept(Object x) {
        // rejects all non-Rectangles
        if (!(x instanceof Rectangle)) return false;
        return ((Rectangle) x).width * 2 + ((Rectangle) x).height * 2 > 10;
    }
}
