package domainapp.modules.hello.dom.hwo;

import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PriorityPrecedence;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.repository.RepositoryService;

import domainapp.modules.hello.types.Name;

@DomainService(
        nature = NatureOfService.VIEW,
        logicalTypeName = "hello.HelloWorldObjects"
)
@javax.annotation.Priority(PriorityPrecedence.EARLY)
public class HelloWorldObjects {

    private final RepositoryService repositoryService;
    private final HelloWorldRepository helloWorldRepository;

    public HelloWorldObjects(
            final RepositoryService repositoryService,
            final HelloWorldRepository helloWorldRepository) {
        this.repositoryService = repositoryService;
        this.helloWorldRepository = helloWorldRepository;
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
        return helloWorldRepository.findByNameContaining(name);
    }

    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    public List<HelloWorldObject> listAll() {
        return helloWorldRepository.findAll();
    }


}
