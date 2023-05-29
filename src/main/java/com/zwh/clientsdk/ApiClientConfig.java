package com.zwh.clientsdk;

import com.zwh.clientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("zwh.client") // 将配置文件中对应的配置映射到对象的属性中,会将配置文件中 zwh.client.accessKey、zwh.client.secretKey 的值自动注入到 ApiClientConfig 的属性中
@Data
@ComponentScan // 不传递参数会默认扫描当前主类所在的包及其子包下的所有组件
public class ApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }

}
