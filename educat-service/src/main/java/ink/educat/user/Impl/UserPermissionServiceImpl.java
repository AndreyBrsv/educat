@Service
public class UserPermissionServiceImpl implements UserPermissionService {
       
       private final ConcurrentHashMap userPermissionsMap;
       private UserPermissionDao userPermissionDao;
       
       @Autowired
       public UserPermissionService(final UserPermissionDao userPermissionDao) {
          this.userPermissionDao = userPermissionDao;
         // микрохак из семантики final полей :)
         
       }
       
}
