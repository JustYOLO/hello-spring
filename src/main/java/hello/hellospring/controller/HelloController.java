package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") // url에 직접 대입 ex) localhost:8080/hello 의 /hello 부분
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        // data -> key, hello!! -> value가 되서 해당하는 html 파일의 data 위치에 value 값을 집어넣음
        return "hello"; // resources/templates에 있는 hello.html에서 찾음
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // parameter로 name을 요구 -> url에서 name이란 이름의 인자를 받는다.
        // ex) localhost:8080/hello-mvc?name=spring!
        // 인자 name의 값을 spring!으로 넘김
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // html의 body 부분에 대입
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // string을 body 부분에 대입
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } // json 파일로 보여줌

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
