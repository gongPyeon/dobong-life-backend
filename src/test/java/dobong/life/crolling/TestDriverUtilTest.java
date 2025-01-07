package dobong.life.crolling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestDriverUtilTest {

    TestDriverUtil testDriverUtil = new TestDriverUtil();
    @Test
    public void Print(){
        testDriverUtil.waitAndPrint();
    }

}