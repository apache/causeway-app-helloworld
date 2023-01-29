package domainapp.modules.hello.dom.hwo;

import domainapp.modules.hello.types.Name;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.causeway.applib.annotation.*;
import org.apache.causeway.applib.query.Query;
import org.apache.causeway.applib.services.repository.RepositoryService;

@Named("hello.HelloWorldObjects")
@DomainService(nature = NatureOfService.VIEW)
@Priority(PriorityPrecedence.EARLY)
public class HelloWorldObjects {

    private final RepositoryService repositoryService;

    @Inject
    public HelloWorldObjects(
            final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public HelloWorldObject create(
            @Name final String name) {
        return repositoryService.persist(new HelloWorldObject(name));
    }
    public String default0Create() {
        return "Hello World!";
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<HelloWorldObject> findByName(
            @Name final String name) {
        return repositoryService.allMatches(
                Query.named(HelloWorldObject.class, "findByName")
                        .withParameter("name", name)
        );
    }

    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    public List<HelloWorldObject> listAll() {
        return repositoryService.allInstances(HelloWorldObject.class);
    }


}
