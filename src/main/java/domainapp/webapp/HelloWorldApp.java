package domainapp.webapp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.apache.causeway.core.config.presets.CausewayPresets;

import domainapp.webapp.HelloWorldApp.SimpleAuthSetup;
import domainapp.webapp.SimpleAuth.Realm;

@SpringBootApplication
@Import({
    SimpleAuthSetup.class,
    SimpleAuth.class,
    AppManifest.class,
})
public class HelloWorldApp extends SpringBootServletInitializer {

    @Configuration
    static class SimpleAuthSetup {

        /* migrated from shiro.ini
            [users]
            sven = pass, admin_role
            dick = pass, hello_role, default_role
            bob  = pass, hello_role, default_role, fixtures_role
            joe  = pass, hello_role, default_role

            [roles]
            hello_role =   *:HelloWorldObjects:*:*,\
                           *:HelloWorldObject:*:*
            admin_role = *
            default_role   = causeway.applib,\
                             causeway.security
            fixtures_role  = causeway.testing.fixtures
            features_role  = causeway.feat
            metamodel_role = causeway.metamodel
            h2_role        = causeway.ext.h2Console
            jdo_role       = causeway.persistence.jdo
            swagger_role   = causeway.viewer.restfulobjects
            conf_role      = causeway.conf
            sudo_role      = causeway.sudo
         */
        @Bean
        public SimpleAuth.Realm simpleAuthRealm() {
            return new Realm()
                .addRole("hello_role", id->id.getFullIdentityString().contains("HelloWorldObject"))
                .addRole("admin_role", id->true)
                .addRole("default_role", id->id.getFullIdentityString().startsWith("causeway.applib")
                        || id.getFullIdentityString().startsWith("causeway.security"))
                .addRole("fixtures_role", id->id.getFullIdentityString().startsWith("causeway..testing.fixtures"))
                .addUser("sven", "pass", List.of("admin_role"))
                .addUser("dick", "pass", List.of("hello_role", "default_role"))
                .addUser("bob", "pass", List.of("hello_role", "default_role", "fixtures_role"))
                .addUser("joe", "pass", List.of("hello_role", "default_role"));
        }
    }

    public static void main(final String[] args) {
        CausewayPresets.prototyping(); // or run with use -DPROTOTYPING=true
        SpringApplication.run(new Class[] { HelloWorldApp.class }, args);
    }
}
