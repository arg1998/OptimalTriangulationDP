package ARG.libs.UI;

import ARG.libs.Point;
import ARG.libs.interfaces.ClickListener;
import ARG.libs.interfaces.Widget;
import processing.core.PApplet;

public class CircleButton extends Button implements Widget
{

    private ClickListener clickListener = null;
    public int radius;

    //############################### Private Methods #####################################
    private int square(int a)
    {
        return (int) Math.pow(a, 2);
    }

    private boolean is_mouse_over_button(PApplet p3)
    {
        return (square(position.x - p3.mouseX) + square(position.y - p3.mouseY) <= square(radius / 2));
    }


    public CircleButton(Point position)
    {
        super();
        this.position = position;
        this.textBox.position = new Point(this.position.x, this.position.y);

    }

    public CircleButton(Point position, int radius)
    {
        super();
        this.position = position;
        this.radius = radius;
        this.textBox.position = new Point(this.position.x, this.position.y - 4);
    }


    //############################## Overrided Methods #####################################

    @Override
    public void setOnClickListener(ClickListener clickListener)
    {
        if (clickListener == null)
        {
            throw new NullPointerException("Event Listener Must Not Be null");
        }
        this.clickListener = clickListener;
    }


    @Override
    public void draw(PApplet p3)
    {
        if (isVisible)
        {
            //region Stroke
            if (this.strokeWidth == 0.0f)
            {
                p3.noStroke();
            } else
            {
                p3.strokeWeight(this.strokeWidth);
                p3.stroke(this.strokeColor.getColor());
            }
            //endregion

            //region Shape And Color
            if (isMouseHovered)
            {
                p3.fill(this.hoverBackgroundColor.getColor());
            } else
            {
                p3.fill(this.backgroundColor.getColor());
            }
            p3.ellipse(position.x, position.y, radius, radius);
            //endregion

            //region Text
            if (showText)
            {
                this.textBox.draw(p3);
            }
            //endregion
        }
    }

    @Override
    public void update(PApplet p3)
    {
        isMouseHovered = is_mouse_over_button(p3);
    }

    @Override
    public void mouseEvent(PApplet p3)
    {
        if (isMouseHovered && isEnabled)
        {
            clickListener.onClick(p3.mouseButton);
        }
    }
}
