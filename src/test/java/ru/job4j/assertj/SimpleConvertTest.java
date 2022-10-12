package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "first", "second", "three", "four", "five");
        assertThat(list).hasSize(6)
                .contains("first")
                .contains("first", Index.atIndex(1))
                .containsOnly("first", "second", "three", "four", "five")
                .containsExactly("first", "first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("three", "second", "four", "five", "first", "first")
                .containsAnyOf("zero", "second")
                .doesNotContain("zero", "seven")
                .startsWith("first")
                .endsWith("five")
                .containsSequence("four", "five");
        assertThat(list).isNotNull()
                .anyMatch(e -> e.startsWith("first"));
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(0).isNotNull().isEqualTo("first");
        assertThat(list).last().isNotNull().isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotNull()
                .hasSize(5);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isNotNull()
                .hasSize(5)
                .doesNotContainKey("ten")
                .doesNotContainValue(10);

    }
}