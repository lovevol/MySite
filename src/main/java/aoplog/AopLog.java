package aoplog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//自定义注解
@Target({ElementType.METHOD})//目标是方法
@Retention(RetentionPolicy.RUNTIME)//运行时通过反射获取
public @interface AopLog {
    /**
     * id
     * @return
     */
    String id() default "";
    /**
     * 操作类型描述
     * @return
     */
    String operateTypeDesc() default "";

    /**
     * 操作类型
     * @return
     */
    long operateType() default -1;

    /**
     * 模块编码
     * @return
     */
    String moudleCode() default "";

    /**
     * 模块名称
     * @return
     */
    String moudleName() default "";

    /**
     * 业务类型
     * @return
     */
    String bussType() default "";
    /**
     * 业务类型描述
     * @return
     */
    String bussTypeDesc() default "";
}
