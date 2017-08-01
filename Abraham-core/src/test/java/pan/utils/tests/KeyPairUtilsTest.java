package pan.utils.tests;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import pan.utils.ca.KeyPairUtils;

import java.security.KeyFactory;

/**
 * Created by panqingrong on 31/05/2017.
 */
public class KeyPairUtilsTest {

    @Test
    public void testTheTimeConsumingOfUtilsInit() throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        KeyPairUtils keyPairUtils = new KeyPairUtils();
        long timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("The time-consuming of initial is [" + timeConsuming + "]ms");
    }

    @Test
    public void testTheTimeConsumingOfKeyPairGenerating() throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        KeyPairUtils keyPairUtils = new KeyPairUtils();
        keyPairUtils.newRSAKeyPair(1024);;
        long timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[RSA] KeySize[1024] Time-Consuming[" + timeConsuming + "ms]");

        stopWatch.start();
        keyPairUtils = new KeyPairUtils();
        keyPairUtils.newRSAKeyPair(2048);
        timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[RSA] KeySize[2048] Time-Consuming[" + timeConsuming + "ms]");

        stopWatch.start();
        keyPairUtils = new KeyPairUtils();
        keyPairUtils.newRSAKeyPair(4096);
        timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[RSA] KeySize[4096] Time-Consuming[" + timeConsuming + "ms]");

        stopWatch.start();
        keyPairUtils = new KeyPairUtils();
        keyPairUtils.newDSAKeyPair(1024);
        timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[DSA] KeySize[1024] Time-Consuming[" + timeConsuming + "ms]");

        stopWatch.start();
        keyPairUtils = new KeyPairUtils();
        keyPairUtils.newDSAKeyPair(2048);
        timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[DSA] KeySize[2048] Time-Consuming[" + timeConsuming + "ms]");

        stopWatch.start();
        keyPairUtils = new KeyPairUtils();
        keyPairUtils.newDSAKeyPair(4096);
        timeConsuming = stopWatch.getTime();
        stopWatch.reset();
        System.out.println("KeyType[DSA] KeySize[4096] Time-Consuming[" + timeConsuming + "ms]");

    }
}
