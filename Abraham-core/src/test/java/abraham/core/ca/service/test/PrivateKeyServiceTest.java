package abraham.core.ca.service.test;

import abraham.core.ApplicationConfig;
import abraham.core.ca.service.KeyPairService;
import abraham.core.ca.service.VOKeyPairRSA;
import abraham.core.test.TestingConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pan.utils.AppBizException;
import pan.utils.annotation.env.Profiles;

/**
 * Created by panqingrong on 04/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={ApplicationConfig.class, TestingConfig.class})
@ActiveProfiles(Profiles.UNIT_TEST_ENV)
public class PrivateKeyServiceTest {
    @Autowired
    private KeyPairService privateKeyService;

    @Test(expected = IllegalArgumentException.class)
    public void testSavePrivateKey() throws AppBizException{
        VOKeyPairRSA voPrivateKeyRSA = new VOKeyPairRSA();
        voPrivateKeyRSA.setKeyName(null);

        privateKeyService.saveKeyPairRSA(voPrivateKeyRSA);




    }
}
