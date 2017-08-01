package abraham.web.service.keymanager;

import abraham.core.ca.domain.DSAKeyExtInfo;
import abraham.core.ca.domain.KeyPairInfo;
import abraham.core.ca.domain.RSAKeyExtInfo;
import abraham.core.ca.service.KeyGenerateRequest;
import abraham.core.ca.service.KeyService;
import abraham.web.service.keymanager.models.GenerateKeyPairRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pan.utils.AppBizException;
import pan.utils.AppExceptionCodes;
import pan.utils.AppRTException;
import pan.utils.ca.KeyPairTypeEnum;
import pan.utils.data.Order;

import java.util.List;
import java.util.Map;

@Component
public class KeyManagerServiceImpl implements KeyManagerService {
    @Autowired
    private Map<String, KeyService> keyServices;

    private KeyService getKeyService(KeyPairTypeEnum keyPairType){
        if (keyServices == null || keyServices.isEmpty()){
            throw new AppRTException("", "There is not any KeyServices can be used by KeyManager.");
        }
        String keyServiceType = keyPairType.getName() + "KeyService";
        KeyService keyService = keyServices.get(keyServiceType);
        if (keyService == null){
            throw new AppRTException("", "There is not a KeyService registered by name[" + keyServiceType + "]");
        }

        return  keyService;
    }

    private KeyPairTypeEnum findKeyPairType(long sid) throws AppBizException{
        if (keyServices == null || keyServices.isEmpty()){
            throw new AppRTException("", "There is not any KeyServices can be used by KeyManager.");
        }
        KeyService keyService = (KeyService) keyServices.values().toArray()[0];

        KeyPairInfo keyPairInfo = keyService.findKeyPairInfoBySid(sid);
        if (keyPairInfo == null){
            Object[] objs = new Object[1];
            objs[0] = sid;
            throw new AppBizException(AppExceptionCodes.CA_KEYPAIR_NOT_EXIST[0], AppExceptionCodes.CA_KEYPAIR_NOT_EXIST[0], objs);

        }

        return KeyPairTypeEnum.valueOf(keyPairInfo.getType());

    }


    @Override
    public void generateKeyPair(GenerateKeyPairRequest request) throws AppBizException {
        KeyGenerateRequest keyGenerateRequest = new KeyGenerateRequest();
        keyGenerateRequest.setKeyName(request.getKeyName());
        keyGenerateRequest.setKeyPassword(request.getKeyPassword());
        keyGenerateRequest.setKeyType(request.getKeyType());
        keyGenerateRequest.setKeySize(request.getKeySize());
        this.getKeyService(request.getKeyType()).generateKeyPair(keyGenerateRequest);
    }

    @Override
    public Page<KeyPairInfo> findAllKeyPairs(int pageNumber, int pageSize, List<Order> orders) {
        KeyService keyService = (KeyService) keyServices.values().toArray()[0];
        return keyService.findAllKeyPairInfo(pageNumber, pageSize, orders);
    }

    @Override
    public void deleteKeyPair(long sid) throws AppBizException{
        KeyPairTypeEnum keyPairType = this.findKeyPairType(sid);
        this.getKeyService(keyPairType).deleteKeyPair(sid);
    }

    @Override
    public RSAKeyExtInfo findRSAKeyExtInfoBySid(long sid) throws AppBizException {
        return (RSAKeyExtInfo) this.getKeyService(KeyPairTypeEnum.RSA).findKeyExtInfoBySid(sid);
    }

    @Override
    public DSAKeyExtInfo findDSAKeyExtInfoBySid(long sid) throws AppBizException {
        return (DSAKeyExtInfo) this.getKeyService(KeyPairTypeEnum.DSA).findKeyExtInfoBySid(sid);
    }

    @Override
    public KeyPairInfo findKeyPairInfoBySid(long sid) throws AppBizException {
        KeyService keyService = (KeyService) keyServices.values().toArray()[0];
        return keyService.findKeyPairInfoBySid(sid);
    }
}
