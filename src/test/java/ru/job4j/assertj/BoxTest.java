package ru.job4j.assertj;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Утверждения с примитивными типами [#504883 #344295]
 *
 * @Test void whenMultiCallHasNextThenTrue() {
 * Arrange - создание объекта и заполнение его данными
 * ArrayIt iterator = new ArrayIt(
 * new int[] {1, 2, 3}
 * );
 * /* Act - выполнение действия и сохранение результата
 * boolean rsl=iterator.hasNext();
 * /* Assert - оценка соответствия результата ожидаемому исходу
 * assertThat(result).isTrue();
 * /* этапы Act и Assert могут быть размещены в одной строке кода
 * assertThat(iterator.hasNext()).isTrue();
 * }
 */

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(9, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty();
    }

    @Test
    void getNumberOfVerticesTest() {
        Box box = new Box(4, 10);
        int numb = box.getNumberOfVertices();
        assertThat(numb).isEqualTo(4)
                .isGreaterThan(-1);
    }

    @Test
    void isExistTrueTest() {
        Box box = new Box(4, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isExistFalseTest() {
        Box box = new Box(5, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void getAreaTest() {
        Box box = new Box(8, 8);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(384.0d)
                .isNotZero();
    }
}