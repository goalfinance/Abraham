/**
 *
 */
package pan.utils;

/**
 * @author panqingrong
 */
public class AppExceptionCodes {

    public static final String[] UNRECOVERABLE_SYSTEM_ERROR = {"S.MAS.9999", ""};

    public static final String[] UNSUPPORTED_FUNCTION = {"S.MAS.9998", "Unsupported function!"};

    /**
     * The authorized network does not exist in system.
     */
    public static final String[] AUTHNET_DOES_NOT_EXIST = {"R.MAS.AUTHNET.0001", "The authorized network[authNet Sid={0}] does not exists in system."};


    /**
     * The user that used to be authorized by outpost does not exist in system.
     */
    public static final String[] SEC_USER_DOES_NOT_EXIST = {"R.MAS.OUTPOST-WEB-SEC.0001", "The user[userId={0}]that used to be authorized by outpost does not exist in system."};

    /**
     * Encountered a error when calculating security algorithm.
     */
    public static final String[] SEC_SECURITY_ALGORITHM__ERROR = {"R.MAS.OUTPOST-WEB-SEC.0002", "Encountered a error when calculating security algorithm."};

    /**
     * The old password input is not matched.
     */
    public static final String[] SEC_NOT_MATCH_OLDPASSWORD = {"R.MAS.OUTPOST-WEB-SEC.0003", "The old password input is not matched."};

    /**
     * Both the input passwords must be consistent.
     */
    public static final String[] SEC_PASSWORD_INPUT_TWICE_NOT_MATCHED = {"R.MAS.OUTPOST-WEB-SEC.0004", "Both the input passwords must be consistent."};

    /**
     * The name of keypair is exist in system.
     */
    public static final String[] CA_KEYPAIR_NAME_EXIST = {"R.CA.KEY-MANAGER.001", "The key pair[key name={0}] is exist in system."};

    /**
     * The keypair is not exist in system.
     */
    public static final String[] CA_KEYPAIR_NOT_EXIST = {"R.CA.KEY-MANAGER.002", "The keypair[key sid={0}] is not exist in system."};

    /**
     * There is error when exporting a key.
     */
    public static final String[] CA_KEY_EXPORT_ERROR = {"R.CA.KEY-MANAGER.003", "There is error when exporting key[key sid={0}]."};


}
