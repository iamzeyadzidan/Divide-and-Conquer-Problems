public class DSelector {
    public int selectMedian(int[] integers, int lBound, int uBound) throws ArrayIndexOutOfBoundsException {
        if(lBound >= uBound)
            return integers[uBound];

        int pivot = dSelect(integers, lBound, uBound);
        int pivotNewIndex = Partitioner.partition(integers, lBound, uBound, pivot, false);
        if (pivotNewIndex == ((integers.length - 1) / 2)) // Median found.
            return pivot;
        else if (pivotNewIndex < ((integers.length - 1) / 2))
            return selectMedian(integers, pivotNewIndex + 1, uBound);
        else return selectMedian(integers, lBound, pivotNewIndex - 1);
    }

    // Retrieves the median of medians of a given int[] array
    private int dSelect(int[] integers, int lBound, int uBound) {
        int n = integers.length;
        if (n <= 5) {
            new NaiveQuickSort().sort(integers, lBound, uBound);
            return integers[(n - 1) / 2];
        }
        n = uBound - lBound + 1;
        int[] medians = (n % 5 == 0) ? new int[n / 5] : new int[(n / 5) + 1];
        int counter = 0;
        for (int i = lBound; i < uBound + 1; i += 5) {
            // If the next group is 5, sort normally
            if ((i + 4) < uBound) {
                new NaiveQuickSort().sort(integers, i, i + 4);
                medians[counter++] = integers[((2 * i) + 5) / 2];
            }
            // Handle the special case of less-than-5 group. (Typically Last Group)
            else {
                new NaiveQuickSort().sort(integers, i, n - 1);
                medians[counter] = integers[((uBound - i) / 2) + i];
            }
        }

        // Recursive call on the medians to get the medians of medians and so on.
        return dSelect(medians, 0, (n - 1) / 5);
    }
}
