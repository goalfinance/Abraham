package abraham.core.ca.service.test;

import abraham.core.ApplicationConfig;
import abraham.core.ca.service.PrivateKeyService;
import abraham.core.ca.service.VOPrivateKey;
import abraham.core.ca.service.VOPrivateKeyRSA;
import abraham.core.test.TestingConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pan.utils.AppBizException;
import pan.utils.annotation.env.Profiles;

import javax.validation.ConstraintViolationException;

/**
 * Created by panqingrong on 04/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={ApplicationConfig.class, TestingConfig.class})
@ActiveProfiles(Profiles.UNIT_TEST_ENV)
public class PrivateKeyServiceTest {
    @Autowired
    private PrivateKeyService privateKeyService;

    @Test(expected = IllegalArgumentException.class)
    public void testSavePrivateKey() throws AppBizException{
        VOPrivateKeyRSA voPrivateKeyRSA = new VOPrivateKeyRSA();
        voPrivateKeyRSA.setKeyName(null);

        privateKeyService.savePrivateKeyRSA(voPrivateKeyRSA);




    }
}
