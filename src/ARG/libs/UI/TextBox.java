package ARG.libs.UI;

import ARG.libs.Color;
import ARG.libs.Defaults;
import ARG.libs.Point;
import ARG.libs.enums.TextAlignMode;
import ARG.libs.interfaces.Widget;
import processing.core.PApplet;
import processing.core.PFont;

public class TextBox implements Widget
{

    public Point position;
    public String text;
    public Color textColor;
    public int textSize;
    public TextAlignMode horizontalTextAlignMode;
    public TextAlignMode verticalTextAlignMode;
    public boolean isVisible;
    public boolean isWrapped;
    public int wrapWidth;
    public int wrapHeight;
    private PFont font;
    public boolean isAngleEnabled;
    public float angle;


    public TextBox()
    {
        this.angle = 0.0f;
        isAngleEnabled = false;
        this.isWrapped = false;
        this.wrapWidth = 0;
        this.wrapHeight = 0;
        this.isVisible = true;
        this.text = "Empty TextNox";
        this.textColor = Defaults.Buttons.textColor;
        this.textSize = Defaults.Buttons.textSize;
        this.verticalTextAlignMode = TextAlignMode.CENTER;
        this.horizontalTextAlignMode = TextAlignMode.CENTER;
        this.position = new Point(0, 0);
        this.font = null;
    }

    public TextBox(String text, Color textColor, int textSize)
    {
        this.angle = 0.0f;
        this.isWrapped = false;
        isAngleEnabled = false;
        this.wrapWidth = 0;
        this.wrapHeight = 0;
        this.isVisible = true;
        this.text = text;
        this.textColor = textColor;
        this.textSize = textSize;
        this.verticalTextAlignMode = TextAlignMode.CENTER;
        this.horizontalTextAlignMode = TextAlignMode.CENTER;
        this.position = new Point(0, 0);
        this.font = null;
    }

    public void setFont(PApplet p3, String fontPath)
    {
        font = p3.createFont(fontPath, this.textSize);
    }


    //region Getters and Setters
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public void setTextColor(Color textColor)
    {
        this.textColor = textColor;
    }

    public int getTextSize()
    {
        return textSize;
    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
    }


    //endregion

    @Override
    public void draw(PApplet p3)
    {
        if (isVisible)
        {
            p3.fill(this.textColor.getColor());
            p3.textSize(this.textSize);
            p3.textAlign(this.horizontalTextAlignMode.getValue(), this.verticalTextAlignMode.getValue());

            if (font != null)
            {
                p3.textFont(font);
            }
//            if (isAngleEnabled)
//            {
//                p3.translate(position.x, position.y);
//                p3.rotate(angle);
//                p3.text(this.text, 0, 0);
//                p3.rotate(0.0f);
//                p3.translate(0, 0);
//            } else
            {
                if (isWrapped)
                {
                    p3.text(this.text, this.position.x, this.position.y, wrapWidth, wrapHeight);
                } else
                {
                    p3.text(this.text, this.position.x, this.position.y);
                }
            }


        }
    }

    @Override
    public void update(PApplet p3)
    {
    }

    @Override
    public void mouseEvent(PApplet p3)
    {
    }


}
