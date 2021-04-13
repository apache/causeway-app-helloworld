package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.persistence.jdo.datanucleus.IsisModuleJdoDatanucleus;
import org.apache.isis.security.bypass.authorization.AuthorizorBypass;
import org.apache.isis.security.spring.IsisModuleSecuritySpring;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;

@Configuration
@Import({
        IsisModuleCoreRuntimeServices.class,

        IsisModuleSecuritySpring.class,
        SecurityConfig.class,   // defines users
        AuthorizorBypass.class, // security-spring doesn't provide an Authorizor impl, so we just use the no-op one from bypass.

        IsisModuleJdoDatanucleus.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.NoTranslations),
})
public class AppManifest {

}
