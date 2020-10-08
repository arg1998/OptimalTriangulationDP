package ARG.libs.UI;

import ARG.libs.Point;
import ARG.libs.interfaces.ClickListener;
import ARG.libs.interfaces.Widget;
import processing.core.PApplet;

public class RectButton extends Button implements Widget
{

    private ClickListener clickListener = null;
    public int width;
    public int height;
    private int top_left_radius , top_right_radius ,bottom_left_radius ,bottom_right_radius;

    //############################### Private Methods #####################################

    private boolean is_mouse_over_button(PApplet p3)
    {
        return (p3.mouseX >= position.x && p3.mouseX <= position.x+width &&
                p3.mouseY >= position.y && p3.mouseY <= position.y+height);
    }




    //################################# Constructors ######################################

    public RectButton(Point position)
    {
        super();
        this.position = position;
        top_left_radius = top_right_radius = bottom_right_radius = bottom_left_radius = 0;
        this.textBox.position = new Point(position.x + width / 2,(position.y + height / 2 ) - 4);
    }

    public RectButton(Point position ,int width, int height)
    {
        super();
        this.position = position;
        this.width = width;
        this.height = height;
        top_left_radius = top_right_radius = bottom_right_radius = bottom_left_radius = 0;
        this.textBox.position = new Point(position.x + width / 2,(position.y + height / 2 ) - 4);

    }

    public RectButton(int width, int height, int top_left_radius, int top_right_radius, int bottom_right_radius, int bottom_left_radius)
    {
        this.width = width;
        this.height = height;
        this.top_left_radius = top_left_radius;
        this.top_right_radius = top_right_radius;
        this.bottom_left_radius = bottom_left_radius;
        this.bottom_right_radius = bottom_right_radius;
    }


    public void setCornerRadius(int top_left_radius , int top_right_radius ,int bottom_right_radius ,int bottom_left_radius)
    {
        this.top_left_radius = top_left_radius;
        this.top_right_radius = top_right_radius;
        this.bottom_left_radius = bottom_left_radius;
        this.bottom_right_radius = bottom_right_radius;
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
            }
            else
            {
                p3.fill(this.backgroundColor.getColor());
            }
            p3.rect(position.x, position.y, width, height, top_left_radius, top_right_radius , bottom_right_radius,bottom_left_radius);
            //endregion

            //region Text
            if(showText)
            {
                textBox.draw(p3);
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
        if(isMouseHovered && isEnabled)
        {
            clickListener.onClick(p3.mouseButton);
        }
    }
}
