package ARG.libs;
import processing.core.PApplet;

public class Color extends PApplet
{
    public int R;
    public int B;
    public int G;
    public int Alpha;

    public Color(int r, int b, int g, int alpha)
    {
        R = r;
        B = b;
        G = g;
        Alpha = alpha;

    }

    public int getColor()
    {
        return color(R,B,G,Alpha);
    }


}
