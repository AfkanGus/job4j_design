package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

/**
 * - flush() - принудительно выводит в консоль все данные, находящиеся в буфере в данный момент.
 */
public class ConsoleDemo {
    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console(); // получаем объект консоли
        if (console == null) { /* Данная проверка обязательна, так как программа может
         быть запущена из разных мест, соответственно, нужно удостовериться, что из
         этого места есть доступ к консоли (командной строке).*/
            System.out.println("Консоль недоступна");
            /*Если объект не null выходим при помощи return.*/
            return;
        }
        /*- readLine() - читает с консоли строку, которую ввёл пользователь. Сохраняет данные в String.*/
        login = console.readLine("%s", "Введите логие : ");
        /**
         * - format() и printf() - эти методы мы уже знаем.
         * format() сохраняет отформатированную строку, а printf() выводит
         * отформатированную строку на печать без сохранения.
         */
        console.printf("Ваш логин : %s\n", login);
        /**
         *  readPassword() - читает с консоли строку, которую ввёл пользователь.
         *  Отличие от метода readLine() в том, что вводимые пользователем символы
         *  не будут отображаться на консоли в целях безопасности.
         *  Сохраняет данные в char[] - массив байтов.
         */
        charPassword = console.readPassword("%s", "Enter пароль : ");
        System.out.println("Ваш пароль" + String.valueOf(charPassword));
        /*метод fill() затирает все данные в массиве пробелами:*/
        Arrays.fill(charPassword, ' ');
    }
}
