package com.study.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    @ResponseBody // 뷰가 아니라, return "ok" 를 문자 그대로 반환해줌
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }


    // Http 파라미터 이름이 변수 이름과 같으면 생략 가능
    @ResponseBody // 뷰가 아니라, return "ok" 를 문자 그대로 반환해줌
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // String, int 등 단순 타입이면 @RequestParam도 생략 가능
    @ResponseBody // 뷰가 아니라, return "ok" 를 문자 그대로 반환해줌
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required = false를 하고 값을 주지 않으면 null이 들어가는데,
     * int는 null값이 들어갈 수 없으므로
     * 객체인 Integer로 바꿔주어야 한다. or dafaultValue 사용
     * int a = null (X)
     * Integer b = null (O)
     */
    @ResponseBody // 뷰가 아니라, return "ok" 를 문자 그대로 반환해줌
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    // defaultValue를 사용하면 required는 필요가 없긴 하다.
    // 빈문자("")도 default값으로 들어감
    @ResponseBody // 뷰가 아니라, return "ok" 를 문자 그대로 반환해줌
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    /**
     * @RequestParam Map
     *  Map(key=value)
     * @RequestParam MultiValueMap
     *  MultiValueMap(key=[value1, value2, ...]
     * 파라미터 값이 1개가 확실하다면 Map, 그렇지 않다면 MultiValueMap을 사용!
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
