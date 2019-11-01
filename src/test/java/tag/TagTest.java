package tag;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest {
    private final Calculator calculator = new Calculator();

    @Fast
    @Test
    public void iamfast(){
        assertEquals(2, calculator.add(1, 1));
    }


    @FastTest
    public void iamveryfast(){
        assertEquals(2, calculator.add(1, 1));
    }

    /*
    SlowTest
    특정 Test만 필터링해서 테스트 가능
     */

    class Calculator {
        public int add(int i, int i1) {
            return i + i1;
        }
    }

}
