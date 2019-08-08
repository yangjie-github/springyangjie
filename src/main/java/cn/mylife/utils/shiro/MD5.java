package cn.mylife.utils.shiro;

import cn.mylife.modules.user.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author yangjie
 * 2018/6/13 21:24
 */
public class MD5 {

    /**
     * 温馨提示：记得在注册中密码存入数据库前也记得加密哦，提供一个utils方法
     * 进行shiro加密，返回加密后的结果
     */
    public static String md5(User user){

        String saltSource = user.getLoginName().trim();
        String hashAlgorithmName = "MD5";
        Object salt = ByteSource.Util.bytes(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, user.getPassWord().trim(), salt, hashIterations);
        return result.toString();
    }
}
