import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RunnerOne {
    public static void main (String[] args) {
        try {
            int power;
            long[] outputRandomized = new long[8];
            long[] outputDeterministic = new long[8];
            long[] outputNaive = new long[8];

            for (power = 0; power < 8; power++) {
                for (int i = 0; i < 1; i++){
                    int n = (int) Math.pow(10, power);
                    int[] integers = new int[n];
                    for (int j = 0; j < n; j++)
                        integers[j] = (int) (Math.random() * (n + 1));

                    int[] copyOfIntegers;
                    long stopWatch;

                    copyOfIntegers = Arrays.copyOf(integers, n);
                    stopWatch = System.currentTimeMillis();
                    System.out.println(RunnerOne.getMedian("rSelect", copyOfIntegers, n));
                    outputRandomized[power] += (System.currentTimeMillis() - stopWatch);
                    System.out.println("Randomized Selection Time (ms) = " + (outputRandomized[power]) / (i + 1));

                    copyOfIntegers = Arrays.copyOf(integers, n);
                    stopWatch = System.currentTimeMillis();
                    System.out.println(RunnerOne.getMedian("dSelect", copyOfIntegers, n));
                    outputDeterministic[power] += (System.currentTimeMillis() - stopWatch);
                    System.out.println("Deterministic Selection Time (ms) = " + (outputDeterministic[power]) / (i + 1));

                    copyOfIntegers = Arrays.copyOf(integers, n);
                    stopWatch = System.currentTimeMillis();
                    System.out.println(RunnerOne.getMedian("Naive", copyOfIntegers, n));
                    outputNaive[power] += (System.currentTimeMillis() - stopWatch);
                    System.out.println("Naive Quicksort Selection Time (ms) = " + (outputNaive[power]) / (i + 1));
                }
                outputRandomized[power] /= 100;
                outputDeterministic[power] /= 100;
                outputNaive[power] /= 100;
            }
            ArrayList<String> outputAverage = new ArrayList<>();
            outputAverage.add(Arrays.toString(outputRandomized));
            outputAverage.add(Arrays.toString(outputDeterministic));
            outputAverage.add(Arrays.toString(outputNaive));
            System.out.println(System.getProperty("user.dir"));
            new OutputPrinter().output(outputAverage, System.getProperty("user.dir"));
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMedian(String method, int[] integers, int n) {
        switch (method) {
            case "rSelect":
                return new RSelector().rSelect(integers, 0, n - 1, (n - 1) / 2);
            case "dSelect":
                return new DSelector().selectMedian(integers, 0, n - 1);
            case "Naive":
                new NaiveQuickSort().sort(integers, 0, n - 1);
                return integers[(n - 1) / 2];
        }
        return 0;
    }
}
