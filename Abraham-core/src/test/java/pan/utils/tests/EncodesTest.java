package pan.utils.tests;

import org.junit.Test;
import pan.utils.Encodes;

/**
 * Created by panqingrong on 03/10/2016.
 */
public class EncodesTest {
    @Test
    public void testConvertHexString2Display() {
        String rawHexString = "A67BED8612B9DBA91D0962B4A080EFC02C71133DA842C26930C0F007F44ED4E3F3206C4C7ED5760A42B0D6F8A6F834D3C9FDA6F5E15F1597106281E065B78A0957DE3E839410EBFE4A31B130CC15263E505A77835099F997EAFCF6C2B35FEE04D076339205D8606B528DAB6A45E38F02D0F676E052064246A29D2EF22B0F29353A7EE114BB3BB1E36D79481CA8049AA01C8E2D0C2861C11C81AF6D1C619101DD24280C08CA3520519F1780D17715756840B63576F27E5498ACE3B772CFF667EBCB1C24D9B333246C6B7ECAC95F0EE77E85BBE9CE661176EF5157DE973DDE8ED5600AFD01BA3DCF326F5805132809AB47534BCF76D2521227A721FD1C91023E77";
        String hexStringWithDisplayFormat = Encodes.hexString2Display(rawHexString);
        System.out.println(hexStringWithDisplayFormat);
        System.out.println("raw hex string's length = '" + rawHexString.length() + "'");
        System.out.println("formatted hex string's length = '" + hexStringWithDisplayFormat.length() + "'");

        rawHexString = "A67BED";
        hexStringWithDisplayFormat = Encodes.hexString2Display(rawHexString);
        System.out.println(hexStringWithDisplayFormat);
    }
}
