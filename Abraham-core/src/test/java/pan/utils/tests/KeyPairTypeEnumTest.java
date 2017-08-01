package pan.utils.tests;

import org.junit.Test;
import pan.utils.ca.KeyPairTypeEnum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class KeyPairTypeEnumTest {

    @Test
    public void testString2Enum() throws Exception{
        KeyPairTypeEnum keyPairTypeEnum = KeyPairTypeEnum.valueOf("RSA");
        assertThat(keyPairTypeEnum, equalTo(KeyPairTypeEnum.RSA));

        keyPairTypeEnum = KeyPairTypeEnum.valueOf("RD");
    }
}
