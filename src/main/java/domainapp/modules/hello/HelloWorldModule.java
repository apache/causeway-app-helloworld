package domainapp.modules.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import domainapp.modules.hello.fixture.DemoFixture;

@Configuration
@Import({

        DemoFixture.class
})
@ComponentScan
public class HelloWorldModule {

}
