import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenDimensions {
    public static int width;
    public static int height;

    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
    }
}