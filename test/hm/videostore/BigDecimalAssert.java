package hm.videostore;

import java.math.BigDecimal;
import org.junit.Assert;

public class BigDecimalAssert {
    public static void assertEquals(BigDecimal expected, BigDecimal actual) {
        Assert.assertEquals(expected.stripTrailingZeros(),
                            actual.stripTrailingZeros());
    }
}
