package domainapp.modules.hello.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;

@javax.jdo.annotations.Column(length = AtPath.MAX_LEN, allowsNull = "false")
@Property(editing = Editing.DISABLED, maxLength = AtPath.MAX_LEN, optionality = Optionality.MANDATORY)
@PropertyLayout()
@Parameter(maxLength = AtPath.MAX_LEN, optionality = Optionality.MANDATORY)
@ParameterLayout()
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtPath {

    int MAX_LEN = 50;

}
