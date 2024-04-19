package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 7. Балансир [#505083].
 * распределяет данные из итератора по переданным ему спискам.
 */
public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int collections = 0;
        while (source.hasNext()) {
            int el = source.next();
            ArrayList<Integer> list = nodes.get(collections);
            list.add(el);
            /*циклическое перемещение между коллекц.*/
            if (collections == nodes.size() - 1) {
                collections = 0;
            } else {
                collections++;
            }
        }
    }
}
