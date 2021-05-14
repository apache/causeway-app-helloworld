package domainapp.security.multitenancy;

import org.springframework.stereotype.Service;

import org.apache.isis.extensions.secman.api.tenancy.spi.ApplicationTenancyEvaluator;
import org.apache.isis.extensions.secman.api.tenancy.dom.HasAtPath;
import org.apache.isis.extensions.secman.api.user.dom.ApplicationUser;

@Service
public class ApplicationTenancyEvaluatorUsingAtPath implements ApplicationTenancyEvaluator {

    @Override
    public boolean handles(Class<?> cls) {
        return HasAtPath.class.isAssignableFrom(cls);
    }

    /**
     * Visible within the user's hierarchy (above or below).
     *
     * <p>for example</p>
     * <ul>
     *     <li>obj = '/', user = '/ITA'  : visible</li>
     *     <li>obj = '/ITA', user = '/ITA'  : visible</li>
     *     <li>obj = '/ITA/MIL', user = '/ITA'  : visible</li>
     *     <li>obj = '/FRA', user = '/ITA'  : not visible</li>
     * </ul>
     */
    @Override
    public String hides(Object domainObject, ApplicationUser applicationUser) {
        final String objAtPath = ((HasAtPath) domainObject).getAtPath();
        if(objAtPath == null) {
            return null; // show
        }
        final String userAtPath = applicationUser.getAtPath();
        if(userAtPath == null) {
            return "user does not have atPath"; // hide
        }
        return objAtPath.startsWith(userAtPath) || userAtPath.startsWith(objAtPath)
                ? null
                : "object not visible within user's tenancy";
    }

    /**
     * Enabled only 'under' the user's hierarchy.
     *
     * <p>for example</p>
     * <ul>
     *         <li>obj = '/', user = '/ITA'  : not editable</li>
     *         <li>obj = '/ITA', user = '/ITA'  : editable</li>
     *         <li>obj = '/ITA/MIL', user = '/ITA'  : editable</li>
     *         <li>obj = '/FRA', user = '/ITA'  : not editable</li>
     * </ul>
     * @return
     */
    @Override
    public String disables(Object domainObject, ApplicationUser applicationUser) {
        final String objAtPath = ((HasAtPath) domainObject).getAtPath();
        if(objAtPath == null) {
            return null; // enable
        }
        final String userAtPath = applicationUser.getAtPath();
        if(userAtPath == null) {
            return "user does not have atPath"; // disable
        }
        return objAtPath.startsWith(userAtPath)
                ? null
                : "object not enabled within user's tenancy";
    }
}
