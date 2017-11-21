package com.example.bootfeign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MyApi {

  @RequestMapping(value = "/{a}", method = RequestMethod.GET)
  String test(@PathVariable("a") String a);
}
