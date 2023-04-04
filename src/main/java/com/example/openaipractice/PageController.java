package com.example.openaipractice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
        @RequestMapping(value = "/wordchain", method = RequestMethod.GET)
        public String goWordchain() {
            System.out.println("wordchain");
            return "wordchain.html";
        }
}
