package top.codx.todotask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * "/" 路径重定向至 接口文档
 *
 * @author Liuch
 * @since 2023-06-07 21:27
 */
@Controller
public class RedirectController {
    @GetMapping("/doc")
    public String redirectWithUsingForwardPrefix(HttpServletRequest request) {
        return "redirect:/doc.html";
    }
}
