package ARG.libs.UI;

import ARG.libs.Color;
import ARG.libs.Point;
import ARG.libs.enums.CapMode;
import ARG.libs.interfaces.Widget;
import processing.core.PApplet;


public class Line implements Widget
{

    public Point startPoint;
    public Point endPoint;
    public Color lineColor;
    public int lineWidth;
    public CapMode capMode;
    public boolean drawLineLength;
    public double drawLineLengthThreshold;
    public TextBox lineLengthText;

    public boolean isVisible;

    public boolean showShadow;
    public Color shadowColor;
    public int shadowOpacity;
    public int shadowWidth;

    public Line()
    {
        lineWidth = 4;
        capMode = CapMode.ROUND;
        drawLineLengthThreshold = 40;
        lineLengthText = new TextBox();
        lineLengthText.isAngleEnabled = false;
        lineLengthText.setTextSize(26);
        shadowWidth = 8;
        shadowOpacity = 60;
        showShadow = true;
        drawLineLength = true;
        isVisible = true;
        startPoint = new Point(0, 0);
        endPoint = new Point(0, 0);
        lineColor = new Color(242, 234, 130, 255);
        shadowColor = new Color(242, 234, 130, shadowOpacity);
    }

    public Line(Point startPoint, Point endPoint)
    {
        lineWidth = 4;
        capMode = CapMode.ROUND;
        drawLineLengthThreshold = 40;
        lineLengthText = new TextBox();
        lineLengthText.isAngleEnabled = false;
        lineLengthText.setTextSize(26);
        shadowWidth = 8;
        shadowOpacity = 60;
        drawLineLength = true;
        showShadow = true;
        isVisible = true;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        lineColor = new Color(242, 234, 130, 255);
        shadowColor = new Color(242, 234, 130, shadowOpacity);
    }

    public double length()
    {
        return Math.round(Math.sqrt
                (
                        (Math.pow(startPoint.x - endPoint.x, 2) + Math.pow(startPoint.y - endPoint.y, 2))

                ) * 100d) / 100d;
    }

    private float findAngle()
    {
        int dy = endPoint.y - startPoint.y;
        int dx = endPoint.x - startPoint.x;
        if (dx == 0) return 1.57079f;
        return (float) Math.round((Math.atan((double) (dy / dx))));
    }

    public boolean isEqualTo(Line otherLine)
    {
        return ((this.startPoint.isEqualTo(otherLine.startPoint) && this.endPoint.isEqualTo(otherLine.endPoint)) ||
                (this.startPoint.isEqualTo(otherLine.endPoint) && this.endPoint.isEqualTo(otherLine.startPoint)));
    }


    @Override
    public void draw(PApplet p3)
    {
        if (isVisible)
        {

            if (showShadow)
            {
                p3.stroke(shadowColor.getColor());
                p3.strokeWeight(shadowWidth);
                p3.strokeCap(capMode.getValue());
                p3.line(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }

//            if (drawLineLength)
//            {
//                double length = length();
//                if (length > drawLineLengthThreshold)
//                {
//                    lineLengthText.position.x = ((startPoint.x + endPoint.x) / 2);
//                    lineLengthText.position.y = ((startPoint.y + endPoint.y) / 2);
//                    lineLengthText.setFont(p3, "null");
//                    lineLengthText.angle = findAngle();
//                    lineLengthText.text = String.valueOf(length);
//                    lineLengthText.draw(p3);
//                }
//
//            }

            p3.stroke(lineColor.getColor());
            p3.strokeWeight(lineWidth);
            p3.strokeCap(capMode.getValue());
            p3.line(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
    }

    @Override
    public void update(PApplet p3)
    {
        return;
    }

    @Override
    public void mouseEvent(PApplet p3)
    {

    }
}
