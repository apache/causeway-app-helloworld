package domainapp.webapp;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.secman.api.IsisModuleExtSecmanApi;
import org.apache.isis.extensions.secman.api.SecmanConfiguration;
import org.apache.isis.extensions.secman.api.SecurityRealm;
import org.apache.isis.extensions.secman.api.SecurityRealmCharacteristic;
import org.apache.isis.extensions.secman.api.SecurityRealmService;
import org.apache.isis.extensions.secman.api.permission.PermissionsEvaluationService;
import org.apache.isis.extensions.secman.api.permission.PermissionsEvaluationServiceAllowBeatsVeto;
import org.apache.isis.extensions.secman.encryption.jbcrypt.IsisModuleExtSecmanEncryptionJbcrypt;
import org.apache.isis.extensions.secman.jpa.IsisModuleExtSecmanPersistenceJpa;
import org.apache.isis.extensions.secman.model.IsisModuleExtSecmanModel;
import org.apache.isis.extensions.secman.shiro.IsisModuleExtSecmanRealmShiro;
import org.apache.isis.persistence.jpa.eclipselink.IsisModuleJpaEclipselink;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;
import domainapp.security.SeedUsersAndRoles;
import domainapp.security.scripts.SecmanConstants;

@Configuration
@Import({
        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecurityShiro.class,
        IsisModuleJpaEclipselink.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleExtSecmanApi.class,
        IsisModuleExtSecmanModel.class,
        IsisModuleExtSecmanPersistenceJpa.class,
        IsisModuleExtSecmanRealmShiro.class,
        IsisModuleExtSecmanEncryptionJbcrypt.class,

        IsisModuleTestingFixturesApplib.class,
        SeedUsersAndRoles.class,

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
                .adminRoleName(SecmanConstants.ADMIN_ROLE_NAME)
                .regularUserRoleName(SecmanConstants.USER_ROLE_NAME)
                .build();
    }

    @Bean
    public PermissionsEvaluationService permissionsEvaluationService() {
        return new PermissionsEvaluationServiceAllowBeatsVeto();
    }

    @Bean
    public SecurityRealmService securityRealmService() {
        return new SecurityRealmService() {
            @Override
            public SecurityRealm getCurrentRealm() {
                return () -> EnumSet.noneOf(SecurityRealmCharacteristic.class);
            }
        };
    }

}
