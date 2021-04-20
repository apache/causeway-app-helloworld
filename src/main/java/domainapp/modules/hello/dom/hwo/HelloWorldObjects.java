package domainapp.modules.hello.dom.hwo;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.extensions.secman.api.tenancy.ApplicationTenancy;
import org.apache.isis.extensions.secman.api.tenancy.ApplicationTenancyRepository;
import org.apache.isis.extensions.secman.model.dom.user.MeService;

import domainapp.modules.hello.types.AtPath;
import domainapp.modules.hello.types.Name;

@DomainService(
        nature = NatureOfService.VIEW,
        objectType = "hello.HelloWorldObjects"
        )
public class HelloWorldObjects {

    private final RepositoryService repositoryService;

    public HelloWorldObjects(
            final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public HelloWorldObject create(
            @Name final String name,
            @AtPath final String atPath) {
        return repositoryService.persist(new HelloWorldObject(name, atPath));
    }
    public String default0Create() {
        return "Hello World!";
    }
    public String default1Create() {
        return meService.me().getAtPath();
    }
    public List<String> choices1Create() {
        return applicationTenancyRepository.allTenancies()
                .stream().map(ApplicationTenancy::getPath)
                .collect(Collectors.toList());
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

    @Inject
    MeService meService;
    @Inject
    ApplicationTenancyRepository<? extends ApplicationTenancy> applicationTenancyRepository;


}
