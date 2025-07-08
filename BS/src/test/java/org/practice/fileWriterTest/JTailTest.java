package org.practice.fileWriterTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.practice.jTail.JTail;

import java.io.IOException;
import java.util.stream.Stream;

public class JTailTest {

    @ParameterizedTest
    @MethodSource("getJTailObjects")
    @DisplayName("testTailMethodForAGivenFile")
    void testTailMethodForAGivenFile(final JTail jTail) throws IOException {
        System.out.println(jTail);
        System.out.println(jTail.tail());
    }
    public static Stream<Arguments> getJTailObjects() {
        final String fileName = "src\\test\\resources\\append.txt";

        return Stream.of(
                Arguments.of(new JTail(fileName, 2)),
                Arguments.of(new JTail(fileName, 3)),
                Arguments.of(new JTail(fileName, 5))
        );
    }

}
