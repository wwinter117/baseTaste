package cn.wwinter.processor;

/**
 * ClassName: ElementProcessor
 * Package: cn.wwinter.processor
 * Description:
 * Datetime: 2023/11/1
 * Author: zhangdd
 */
public interface ElementProcessor<P, R> {
    R process(P resource);
}
