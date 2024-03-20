package domainapp.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jakarta.inject.Inject;

import org.springframework.stereotype.Component;

import org.apache.causeway.applib.Identifier;
import org.apache.causeway.applib.services.iactnlayer.InteractionContext;
import org.apache.causeway.applib.services.user.UserMemento;
import org.apache.causeway.core.security.authentication.AuthenticationRequest;
import org.apache.causeway.core.security.authentication.AuthenticationRequestPassword;
import org.apache.causeway.core.security.authentication.Authenticator;
import org.apache.causeway.core.security.authorization.Authorizor;

@Component
public class SimpleAuth implements Authenticator, Authorizor {

    static class Realm {
        static record Role(String name, Predicate<Identifier> grantsRead, Predicate<Identifier> grantsChange) {
        }
        static record User(String name, String pass, List<Role> roles) {
        }
        final Map<String, Role> roles = new HashMap<>();
        final Map<String, User> users = new HashMap<>();
        public Realm addRole(final String name, final Predicate<Identifier> grants) {
            roles.put(name, new Role(name, grants, grants));
            return this;
        }
        public Realm addUser(final String name, final String pass, final List<String> roleNames) {
            users.put(name, new User(name, pass, roleNames.stream().map(roles::get).toList()));
            return this;
        }
    }

    @Inject Realm realm;

    /**
     * Default implementation returns a validated {@link InteractionContext}; can be overridden
     * if required.
     */
    @Override
    public final InteractionContext authenticate(
            final AuthenticationRequest request,
            final String validationCode) {

        if(request instanceof AuthenticationRequestPassword pwdRequest) {
            if (!isValid(pwdRequest)) {
                return null;
            }
        }

        var user = UserMemento.ofNameAndRoleNames(request.getName(), Stream.concat(
                    request.streamRoles(),
                    realm.users.get(request.getName())
                        .roles()
                        .stream()
                        .map(Realm.Role::name))
                )
                .withAuthenticationCode(validationCode);
        return InteractionContext.ofUserWithSystemDefaults(user);
    }

    @Override
    public final void logout() {
        // no-op
    }

    @Override
    public boolean isVisible(final InteractionContext ctx, final Identifier identifier) {
        return roles(ctx.getUser()).stream()
            .anyMatch(role->role.grantsRead().test(identifier));
    }

    @Override
    public boolean isUsable(final InteractionContext ctx, final Identifier identifier) {
        return roles(ctx.getUser()).stream()
                .anyMatch(role->role.grantsChange().test(identifier));
    }

    @Override
    public final boolean canAuthenticate(final Class<? extends AuthenticationRequest> authenticationRequestClass) {
        return AuthenticationRequestPassword.class.isAssignableFrom(authenticationRequestClass);
    }

    protected boolean isValid(final AuthenticationRequestPassword request) {
        return Optional.ofNullable(realm.users.get(request.getName()))
            .map(Realm.User::pass)
            .map(pass->pass.equals(request.getPassword()))
            .orElse(false);
    }

    protected List<Realm.Role> roles(final UserMemento userMemento){
        var roles = Optional.ofNullable(realm.users.get(userMemento.getName()))
                .map(Realm.User::roles)
                .orElseGet(List::of);
        return roles;
    }

}
