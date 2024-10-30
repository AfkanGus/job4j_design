package ru.job4j.memory;

/**
 * 2. Stack, Heap, Metaspace [#504932].
 * Stack - хранит знчн.прмтв.тп созданные в методах и ссылки на обкты.
 * stack заполняется и очищяется в процессе рбты пргрмы.
 * Heap - хранит обкты. под которые выделяется помять во время рбт пргмы.
 */
public class Memory {
    public static void main(String[] args) {
        int x = 0;
        Object obj = new Object();
        Memory example = new Memory();
        example.action(obj);
    }

    public void action(Object parameter) {
        String str = parameter.toString();
        System.out.println(str);
    }
}
