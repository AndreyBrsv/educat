package ink.educat.user.Impl;

import com.google.common.base.Preconditions;
import ink.educat.user.api.Entities.User;
import ink.educat.user.api.NotEnoughPermissionsException;
import ink.educat.user.api.UserPermissionDao;
import ink.educat.user.api.UserPermissionService;
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

    private final ConcurrentHashMap<String, String> userPermissionsMap;
    private UserPermissionDao userPermissionDao;

    @Autowired
    public UserPermissionServiceImpl(final UserPermissionDao userPermissionDao) {
        // На всякий случай делаем @Autowired целого dao, чтобы при изменениях
        // в БД не пришлось перезапускать сервер, а сделать refresh() пермишенов.
        this.userPermissionDao = userPermissionDao;
        // микрохак из семантики final полей :)
        userPermissionsMap = new ConcurrentHashMap<>(userPermissionDao.loadRolesAndPermissions());
    }

    @Override
    public void guardian(@NonNull final User user, @NonNull final String permission) {
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
}
