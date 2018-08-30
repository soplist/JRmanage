package com.bureau.unit;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * DES加密 解密算法,主要用于对data数据加解密
 */
public class DesUtil {
	private final static String DES = "DES";
	private final static String ENCODE = "GBK";
	private final static String defaultKey = "er3'0-l@";//设置默认密钥，8位
	
	/**
     * Description 根据键值进行加密
     * @param data String
     * @param key  String
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encryptByte(data.getBytes(ENCODE), defaultKey.getBytes(ENCODE));
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     * @param data String
     * @param key  String
     */
    public static String decrypt(String data) throws Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decryptByte(buf, defaultKey.getBytes(ENCODE));
        return new String(bt, ENCODE);
    }
    
    /**
     * Description byte加密
     * @param data byte[]
     * @param key  byte[]
     */
    private static byte[] encryptByte(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description byte解密
     * @param data byte[]
     * @param key  byte[]
     */
    private static byte[] decryptByte(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    public static void main(String[] args) throws Exception {
		System.out.println(encrypt("11111.2222"));
		System.out.println(decrypt("0YaAoxHgtijVFNsF4iUjNA=="));
	}
}
