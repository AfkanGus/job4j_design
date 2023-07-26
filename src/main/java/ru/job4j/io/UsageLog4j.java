package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Класс деманстрирует записи логов в соответствующий лог-файл или
 * другой назначенный источник логирования, в зависимости от настроек Log4j
 * <p>
 * LogManager отвечает за создание и настройку логгеров.
 * Logger предоставляет методы для записи логов разного уровня.
 * LOG - статическая константа LOG, которая будет использоваться для записи логов.
 * LogManager.getLogger() создает новый логгер, связанный с классом UsageLog4j,для записи логов.
 * <p>
 * Уровень TRACE используется для отладочных сообщений, которые обычно не нужны в продакшене.
 * Уровень DEBUG используется для отладочных сообщений, которые могут быть полезны при разработке и отладке.
 * Уровень INFO используется для информационных сообщений о ходе выполнения программы.
 * Уровень WARN используется для сообщений о предупреждениях или потенциальных проблемах.
 * Уровень ERROR используется для сообщений об ошибках или исключительных ситуациях.
 */
public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        /* Эта строка записывает лог сообщения с разными уровнями*/
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
