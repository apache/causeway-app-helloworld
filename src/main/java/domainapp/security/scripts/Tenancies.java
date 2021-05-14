package domainapp.security.scripts;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.applib.services.appfeat.ApplicationFeatureSort;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.api.permission.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractRoleAndPermissionsFixtureScript;
import org.apache.isis.extensions.secman.jdo.seed.scripts.AbstractTenancyFixtureScript;

public class Tenancies extends AbstractTenancyFixtureScript {

    public Tenancies() {
        super();
    }

    @Override
    protected void execute(ExecutionContext ec) {
        create("World", "/", null, ec);

        create("Great Britain", "/GBR","/", ec);
        create("London, GB", "/GBR/LON","/GBR", ec);
        create("Liverpool, GB", "/GBR/LIV","/GBR", ec);
        create("Edinburgh, GB", "/GBR/EDN","/GBR", ec);

        create("Italy", "/ITA","/", ec);
        create("Milan, IT", "/ITA/MIL", "/ITA", ec);
        create("Florence, IT", "/ITA/FLO", "/ITA", ec);
        create("Rome, IT", "/ITA/ROM", "/ITA", ec);

        create("France", "/FRA", "/", ec);
        create("Paris, FR", "/FRA/PAR", "/FRA", ec);
        create("Lyons, FR", "/FRA/LYN", "/FRA", ec);
        create("Marseilles, FR", "/FRA/MAR", "/FRA", ec);

        create("Belgium", "/BEL", "/", ec);
        create("Brussels, BE", "/BEL/BRU", "/", ec);
    }
}