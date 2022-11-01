public class RSelector {

    // Selects the i-th element in an array
    public int rSelect(int[] integers, int lBound, int uBound, int i) throws ArrayIndexOutOfBoundsException {
        // Partition around the random pivot (normal partitioning subroutine)
        int pIndex = (int) (Math.random() * (uBound - lBound + 1)) + lBound;              // Randomly choose a pivot index, (uBound + 1 = n)
        int pivot = integers[pIndex];
        int pivotIndex = Partitioner.partition(integers, lBound, uBound, pivot, false);

        if (pivotIndex == i)  // Element found
            return pivot;

        /*
         * If not found, check where the element could be in the
         * two left sub-arrays.
         */
        if (pivotIndex > i)   // Element is in the smaller sub-array
            return rSelect(integers, lBound, pivotIndex - 1, i);
        else return rSelect(integers, pivotIndex + 1, uBound, i); // Element is in the greater sub-array
    }
}
