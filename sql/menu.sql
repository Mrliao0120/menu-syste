

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_user
-- ----------------------------
DROP TABLE IF EXISTS `account_user`;
CREATE TABLE `account_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `is_delete` int(255) DEFAULT NULL COMMENT '是否删除',
  `is_lock` int(2) DEFAULT NULL COMMENT '是否锁定',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `system_level` int(11) DEFAULT NULL COMMENT '系统权限等级  1.root 2.管理员 3菜品管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu_ae
-- ----------------------------
DROP TABLE IF EXISTS `menu_ae`;
CREATE TABLE `menu_ae` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜品名称',
  `menu_price` decimal(10,2) DEFAULT NULL COMMENT '菜品单价',
  `menu_image` varchar(255) DEFAULT NULL COMMENT '菜品图片',
  `create_userId` bigint(20) DEFAULT NULL COMMENT '创建人用户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `menu_text` varchar(255) DEFAULT NULL COMMENT '菜品详细',
  `menu_floor` varchar(255) DEFAULT NULL COMMENT '菜品楼层',
  `menu_window` varchar(255) DEFAULT NULL COMMENT '菜品窗口',
  `canteen_name` varchar(255) DEFAULT NULL COMMENT '食堂',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for menu_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `menu_evaluate`;
CREATE TABLE `menu_evaluate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_evaluate` varchar(255) DEFAULT NULL COMMENT '菜品评价',
  `menu_evaluate_score` int(10) DEFAULT NULL COMMENT '菜品分数',
  `user_id` bigint(20) DEFAULT NULL COMMENT '创建 用户',
  `menu_evaluate_id` bigint(20) DEFAULT NULL COMMENT '菜品评论父id',
  `is_root_reply` int(11) DEFAULT NULL COMMENT '是否是管路员回复',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `menu_type` int(11) DEFAULT NULL COMMENT '回复类型 1普通 2商家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除',
  `is_lock` int(11) DEFAULT NULL COMMENT '是否锁定',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
