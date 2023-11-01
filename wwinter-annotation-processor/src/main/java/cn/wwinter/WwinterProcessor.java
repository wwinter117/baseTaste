package cn.wwinter;

import cn.wwinter.annotations.DBTable;
import cn.wwinter.annotations.SQLLong;
import cn.wwinter.annotations.SQLString;
import cn.wwinter.processor.ElementProcessor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor6;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
import java.util.Arrays;
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
@SupportedAnnotationTypes({
        "cn.wwinter.annotations.DBTable",
        "cn.wwinter.annotations.SQLLong",
        "cn.wwinter.annotations.SQLString"
})
public class WwinterProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            Set<TypeElement> tables = getTables(annotations, roundEnv);
            System.out.println("------- 待处理表：-------");
            tables.forEach(System.out::println);
            System.out.println("------- 开始生成SQL -------");
            long start = System.currentTimeMillis();

            for (TypeElement table : tables) {
                genCreateSql(table);
            }
            System.out.println("------- 处理完毕，耗时：" + (System.currentTimeMillis() - start) + "ms -------");
        }
        return true;
    }

    private void genCreateSql(TypeElement table) {
        System.out.println("开始处理表：" + table);
        String tableName = table.getAnnotationsByType(DBTable.class)[0].name();
        String sql = "CREATE TABLE `" + tableName + "` (\n  ";
        List<? extends Element> fields = table.getEnclosedElements();
        for (Element element : fields) {
            if (element.getKind() == ElementKind.FIELD) {
                List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
//                List<ElementProcessor> processors = getFieldProcessors();


//                for (AnnotationMirror annotation : annotations) {
//                    handleAnnotation(sql, annotation);
//                }
            }
        }
        System.out.println("sql = " + sql);
    }

    private void handleAnnotation(String sql, AnnotationMirror annotation) {
//        System.out.println("annotation = " + annotation);
        if (annotation instanceof SQLLong) {
            SQLLong aLong = (SQLLong) annotation;
            String name = aLong.name();
            sql += name;
        } else if (annotation instanceof SQLString) {
            String name = ((SQLString) annotation).name();
            sql += name;
        } else {
            System.out.println("false");
        }
    }

    private Set<TypeElement> getTables(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<TypeElement> tableType = new HashSet<>();
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedTables = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : annotatedTables) {
                TypeElement typeElement = asTypeElement(element);
                if (typeElement != null) {
                    tableType.add(typeElement);
                }
            }
        }
        return tableType;
    }

    private TypeElement asTypeElement(Element element) {
        return element.accept(
                new ElementKindVisitor6<TypeElement, Void>() {
                    @Override
                    public TypeElement visitTypeAsInterface(TypeElement e, Void p) {
                        return e;
                    }

                    @Override
                    public TypeElement visitTypeAsClass(TypeElement e, Void p) {
                        return e;
                    }

                }, null
        );
    }

    /**
     * 生成源代码
     *
     * @param packageName 包路径
     * @param classname   类名
     */
    private void writeFile(String packageName, String classname, Element element) {
        JavaFileObject file = null;
        PrintWriter printWriter = null;
        try {
            file = filer.createSourceFile(classname, element);
            printWriter = new PrintWriter(file.openWriter());
            printWriter.println("package " + packageName + ";");
            printWriter.println("// 由注解处理器WwinterProcessor生成");
            printWriter.println("public class " + classname + " {");
            printWriter.println();
            printWriter.println("}");
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
