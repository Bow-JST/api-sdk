package com.zwh.clientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import com.zwh.clientsdk.model.Calculate;
import com.zwh.clientsdk.model.User;
import com.zwh.clientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 * @author zwh
 */
public class ApiClient {

    private static final String GATEWAY_HOST = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    /**
     * 封装了请求头方法
     * @param body 请求体
     * @return 存放请求头数据的集合
     */
    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 设置随机数防重放
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        // 用户请求参数
        hashMap.put("body", body);
        // 发起请求时间戳 [单位为秒]
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        // 添加签名信息
        hashMap.put("sign", SignUtils.genSign(body, secretKey));
        return hashMap;
    }

    /**
     * 使用 hutool 的 HttpRequest.post 发起 Restful 风格的 post 请求 [数据以 json 的形式传递]
     * @param user
     * @return
     */
    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

    // region 两数基本运算
    public String add(Calculate calculate) {
        String json = JSONUtil.toJsonStr(calculate);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/number/add")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        return httpResponse.body();
    }

    public String sub(Calculate calculate) {
        String json = JSONUtil.toJsonStr(calculate);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/number/sub")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();

        return httpResponse.body();
    }


    public String multiply(Calculate calculate) {
        String json = JSONUtil.toJsonStr(calculate);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/number/multiply")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();

        return httpResponse.body();
    }


    public String division(Calculate calculate) {
        String json = JSONUtil.toJsonStr(calculate);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/number/division")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();

        return httpResponse.body();
    }




    // endregion
}
