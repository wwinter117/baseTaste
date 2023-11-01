package cn.wwinter;

import cn.wwinter.annotations.DBTable;
import cn.wwinter.annotations.SQLLong;
import cn.wwinter.annotations.SQLString;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor6;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: WwinterProcessor
 * Package: cn.wwinter
 * Description:
 * Datetime: 2023/10/30
 * Author: zhangdd
 */
public class FieldProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
