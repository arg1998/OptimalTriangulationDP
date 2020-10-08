package ARG.libs.UI;


import ARG.libs.Color;
import ARG.libs.Defaults;
import ARG.libs.Point;
import ARG.libs.enums.TextAlignMode;
import ARG.libs.interfaces.ClickListener;

public abstract class Button
{

    public Color backgroundColor; // color of the background
    public Color hoverBackgroundColor; // color of the background when mouse enter in area

    public Color strokeColor;
    public float strokeWidth;

    public Point position; // the position of the button

    public TextBox textBox;
    public TextAlignMode textAlignMode;

    public boolean showText;
    public boolean isEnabled; // is button clickable
    public boolean isVisible; // has button been rendering or not

    protected boolean isPressed; //temporal value to hold the state of "CLICk"
    protected boolean isMouseHovered; //temporal value to hold the state of "MOUSE_ENTER"


    //###################################### Constructors ###########################################
    public Button()
    {
        backgroundColor = Defaults.Buttons.backgroundColor;
        hoverBackgroundColor = Defaults.Buttons.hoverBackgroundColor;

        strokeColor = Defaults.Buttons.strokeColor;
        strokeWidth = Defaults.Buttons.strokeWidth;

        position = Defaults.Buttons.point;

        textBox = new TextBox(Defaults.Buttons.text,Defaults.Buttons.textColor,Defaults.Buttons.textSize);

        isEnabled = true;
        isVisible = true;
        showText = true;

        isPressed = false;
        isMouseHovered = false;
    }


    //region Getters and Setters

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Color getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor)
    {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getStrokeColor()
    {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth()
    {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }

    //endregion




    public abstract void setOnClickListener(ClickListener clickClickListener);



}
