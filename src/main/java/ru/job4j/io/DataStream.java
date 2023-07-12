package ru.job4j.io;

import java.io.*;

/**
 * Значения нескольких массивов записываются в файл. И из него
 * читаются и выводятся в консоль.
 */
public class DataStream {
    public static void main(String[] args) {
        String path = "data/dataOutput.txt";
        String[] names = {"util1", "util2", "util3"};
        int[] amount = {5, 7, 2};
        boolean[] checked = {true, false, true};
        /*Создание потока out and in  в двоичном формате*/
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream in = new DataInputStream(new FileInputStream(path))
        ) {
            /* пишем данные в двоичном формате из всех массивов*/
            for (int i = 0; i < names.length; i++) {
                /*writeUTF - переводим строки в двоичный формат*/
                out.writeUTF(names[i]);
                out.writeInt(amount[i]);
                out.writeBoolean(checked[i]);
            }
            /*true- при конце файла, цикл закончится EOFException*/
            while (true) {
                /*читаем в том же порядке и в те же типы данных и выводим на консоль*/
                String n = in.readUTF();
                int a = in.readInt();
                boolean c = in.readBoolean();
                System.out.println("Наименовани : " + n + ", Количество : " + a + ", Проверен : " + c);
            }
        } catch (EOFException e) { /* ловим при конце файла - End of file exception.*/
            System.out.println("Достигнут конец файла");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
