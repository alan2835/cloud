package com.alan.ham.cloud.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.alan.ham.cloud.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Person")
public class PersonCtrl {

  @RequestMapping(value = "/Manager", method = GET, produces = "application/json")
  public Person getPerson(){
    return new Person.Builder()
        .withAddress("12 Smith Street")
        .withCity("San Diego")
        .withLname("Hamrick")
        .withFname("Alan")
        .withMobile("555-555-5555")
        .build();
  }

}
