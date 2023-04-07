package com.example.dealingDB;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConservationDAO {
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    String config = "batis-config.xml";
    public ConservationDAO() {
        try {
            inputStream = Resources.getResourceAsStream(config);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConservationVO selectOneConversation(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            return sqlSession.selectOne("com.example.dealingDB.mapper.selectConservation", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertConversation(ConservationVO vo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            int a = sqlSession.insert("com.example.dealingDB.mapper.insertConservation", vo);
            sqlSession.commit();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int insertConversation(ChatCompletionRequest requests) {
        List<ConservationVO> vos = converting(requests);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            int a=0;
            for(ConservationVO vo : vos){
                sqlSession.insert("com.example.dealingDB.mapper.insertConservation", vo);
                a++;
            }
            sqlSession.commit();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int insertConversation(ChatCompletionResult result) {
        ConservationVO vo = converting(result);
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            int a = sqlSession.insert("com.example.dealingDB.mapper.insertConservation", vo);
            sqlSession.commit();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    private List<ConservationVO> converting(ChatCompletionRequest request){
        List<ConservationVO> vos = new ArrayList<>();
        for(ChatMessage message : request.getMessages()){
            ConservationVO vo = new ConservationVO();
            vo.setObject("chat.completion");
            vo.setRole(message.getRole());
            vo.setContent(message.getContent());
            vo.setDirection("request");
            vo.setCreated(System.currentTimeMillis()/1000);
            vos.add(vo);
        }
        return vos;
    }
    private ConservationVO converting(ChatCompletionResult result){
        ConservationVO vo = new ConservationVO();
        vo.setId(result.getId());
        vo.setObject(result.getObject());
        vo.setCreated(result.getCreated());
        vo.setModel(result.getModel());
        vo.setPrompt_tokens(result.getUsage().getPromptTokens());
        vo.setCompletion_tokens(result.getUsage().getCompletionTokens());
        vo.setTotal_tokens(result.getUsage().getTotalTokens());
        vo.setRole(result.getChoices().get(0).getMessage().getRole());
        vo.setContent(result.getChoices().get(0).getMessage().getContent());
        vo.setDirection("response");
        return vo;
    }


}
