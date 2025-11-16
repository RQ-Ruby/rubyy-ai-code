package com.rq.rubyyaicode.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用查询请求（分页与筛选）
 */
@Data
public class AppQueryRequest implements Serializable {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 应用名称（模糊）
     */
    private String appName;

    /**
     * 封面（模糊）
     */
    private String cover;

    /**
     * 初始化 prompt（模糊）
     */
    private String initPrompt;

    /**
     * 代码生成类型（枚举值）
     */
    private String codeGenType;

    /**
     * 部署标识
     */
    private String deployKey;

    /**
     * 优先级（精选等）
     */
    private Integer priority;

    /**
     * 创建人 id
     */
    private Long userId;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（ascend/descend）
     */
    private String sortOrder;

    /**
     * 页码
     */
    private long pageNum = 1;

    /**
     * 每页条数
     */
    private long pageSize = 10;

    private static final long serialVersionUID = 1L;
}
