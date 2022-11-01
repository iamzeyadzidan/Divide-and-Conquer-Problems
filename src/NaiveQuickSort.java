public class NaiveQuickSort {
    // Sorts a given int[] array using quicksort
    public void sort(int[] integers, int lBound, int uBound) throws ArrayIndexOutOfBoundsException {
        if(lBound >= uBound)
            return;
        int pivotIndex = Partitioner.partition(integers, lBound, uBound, integers[uBound], true);
        sort(integers, lBound, pivotIndex - 1);
        sort(integers, pivotIndex + 1 , uBound);
    }
}
