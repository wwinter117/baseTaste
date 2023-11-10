package cn.wwinter.freemaker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * ClassName: AbstractTemplateHandler
 * Package: cn.wwinter.freemaker
 * Description:
 * Datetime: 2023/11/9
 * Author: zhangdd
 */
public abstract class AbstractTemplateHandler implements GetSqlHandler {
    private static final String BASE_PATH;
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

    static {
        BASE_PATH = "/freemaker/templates";
        // 配置FreeMarker
        cfg.setClassForTemplateLoading(AbstractTemplateHandler.class, BASE_PATH); // 设置模板文件所在目录
    }

    public String generateSql(Map<String, Object> dataModel, String templateName) {
        try {
            Template template = cfg.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(dataModel, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("freemaker生成异常：",e);
        }
    }
}
