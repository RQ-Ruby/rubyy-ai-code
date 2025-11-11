package com.rq.rubyyaicode.ai.model;

import dev.langchain4j.model.output.structured.Description;
import lombok.Data;
/**
 * @description HTML 代码结果
 * @author RQ
 * @date 2025/11/9 下午1:26
 */
@Description("生成 HTML 代码文件的结果")
@Data
public class HtmlCodeResult {

    @Description("HTML代码")
    private String htmlCode;

    @Description("生成代码的描述")
    private String description;
}

