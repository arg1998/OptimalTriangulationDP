package ARG.libs;


public class Point
{
    public int x;
    public int y;

    private int square(int a)
    {
        return (int) Math.pow(a, 2);
    }


    public Point()
    {
        this.clear();
    }

    public double distanceTo(Point otherPoint)
    {
        return Math.sqrt(square(otherPoint.x - this.x) + square(otherPoint.y - this.y));
    }

    public double distanceTo(int otherX, int otherY)
    {
        return Math.sqrt(square(otherX - this.x) + square(otherY - this.y));
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean isEqualTo(Point otherPoint)
    {
        return (this.x == otherPoint.x && this.y == otherPoint.y);
    }

    public void clear()
    {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
