package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 0. Виды ссылок [#6854]
 * JVM удалить объект, если память заканчивается,
 * но сохраняет доступ к объекту, пока он не удален.
 */
public class SoftDemo {
    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1() {
        Object strong = new Object();
        /*Безопасная ссылка на объект находится в переменной soft:*/
        SoftReference<Object> soft = new SoftReference<>(strong);
        strong = null; /*ссильная ссылка удаляется*/
        System.out.println(soft.get()); /*Безопасная ссылка существует*/
    }

    /**
     * Каждая SoftReference оборачивает объект,
     * создавая безопасную ссылку на него. Это позволяет
     * сборщику мусора удалить объект, если памяти недостаточно,
     * но объект остается доступным через безопасную ссылку до этого момента.
     */
    private static void example2() {
        List<SoftReference<Object>> objects = new ArrayList<>();
        /*Цикл создает 100 миллионов объектов типа Object и
         оборачивает каждый из них в SoftReference.*/
        for (int i = 0; i < 100_000_000; i++) {
            objects.add(new SoftReference<Object>(new Object() {
                /**
                 * Каждому создаваемому объекту в цикле присваивается
                 * уникальная строка, основанная на текущем времени
                 * Это используется для обеспечения некоторого различия между объектами.
                 */
                String value = String.valueOf(System.currentTimeMillis());

                /*Когда объект будет удален сборщиком мусора,
                метод finalize выведет сообщение Object removed!.*/
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed!");
                }
                /*В результате в памяти создается огромное количество объектов.
                 Если выделенная память для кучи (heap) мала, объекты начнут
                 удаляться сборщиком мусора.*/
            }));
        }
        System.gc(); /*Принудительный вызов сборщика мусора.*/
        int liveObject = 0;
        for (SoftReference<Object> ref : objects) { /*Перебирается список objects.*/
            /*Если объект еще существует в памяти, возвращается ссылка на объект.*/
            Object object = ref.get();
            /*Если объект был удален сборщиком мусора, метод get() возвращает null.*/
            if (object != null) {
                /*Если объект существует, счетчик liveObject увеличивается.*/
                liveObject++;
            }
        }
        /*В конце выводится количество объектов, которые остались в памяти.*/
        System.out.println(liveObject);
    }
}
