package domainapp.webapp;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.apache.causeway.applib.CausewayModuleApplibChangeAndExecutionLoggers;
import org.apache.causeway.applib.CausewayModuleApplibMixins;
import org.apache.causeway.core.config.presets.CausewayPresets;
import org.apache.causeway.core.runtimeservices.CausewayModuleCoreRuntimeServices;
import org.apache.causeway.persistence.jdo.datanucleus.CausewayModulePersistenceJdoDatanucleus;
import org.apache.causeway.security.simple.CausewayModuleSecuritySimple;
import org.apache.causeway.security.simple.realm.SimpleRealm;
import org.apache.causeway.security.simple.realm.SimpleRealm.Grant;
import org.apache.causeway.testing.h2console.ui.CausewayModuleTestingH2ConsoleUi;
import org.apache.causeway.viewer.restfulobjects.jaxrsresteasy.CausewayModuleViewerRestfulObjectsJaxrsResteasy;
import org.apache.causeway.viewer.wicket.applib.CausewayModuleViewerWicketApplibMixins;
import org.apache.causeway.viewer.wicket.viewer.CausewayModuleViewerWicketViewer;

import domainapp.modules.hello.HelloWorldModule;

@Configuration
@Import({
        CausewayModuleApplibMixins.class,
        CausewayModuleApplibChangeAndExecutionLoggers.class,

        CausewayModuleCoreRuntimeServices.class,

        CausewayModuleSecuritySimple.class,
        CausewayModulePersistenceJdoDatanucleus.class,
        CausewayModuleViewerRestfulObjectsJaxrsResteasy.class,
        CausewayModuleViewerWicketApplibMixins.class,
        CausewayModuleViewerWicketViewer.class,

        CausewayModuleTestingH2ConsoleUi.class,
        HelloWorldModule.class
})
@PropertySources({
    @PropertySource(CausewayPresets.NoTranslations),
})
public class AppManifest {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Sets up a simple in-memory authentication/authorization realm.
     * <p>
     * For production use you would perhaps replace the {@link CausewayModuleSecuritySimple}
     * with a different CausewayModuleSecurityXxx integration.
     */
    @Bean
    public SimpleRealm simpleAuthRealm(final PasswordEncoder passwordEncoder) {
        var hashedPass = passwordEncoder.encode("pass");
        return new SimpleRealm()
            .addRole("admin_role", id->Grant.CHANGE)
            .addRole("hello_role", id->
                id.getFullIdentityString().contains("HelloWorldObject")
                    ? Grant.CHANGE
                    : Grant.NONE)
            .addRole("default_role", id->
                id.getLogicalType().getNamespace().startsWith("causeway.applib")
                    || id.getLogicalType().getNamespace().startsWith("causeway.security")
                    ? Grant.CHANGE
                    : Grant.NONE)
            .addRole("fixtures_role", id->
                id.getLogicalType().getNamespace().startsWith("causeway.testing.fixtures")
                    ? Grant.CHANGE
                    : Grant.NONE)
            .addUser("sven", hashedPass, List.of("admin_role"))
            .addUser("dick", hashedPass, List.of("hello_role", "default_role"))
            .addUser("bob", hashedPass, List.of("hello_role", "default_role", "fixtures_role"))
            .addUser("joe", hashedPass, List.of("hello_role", "default_role"));
    }

}
