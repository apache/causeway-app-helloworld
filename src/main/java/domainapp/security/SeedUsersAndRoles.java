package domainapp.security;

import javax.inject.Inject;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import org.apache.isis.applib.annotation.OrderPrecedence;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.core.metamodel.events.MetamodelEvent;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScripts;

import domainapp.security.scripts.RoleAndPerms__NoDelete;
import domainapp.security.scripts.RoleAndPerms__UserRo;
import domainapp.security.scripts.RoleAndPerms__UserRw;
import domainapp.security.scripts.UserToRole__bob_UserRw;
import domainapp.security.scripts.UserToRole__dick_UserRo;
import domainapp.security.scripts.UserToRole__joe_UserRw_but_NoDelete;

@Service
@Order(OrderPrecedence.MIDPOINT + 10)
public class SeedUsersAndRoles {

    private final FixtureScripts fixtureScripts;
    private final TransactionService transactionService;

    @Inject
    public SeedUsersAndRoles(
            final FixtureScripts fixtureScripts,
            final TransactionService transactionService) {
        this.fixtureScripts = fixtureScripts;
        this.transactionService = transactionService;
    }

    @EventListener(MetamodelEvent.class)
    public void onMetamodelEvent(final MetamodelEvent event) {
        if (event.isPostMetamodel()) {
            runScripts();
        }
        transactionService.flushTransaction();
    }

    private void runScripts() {
        fixtureScripts.run(new FixtureScript() {
            @Override
            protected void execute(ExecutionContext ec) {
                ec.executeChildren(this,
                        new RoleAndPerms__UserRw()
                        , new RoleAndPerms__UserRo()
                        , new RoleAndPerms__NoDelete()
                        , new UserToRole__bob_UserRw()
                        , new UserToRole__dick_UserRo()
                        , new UserToRole__joe_UserRw_but_NoDelete()
                );
            }
        });
    }

}
