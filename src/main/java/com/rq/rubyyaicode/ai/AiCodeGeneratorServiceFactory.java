package com.rq.rubyyaicode.ai;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * @description AI 代码生成服务工厂
 * @author RQ
 * @date 2025/11/9 下午3:24
 */
public class AiCodeGeneratorServiceFactory {

    @Resource
    private ChatModel chatModel;

    @Resource
    private StreamingChatModel streamingChatModel;



    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
            return AiServices.builder(AiCodeGeneratorService.class)
                    .chatModel(chatModel)
                    .streamingChatModel(streamingChatModel)
                    .build();
        }
    }




