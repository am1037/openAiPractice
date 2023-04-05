package com.example.openaipractice;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

public class TopicTalk {
    public static String sendRequest(String topic, String question) {
        ///////
//        String topic = "dinner";
//        String youLast = "Sure, what would you like to know about dinner? Do you need suggestions for what to make, or advice on planning a dinner party, or something else entirely? Let me know how I can assist you.";
//        String meLast = "I want to eat noodles. Please recommend what kind of noodles to eat.";
        ///////
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);

        String str ="We were talking about '";
        str += topic;
        str += "'.";

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), str));
        messages.add(new ChatMessage(ChatMessageRole.USER.value(), question));
        System.out.println("input here : ");
        for(ChatMessage m : messages){
            System.out.println(m.getRole() + " : " + m.getContent());
        }

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(messages)
                .maxTokens(100)
                .temperature(0.5)
                .model("gpt-3.5-turbo")
                .build();

        ChatCompletionResult result = service.createChatCompletion(request);
        System.out.println("answer here : ");
        result.getChoices().forEach(System.out::println);
        String answer = result.getChoices().get(0).getMessage().getContent();
        return answer;
    }

    public static String sendRequest(String topic, String question, String lastAnswer) {
        ///////
//        String topic = "dinner";
//        String youLast = "Sure, what would you like to know about dinner? Do you need suggestions for what to make, or advice on planning a dinner party, or something else entirely? Let me know how I can assist you.";
//        String meLast = "I want to eat noodles. Please recommend what kind of noodles to eat.";
        ///////
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token);

        String str ="We were talking about '";
        str += topic;
        str += "'.";
        str += "You said '";
        str += lastAnswer;
        str += "'.";

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), str));
        messages.add(new ChatMessage(ChatMessageRole.USER.value(), question));
        System.out.println("input here : ");
        for(ChatMessage m : messages){
            System.out.println(m.getRole() + " : " + m.getContent());
        }

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(messages)
                .maxTokens(100)
                .temperature(0.5)
                .model("gpt-3.5-turbo")
                .build();

        ChatCompletionResult result = service.createChatCompletion(request);
        System.out.println("answer here : ");
        result.getChoices().forEach(System.out::println);
        String answer = result.getChoices().get(0).getMessage().getContent();
        return answer;
    }

}
