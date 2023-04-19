package com.example.openaipractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class MyRestfulController {
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



        ObjectMapper mapper = new ObjectMapper();
        Roundtable roundtable;
        TalkClassForRoundTable talkClassForRoundTable = new TalkClassForRoundTable();
        @RequestMapping("/twoaisButton")
        public String twoaisButton(@RequestParam("jsonString") String str) {
            try {
                roundtable = mapper.readValue(str, Roundtable.class);
                System.out.println(roundtable.toString());
                talkClassForRoundTable.setRoundtable(roundtable);
                str = talkClassForRoundTable.sendRequest();
            } catch (JsonProcessingException e) {
                roundtable = null;
                System.out.println("JsonProcessingException");
                e.printStackTrace();
            }
            return str;
        }

}
