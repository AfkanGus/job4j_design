package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.2. PreparedStatement [#379307]
 * Особенность объекта PreparedStatement заключается
 * в том, что при создании ему передается
 * SQL - запрос с параметрами.
 * методы execute(), executeUpdate() и executeQuery()
 * интерфейса PreparedStatement не принимают никаких аргументов,
 * в отличие от одноименных методов Statement.
 * Они выполняют указанный при создании объекта SQL-запрос
 * с подставленными аргументами.
 */
public class PreparedStatementDemo {
    private Connection connection;

    /*метод создания подключения*/
    public void initConnection() throws Exception {
        /*регистрация драйвера в системе*/
        Class.forName("org.postgresql.Driver");
        /*подключаемся к БД через jdbc*/
        String url = "jdbc:postgresql://localhost:5432//postgres";
        String login = "postgres";
        String password = "password";
        /*загрузим драйверы и установим соединение*/
        connection = DriverManager.getConnection(url, login, password);
    }

    public void insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             /*параметры - места ставки аргументов «?» */
                             "INSERT INTO cities(name,population) VALUES (?,?)")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE cities SET name = ?, population = ? WHERE id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            /*если вернет 0 ТО обновление не произошло,проверяем,что результат больше 0.*/
            result = statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*метод получаниея всех элементов
    В методе findAll вы создаете PreparedStatement для выполнения запроса,
     затем получаете ResultSet, который содержит результаты запроса.
      Далее вы перемещаетесь по строкам результата с помощью метода next() и
      извлекаете данные из каждой строки.
     * ResultSet — это объект, который хранит результаты SQL-запроса,
     * выполненного с помощью Statement или PreparedStatement.
     *  Он позволяет перемещаться по результатам запроса и извлекать данные.*/
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM cities")) {
            /* ResultSet мы использовали для сохранения возсращаемых строк*/
            try (ResultSet resultSet = statement.executeQuery()) {
                /*чтоб сдвинуть курсор испльз.next() если он возвращает true, то сдвиг произошел и мы можем получить
                данные.*/
                while (resultSet.next()) {
                    /* Извлекаем данные из текущей строки и создаем новый объект City*/
                    cities.add(new City(
                            /*для доспута к эл записи исп.мет.get*/
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    /*получение id  вставленного элемента*/
    public City insertWithoutReturningId(City city) {
        /**
         * Здесь мы подготавливаем SQL-запрос на вставку и указываем Statement.RETURN_GENERATED_KEYS для получения
         * сгенерированных ключей.
         */
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO cities(name, population) VALUES (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            /**
             * После выполнения запроса INSERT мы используем метод getGeneratedKeys,
             * который возвращает объект ResultSet. Этот ResultSet
             * содержит ключи, сгенерированные в результате выполнения данного запроса.
             */
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                /*перемещает курсор на первую строку в ResultSet.*/
                if (generatedKeys.next()) {
                    /*получает значение первого столбца (первого сгенерированного ключа) в текущей строке ResultSet.*/
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }
}






















