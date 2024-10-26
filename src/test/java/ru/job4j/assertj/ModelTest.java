package ru.job4j.assertj;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Утверждения с примитивными типами [#504883 #344295].
 */
class ModelTest {
    @Test
    void checkBoolean() {
        /*Arrange - создание объекта и заполнение их данными*/
        Model model = new Model(1, 5.255d, "name", true);
       /*Act - выполнение дейсвия и сохранение результата*/
        boolean result = model.isCondition();
        /*Assert - оценка соответсвия фактического результата ожидаемогу исходу*/
        assertThat(result).isTrue();
    }

    @Test
    void checkStringChain() {
        /*Arrange - создаем объект и заполняем данными*/
        Model model = new Model(1, 5.255d, "I am learning Java", true);
        /*Act - выполняем дейсвие и сохраняем результат*/
        String result = model.getLine();
        /*проверяем фактический результат ожидаемому исходу*/
        assertThat(result).isNotNull() /*проверяем что result не равна нулл*/
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("java")
                .contains("am", "Java")
                .doesNotContain("javascript")
                .startsWith("I am")
                .startsWithIgnoringCase("i")
                .endsWith("Java")
                .isEqualTo("I am learning Java");
    }

    @Test
    void checkInt() {
        Model model = new Model(2, 5.2d, "name", true);
        int result = model.getTop();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(3)
                .isEqualTo(2);
    }

    @Test
    void checkDoubleChain() {
        Model model = new Model(1, 5.255d, "name", true);
        double result = model.getNum();
        assertThat(result).isEqualTo(5.26d, withPrecision(0.006d))
                .isCloseTo(5.25d, withPrecision(0.01d))
                .isCloseTo(5.25d, Percentage.withPercentage(1.0d))
                .isGreaterThan(5.25d)
                .isLessThan(5.26d);
    }
}