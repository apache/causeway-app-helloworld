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
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.extensions.secman.applib.tenancy.dom.ApplicationTenancy;
import org.apache.isis.extensions.secman.applib.tenancy.dom.ApplicationTenancyRepository;
import org.apache.isis.extensions.secman.applib.user.menu.MeService;

import domainapp.modules.hello.types.AtPath;
import domainapp.modules.hello.types.Name;

@DomainService(
        nature = NatureOfService.VIEW,
        objectType = "hello.HelloWorldObjects"
        )
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
            @Name final String name,
            @AtPath final String atPath) {
        final ApplicationTenancy applicationTenancy = applicationTenancyRepository.findByPath(atPath);
        if(applicationTenancy == null) {
            applicationTenancyRepository.newTenancy(atPath, atPath, null);
        }
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
        return helloWorldRepository.findByNameContaining(name);
    }

    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    public List<HelloWorldObject> listAll() {
        return helloWorldRepository.findAll();
    }

    @Inject transient MeService meService;
    @Inject transient ApplicationTenancyRepository applicationTenancyRepository;


}
