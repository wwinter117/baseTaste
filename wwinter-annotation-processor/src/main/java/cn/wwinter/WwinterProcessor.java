package cn.wwinter;

import cn.wwinter.annotations.*;
import cn.wwinter.model.TableInfo;
import cn.wwinter.model.MetaField;
import cn.wwinter.processor.ElementProcessor;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.annotation.processing.*;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
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

    /**
     * TODO: 没有多态这里怎么做
     */
    private TableInfo processFields(List<Element> fields) {
        TableInfo tableInfo = new TableInfo();
        List<MetaField> metaFields = new ArrayList<>();
        for (Element field : fields) {
            String name = null;
            int length = 0;
            boolean primaryKey = false;
            boolean allowNull = true;
            boolean unique = false;
            if (field.getAnnotation(SQLLong.class) != null) {
                SQLLong sqlLong = field.getAnnotation(SQLLong.class);
                name = sqlLong.name();
                length = sqlLong.length();
                primaryKey = sqlLong.primaryKey();
                allowNull = sqlLong.allowNull();
                unique = sqlLong.unique();
            } else if (field.getAnnotation(SQLString.class) != null) {
                SQLString sqlString = field.getAnnotation(SQLString.class);
                name = sqlString.name();
                length = sqlString.length();
                primaryKey = sqlString.primaryKey();
                allowNull = sqlString.allowNull();
                unique = sqlString.unique();
            } else if (field.getAnnotation(SQLInteger.class) != null) {
                SQLInteger sqlInteger = field.getAnnotation(SQLInteger.class);
                name = sqlInteger.name();
                length = sqlInteger.length();
                primaryKey = sqlInteger.primaryKey();
                allowNull = sqlInteger.allowNull();
                unique = sqlInteger.unique();
            } else if (field.getAnnotation(SQLDate.class) != null) {
                SQLDate sqlDate = field.getAnnotation(SQLDate.class);
                name = sqlDate.name();
                length = sqlDate.length();
                primaryKey = sqlDate.primaryKey();
                allowNull = sqlDate.allowNull();
                unique = sqlDate.unique();
            }
            MetaField metaField = new MetaField();
            metaField.setName(name);
            metaField.setLength(length);
            metaField.setPrimaryKey(primaryKey);
            metaField.setAllowNull(allowNull);
            metaField.setUnique(unique);
            metaFields.add(metaField);
        }
        tableInfo.setFields(metaFields);
        return tableInfo;
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








    private void genCreateSql(TypeElement table) {
        System.out.println("开始处理表：" + table);
        String tableName = table.getAnnotationsByType(DBTable.class)[0].name();
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(tableName).append("\n  ");
        List<? extends Element> fields = table.getEnclosedElements();
        List<MetaField> metaFields = new ArrayList<>();
        for (Element element : fields) {
            if (element.getKind() == ElementKind.FIELD) {
                System.out.println("element = " + element);
                List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
                metaFields.add(processAnnotations(annotations));
            }
        }
//        for (MetaField metaField : metaFields) {
//            sql.append(metaField.getName()).append(' ').append(metaField.getJdbcType().name());
//            if (metaField.getLength() != 0) {
//                sql.append('(').append(metaField.getLength()).append(')');
//            }
//            if (metaField.isPrimaryKey()) {
//                sql.append("PRIMARY KEY,");
//            }
//            if (!metaField.isAllowNull()) {
//                sql.append("NOT NULL,");
//            }
//            if (metaField.isUnique()) {
//                sql.append("UNIQUE,");
//            }
//        }
        System.out.println("sql = " + sql);
    }

    private MetaField processAnnotations(List<? extends AnnotationMirror> annotations) {
        AnnotationMirror anno = annotations.stream().filter(s -> s.getAnnotationType().equals(SQLLong.class.getCanonicalName()))
                .findFirst()
                .orElse(null);
        if (anno == null) {
            return null;
        }
        System.out.println("anno = " + anno);
        MetaField metaField = null;
        for (ElementProcessor<?, ?> processor : getFieldProcessors()) {
            metaField = (MetaField) processAnno(processor, anno);
            if (metaField != null) {
                break;
            }
        }
        return metaField;
    }

    @SuppressWarnings("unchecked")
    private <P, R> R processAnno(ElementProcessor<P, R> processor, AnnotationMirror anno) {
        P param = (P) anno;
        return processor.process(param);
    }

    private List<ElementProcessor<?, ?>> getFieldProcessors() {
        @SuppressWarnings("rawtypes")
        ServiceLoader<ElementProcessor> processors = ServiceLoader.load(ElementProcessor.class, WwinterProcessor.class.getClassLoader());
        List<ElementProcessor<?, ?>> list = new ArrayList<>();
        for (ElementProcessor<?, ?> processor : processors) {
            if (processor != null) {
                list.add(processor);
            }
        }
        return list;
    }





}
