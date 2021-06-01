package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.secman.encryption.jbcrypt.IsisModuleExtSecmanEncryptionJbcrypt;
import org.apache.isis.extensions.secman.jpa.IsisModuleExtSecmanPersistenceJpa;
import org.apache.isis.persistence.jpa.eclipselink.IsisModuleJpaEclipselink;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;
import domainapp.security.SeedUsersAndRoles;
import domainapp.security.fixturescripts.FixtureScriptSpecProvider;
import domainapp.security.multitenancy.ApplicationTenancyEvaluatorUsingAtPath;

@Configuration
@Import({
        IsisModuleCoreRuntimeServices.class,
        IsisModuleJpaEclipselink.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleExtSecmanPersistenceJpa.class,
        IsisModuleExtSecmanEncryptionJbcrypt.class,

        IsisModuleTestingFixturesApplib.class,
        IsisModuleTestingH2ConsoleUi.class,
        SeedUsersAndRoles.class,

        FixtureScriptSpecProvider.class,
        ApplicationTenancyEvaluatorUsingAtPath.class,

        IsisModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.NoTranslations),
})
public class AppManifest {

}
