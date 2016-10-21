package abraham.core.ca.service;

import abraham.core.ca.domain.PrivateKeyInfo;
import abraham.core.ca.domain.PrivateKeyRSAExtInfo;
import abraham.core.ca.repository.PrivateKeyInfoRepository;
import abraham.core.ca.repository.PrivateKeyRSAExtInfoRepository;
import abraham.core.ca.repository.RSAKeyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.BeanValidator;
import pan.utils.data.Order;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by panqingrong on 04/10/2016.
 */
@Component
public class PrivateKeyServiceImpl implements PrivateKeyService {
    @Autowired
    private PrivateKeyInfoRepository privateKeyInfoRepository;
    @Autowired
    private PrivateKeyRSAExtInfoRepository privateKeyRSAExtInfoRepository;
    @Autowired
    private RSAKeyInfoRepository rsaKeyInfoRepository;

    @Override
    public Page<PrivateKeyInfo> findAllPrivateKeyInfo(int pageNumber, int pageSize, List<Order> orders) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Order.newSort(orders));
        return privateKeyInfoRepository.findAll(pageRequest);
    }

    @Override
    @Transactional
    public void savePrivateKeyRSA(VOPrivateKeyRSA privateKeyRSA) throws AppBizException{
        if (privateKeyRSA == null){
            throw new IllegalArgumentException("The argument is not allowed to be null!");
        }
        BeanValidator.validate(privateKeyRSA);

        if (null == privateKeyInfoRepository.findByName(privateKeyRSA.getKeyName())){
            Object[] args = new Object[1];
            args[0] = privateKeyRSA.getKeyName();
            throw new AppBizException(AppExceptionCodes.CA_PRIVATEKEY_NAME_EXIST[0],
                    AppExceptionCodes.CA_PRIVATEKEY_NAME_EXIST[1], args);
        }

        PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo();
        privateKeyInfo.setName(privateKeyRSA.getKeyName());
        privateKeyInfo.setType(privateKeyRSA.getKeyType().getName());
        privateKeyInfo.setSize(privateKeyRSA.getKeySize().getSizeValue());
        privateKeyInfo.setPassword(privateKeyRSA.getKeyPassword());
        privateKeyInfo = privateKeyInfoRepository.save(privateKeyInfo);


        PrivateKeyRSAExtInfo privateKeyRSAExtInfo = new PrivateKeyRSAExtInfo();
        privateKeyRSAExtInfo.setSid(privateKeyInfo.getSid());
        privateKeyRSAExtInfo.setPrivateKeyExponent(privateKeyRSA.getPrivateExponent());
        privateKeyRSAExtInfo.setPrivateKeyModulus(privateKeyRSA.getPrivateModulus());
        privateKeyRSAExtInfo.setPublicKeyExponent(privateKeyRSA.getPublicExponent());
        privateKeyRSAExtInfo.setPublicKeyModulus(privateKeyRSAExtInfo.getPublicKeyModulus());
        privateKeyRSAExtInfoRepository.save(privateKeyRSAExtInfo);

    }
}
