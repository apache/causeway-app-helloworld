package domainapp.modules.hello.dom.impl;

import domainapp.modules.hello.dom.impl.HelloWorldObject;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.title.TitleService;
import org.apache.isis.unittestsupport.jmocking.JUnitRuleMockery2;

import static org.hamcrest.CoreMatchers.containsString;

public class HelloWorldObject_delete_Test {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(JUnitRuleMockery2.Mode.INTERFACES_AND_CLASSES);

    @Mock
    TitleService mockTitleService;

    @Mock
    MessageService mockMessageService;

    @Mock
    RepositoryService mockRepositoryService;

    @Test
    public void happy_case() throws Exception {

        // given
        final HelloWorldObject object = new HelloWorldObject("Foo");
        object.titleService = mockTitleService;
        object.messageService = mockMessageService;
        object.repositoryService = mockRepositoryService;

        // expecting
        context.checking(new Expectations() {{
            allowing(mockTitleService).titleOf(object); will(returnValue("Foo"));

            oneOf(mockMessageService).informUser(with(containsString("'Foo' deleted")));
            oneOf(mockRepositoryService).removeAndFlush(object);
        }});

        // when
        object.delete();
    }


}