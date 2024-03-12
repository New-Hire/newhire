package com.muyu.newhire.provider;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SignProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    public String sign(String content) {
        var signed = SecureUtil.hmac(HmacAlgorithm.HmacSHA512, secret);
        return Arrays.toString(signed.digest(content));
    }

    /**
     * 验证签名
     *
     * @param target 需要被验签的
     * @param source 原文
     * @return boolean
     */
    public boolean verify(String target, String source) {
        var signed = SecureUtil.hmac(HmacAlgorithm.HmacSHA512, secret);
        return signed.verify(target.getBytes(), signed.digest(source));
    }
}
