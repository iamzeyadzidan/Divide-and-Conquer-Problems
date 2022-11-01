import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RunnerTwo {
    public static void main (String[] args) throws IOException {
        try {
            // Modify the file path to output path
            String[] filePath = args[0].split("\\\\");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < filePath.length - 1; i++)
                sb.append(filePath[i]).append("\\");
            sb.append("output.txt");
            String outputPath = sb.toString();

            // Scan from the file
            Scanner scanner = new Scanner(new File(args[0]));
            int n;
            ArrayList<Point> pointList;
            ArrayList<Integer> outputList = new ArrayList<>();
            String nextLine;
            String[] pointAsString;

            while (scanner.hasNext()) {
                pointList = new ArrayList<>();
                n = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < n; i++) {
                    nextLine = scanner.nextLine();
                    pointAsString = nextLine.split(" ");
                    pointList.add(new Point(Integer.parseInt(pointAsString[0]), Integer.parseInt(pointAsString[1])));
                }
                outputList.add(solveMaximumSideLength(pointList));
            }
            scanner.close();
            new OutputPrinter().output(outputList, outputPath);
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int solveMaximumSideLength(ArrayList<Point> points) {
        return new MaxLenSolver().solve(points);
    }
}
