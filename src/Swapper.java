public class Swapper {
    // Swaps two elements in an array
    public static void swap(int[] integers, int firstIndex, int secondIndex) {
        int temporaryElement = integers[secondIndex];
        integers[secondIndex] = integers[firstIndex];
        integers[firstIndex] = temporaryElement;
    }
}
