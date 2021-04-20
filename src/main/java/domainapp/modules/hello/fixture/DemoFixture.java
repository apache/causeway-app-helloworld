package domainapp.modules.hello.fixture;

import javax.inject.Inject;

import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;

import domainapp.modules.hello.dom.hwo.HelloWorldObjects;

public class DemoFixture extends FixtureScript {

    public DemoFixture() {
        super();
    }

    @Override
    protected void execute(FixtureScript.ExecutionContext ec) {
        ec.addResult(this, helloWorldObjects.create("Hello, World!", "/"));
        ec.addResult(this, helloWorldObjects.create("Hello, Great Britain!", "/GBR"));
        ec.addResult(this, helloWorldObjects.create("Hello, Manchester!", "/GBR/MAN"));
        ec.addResult(this, helloWorldObjects.create("Hello, Liverpool!", "/GBR/LIV"));
        ec.addResult(this, helloWorldObjects.create("Hello, Edinburgh!", "/GBR/EDN"));

        ec.addResult(this, helloWorldObjects.create("Hello, Italy!", "/ITA"));
        ec.addResult(this, helloWorldObjects.create("Hello, Milan!, IT", "/ITA/MIL"));
        ec.addResult(this, helloWorldObjects.create("Hello, Florence!, IT", "/ITA/FLO"));
        ec.addResult(this, helloWorldObjects.create("Hello, Rome!, IT", "/ITA/ROM"));

        ec.addResult(this, helloWorldObjects.create("Hello, France!", "/FRA"));
        ec.addResult(this, helloWorldObjects.create("Hello, Paris!, FR", "/FRA/PAR"));
        ec.addResult(this, helloWorldObjects.create("Hello, Lyons!, FR", "/FRA/LYN"));
        ec.addResult(this, helloWorldObjects.create("Hello, Marseilles!, FR", "/FRA/MAR"));

        ec.addResult(this, helloWorldObjects.create("Hello, Belgium!", "/BEL"));
        ec.addResult(this, helloWorldObjects.create("Hello, Brussels!, BE", "/BEL/BRU"));
    }

    @Inject
    HelloWorldObjects helloWorldObjects;
}
