package ARG;

import java.util.ArrayList;
import java.util.TreeMap;

import ARG.libs.UI.CircleButton;
import ARG.libs.Color;
import ARG.libs.Point;
import ARG.libs.UI.Line;
import ARG.libs.UI.RectButton;
import ARG.libs.UI.TextBox;
import ARG.libs.enums.TextAlignMode;
import ARG.libs.interfaces.Widget;
import processing.core.PApplet;

public class Processing extends PApplet
{
    //################################ Properties ###########################################

    private static final boolean DEBUG = false;
    private TextBox mousePositionTextBox;
    private TextBox messageTextBox;
    private ArrayList<TextBox> pointsIndicesTextBoxes;
    private ArrayList<Widget> ui; // ui elements like buttons, etc ...
    private ArrayList<Point> points; // collection of all points which placed in canvas
    private Point lastPoint; // holds the position of the last point to help drawing the Temporal Line
    private ArrayList<Line> lines; // collection of Line objects
    private ArrayList<Line> triangledLines;
    private int magnetDistance;
    private int backgroundColor;
    private int canvasColor;
    private int canvasWidth, canvasHeight;
    private Line tempLine; // the temporal line object which starts from the last placed point and ends in mouse Position (a line which follows the mouse)
    private boolean isMouseCloseToLastPoint; //checks if the mouse is close to first point.if it is, it will enable the magnet effect
    private boolean isDrawingDone; // the condition for termination of Drawing

    //################################ Private Methods #######################################

    private boolean isMouseOnCanvas()
    {
        return (mouseX < canvasWidth);
    }

    //############################### Public Methods ########################################

    public static void log(String text)
    {
        if (DEBUG) System.out.println(text);
    }

    public static void main(String[] args)
    {

        //region Test Area
        /*
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(1,0));
        points.add(new Point(2,1));
        points.add(new Point(1,2));
        points.add(new Point(0,2));

        OTDP otdp = new OTDP(points);
        otdp.calculate();
        otdp.printBacktrack();
        */
        //endregion
        PApplet.main(Processing.class, args);
    }

    public void settings()
    {
        fullScreen();
//        size(1700,900);
    }

    public void setup()
    {
        //region Initializing Variables
        ui = new ArrayList<>();
        lastPoint = null;
        points = new ArrayList<>();
        pointsIndicesTextBoxes = new ArrayList<>();
        triangledLines = new ArrayList<>();
        lines = new ArrayList<>();
        tempLine = new Line();
        backgroundColor = new Color(39, 39, 39, 255).getColor();
        canvasColor = new Color(45, 45, 45, 255).getColor();
        canvasWidth = width - 340;
        canvasHeight = height;
        isMouseCloseToLastPoint = false;
        isDrawingDone = false;
        magnetDistance = 40;
        //endregion

        //region Creating UI Elements
        messageTextBox = new TextBox();
        messageTextBox.position = new Point(canvasWidth / 2, canvasHeight - 100);
        messageTextBox.textColor = new Color(214, 212, 212, 200);
        messageTextBox.textSize = 60;
        messageTextBox.setFont(this, "ARG/res/fonts/Bellerose.ttf");
        messageTextBox.isVisible = false;

        mousePositionTextBox = new TextBox();
        mousePositionTextBox.textColor = new Color(174, 174, 174, 255);
        mousePositionTextBox.horizontalTextAlignMode = TextAlignMode.RIGHT;
        mousePositionTextBox.textSize = 20;
        mousePositionTextBox.setFont(this, "null");


        TextBox demoTextBox = new TextBox();
        demoTextBox.position = new Point(width - 330, 150);
        demoTextBox.horizontalTextAlignMode = TextAlignMode.RIGHT;
        demoTextBox.textColor = new Color(89, 244, 66, 255);
        demoTextBox.textSize = 35;
        demoTextBox.text = "Optimal\nTriangulation\nOf A Polygon";
        demoTextBox.setFont(this, "ARG/res/fonts/FunSized.ttf");


        TextBox argTextBox = new TextBox();
        argTextBox.position = new Point(width - 330, 330);
        argTextBox.horizontalTextAlignMode = TextAlignMode.RIGHT;
        argTextBox.textColor = new Color(174, 174, 174, 255);
        argTextBox.textSize = 30;
        argTextBox.text = "Amir Reza Ghorbani";
        argTextBox.setFont(this, "ARG/res/fonts/Bellerose.ttf");

        TextBox ampTextBox = new TextBox();
        ampTextBox.position = new Point(width - 330, 360);
        ampTextBox.horizontalTextAlignMode = TextAlignMode.RIGHT;
        ampTextBox.textColor = new Color(174, 174, 174, 255);
        ampTextBox.textSize = 30;
        ampTextBox.text = "Amir Mohammad Packdel";
        ampTextBox.setFont(this, "ARG/res/fonts/Bellerose.ttf");

        TextBox arrTextBox = new TextBox();
        arrTextBox.position = new Point(width - 330, 390);
        arrTextBox.horizontalTextAlignMode = TextAlignMode.RIGHT;
        arrTextBox.textColor = new Color(174, 174, 174, 255);
        arrTextBox.textSize = 30;
        arrTextBox.text = "Ali Reza Ramzani";
        arrTextBox.setFont(this, "ARG/res/fonts/Bellerose.ttf");


        CircleButton exitBtn = new CircleButton(new Point(width - 60, 60), 100);
        exitBtn.backgroundColor = new Color(65, 40, 119, 0);
        exitBtn.hoverBackgroundColor = new Color(160, 20, 20, 200);
        exitBtn.textBox.text = "Exit";
        exitBtn.textBox.textColor = new Color(242, 242, 188, 200);
        exitBtn.textBox.textSize = 28;
        exitBtn.setOnClickListener(mouseButtonId -> exit());
        exitBtn.textBox.setFont(this, "null");


        RectButton startBtn = new RectButton(new Point(width - 300, height - 140), 260, 100);
        startBtn.setCornerRadius(5, 5, 5, 5);
        startBtn.backgroundColor = new Color(65, 40, 119, 0);
        startBtn.hoverBackgroundColor = new Color(15, 20, 20, 200);
        startBtn.textBox.text = "Start";
        startBtn.textBox.textColor = new Color(242, 242, 188, 200);
        startBtn.textBox.textSize = 40;
        startBtn.textBox.setFont(this, "null");
        startBtn.setOnClickListener(mouseButtonId ->
        {
            if (points.size() > 2 && isDrawingDone)
            {
                OTDP otdp = new OTDP(points);
                double cost = otdp.calculate();
                triangledLines = otdp.getLines();
                otdp.printTable();
                otdp.printBacktrack();
                for (Line l : triangledLines)
                {
                    l.lineColor = new Color(66, 244, 197, 255);
                    l.shadowColor = new Color(66, 244, 197, 255);
                }
                messageTextBox.isVisible = true;
                messageTextBox.textColor = new Color(214, 212, 212, 200);
                messageTextBox.text = "Minimum Cost Of Triangulation With " + (points.size()) + " Vertices: " + Math.round(cost * 1000d) / 1000d;
                startBtn.isEnabled = false;

            } else
            {
                messageTextBox.isVisible = true;
                messageTextBox.textColor = new Color(255, 20, 20, 255);
                messageTextBox.text = "Something is Wrong !!!";
            }
        });


        RectButton clearBtn = new RectButton(new Point(width - 300, height - 140 * 2), 260, 100);
        clearBtn.setCornerRadius(5, 5, 5, 5);
        clearBtn.backgroundColor = new Color(65, 40, 119, 0);
        clearBtn.hoverBackgroundColor = new Color(15, 20, 20, 200);
        clearBtn.textBox.text = "Clear";
        clearBtn.textBox.textColor = new Color(242, 242, 188, 200);
        clearBtn.textBox.textSize = 40;
        clearBtn.textBox.setFont(this, "null");
        clearBtn.setOnClickListener(mouseButtonId ->
        {
            messageTextBox.isVisible = false;
            pointsIndicesTextBoxes.clear();
            startBtn.isEnabled = true;
            messageTextBox.text = "";
            lines.clear();
            points.clear();
            triangledLines.clear();
            isDrawingDone = false;
            isMouseCloseToLastPoint = false;
            lastPoint = null;
            log("Canvas Cleared");
        });
        //endregion

        //region Add Objects to Render Queue
        ui.add(exitBtn);
        ui.add(startBtn);
        ui.add(clearBtn);
        ui.add(argTextBox);
        ui.add(ampTextBox);
        ui.add(arrTextBox);
        ui.add(demoTextBox);
        //endregion

    }

    public void draw()
    {
        frame.setTitle("Optimal Triangulation of a Polygon [ " + Math.round(frameRate ) + "FPS ]");
        background(backgroundColor);

        //region drawing the Canvas
        noStroke();
        fill(canvasColor);
        rect(0, 0, canvasWidth, canvasHeight);
        //endregion

        //region UI Elements Render Queue
        for (Widget widget : ui)
        {
            widget.update(this);
            widget.draw(this);
        }
        //endregion

        //region Draw Line
        for (Widget widget : lines)
        {
            widget.draw(this);
        }
        //################ Drawing the Temporal Line that Follows The Mouse Pointer #################
        if (lastPoint != null && isMouseOnCanvas() && !isDrawingDone)
        {
            Point mouseCoordinate = new Point(mouseX, mouseY);
            Point firstPoint = points.get(0);
            if (mouseCoordinate.distanceTo(points.get(0)) < magnetDistance && points.size() > 2)
            {
                tempLine.endPoint = firstPoint;
                isMouseCloseToLastPoint = true;
            } else
            {
                tempLine.endPoint = mouseCoordinate;
                isMouseCloseToLastPoint = false;
            }
            tempLine.draw(this);
        }

        //endregion

        //region Draw Triangled Lines
        for (Line trLine : triangledLines)
        {
            trLine.draw(this);
        }
        //endregion

        //region Draw Mouse Position
        if (isMouseOnCanvas())
        {
            mousePositionTextBox.position.x = mouseX + 10;
            mousePositionTextBox.position.y = mouseY - 10;
            mousePositionTextBox.text = mousePositionTextBox.position.toString();
            mousePositionTextBox.draw(this);
        }
        //endregion

        messageTextBox.draw(this);

        for (TextBox t : pointsIndicesTextBoxes)
        {
            t.draw(this);
        }

    }

    @Override
    public void mousePressed()
    {
        super.mousePressed();

        //region Drawing Lines
        if (isMouseOnCanvas() && !isDrawingDone)
        {

            Point newPoint = new Point(mouseX, mouseY);
            if (isMouseCloseToLastPoint) // if the mouse is close to first point
            {
                newPoint = points.get(0);
                isDrawingDone = true; // endPoint of drawing
                lines.add(new Line(lastPoint, newPoint));
                points.add(newPoint);
                log("Last Point Added");
            } else if (lastPoint != null)
            {
                lines.add(new Line(lastPoint, newPoint));
                lastPoint = newPoint;
                tempLine.startPoint = lastPoint;
                points.add(newPoint);

                //region Adding Index Number of Each Point
                TextBox textBox = new TextBox();
                textBox.textSize = 20;
                textBox.setTextColor(new Color(255, 255, 0, 255));
                textBox.setFont(this, "null");
                textBox.position.x = mouseX;
                textBox.position.y = mouseY - 30;
                pointsIndicesTextBoxes.add(textBox);
                textBox.text = String.valueOf(pointsIndicesTextBoxes.size());
                //endregion

                log("" + points.size() + "'nd Point Added");
            } else
            {
                lastPoint = newPoint;
                tempLine.startPoint = lastPoint;
                points.add(newPoint);

                //region Adding Index Number of Each Point
                TextBox textBox = new TextBox();
                textBox.textSize = 20;
                textBox.setTextColor(new Color(255, 255, 0, 255));
                textBox.setFont(this, "null");
                textBox.position.x = mouseX;
                textBox.position.y = mouseY - 30;
                pointsIndicesTextBoxes.add(textBox);
                textBox.text = String.valueOf(pointsIndicesTextBoxes.size());
                //endregion
                log("First Point Added");
            }
        }
        //endregion

        //region Mouse Event Handling for Clickable Widgets
        for (Widget widget : ui)
        {
            widget.mouseEvent(this);
        }
        //endregion
    }

}
