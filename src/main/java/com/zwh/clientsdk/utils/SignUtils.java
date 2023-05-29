package com.zwh.clientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 */
public class SignUtils {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey) {
        // 1.指定使用 SHA256 摘要算法进行加密
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        // 2.待加密的内容
        String content = body + "." + secretKey;
        // 3.加密成十六进制的字符串返回
        return md5.digestHex(content);

        // return DigestUtil.md5Hex(body + "." + secretKey); 可以用下面这一行代码替换
    }
}
