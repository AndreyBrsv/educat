-- Дроп всех таблиц для пересоздания

drop table ec_securities;

drop table ec_users;

drop table ec_role_permission_references;

drop table ec_user_roles;

drop table ec_user_permissions;

drop trigger TRG_EC_USER_TO_USER_SUBSCRIPTIONS_CHECK_USER_IDS_BI on ec_user_to_user_subscriptions;
drop procedure CHECK_SUBSCRIBERS_ID();
drop table ec_user_to_user_subscriptions;


drop table ec_educational_courses;

drop table ec_educational_tasks;

drop table ec_user_complete_tasks;

drop table ec_user_course_subscriptions;

drop table ec_educational_achievements;

drop table ec_articles;

drop table ec_article_comments;

drop table ec_user_bookmarked_articles;