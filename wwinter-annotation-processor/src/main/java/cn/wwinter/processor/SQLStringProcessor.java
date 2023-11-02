package cn.wwinter.processor;

import javax.lang.model.element.AnnotationMirror;

/**
 * ClassName: SQLStringProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public class SQLStringProcessor implements ElementProcessor<AnnotationMirror, String> {
    @Override
    public String process(AnnotationMirror resource) {
        return null;
    }
}
