package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}

