// Under construction

//package ink.educat.core.dao.impl;
//
//import com.google.common.base.Preconditions;
//import ink.educat.core.dao.api.AbstractDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import java.util.Collection;
//import java.util.List;
//
//public abstract class AbstractDaoImpl<Entity> implements AbstractDao<Entity> {
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public abstract RowMapper<Entity> getRowMapper();
//    public abstract String getTableName();
//
//    @Autowired
//    public AbstractDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    @Override
//    public Entity findById(long id) {
//        Preconditions.checkArgument(
//                id > 0,
//                "ID must be > 0!");
//
//        final MapSqlParameterSource mapSqlParameterSource =
//                new MapSqlParameterSource().addValue("id", id);
//
//        final List<Entity> userList = namedParameterJdbcTemplate.query(
//                "SELECT * FROM EC_USERS U \n" +
//                        "JOIN EC_USER_ROLES R ON U.USER_ROLE_ID = R.USER_ROLE_ID \n" +
//                        "WHERE USER_ID = :id",
//                mapSqlParameterSource,
//                getRowMapper()
//        );
//
//        if (userList.isEmpty()) {
//            throw new NotFoundException("User with given id not found!");
//        }
//
//        Preconditions.checkState(
//                userList.size() == 1,
//                "Found multiple users with this id!"
//        );
//
//        return userList.iterator().next();
//        return null;
//    }
//
//    @Override
//    public Collection<Entity> findByIDs(Iterable<Long> ids) {
//        return null;
//    }
//
//    @Override
//    public void update(Iterable<Entity> entities) {
//
//    }
//
//    @Override
//    public Entity saveOrUpdate(Entity entity) {
//        return null;
//    }
//
//    @Override
//    public void delete(Entity entity) {
//
//    }
//}
