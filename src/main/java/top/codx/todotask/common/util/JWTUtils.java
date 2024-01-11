package top.codx.todotask.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import top.codx.todotask.exception.SysException;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * jwt工具类
 *
 * @author Liuch
 * @since 2023-04-03 16:29
 */
public class JWTUtils {

    private static final char[] WORDS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    protected static final byte[] TOKEN_SIGNER = "todoTask-admin".getBytes();

    public static final JWTSigner SIGNER = JWTSignerUtil.hs256(TOKEN_SIGNER);

    /**
     * 创建token<br>
     * 根据用户id创建token
     *
     * @param key     {@link String}
     * @param value   {@link String}
     * @param seconds {@link Integer}
     * @return {@link String}
     */
    public static String createToken(String key, String value, int seconds) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("current", System.currentTimeMillis());

        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.SECOND, seconds);

        return JWT.create().setIssuedAt(now) // 签发时间
                .setExpiresAt(newTime) // 过期时间
                .setNotBefore(now) // 生效时间
                .setPayload("random", JWTUtil.createToken(map, TOKEN_SIGNER)).setPayload(key, value).setSigner(SIGNER).sign();
    }

    /**
     * 解析token<br>
     * 根据传入的key的获取值
     *
     * @param token      {@link String}
     * @param key        {@link String}
     * @param verifyTime {@link String}
     * @return {@link String}
     */
    public static String parseToken(String token, String key, boolean verifyTime) throws SysException {
        try {
            if (!JWTUtil.verify(token, SIGNER)) {
                throw new SysException(40010, "token非法");
            }
        } catch (Exception e) {
            throw new SysException(40010, "token非法");
        }
        JWT jwt = JWT.create().parse(token);
        JWTPayload payload = jwt.getPayload();
        if (verifyTime) { // 是否验证过期时间
            // 获取当前时间
            long nowTime = new Date().getTime() / 1000;
            // 获取过期时间
            long exTime = Long.parseLong(payload.getClaim(RegisteredPayload.EXPIRES_AT).toString());
            if (exTime - nowTime <= 0) {
                throw new SysException(40011, "token过期");
            }
        }
        Object object = payload.getClaim(key);
        return String.valueOf(object);
    }

    /**
     * 解析token<br>
     * 根据传入的key的获取值
     *
     * @param token {@link String}
     * @param key   {@link String}
     * @return {@link String}
     */
    public static String parseToken(String token, String key) throws SysException {
        return parseToken(token, key, true);
    }

    /**
     * 生成六位数验证码
     *
     * @return {@link String}
     */
    public static String captcha() {
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(WORDS.length - 1);
            captcha.append(WORDS[nextInt]);
        }
        return captcha.toString();
    }
}
