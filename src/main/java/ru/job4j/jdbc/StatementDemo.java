package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.StringJoiner;

/**
 * 0.1. Statement[#379306].
 * для операций в подключенной БД еть интерфейсы Statement, PreparedStatement.
 * Объект Statement
 * В JDBC (Java Database Connectivity) объект Statement используется для выполнения
 * SQL-запросов к базе данных. С помощью объекта Statement можно выполнять три типа запросов:
 * <p>
 * DDL (Data Definition Language): Например, CREATE, DROP, ALTER.
 * DML (Data Manipulation Language): Например, INSERT, UPDATE, DELETE.
 * DQL (Data Query Language): Например, SELECT.
 * Метод execute
 * Метод execute объекта Statement используется для выполнения SQL-запросов,
 * которые могут быть любого типа (DDL, DML, DQL). Этот метод возвращает boolean:
 * <p>
 * true, если первый результат выполнения запроса является объектом ResultSet (например, для SELECT запросов).
 * false, если первый результат выполнения запроса является счетчиком обновлений или если нет результата.
 */
public class StatementDemo {
    /**
     * @param -getConnection(url,login,password) - метод загружает драйвер JDBC для PostgreSQL
     *                                           расположенной на localhost на порту 5432 с именем базы данных postgres
     *                                           используя имя пользователя postgres и пароль password.
     * @return
     * @throws Exception
     */
    private static Connection getConnection() throws Exception {
        /*Подключаемся к базе данных postgres*/
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String login = "postgres";
        String password = "password";
        /*DriverManager - испльзуется для загрузки баз данных и помогает соединиться с базой.*/
        return DriverManager.getConnection(url, login, password);
        /*getConnection - использует драйверы для устновления соединения с указанным url базы данных*/
    }

    /**
     * Устанавливается соединение с базой данных, создается объект Statement
     * выполняется SQL-запрос для создания таблицы demo_table если она еще не существует
     * Connection - й соединение с базой данных, которое можно использовать
     * getConnection() -  для получения объекта Connection с драйверами и данными базы данных.
     * для выполнения запросов и других операций с базой данных.
     * Statement -
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );
                /*SQL-запрос для создания таблицы. Метод execute отправляет запрос на сервер базы данных и выполняет его.*/
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        /*Создаем строку-разделитель из 30 дефисов, завершающуюся символом новой строки*/
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        /* Создаем строку заголовка таблицы с двумя колонками "NAME" и "TYPE", выровненными по ширине 15 символов*/
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        /*Создаем StringJoiner с начальной, конечной и промежуточной строками-разделителями*/
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            /*Выполняем SQL-запрос для выборки одной строки из указанной таблицы*/
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            /*Итерация по всем колонкам в результате выборки*/
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                /*Добавляем информацию о каждой колонке (имя и тип) в buffer*/
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        /* Возвращаем строку, представляющую схему таблицы*/
        return buffer.toString();
    }
}
