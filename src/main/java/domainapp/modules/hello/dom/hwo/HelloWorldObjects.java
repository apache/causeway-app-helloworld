package domainapp.modules.hello.dom.hwo;

import java.util.List;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.NatureOfService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.RestrictTo;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import domainapp.modules.hello.types.Name;

@Named("hello.HelloWorldObjects")
@DomainService(nature = NatureOfService.VIEW)
@Priority(PriorityPrecedence.EARLY)
public class HelloWorldObjects {

    private final RepositoryService repositoryService;
    private final HelloWorldRepository helloWorldRepository;

    @Inject
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
