package ink.educat.security.service.impl;

import com.google.common.base.Preconditions;
import ink.educat.security.dao.api.UserPermissionDao;
import ink.educat.security.service.api.NotEnoughPermissionsException;
import ink.educat.security.service.api.UserPermissionService;
import ink.educat.user.dao.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервис для проверки прав доступа.
 *
 * @author Ilya Mikheev
 * @see UserPermissionService
 * @see UserPermissionDao
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    /**
     * Читай discussion в {@link SecurityServiceImpl}
     */
    private ConcurrentHashMap<String, String> userPermissionsMap;
    private final UserPermissionDao userPermissionDao;

    @Autowired
    public UserPermissionServiceImpl(final UserPermissionDao userPermissionDao) {
        userPermissionsMap = userPermissionDao.loadRolesAndPermissions();
        this.userPermissionDao = userPermissionDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guard(@NonNull final User user, @NonNull final String permission) {
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                user != null,
                "User can't be null!"
        );
        //noinspection ConstantConditions
        Preconditions.checkArgument(
                permission != null,
                "Permission can't be null"
        );
        final String availableRoleList = userPermissionsMap.get(permission);
        Preconditions.checkState(
                availableRoleList != null,
                "No permission found!"
        );

        // Так как для конкретного пермишена доступные роли (в первои итерации)
        // будут содержаться в строке ввида "ADMIN, TUTOR, ...", то проверяем на contains()
        if (!availableRoleList.contains(user.getUserRole().name())) {
            throw new NotEnoughPermissionsException("Not enough access permissions!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        userPermissionsMap = userPermissionDao.loadRolesAndPermissions();
    }
}
