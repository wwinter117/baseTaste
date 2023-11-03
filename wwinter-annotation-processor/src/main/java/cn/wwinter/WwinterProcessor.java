package cn.wwinter;

import cn.wwinter.annotations.*;
import cn.wwinter.model.TableInfo;
import cn.wwinter.model.MetaField;
import cn.wwinter.processor.SQLElementProcessor;

import javax.annotation.processing.*;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor6;
import java.lang.annotation.Annotation;
import java.util.*;

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

    private static final List<Class<? extends Annotation>> ANNOTATIONS = new ArrayList<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        ANNOTATIONS.add(SQLInteger.class);
        ANNOTATIONS.add(SQLLong.class);
        ANNOTATIONS.add(SQLString.class);
        ANNOTATIONS.add(SQLDate.class);
        ANNOTATIONS.add(SQLTime.class);

        System.out.println(" ____      ____            _            _                  \n" +
                           "|_  _|    |_  _|          (_)          / |_                \n" +
                           "  \\ \\  /\\  / /_   _   __  __   _ .--. `| |-'.---.  _ .--.  \n" +
                           "   \\ \\/  \\/ /[ \\ [ \\ [  ][  | [ `.-. | | | / /__\\\\[ `/'`\\] \n" +
                           "    \\  /\\  /  \\ \\/\\ \\/ /  | |  | | | | | |,| \\__., | |     \n" +
                           "     \\/  \\/    \\__/\\__/  [___][___||__]\\__/ '.__.'[___]    \n");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (!roundEnv.processingOver() && annotations != null) {
            Set<TypeElement> tables = getTables(annotations, roundEnv);
            if (tables.isEmpty()) {
                return true;
            }
            System.out.println("------- 待处理表：-------");
            tables.forEach(System.out::println);
            System.out.println("------- 表信息： -------");
            TableInfo tableInfo = null;
            for (TypeElement table : tables) {
                tableInfo = processTable(table);
                System.out.println(table.getSimpleName() + " : " + tableInfo);
            }
            System.out.println("------- 开始处理... -------");
            long start = System.currentTimeMillis();
            


            System.out.println("------- 处理完毕，耗时：" + (System.currentTimeMillis() - start) + "ms -------");
        }
        return true;
    }

    private TableInfo processTable(TypeElement table) {
        List<Element> fields = getFields(table);
        TableInfo tableInfo = processFields(fields);
        tableInfo.setTableName(table.getAnnotation(DBTable.class).name());
        return tableInfo;
    }

    private TableInfo processFields(List<Element> fields) {
        TableInfo tableInfo = new TableInfo();
        List<MetaField> metaFields = new ArrayList<>();
        for (Element field : fields) {
            List<? extends AnnotationMirror> annotationMirrors = field.getAnnotationMirrors();
            metaFields.add(processAnnotation(field));
        }
        tableInfo.setFields(metaFields);
        return tableInfo;
    }

    private MetaField processAnnotation(Element field) {
        MetaField metaField = null;
        for (SQLElementProcessor processor : getFieldProcessors()) {
            metaField = processor.process(field);
            if (metaField != null) {
                break;
            }
        }
        return metaField;
    }

    private List<SQLElementProcessor> getFieldProcessors() {
        ServiceLoader<SQLElementProcessor> processors = ServiceLoader.load(SQLElementProcessor.class, WwinterProcessor.class.getClassLoader());
        List<SQLElementProcessor> list = new ArrayList<>();
        for (SQLElementProcessor processor : processors) {
            if (processor != null) {
                list.add(processor);
            }
        }
        return list;
    }

    /**
     * get all tables which has `@DBTable`
     */
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

    /**
     * get a table`s all fields
     */
    private List<Element> getFields(TypeElement table) {
        List<Element> fields = new ArrayList<>();
        for (Element element : table.getEnclosedElements()) {
            if (element.getKind() == ElementKind.FIELD) {
                fields.add(element);
            }
        }
        return fields;
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

}
