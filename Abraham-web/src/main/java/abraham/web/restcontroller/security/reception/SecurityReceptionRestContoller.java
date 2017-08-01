/**
 *
 */
package abraham.web.restcontroller.security.reception;

import abraham.web.service.security.reception.models.LoginBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pan.utils.web.MediaTypes;

/**
 * @author panqingrong
 */
@RestController
@RequestMapping("restapis/security")
public class SecurityReceptionRestContoller {

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaTypes.JSON_UTF_8)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void login(@RequestBody LoginBean loginBean) {
        assert (loginBean.getUsername() != null && loginBean.getUsername().equals("") == false);
        assert (loginBean.getPassword() != null && loginBean.getPassword().equals("") == false);

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginBean.getUsername(), loginBean.getPassword());
        currentUser.login(token);

    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("You can not pass the authentication!", HttpStatus.FORBIDDEN);
        return responseEntity;
    }

}
