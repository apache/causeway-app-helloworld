package domainapp.modules.hello.dom.hwo;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;

/**
 * Returns the 'other' hello world objects.
 */
@Collection
public class HelloWorldObject_isis2700Coll {

    private final HelloWorldObject helloWorldObject;

    public HelloWorldObject_isis2700Coll(HelloWorldObject helloWorldObject) {
        this.helloWorldObject = helloWorldObject;
    }

    public List<HelloWorldObject> coll() {
        return helloWorldObjects.listAll().stream()
                .filter(x -> x != helloWorldObject)
                .collect(Collectors.toList());
    }

    @Inject
    HelloWorldObjects helloWorldObjects;
}
