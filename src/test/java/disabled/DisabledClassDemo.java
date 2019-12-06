package disabled;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit4 - @Ignore
 */
@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
    }

    /*
     * 실패한 테스트 케이스는 실패가 되지않는다..?
     */
    @Test
    public void failTestisNotSkipped(){
        assertEquals(1,2);
    }

}