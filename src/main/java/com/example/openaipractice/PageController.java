package com.example.openaipractice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String index() {
            System.out.println("index");
            return "index.html";
        }
        @RequestMapping(value = "/wordchain", method = RequestMethod.GET)
        public String goWordchain() {
            System.out.println("wordchain");
            return "wordchain.html";
        }
        @RequestMapping(value = "/papagotranslator", method = RequestMethod.GET)
        public String goTranslator() {
            System.out.println("papagotranslator");
            return "papagotranslator.html";
        }

        @RequestMapping(value = "/gptSpeakKorean", method = RequestMethod.GET)
        public String goGptSpeakKorean() {
            System.out.println("gptSpeakKorean");
            return "gptSpeakKorean.html";
        }
        @RequestMapping(value = "/gptSpeakKoreanWithHistory", method = RequestMethod.GET)
        public String goGptSpeakKoreanWithHistory() {
            System.out.println("gptSpeakKorean");
            return "gptSpeakKoreanWithHistory.html";
        }
        @RequestMapping(value = "/kakaoMap", method = RequestMethod.GET)
        public String goKakaoMap() {
            System.out.println("KakaoMap");
            return "kakaoMap.html";
        }
}
