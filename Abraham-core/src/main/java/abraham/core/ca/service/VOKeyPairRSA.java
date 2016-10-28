package abraham.core.ca.service;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Data
public class VOKeyPairRSA extends VOKeyPair {
    @NotNull(message = "public key's exponent is null!")
    private String publicExponent;

    @NotNull(message = "public key's modulus is null!")
    private String publicModulus;

    @NotNull(message = "private key's exponent is null!")
    private String privateExponent;

    @NotNull(message = "private key;s modulus is null!")
    private String privateModulus;
}
