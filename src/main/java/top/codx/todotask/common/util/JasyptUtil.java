package top.codx.todotask.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * Jasypt加密解密工具类
 */
@Slf4j
public class JasyptUtil {
    public static void main(String[] args) {
        String text = "123123";
        String crack = "Team@2023";
        encryptWithSHA512(text, crack);
    }
    private static final String PBEWITHMD5ANDDES = "PBEWithMD5AndDES";

    private static final String PBEWITHHMACSHA512ANDAES_256 = "PBEWITHHMACSHA512ANDAES_256";

    /**
     * @param text  待加密原文
     * @param crack 盐值（密钥）
     * @return 加密后的字符串
     * @Description: Jasypt加密（PBEWithMD5AndDES）
     */
    public static String encryptWithMD5(String text, String crack) {
        //1.创建加解密工具实例
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //2.加解密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm(PBEWITHMD5ANDDES);
        config.setPassword(crack);
        encryptor.setConfig(config);
        //3.加密
        String encrypt = encryptor.encrypt(text);
        log.warn(text + "加密后的字符串:" + encrypt);
        return text + "加密后的字符串:" + encrypt;
    }

    /**
     * @param text  待解密原文
     * @param crack 盐值（密钥）
     * @return 解密后的字符串
     * @Description: Jasypt解密（PBEWithMD5AndDES）
     */
    public static String decryptWithMD5(String text, String crack) {
        //1.创建加解密工具实例
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //2.加解密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm(PBEWITHMD5ANDDES);
        config.setPassword(crack);
        encryptor.setConfig(config);
        //解密
        String decrypt = encryptor.decrypt(text);
        log.warn(text + "解密后的字符串:" + decrypt);
        return text + "解密后的字符串:" + decrypt;
    }

    /**
     * @param text  待加密的原文
     * @param crack 盐值（密钥）
     * @return 加密后的字符串
     * @Description: jasypt 加密（PBEWITHHMACSHA512ANDAES_256）
     */
    public static String encryptWithSHA512(String text, String crack) {
        //1.创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        //2.加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(crack);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasypt 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        //3.加密
        String encrypt = encryptor.encrypt(text);
        log.warn(text + "加密后的字符串:" + encrypt);
        return text + "加密后的字符串:" + encrypt;
    }

    /**
     * @param text  待解密原文
     * @param crack 盐值（密钥）
     * @return 解密后的字符串
     * @Description: jasypt 解密（PBEWITHHMACSHA512ANDAES_256）
     */
    public static String decryptWithSHA512(String text, String crack) {
        //1.创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        //2.加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(crack);
        config.setAlgorithm(PBEWITHHMACSHA512ANDAES_256);
        // 为减少配置文件的书写，以下都是 Jasypt 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        //3.解密
        String decrypt = encryptor.decrypt(text);
        log.warn(text + "解密后的字符串:" + decrypt);
        return text + "解密后的字符串:" + decrypt;
    }
}
