package com.example.openaipractice;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class MyRestfulController {


//    public static void main(String[] args) {
//        WordChain wordChain = new WordChain();
//        String str = "apple";
//        for(int i=0; i<10; i++){
//            str = wordChain.sendWord(str);
//            System.out.println(str);
//        }
//    }


        WordChain wordChain = new WordChain();
        String str;
        HashSet<String> wordSet = new HashSet<>();
        @RequestMapping("/word")
        public String word(@RequestParam(value="word", defaultValue="word") String word) {
            str = wordChain.sendWord(word);
            System.out.println(str);
            return str;
        }


}
