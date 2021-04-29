package domainapp.modules.hello.dom.hwo;

import org.apache.isis.applib.annotation.Property;

@Property
public class HelloWorldObject_isis2619Prop {

    private final HelloWorldObject helloWorldObject;

    public HelloWorldObject_isis2619Prop(HelloWorldObject helloWorldObject) {
        this.helloWorldObject = helloWorldObject;
    }

    public String prop() {
        return "mixinProp";
    }
}
