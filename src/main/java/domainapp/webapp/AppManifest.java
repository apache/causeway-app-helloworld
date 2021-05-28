package domainapp.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.secman.applib.SecmanConfiguration;
import org.apache.isis.extensions.secman.applib.permission.spi.PermissionsEvaluationService;
import org.apache.isis.extensions.secman.applib.permission.spi.PermissionsEvaluationServiceAllowBeatsVeto;
import org.apache.isis.extensions.secman.encryption.jbcrypt.IsisModuleExtSecmanEncryptionJbcrypt;
import org.apache.isis.extensions.secman.jpa.IsisModuleExtSecmanPersistenceJpa;
import org.apache.isis.extensions.secman.shiro.IsisModuleExtSecmanRealmShiro;
import org.apache.isis.persistence.jpa.eclipselink.IsisModuleJpaEclipselink;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
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
        IsisModuleSecurityShiro.class,
        IsisModuleJpaEclipselink.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleExtSecmanPersistenceJpa.class,
        IsisModuleExtSecmanRealmShiro.class,
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

    @Bean
    public SecmanConfiguration secmanConfiguration() {
        return SecmanConfiguration.builder()
                .adminUserName("sven").adminPassword("pass")
                .build();
    }

    @Bean
    public PermissionsEvaluationService permissionsEvaluationService() {
        return new PermissionsEvaluationServiceAllowBeatsVeto();
    }


}
