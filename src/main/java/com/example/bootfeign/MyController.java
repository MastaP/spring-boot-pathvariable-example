package com.example.bootfeign;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController implements MyApi {

  public String test(String a) {
    System.out.println("a = " + a);
    return a;
  }
}
