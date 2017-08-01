/**
 *
 *
 */
package pan.utils;

import org.springframework.context.support.MessageSourceSupport;

import java.util.Locale;

/**
 * @author panqingrong
 */
public class DefaultMessageSourceServiceImpl extends MessageSourceSupport implements MessageSourceService {

    /* (non-Javadoc)
     * @see pan.utils.MessageSourceService#getMessage(java.lang.String, java.lang.Object[])
     */
    public String getMessage(String msg, Object[] args) {
        return this.renderDefaultMessage(msg, args, Locale.getDefault());
    }

}
