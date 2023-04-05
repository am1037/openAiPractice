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
            //System.out.println(str);
            return str;
        }

        @RequestMapping("/translateK2E")
        public String translateK2E(@RequestParam(value="korean", defaultValue="안녕하세요") String korean) {
            String str = Papago.translateK2E(korean);
            //System.out.println(str);
            return str;
        }

        @RequestMapping("/translateE2K")
        public String translateE2K(@RequestParam(value="English", defaultValue="Hello") String English) {
            String str = Papago.translateE2K(English);
            //System.out.println(str);
            return str;
        }

        @RequestMapping("/askAI")
        public String askAI(@RequestParam(value="topic", defaultValue="{topic}") String topic,
                            @RequestParam(value="question", defaultValue="{question}") String question) {
            topic = Papago.translateK2E(topic);
            question = Papago.translateK2E(question);
            String answer = TopicTalk.sendRequest(topic, question);
            answer = Papago.translateE2K(answer);
            return answer;
        }

        @RequestMapping("/askAIWithHistory")
        public String[] askAI(@RequestParam(value="topic", defaultValue="{topic}") String topic,
                              @RequestParam(value="question", defaultValue="{question}") String question,
                              @RequestParam(value="lastAnswer", defaultValue="{lastAnswer}") String lastAnswer) {
            topic = Papago.translateK2E(topic);
            question = Papago.translateK2E(question);
            String answer = TopicTalk.sendRequest(topic, question, lastAnswer);
            String answerKor = Papago.translateE2K(answer);
            return new String[]{answer, answerKor};
        }

}
