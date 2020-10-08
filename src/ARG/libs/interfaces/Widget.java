package ARG.libs.interfaces;

import processing.core.PApplet;

public interface Widget
{
    /**
     * this method must implement the drawing part of the rendering
     */
    void draw(PApplet p3);

    /**
     * this method update the information about the object
     * like mouse Enter or Leave event
     * it must be called in each frame
     */
    void update(PApplet p3);


    void mouseEvent(PApplet p3);

}
