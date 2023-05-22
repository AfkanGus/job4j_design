package ru.job4j.question;

import java.util.Objects;

/**
 * Класс Info содержит информацию о разнице между состояниями множества пользователей.
 */

public class Info {
    private int added;
    private int changed;
    private int deleted;

    /**
     * Конструктор класса Info.
     *
     * @param added   количество добавленных пользователей
     * @param changed количество измененных пользователей
     * @param deleted количество удаленных пользователей
     */
    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }
}
