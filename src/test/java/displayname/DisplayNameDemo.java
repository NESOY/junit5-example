package displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Method 이름이 특수문자, Space, 이모지가 불가능한 점을 해결하기 위해?
 */
@DisplayName("A special test case")
class DisplayNameDemo {
    /*
     * space 공간도 가능
     */
    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    /**
     * 특수문자 가능
     */
    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }

    /**
     * 이모지도 가능
     */
    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }


}