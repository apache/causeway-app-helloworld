package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.applib.IsisModuleApplibChangeAndExecutionLoggers;
import org.apache.isis.applib.IsisModuleApplibMixins;
import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleus;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.applib.IsisModuleViewerWicketApplibMixins;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;

@Configuration
@Import({
        IsisModuleApplibMixins.class,
        IsisModuleApplibChangeAndExecutionLoggers.class,

        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecurityShiro.class,
        IsisModulePersistenceJdoDatanucleus.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketApplibMixins.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.NoTranslations),
})
public class AppManifest {
}
