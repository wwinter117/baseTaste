package cn.wwinter;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * ClassName: WwinterProcessor
 * Package: cn.wwinter
 * Description:
 * Datetime: 2023/10/30
 * Author: zhangdd
 */
@SupportedAnnotationTypes("cn.wwinter.DBTable")
public class WwinterProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            long start = System.currentTimeMillis();
            System.out.println("---- 开始处理注解 ----");


            System.out.println("---- 处理完毕，耗时：" + (System.currentTimeMillis() - start) + "ms ----");
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
