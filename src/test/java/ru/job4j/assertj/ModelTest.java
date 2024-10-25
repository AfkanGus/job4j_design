package ru.job4j.assertj;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. ����������� � ������������ ������ [#504883 #344295].
 */
class ModelTest {
    @Test
    void checkBoolean() {
        /*Arrange - �������� ������� � ���������� �� �������*/
        Model model = new Model(1, 5.255d, "name", true);
       /*Act - ���������� ������� � ���������� ����������*/
        boolean result = model.isCondition();
        /*Assert - ������ ����������� ������������ ���������� ���������� ������*/
        assertThat(result).isTrue();
    }

    @Test
    void checkStringChain() {
        /*Arrange - ������� ������ � ��������� �������*/
        Model model = new Model(1, 5.255d, "I am learning Java", true);
        /*Act - ��������� ������� � ��������� ���������*/
        String result = model.getLine();
        /*��������� ����������� ��������� ���������� ������*/
        assertThat(result).isNotNull() /*��������� ��� result �� ����� ����*/
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