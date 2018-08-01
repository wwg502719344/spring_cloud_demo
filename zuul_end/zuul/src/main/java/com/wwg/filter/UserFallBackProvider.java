
package com.wwg.filter;


import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by W2G on 2018/7/31.
 * 实现容错和回退
 * 官方文档
 */

@Component
public class UserFallBackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        //表明是为哪个服务进行回退，可为null或*
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String s, Throwable throwable) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //fallback时的返回码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //数字类型的返回码
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                //状态文本
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("用户微服务不可用，请稍后再试".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

