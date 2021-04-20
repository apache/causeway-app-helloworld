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
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Publishing;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.extensions.secman.api.tenancy.HasAtPath;

import domainapp.modules.hello.types.AtPath;
import domainapp.modules.hello.types.Name;
import domainapp.modules.hello.types.Notes;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "hello" )
@javax.jdo.annotations.DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Queries(
        @javax.jdo.annotations.Query(
                name = "findByName",
                value = "SELECT " +
                        "FROM domainapp.modules.hello.dom.hwo.HelloWorldObject " +
                        "WHERE name.indexOf(:name) >= 0"
        )
)
@javax.jdo.annotations.Version(strategy= VersionStrategy.DATE_TIME, column ="version")
@javax.jdo.annotations.Unique(name="HelloWorldObject_name_UNQ", members = {"name"})
@DomainObject(entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
public class HelloWorldObject implements Comparable<HelloWorldObject>, HasAtPath {

    public HelloWorldObject(){}

    public HelloWorldObject(final String name, final String atPath) {
        this.name = name;
        this.atPath = atPath;
    }

    public String title() {
        return "Object: " + getName();
    }

    private String name;
    @Name
    @PropertyLayout(fieldSetId = "identity", sequence = "1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String notes;
    @Notes
    @PropertyLayout(fieldSetId = "details", sequence = "1", multiLine = 10, hidden = Where.ALL_TABLES)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @AtPath
    @MemberOrder(name = "metadata", sequence = "3")
    private String atPath;
    public String getAtPath() {
        return atPath;
    }
    public void setAtPath(String atPath) {
        this.atPath = atPath;
    }

    @Action(
            semantics = SemanticsOf.IDEMPOTENT,
            executionPublishing = Publishing.ENABLED,
            associateWith = "name"
    )
    @ActionLayout(describedAs = "Updates the object's name")
    public HelloWorldObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    public String default0UpdateName() {
        return getName();
    }


    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE, associateWith = "name")
    @ActionLayout(position = ActionLayout.Position.PANEL, describedAs = "Deletes this object from the persistent datastore")
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
