package domainapp.modules.hello.dom.hwo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloWorldRepository extends JpaRepository<HelloWorldObject, Long> {

    List<HelloWorldObject> findByNameContaining(final String name);

    HelloWorldObject findByName(final String name);

}
