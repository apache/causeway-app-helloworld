package domainapp.modules.hello.dom.hwo;

import java.util.Comparator;

import javax.inject.Inject;

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
import org.apache.isis.persistence.jpa.applib.integration.JpaEntityInjectionPointResolver;

import domainapp.modules.hello.types.Name;
import domainapp.modules.hello.types.Notes;

@javax.persistence.Entity
@javax.persistence.Table(
        schema="hello",
        uniqueConstraints = {
                @javax.persistence.UniqueConstraint(name = "HelloWorldObject_name_UNQ", columnNames = {"name"})
        }
)
@javax.persistence.EntityListeners(JpaEntityInjectionPointResolver.class) // injection support
@DomainObject(objectType = "hello.HelloWorldObject", entityChangePublishing = Publishing.ENABLED)
@DomainObjectLayout()  // causes UI events to be triggered
public class HelloWorldObject implements Comparable<HelloWorldObject> {

    protected HelloWorldObject(){}

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @javax.persistence.Column(nullable = false)
    private Long id;

    @javax.persistence.Version
    @javax.persistence.Column(name = "OPTLOCK")
    private int version;


    public HelloWorldObject(final String name) {
        this.name = name;
    }

    public String title() {
        return "Object: " + getName();
    }


    @javax.persistence.Column(length = Name.MAX_LEN, nullable = false)
    private String name;

    @Name
    @PropertyLayout(fieldSetId = "identity", sequence = "1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @javax.persistence.Column(length = Notes.MAX_LEN, nullable = true)
    private String notes;

    @Notes
    @PropertyLayout(fieldSetId = "details", sequence = "1", multiLine = 10, hidden = Where.ALL_TABLES)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Action(
            semantics = SemanticsOf.IDEMPOTENT,
            executionPublishing = Publishing.ENABLED
    )
    @ActionLayout(
            associateWith = "name",
            describedAs = "Updates the object's name"
    )
    public HelloWorldObject updateName(
            @Name final String name) {
        setName(name);
        return this;
    }
    public String default0UpdateName() {
        return getName();
    }


    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE
    )
    @ActionLayout(
            associateWith = "name",
            describedAs = "Deletes this object from the persistent datastore",
            position = ActionLayout.Position.PANEL
    )
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


    @Inject @javax.persistence.Transient RepositoryService repositoryService;
    @Inject @javax.persistence.Transient TitleService titleService;
    @Inject @javax.persistence.Transient MessageService messageService;

}
