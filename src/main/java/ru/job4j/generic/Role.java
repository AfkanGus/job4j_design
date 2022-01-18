package ru.job4j.generic;

/**
 * Role -  модель данных унаследованная от Base.
 */
public class Role extends Base {
    private final String role;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
