package com.github.ulwx.aka.admin.utils.secure;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;


public class RsaApplyUtils {

//商户验存管系统同理
	/**
	 * 解密RSA   content 加密内容
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String  decryptRsa(String content,String privateStr) throws Exception {
		// 1.生成密钥对
		//KeyPair keyPair = RsaUtils.generateRsaKeyPair(1024);
		// 2.生成公私钥匙base64
		//String privateStr = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());// 商户留着签名
		//String publicStr = Base64.encodeBase64String(keyPair.getPublic().getEncoded());// 发给存管系统
		//System.out.println("privateStr="+privateStr);
		//System.out.println("publicStr="+publicStr);
		//String privateStr="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAi/52sB31O/1aBK72kQzcs9NskBDoJPgp1VVPHFn0BjyHRwNJ2V/MlmSO/WrsrtI+xqPCgTFd/h9BmXufQ4Q3AwIDAQABAkAY++DSrYYGnsBh9Zera1A4B5NoYpwLfP56RC9KMAOM2RwMqizZmAVmIdFl2gSYxkQxzn1T60kzJ1aq0KIN/zUBAiEA306hOO5Ky+xyELhYwKJtJrecVE3cWWc1K6ZrUHyxbjsCIQCgfVMmMsR6Obk+tvzuWecWIYf5h4sDOQww3j3nJVnl2QIgBGHAnCNg6Ft9aYKUi0MkrSvRL35Popl5259qX6vyt10CIAY3tL/3GYuOrvGSD0yAqSY/WFA/gxA15pYCFi3dUZVJAiEA127BTg/iYElzgIDzLJi32ZR/0/huE+nwCY9YwXrbW1w=";
		//String publicStr="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIv+drAd9Tv9WgSu9pEM3LPTbJAQ6CT4KdVVTxxZ9AY8h0cDSdlfzJZkjv1q7K7SPsajwoExXf4fQZl7n0OENwMCAwEAAQ==";
		// 3.公私钥匙生成
		//String privateStr= CbAppConfigProperties.getPrivateKey();

		PrivateKey privateKey= RsaUtils.getRsaPkcs8PrivateKey(Base64.decodeBase64(privateStr));
		//PublicKey publicKeyr = RsaUtils.getRsaX509PublicKey(Base64.decodeBase64(publicStr));
		return decryptBase64(content,privateKey);

	}
	public static String decryptBase64(String content,PrivateKey privateKey) {
	        return new String(decrypt(Base64.decodeBase64(content),privateKey));
	    }
	private static byte[] decrypt(byte[] string,PrivateKey privateKey) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
            RSAPrivateKey pbk = (RSAPrivateKey)privateKey;
            cipher.init(Cipher.DECRYPT_MODE, pbk);
            byte[] plainText = cipher.doFinal(string);
            return plainText;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	public static void main(String[] args) throws Exception{
		//createTestKeys();
	}

}
