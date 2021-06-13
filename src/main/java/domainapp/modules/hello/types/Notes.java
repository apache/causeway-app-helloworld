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
import org.apache.isis.applib.annotation.Where;

@javax.jdo.annotations.Column(length = Notes.MAX_LEN, allowsNull = "true")
@Property(editing = Editing.ENABLED, maxLength = Notes.MAX_LEN, optionality = Optionality.OPTIONAL)
@PropertyLayout(multiLine = 10, hidden = Where.ALL_TABLES)
@Parameter(maxLength = Notes.MAX_LEN, optionality = Optionality.OPTIONAL)
@ParameterLayout(multiLine = 10)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Notes {

    int MAX_LEN = 4000;

}
