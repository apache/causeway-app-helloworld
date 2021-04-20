package domainapp.security.fixturescripts;

import org.springframework.stereotype.Service;

import org.apache.isis.testing.fixtures.applib.fixturespec.FixtureScriptsSpecification;
import org.apache.isis.testing.fixtures.applib.fixturespec.FixtureScriptsSpecificationProvider;

@Service
public class FixtureScriptSpecProvider implements FixtureScriptsSpecificationProvider {
    @Override
    public FixtureScriptsSpecification getSpecification() {
        return FixtureScriptsSpecification.builder("domainapp").build();
    }
}
