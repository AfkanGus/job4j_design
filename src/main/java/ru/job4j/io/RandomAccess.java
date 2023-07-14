package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 20. Файл произвольного доступа. RandomAccessFile  [#505000  [#505000]].
 * Важно! Запись данных в произвольном месте будет затирать существующие данные,
 * поэтому запись в произвольном месте можно использовать только для замены данных в
 * пределах того же типа (в том же диапазоне памяти).
 */
public class RandomAccess {
    public static void main(String[] args) {
        try {
            /* режим rw - при запуске создается файл*/
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            /*запишем несколько значений в байтовом формате*/
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            /*Указатель наход.в конце ф.,чтобы считать и вывсети напечать, переместим ук.в начало seek(0)*/
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());

            /*Прочтем char. переместим на 4.т.к.перед char тип int хранится в первых 4 байтах (0, 1, 2, 3)
            указатель находится на поз. 6(4байта int + 2 байта char).*/
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            /*Проверить положение указателя можно с помощью метода getFilePointer():*/
            System.out.println(randomAccess.getFilePointer());

            /*Что бы переписать char, нужно переместить указатель перед char и записать его еще раз*/
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());

            /*Можно установть указатель в конец файла методом length() и дописать новые данные*/
            randomAccess.seek(randomAccess.length());
            System.out.println("After Boolean : " + randomAccess.getFilePointer());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
