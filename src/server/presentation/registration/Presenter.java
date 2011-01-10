package server.presentation.registration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

@Stereotype
@Named
@RequestScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Presenter {

}
