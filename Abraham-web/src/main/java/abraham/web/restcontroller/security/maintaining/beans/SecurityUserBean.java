package abraham.web.restcontroller.security.maintaining.beans;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by panqingrong on 28/12/2016.
 */
@Data
public class SecurityUserBean implements Serializable {
    @NotNull(message = "userId is null!")
    @NotEmpty(message = "userId is empty!")
    private String userId;

    private String fullName;

    @NotNull(message = "nickName is null!")
    @NotEmpty(message = "nickName is empty!")
    private String nickName;

    @NotNull(message = "passwd is null!")
    @NotEmpty(message = "passwd is empty!")
    private String passwd;

    @NotNull(message = "passwdConfirmed is null!")
    @NotEmpty(message = "passwdConfirmed is empty!")
    private String passwdConfirmed;
}
