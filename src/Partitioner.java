public class Partitioner {
    // Partitions an array around a pivot
    public static int partition(int[] integers, int lBound, int uBound, int pivot, boolean naiveQuickSort) {
        int index = lBound - 1;
        for (int i = lBound; i < uBound + 1; i++) {
            /*
             * Keep swapping elements to the head of the array
             * until a greater element is encountered.
             * */
            if (pivot > integers[i]) {
                index++;
                Swapper.swap(integers, index, i);
            }
        }
        if (!naiveQuickSort) {
            int pivotNewIndex = lBound;
            for (int i = lBound; i < uBound + 1; i++) {
                // Now get the new index of the pivot (assuming it might change in the runtime).
                if (pivot == integers[i]) {
                    pivotNewIndex = i;
                    break;
                }
            }
            // Swap the pivot with the next index
            Swapper.swap(integers, index + 1, pivotNewIndex);
            return (index + 1);
        }
        // Swap the pivot with the next index
        Swapper.swap(integers, index + 1, uBound);
        return (index + 1);
    }
}
