package com.rq.rubyyaicode.service;


import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.rq.rubyyaicode.model.dto.chathistory.ChatHistoryQueryRequest;
import com.rq.rubyyaicode.model.entity.ChatHistory;
import com.rq.rubyyaicode.model.entity.User;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 */
public interface ChatHistoryService extends IService<ChatHistory> {
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);


    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);

    /**
 * @description 添加一条新纪录
 * @author RQ
 * @date 2025/11/24 下午6:19
 */
    public boolean addChatMessage(Long appId, Long userId, String message, String messageType);
/**
 * @description 根据应用ID删除所有对话历史记录
 * @author RQ
 * @date 2025/11/24 下午6:58
 */
   public boolean deleteByAppId(Long appId);
}
