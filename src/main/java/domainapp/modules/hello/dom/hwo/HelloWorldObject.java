package domainapp.modules.hello.dom.hwo;

import java.util.Comparator;

import javax.inject.Inject;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;

import domainapp.modules.hello.types.Name;
import domainapp.modules.hello.types.Notes;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "hello" )
@javax.jdo.annotations.DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Queries(
        @javax.jdo.annotations.Query(
                name = "findByName",
                value = "SELECT " +
                        "FROM domainapp.modules.hello.dom.hwo.HelloWorldObject " +
                        "WHERE name.indexOf(:name) > 0"
        )
)
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column ="version")
@javax.jdo.annotations.Unique(name="HelloWorldObject_name_UNQ", members = {"name"})
@DomainObject(entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
public class HelloWorldObject implements Comparable<HelloWorldObject> {

    public HelloWorldObject(){}

    public HelloWorldObject(final String name) {
        this.name = name;
    }

    public String title() {
        return "Object: " + getName();
    }

    @Name
    @MemberOrder(name = "identity", sequence = "1")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Notes
    @MemberOrder(name = "details", sequence = "1")
    private String notes;
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Action(
            semantics = SemanticsOf.IDEMPOTENT,
            executionPublishing = Publishing.ENABLED,
            associateWith = "name"
    )
    public HelloWorldObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    public String default0UpdateName() {
        return getName();
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE, associateWith = "name")
    @ActionLayout(position = ActionLayout.Position.PANEL)
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(final HelloWorldObject other) {
        return Comparator.comparing(HelloWorldObject::getName).compare(this, other);
    }


    @Inject RepositoryService repositoryService;
    @Inject TitleService titleService;
    @Inject MessageService messageService;

}
