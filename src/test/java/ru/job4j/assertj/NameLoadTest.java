package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkNameArrayEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkSymbol() {
        NameLoad nameLoad = new NameLoad();
        String string1 = "check=symbol";
        String string2 = "Java";
        assertThatThrownBy(() -> nameLoad.parse(string1, string2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining(string2);
    }

    @Test
    void checkKey() {
        NameLoad nameLoad = new NameLoad();
        String string1 = "=check";
        String string2 = "Java=Check";
        assertThatThrownBy(() -> nameLoad.parse(string1, string2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(string1);
    }

    @Test
    void checkValue() {
        NameLoad nameLoad = new NameLoad();
        String string1 = "Java=Check";
        String string2 = "Check=";
        assertThatThrownBy(() -> nameLoad.parse(string1, string2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(string2);
    }

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
}