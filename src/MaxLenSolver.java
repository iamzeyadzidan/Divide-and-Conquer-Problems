import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxLenSolver {

    /**
     * Solves the problem for the square with the maximum side length
     * given an array of points.
     * */
    public int solve(List<Point> points) {
        List<Point> xSorted = cloneArray(points);
        xSorted.sort(Comparator.comparingInt(p -> (int) p.getX()));
        points.sort(Comparator.comparingInt(p -> (int) p.getY()));
        return closestPair(xSorted, points);
    }

    /**
     * Computes the maximum length between the closest pair of points
     */
    private int closestPair(List<Point> xSorted, List<Point> ySorted) {
        // Base Case
        if (xSorted.size() < 4) {
            // Brute force through the points if they are 3 or less.
            double maximumSideLength = Double.POSITIVE_INFINITY;
            for (int i = 0; i < xSorted.size(); i++) {
                for (int j = i + 1; j < xSorted.size(); j++) {
                    // Get minimum maximum side length possible between a pair of points.
                    int xDifference = (int) Math.abs(xSorted.get(i).getX() - xSorted.get(j).getX());
                    int yDifference = (int) Math.abs(xSorted.get(i).getY() - xSorted.get(j).getY());
                    maximumSideLength = Math.min(maximumSideLength, Math.max(xDifference, yDifference));
                }
            }
            return (int) maximumSideLength;
        }

        // Divide n Conquer approach for more than 3 points.
        int xSize = xSorted.size();
        List<Point> leftPlaneXSorted = xSorted.subList(0, xSize / 2);
        List<Point> leftPlaneYSorted = cloneArray(leftPlaneXSorted);
        // Sort the left sub problem points with respect to Y
        leftPlaneYSorted.sort(Comparator.comparingInt(p -> (int) p.getY()));

        // Repeat for right sub problem.
        List<Point> rightPlaneXSorted = xSorted.subList(xSize / 2, xSize);
        List<Point> rightPlaneYSorted = cloneArray(rightPlaneXSorted);
        rightPlaneYSorted.sort(Comparator.comparingInt(p -> (int) p.getY()));

        // Recursive call on each sub problem.
        int dLeft = closestPair(leftPlaneXSorted, leftPlaneYSorted);
        int dRight = closestPair(rightPlaneXSorted, rightPlaneYSorted);

        // Check for split pairs (not in either left or right sub problems).
        int delta = Math.min(dLeft, dRight);
        int dSplit = closestSplitPair(xSorted, ySorted, delta);

        // Finally return the lowest value among the 3 values.
        return Math.min(Math.min(dLeft, dSplit), Math.min(dRight, dSplit));
    }

    /**
     * Computes the maximum length between a split pair of points.
     * */
    private int closestSplitPair(List<Point> xSorted, List<Point> ySorted, int delta) {
        // Get x dash as the median of the sorted points with respect to X axis.
        int xDash = (int) xSorted.get((xSorted.size() - 1) / 2).getX();
        /*
        * Initialize a point array list (spaceY),
        * where every point in spaceY has its x = xDash +/- delta
        */
        List<Point> spaceY = new ArrayList<>();
        for (Point point : ySorted)
            if (point.getX() <= (xDash + delta) && point.getX() >= (xDash - delta))
                spaceY.add(point);
        // Since ySorted is sorted with respect to Y axis, spaceY will be sorted as well.
        int bestDelta = delta, xDifference, yDifference;
        for (int i = 0; i < spaceY.size(); i++)
            for (int j = i + 1; j - i < 8 && j < spaceY.size(); j++) {
                Point pointOne = spaceY.get(i);
                Point pointTwo = spaceY.get(j);
                /*
                * If maximum side length acquired from X-axis
                * is less than bestDelta, assign it to bestDelta.
                * But if the one acquired from Y-axis is less, assign it to bestDelta.
                */
                xDifference = (int) Math.abs(pointOne.getX() - pointTwo.getX());
                yDifference = (int) Math.abs(pointOne.getY() - pointTwo.getY());
                bestDelta = Math.min(bestDelta, Math.max(xDifference, yDifference));
            }
        return bestDelta;
    }

    private List<Point> cloneArray(List<Point> points) {
        return new ArrayList<>(points);
    }
}