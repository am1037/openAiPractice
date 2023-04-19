package com.example.openaipractice;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

public class TalkClassForRoundTable {
    String model = "gpt-3.5-turbo";

    OpenAiService service;
    String token = System.getenv("OPENAI_TOKEN");
    List<artificialIntelligence> aiList;
    Roundtable roundtable;
    int nextBaton;
    artificialIntelligence aiWithBaton;
    String str;

    public void setRoundtable(Roundtable roundtable) {
        System.out.println("setRoundtable");
        this.roundtable = roundtable;
        aiList = new ArrayList<>();
        for(int i=0; i<roundtable.getAI_names().size(); i++){
            aiList.add(new artificialIntelligence());
            aiList.get(i).setName(roundtable.getAI_names().get(i));
            aiList.get(i).setSystem(roundtable.getAI_systems().get(i));
        }
        nextBaton = roundtable.getNext_Baton();
    }

    public String sendRequest(){
        System.out.println("sendRequest");
        System.out.println("aiList: "+aiList);
        System.out.println("nextBaton: "+nextBaton);
        aiWithBaton = aiList.get(nextBaton);

        List<ChatMessage> chatMessages = new ArrayList<>();
        str = "You are a AI for role-play.";
        chatMessages.add(new ChatMessage("system", str));
        str = roundtable.getAI_names().get(0) + " and " + roundtable.getAI_names().get(1) + " are having a conversation about " + roundtable.getTopic();
        chatMessages.add(new ChatMessage("system", str));
        str = "Your role is " + aiWithBaton.getName()+". Generate next line for your character.";
        chatMessages.add(new ChatMessage("system", str));

        if(!aiWithBaton.getSystem().equals("")) {
            str = "Profiles of " + aiWithBaton.getName() + " : \n" + aiWithBaton.getSystem();
            chatMessages.add(new ChatMessage("system", str));
        }
        if(!roundtable.last_Script.equals("")) {
            str = roundtable.last_Script;
            chatMessages.add(new ChatMessage("user", str));
        }

        System.out.println("this is "+this.getClass());
        chatMessages.forEach(System.out::println);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .model(model)
                .n(1)
                .temperature(0.3)
                .maxTokens(200)
                .build();
        ChatCompletionResult ccr = service.createChatCompletion(request);

        System.out.println("this is "+this.getClass());
        ccr.getChoices().forEach(System.out::println);

        return ccr.getChoices().get(0).getMessage().getContent();
    }

    //    List<AI> aiList = new ArrayList<>();
    public TalkClassForRoundTable() {
        service = new OpenAiService(token);
    }



}