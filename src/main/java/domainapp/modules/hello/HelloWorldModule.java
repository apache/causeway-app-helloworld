package domainapp.modules.hello;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domainapp.modules.hello.fixture.DemoFixture;

@Configuration
@Import({

        DemoFixture.class
})
@ComponentScan
@EnableJpaRepositories
@EntityScan(
    basePackageClasses = {
        HelloWorldModule.class
})
public class HelloWorldModule {

}
