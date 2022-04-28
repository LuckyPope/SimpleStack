package ru.vsu.cs.zhilyaev;

public class SelectionSort {
    public static <T extends Comparable<T>> void sort(T[] data, int from, int to) {
        int x = 1;
        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
            x = -1;
        }

        if(from < 0) {
            from = 0;
        }
        if (to > data.length) {
            to = data.length;
        }

        for(int i = from; i < to; i++) {
            int min = i;
            for(int j = i + 1; j < to; j++) {
                if(x*data[j].compareTo(data[min]) < 0) {
                    min = j;
                }
            }
            T tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }
}
