package ru.job4j.jdbc;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 0.2. PreparedStatement [#379307]
 */
public class ImportDB {
    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(line -> {
                String[] stingParts = line.split(";");
                if (stingParts.length != 2 || stingParts[0].isEmpty() || stingParts[1].isEmpty()) {
                    throw new IllegalArgumentException(line);
                }
                users.add(new User(stingParts[0], stingParts[1]));

            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO users(name,email) VALUES(?,?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class
                .getClassLoader()
                .getResourceAsStream("appImport.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
