/*
-- Query: SELECT * FROM shiroDemo.shiro_function
LIMIT 0, 1000

-- Date: 2018-02-08 16:40
*/
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (1,NULL,'演示系统','1',0);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (29,NULL,'系统管理功能','4',1);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (30,NULL,'用户管理','4-1',29);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (31,'2016-08-17 10:16:48','添加','4-1-add',30);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (32,NULL,'功能管理','4-2',29);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (34,NULL,'角色管理','4-3',29);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (43,'2016-08-17 10:16:16','查询','4-1-search',30);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (44,'2016-08-17 10:17:07','修改','4-1-edit',30);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (45,'2016-08-17 10:17:13','审核','4-1-audit',30);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (46,'2016-08-17 10:17:45','添加角色','4-1-role',30);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (49,'2016-08-17 14:20:35','操作（增加，修改，删除，赋权限）','4-3-operate',34);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (67,'2018-02-05 12:41:19','添加','4-2-add',32);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (68,'2018-02-05 12:41:34','修改','4-2-edit',32);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (69,'2018-02-05 12:41:44','删除','4-2-del',32);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (70,'2018-02-05 12:45:35','添加','4-3-add',34);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (72,'2018-02-05 12:46:15','删除','4-3-del',34);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (73,'2018-02-05 12:46:23','修改','4-3-edit',34);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (74,'2018-02-05 12:47:10','角色添加功能','4-3-role-func',34);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (75,'2018-02-05 12:54:40','测试菜单','1-test',1);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (76,'2018-02-05 12:55:14','测试-1','2-1',75);
INSERT INTO `shiro_function` (`id`,`create_time`,`function_name`,`permission_name`,`pid`) VALUES (77,'2018-02-05 12:57:17','测试2','2-2',75);
