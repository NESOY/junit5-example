package standard;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


/**
 * Annotation Naming이 변경됨
 */
class StandardTest {
    /**
     * junit 4 - @BeforeClass
     * junit 5 - @BeforeAll
     */
    @BeforeAll
    static void initAll() {
    }

    /**
     * junit 4 - @Before
     * junit 5 - @BeforeEach
     */
    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    /**
     * junit 4 - @Ignore
     * junit 5 - @Disabled
     */
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    /**
     * junit 4 - @After
     * junit 5 - @AfterEach
     */
    @AfterEach
    void tearDown() {
    }

    /**
     * junit 4 - @AfterClass
     * junit 5 - @AfterAll
     */
    @AfterAll
    static void tearDownAll() {
    }
}