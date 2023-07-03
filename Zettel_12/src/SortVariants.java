import java.util.Comparator;
import java.util.List;

public class SortVariants {
    private static <E> void naiveQuickSort(List<E> list, Comparator<E> comparator, int left, int right) {
        // TODO: 2. a)
    }

    private static <E> void randomQuickSort(List<E> list, Comparator<E> comparator, int left, int right) {
        // TODO: 2. b)
    }

    public static <E> void naiveQuickSort(List<E> list, Comparator<E> comparator) {
        naiveQuickSort(list, comparator, 0, list.size() - 1);
    }

    public static <E> void randomQuickSort(List<E> list, Comparator<E> comparator) {
        randomQuickSort(list, comparator, 0, list.size() - 1);
    }

    public static <E> void newRecursiveSort(List<E> list, Comparator<E> comparator) {
        // TODO: 2. c)
    }

    public static <E> void shuffle(List<E> list) {
        // TODO: 2. d)
    }

    public static <E> void bogoSort(List<E> list, Comparator<E> comparator) {
        // TODO: 2. d)
    }
}
