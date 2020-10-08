package ARG;

import java.util.ArrayList;
import java.util.Arrays;

import ARG.libs.Point;
import ARG.libs.UI.Line;

public class OTDP
{
    private ArrayList<Point> points;
    private int size;
    private double[][] table;
    private int[][] backtrack;

    public OTDP(ArrayList<Point> points)
    {
        this.points = points;
        this.points.remove(points.size() - 1);
        this.size = points.size();
        backtrack = new int[size][size];
        table = new double[size][size];
        for (int i = 0; i < size; i++)
        {
            Arrays.fill(table[i], 0);
            Arrays.fill(backtrack[i], 0);
        }
    }


    public double calculate()
    {
        if (size < 3)
            return 0;

        for (int gap = 0; gap < size; gap++)
        {
            for (int i = 0, j = gap; j < size; i++, j++)
            {
                if (j < i + 2)
                    table[i][j] = 0.0;
                else
                {
                    table[i][j] = Double.MAX_VALUE;
                    for (int k = i + 1; k < j; k++)
                    {
                        double val = table[i][k] + table[k][j] + cost(i, j, k);
                        if (table[i][j] >= val)
                        {
                            table[i][j] = val;
                            backtrack[i][j] = k;
                        }
                    }
                }
            }
        }
        return table[0][size - 1];

    }

    private double cost(int i, int j, int k)
    {
        Point a = points.get(i), b = points.get(j), c = points.get(k);
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a);
    }

    public void printSolution()
    {

        for (int i = 0; i < size; i++)
        {
            int x = i;
            int y = size - i - 1;
            int value = backtrack[x][y];


            if (value == -1) break;
            System.out.println("" + x + ", " + y + " -> " + value);
        }
    }

    public ArrayList<Line> getLines()
    {
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < size; i++)
        {
            int x = i;
            int y = size - i - 1;
            int value = backtrack[x][y];
            if (value == -1) break;

            //region Check if the Line is unique or not
            int distance = Math.abs(x - y);
            if (distance >= 2 && distance != size - 1)
            {
                lines.add(new Line(points.get(x), points.get(y)));
            }

            distance = Math.abs(y - value);
            if (distance >= 2 && distance != size - 1)
            {
                lines.add(new Line(points.get(y), points.get(value)));
            }

            distance = Math.abs(value - x);
            if (distance >= 2 && distance != size - 1)
            {
                lines.add(new Line(points.get(value), points.get(x)));
            }
            //endregion
        }

        return lines;


    }

    public void printTable()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(String.valueOf(table[i][j]) + "  ");
            }
            System.out.println();
        }
    }

    private void printEdges(int i, int j)
    {
        if (backtrack[i][j] == 0) return;
        if (i + 1 < backtrack[i][j]) System.out.println("Diagonal " + i + "-" + backtrack[i][j]);
        if (backtrack[i][j] + 1 < j) System.out.println("Diagonal " + backtrack[i][j] + "-" + j);
        printEdges(i, backtrack[i][j]);
        printEdges(backtrack[i][j], j);

    }

    public void printBacktrack()
    {

        System.out.println("Printing The Result : ");
        printEdges(1, size -1);
        System.out.println("\nPrinting The backTrack Table : ");
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.print(String.valueOf(backtrack[i][j]) + "  ");
            }
            System.out.println();
        }
    }

}
