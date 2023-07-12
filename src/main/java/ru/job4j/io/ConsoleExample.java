package ru.job4j.io;

import java.io.Console;

/**
 * Обратите внимание, что при запуске программы из
 * среды разработки доступ к объекту Console может быть недоступен
 */
public class ConsoleExample {
    public static void main(String[] args) {
        /*объект получается при вызове System.console()*/
        Console console = System.console();
        if (console != null) {
            /**
             * Чтение строки с консоли
             * - readLine() - читает с консоли строку, которую ввёл пользователь. Сохраняет данные в String.*/

            String input = console.readLine("Введите значение : ");
            /**
             * printf - выводит на консоль указанные аргунен'l'Ы,
             используя формат, определяемый параметром*/
            console.printf("Вы ввели : %s\n", input);
        /*
        format() сохраняет отформатированную строку
         */
            console.format("Введите пароль: ");
        /*
        readPassword() - читает с консоли строку, которую ввёл пользователь.
         Отличие от метода readLine() в том, что вводимые пользователем символы
         не будут отображаться на консоли в целях безопасности.
         Сохраняет данные в char[] - массив байтов.
         */
            char[] password = console.readPassword();
            console.format("Вы ввели пароль: %s\n", new String(password));
            /**
             Возвращает ссылку на поток записи типа Wri ter,
             связанный с консолью
             */
            console.writer().println("Просто строка на консоли");
            // Очистка консоли
            console.flush();
        } else {
            System.out.println("Консоль не доступна.");
        }
    }
}
