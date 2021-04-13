package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.persistence.jdo.datanucleus.IsisModuleJdoDatanucleus;
import org.apache.isis.security.bypass.authorization.AuthorizorBypass;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.security.spring.IsisModuleSecuritySpring;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;

@Configuration
@Import({
        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecuritySpring.class,
        IsisModuleJdoDatanucleus.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        LoginController.class,
        AuthorizorBypass.class,

        IsisModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.NoTranslations),
})
public class AppManifest {

}
