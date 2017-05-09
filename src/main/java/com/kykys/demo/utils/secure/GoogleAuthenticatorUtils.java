package com.kykys.demo.utils.secure;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

/**
 * idea by Google Authenticator
 * 30s key
 * Created by kuangye on 2017/5/9.
 *
 * 操作时最好客户端同时发送一个时间戳用于校验服务器与客户端的时差（密码偏移量）
 */
public class GoogleAuthenticatorUtils  {
    /**
     * 时间前后偏移量
     * 用于防止客户端时间不精确导致生成的TOTP与服务器端的TOTP一直不一致
     * 如果为0,当前时间为 10:10:15
     * 则表明在 10:10:00-10:10:30 之间生成的TOTP 能校验通过
     * 如果为1,则表明在
     * 10:09:30-10:10:00
     * 10:10:00-10:10:30
     * 10:10:30-10:11:00 之间生成的TOTP 能校验通过
     * 以此类推
     */
    private static final int timeExcursion = 1;

    private static final int loopSecond = 30;

    private static final int codeLength = 6;

    /**
     * 创建一个密钥
     */
    public static String createSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        String secretKey = base32.encodeToString(bytes);
        return secretKey.toLowerCase();
    }

    /**
     * 校验方法
     *
     * @param secretKey 密钥
     * @param code      用户输入的TOTP
     */
    public static boolean verify(String secretKey, String code) {
        long time = System.currentTimeMillis() / 1000 / loopSecond;
        for (int i = -timeExcursion; i <= timeExcursion; i++) {
            String totp = getTOTP(secretKey, time + i);
            System.out.println(totp);
            if (code.equals(totp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据密钥获取验证码
     * 返回字符串是因为数值有可能以0开头
     * @param secretKey 密钥
     * @param time      第几个30秒 System.currentTimeMillis() / 1000 / 30
     */
    public static String getTOTP(String secretKey, long time) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey.toUpperCase());
        String hexKey = Hex.encodeHexString(bytes);
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, String.valueOf(codeLength));
    }

    /**
     * 生成Google Authenticator二维码所需信息
     * Google Authenticator 约定的二维码信息格式 : otpauth://totp/{issuer}:{account}?secret={secret}&issuer={issuer}
     * 参数需要url编码 +号需要替换成%20
     * @param secret  密钥 使用createSecretKey方法生成
     * @param account 用户账户 如: example@domain.com 138XXXXXXXX
     * @param issuer  服务名称 如: Google Github 印象笔记
     */
    public static String createGoogleAuthQRCodeData(String secret, String account, String issuer) {
        String qrCodeData = "otpauth://totp/%s?secret=%s&issuer=%s";
        try {
            return String.format(qrCodeData, URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20"), URLEncoder.encode(secret, "UTF-8")
                    .replace("+", "%20"), URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static void main(String[] args) {

//        String key = createSecretKey();
//        System.out.println(key);

//        String url = createGoogleAuthQRCodeData(key,"495621336@qq.com","ky");
//        System.out.println(url);

//        String googleJPGUrl = "https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=" + url;


        String myKey = "52mto7dipeoiae3yc7qhccw3svx7hxm2";
        String myUrl = "otpauth://totp/ky%3A495621336%40qq.com?secret=52mto7dipeoiae3yc7qhccw3svx7hxm2&issuer=ky";


        verify(myKey,"748853");


    }





}
