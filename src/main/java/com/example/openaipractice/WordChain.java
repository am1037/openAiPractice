package com.example.openaipractice;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordChain {
    WordChain() {
    }

    OpenAiService service = new OpenAiService(System.getenv("OPENAI_TOKEN"));
    ChatCompletionRequest request;
    ChatCompletionResult result;
    List<ChatMessage> messages = new ArrayList<>();
    String word;
    String firstChar;
    String lastChar;
    HashSet<String> wordSet = new HashSet<>();

    String sendWord(String lastWord){
        messages.clear();
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), "You are a ai for word chain game. The last word is " + lastWord));
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), "Return ONLY A WORD. for example : apple"));

        sendRequest(lastWord);

//        while(!firstChar.equals(lastChar)){
//            System.out.println(word);
//            System.out.println(lastWord);
//            messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), word + " is not start with "+ lastChar +". please return another word."));
//            sendRequest(lastWord);
//        }

        while(wordSet.contains(word)){
            messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), word + " is already used. please return another word."));
            request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .temperature(0.2)
                    .maxTokens(10)
                    .build();
            result = service.createChatCompletion(request);
            word = result.getChoices().get(0).getMessage().getContent();
        }

        wordSet.add(word);
        System.out.println(wordSet);

        for(ChatMessage c : messages){
            System.out.println(c.getContent());
        }
        return word;
    }

    private void sendRequest(String lastWord) {
        request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .temperature(0.2)
                .maxTokens(10)
                .build();
        result = service.createChatCompletion(request);
        word = result.getChoices().get(0).getMessage().getContent();
        firstChar = word.substring(0, 1);
        lastChar = lastWord.substring(lastWord.length() - 1);
    }


}