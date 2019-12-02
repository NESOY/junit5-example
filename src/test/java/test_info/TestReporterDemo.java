package test_info;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;

/**
 * TestReporter Default Value
 * - Timestamp
 */
class TestReporterDemo {

    /**
     * OUTPUT
     * timestamp = 2019-12-02T17:29:56.492, value = a status message
     */
    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a status message");
    }

    /**
     * OUTPUT
     * timestamp = 2019-12-02T17:29:30.738, a key = a value
     */
    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
    }

    /**
     * OUTPUT
     * timestamp = 2019-12-02T17:30:41.893, user name = dk38, award year = 1974
     */
    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");

        testReporter.publishEntry(values);
    }
}