package domainapp.modules.hello.dom.hwo;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.extensions.secman.applib.tenancy.dom.ApplicationTenancy;
import org.apache.isis.extensions.secman.jpa.tenancy.dom.ApplicationTenancyRepository;

/**
 * Returns the app tenancies of all objects.
 */
@Collection
public class HelloWorldObject_isis2700Coll2 {

    private final HelloWorldObject helloWorldObject;

    public HelloWorldObject_isis2700Coll2(HelloWorldObject helloWorldObject) {
        this.helloWorldObject = helloWorldObject;
    }

    public Set<ApplicationTenancy> coll() {
        return helloWorldObjects.listAll().stream()
                .map(x -> applicationTenancyRepository.findByName(x.getAtPath()))
                .collect(Collectors.toSet());
    }

    @Inject
    HelloWorldObjects helloWorldObjects;

    @Inject
    ApplicationTenancyRepository applicationTenancyRepository;
}
