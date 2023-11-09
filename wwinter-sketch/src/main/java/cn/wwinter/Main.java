package cn.wwinter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Main
 * Package: cn.wwinter
 * Description:
 * Datetime: 2023/11/6
 * Author: zhangdd
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException {
        // 配置FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setClassForTemplateLoading(Main.class, "/templates"); // 设置模板文件所在目录

        try {
            // 加载模板
            Template template = cfg.getTemplate("example.ftl"); // 模板文件名为example.ftl，放在/templates目录下

            // 准备数据模型
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("name", "张冬冬");
            dataModel.put("age", 25);

            // 渲染模板并输出结果
            BufferedWriter writer = new BufferedWriter(new FileWriter(Main.class.getResource("/").getFile() + "test.html"));
//            OutputStreamWriter writer = new OutputStreamWriter(System.out);
            template.process(dataModel, writer);
            writer.flush();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
