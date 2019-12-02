package domainapp.modules.hello.dom.impl;

import domainapp.modules.hello.dom.impl.HelloWorldObject;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldObject_updateName_Test {

    @Test
    public void happy_case() throws Exception {
        // given
        final HelloWorldObject object = new HelloWorldObject("Foo");
        assertThat(object.getName()).isEqualTo("Foo");

        // when
        object.updateName("Bar");

        // then
        assertThat(object.getName()).isEqualTo("Bar");
    }

}