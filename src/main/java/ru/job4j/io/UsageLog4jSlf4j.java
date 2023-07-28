package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3. Slf4j - вывод переменных.
 * slf4j используется шаблон для подстановки переменных, вместо сложения строк.
 * User info name : - Первый параметр метода - это шаблон. {} - это отметки.
 * Первая метка заменится первым параметром и так далее.
 */
public class UsageLog4jSlf4j {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsageLog4jSlf4j.class.getName());

    public static void main(String[] args) {
        String name = "Afkan Guseinov";
        int age = 36;
        long numLog = 12345L;
        short numShort = 31999;
        byte numBytes = 127;
        char gender = 'M';
        double weight = 65.5;
        float height = 1.83f;
        boolean isStudent = true;
        LOGGER.debug("Основная информация имя: {}, возраст : {} лет", name, age);
        LOGGER.debug("Пол : {}, Вес : {} кг., Рост : {} см.", gender, weight, height);
        LOGGER.debug("Соц. статус : {} - студент", isStudent);
        LOGGER.debug("Число из short : {}", numShort);
        LOGGER.debug("Число из long : {}", numLog);
        LOGGER.debug("Число из byte : {}", numBytes);
    }
}
