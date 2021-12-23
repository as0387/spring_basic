package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path="/hello") //이게 현재 사용하는 방식 (http://localhost:9090/api/get/hello)
    public String hello(){
        return "get Hello!";
    }
    
    @RequestMapping(path="/hi", method = RequestMethod.GET) //RequestMapping은 모든 요청메서드를 받음 따라서 지정해줘야함
    public String hi(){
        return "Hi!";
    }


    //PathVatiable로 값 받기
    @GetMapping("/path-variable/{name}") //http://localhost:9090/api/get/path-variable/{name} -> {}부분의 값은 특정 짓는것이아닌 변화하는 값이온다.
    public String pathVariable(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName +"님 안녕하세요!";
    }

    //QueryParam
    //http://localhost:9090/api/get/query-param?user=dongs&email=dongs@naver.com&age=28
    @GetMapping(path="/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        });
        return sb.toString();
    }

    @GetMapping("/query-param02")
    public String queryParam02(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam int age){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name +" "+ email +" "+ age;
    }


    //?user=dongs&email=dongs@naver.com&age=28 Spring Boot에서 클래스의 속성 이름과 자동으로 매칭을 해준다. @RequestParam 안써도된다.
    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();
    }
}
