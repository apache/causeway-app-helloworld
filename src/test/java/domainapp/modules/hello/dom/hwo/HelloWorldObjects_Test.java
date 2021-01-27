package domainapp.modules.hello.dom.hwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import org.apache.isis.applib.services.repository.RepositoryService;

@ExtendWith(MockitoExtension.class)
class HelloWorldObjects_Test {

    @Mock RepositoryService mockRepositoryService;
    @Mock HelloWorldRepository mockHelloWorldRepository;

    HelloWorldObjects objects;

    @BeforeEach
    public void setUp() {
        objects = new HelloWorldObjects(mockRepositoryService, mockHelloWorldRepository);
    }

    @Nested
    class create {

        @Test
        void happyCase() {

            // given
            final String someName = "Foobar";

            // expect
            when(mockRepositoryService.persist(
                    argThat((ArgumentMatcher<HelloWorldObject>) helloWorldObject -> Objects.equals(helloWorldObject.getName(), someName)))
            ).then((Answer<HelloWorldObject>) invocation -> invocation.getArgument(0));

            // when
            final HelloWorldObject obj = objects.create(someName);

            // then
            assertThat(obj).isNotNull();
            assertThat(obj.getName()).isEqualTo(someName);
        }
    }

    @Nested
    class ListAll {

        @Test
        void happyCase() {

            // given
            final List<HelloWorldObject> all = new ArrayList<>();

            // expecting
            when(mockHelloWorldRepository.findAll())
                .thenReturn(all);

            // when
            final List<HelloWorldObject> list = objects.listAll();

            // then
            assertThat(list).isEqualTo(all);
        }
    }
}
