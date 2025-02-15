package org.javaboy.filteranalysis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {

    @GetMapping("/foo")
    public String foo() {
        System.out.println("foo");
        return "foo";
    }

    @GetMapping("/bar")
    public String bar() {
        System.out.println("bar");
        return "bar";
    }

}
