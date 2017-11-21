package com.example.bootfeign;

import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BootFeignApplicationTests {

  @Autowired
  ObjectFactory<HttpMessageConverters> messageConverters;

  @Value("http://localhost:${local.server.port}")
  protected String apiUrl;

  @Test
  public void test() {
    MyApi api = feignBuilder().target(MyApi.class, apiUrl);

    String param = "yo";
    String result = api.test(param);
    Assert.assertEquals("Endpoint should returned the same param value!", param, result);
  }


  protected Feign.Builder feignBuilder() {
    return Feign.builder()
        .contract(new SpringMvcContract())
        .client(new ApacheHttpClient())
        .decoder(new SpringDecoder(messageConverters))
        .encoder(new SpringEncoder(messageConverters));
  }
}
