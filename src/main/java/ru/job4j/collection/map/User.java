package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        GregorianCalendar birthday1 = new GregorianCalendar(2022, Calendar.FEBRUARY, 1, 1, 1, 1);
        GregorianCalendar birthday2 = new GregorianCalendar(2022, Calendar.FEBRUARY, 1, 1, 1, 1);
        User user1 = new User("Tom", 1, birthday1);
        User user2 = new User("Tom", 1, birthday2);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (User user : map.keySet()) {
            System.out.println(map);
        }
    }
}
