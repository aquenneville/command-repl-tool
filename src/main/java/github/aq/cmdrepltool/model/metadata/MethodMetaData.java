package github.aq.cmdrepltool.model.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodMetaData {
	
	String name() default "";
	String arguments() default "";
	String description() default "";
	String usage() default "";
}
