/*
 Navicat Premium Data Transfer

 Source Server         : MyDatabase
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : carworld

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 09/12/2020 15:46:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `car_num` varchar(8) DEFAULT NULL,
  `car_name` varchar(20) DEFAULT NULL,
  `car_age` int(11) DEFAULT NULL,
  `car_price` double DEFAULT NULL,
  `car_seat` int(11) DEFAULT NULL,
  `car_volume` double DEFAULT NULL,
  `car_introduction` varchar(20) DEFAULT NULL,
  `car_status` varchar(11) DEFAULT NULL,
  `car_user` int(11) DEFAULT '0',
  PRIMARY KEY (`car_id`) USING BTREE,
  UNIQUE KEY `car_num` (`car_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100042 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
BEGIN;
INSERT INTO `car` VALUES (100001, '苏E6D98S', '雪佛兰科沃兹', 1, 60, 5, 1, '免费取消,半年内新车,立即确认', '已出租', 0);
INSERT INTO `car` VALUES (100002, '辽HW99IC', '福特翼搏', 2, 58, 5, 1.5, '限时免费取消,倒车雷达,立即确认', '已出租', 0);
INSERT INTO `car` VALUES (100003, '云PJWZ7S', '雪佛兰迈锐宝XL', 3, 90, 5, 1.3, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100004, '湘JY3D61', '众泰T600', 2, 70, 5, 1.5, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100005, '苏AS2M5Z', '大众速腾', 2, 78, 5, 1.6, '限时免费取消,倒车雷达,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100006, '鄂HAH21I', '斯柯达明锐', 1, 138, 5, 1.6, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100007, '粤Q37FFJ', '大众朗逸', 2, 138, 5, 1.6, '免费取消,半年内新车,立即确认', '已出租', 129);
INSERT INTO `car` VALUES (100008, '青GV71PG', '别克凯越', 3, 168, 5, 1.5, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100009, '桂B6GFLJ', '丰田卡罗拉', 2, 168, 5, 1.2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100010, '鲁QE7SRZ', '荣威EI6', 2, 178, 5, 1, '免费取消,半年内新车,立即确认', '已出租', 100);
INSERT INTO `car` VALUES (100011, '鄂QWE1TJ', '丰田卡罗拉', 2, 189, 5, 1.8, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100012, '川TE3LF06', '上汽大通G10', 2, 229, 7, 2, '免费取消,半年内新车,立即确认', '已出租', 137);
INSERT INTO `car` VALUES (100013, '陕F8220P', '奥迪A3', 1, 248, 5, 1.4, '免费取消,半年内新车,立即确认', '已出租', 130);
INSERT INTO `car` VALUES (100014, '贵FP0ZVT', '奥迪Q2L', 2, 288, 5, 1.4, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100015, '晋BSKI8A', '华颂7', 2, 288, 7, 2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100016, '浙BYIT6W', '别克GL8', 3, 358, 7, 2.4, '限时免费取消,三年内新车,倒车雷达', '已出租', 156);
INSERT INTO `car` VALUES (100017, '黑HJUAG9', '比亚迪秦Pro', 3, 368, 5, 1.5, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100018, '豫A9S3L7', '别克GL8豪华版', 2, 450, 7, 2.5, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100019, '粤BF9EVT', '大众帕萨特', 1, 187, 5, 1.8, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100020, '晋LTSX22', '丰田卡罗拉双擎E+', 2, 80, 2, 1.8, '免费取消,半年内新车,立即确认', '已出租', 116);
INSERT INTO `car` VALUES (100021, '鄂BRP45D', '本田凌派', 3, 172, 5, 1, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100022, '陕J7M0Y7', '现代领动', 2, 172, 5, 1.5, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100023, '贵FBU3X5', '奥迪A4L', 3, 310, 5, 2, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100024, '蒙F84G2D', '捷豹XFL', 2, 550, 5, 2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100025, '闽H5S41A', 'SWM斯威X7', 2, 158, 7, 1.5, '限时免费取消,倒车雷达,立即确认', '已出租', 147);
INSERT INTO `car` VALUES (100026, '青A9QM23', '宝马3系', 2, 550, 5, 2, '免费取消,半年内新车,立即确认', '已出租', 117);
INSERT INTO `car` VALUES (100027, '川C34VXP', '大众途观', 2, 168, 5, 1.4, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100028, '鄂JYBHE5', '雪佛兰创酷', 2, 178, 5, 1, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100029, '粤ANP3O8', '特斯拉Model S', 2, 1500, 5, 0, '免费取消,半年内新车,立即确认', '已出租', 140);
INSERT INTO `car` VALUES (100031, '沪B4YHI8', '汉龙汽车旷世', 1, 200, 5, 2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100032, '冀D8Z6D0', '奥迪A6L', 2, 800, 5, 2, '限时免费取消,倒车雷达,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100033, '津AP9830', '别克昂科拉', 2, 208, 5, 1.4, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100034, '京A3SRRU', '奔驰E级', 3, 695, 5, 2, '限时免费取消,三年内新车,倒车雷达', '可租赁', 0);
INSERT INTO `car` VALUES (100035, '冀TY57TB', '兰博基尼', 2, 12000, 2, 5.2, '限时免费取消,倒车雷达,立即确认', '已出租', 143);
INSERT INTO `car` VALUES (100036, '辽HVZH5C', '雷克萨斯ES', 2, 650, 5, 2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100037, '京E9A7Y1', '宝马5系新能源', 1, 650, 5, 2, '免费取消,半年内新车,立即确认', '可租赁', 0);
INSERT INTO `car` VALUES (100038, '粤A0NC4A', '劳斯莱斯古思特', 2, 5999, 5, 6.6, '限时免费取消,倒车雷达,立即确认', '可租赁', 0);
COMMIT;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_selected` tinyint(1) DEFAULT NULL COMMENT '记住是否勾选',
  `username` varchar(20) DEFAULT NULL COMMENT '记住用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '记住密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------
BEGIN;
INSERT INTO `data` VALUES (1, 1, 'boss', '111111');
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `car_num` varchar(11) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  `date` varchar(11) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100203 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES (100158, '冀D8Z6D0', '116', 'customer', '13952078158', '2020年12月07日', '租借');
INSERT INTO `log` VALUES (100159, '冀D8Z6D0', '116', 'customer', '13952078158', '2020年12月07日', '归还');
INSERT INTO `log` VALUES (100160, '粤BF9EVT', '116', 'customer', '13952078158', '2020年12月07日', '租借');
INSERT INTO `log` VALUES (100161, '粤BF9EVT', '116', 'customer', '13952078158', '2020年12月07日', '归还');
INSERT INTO `log` VALUES (100162, '闽H5S41A', '108', '枫少', '13952078158', '2020年12月07日', '租借');
INSERT INTO `log` VALUES (100163, '闽H5S41A', '108', '枫少', '13952078158', '2020年12月07日', '归还');
INSERT INTO `log` VALUES (100164, '京E9A7Y1', '116', 'customer', '13952078158', '2020年12月07日', '租借');
INSERT INTO `log` VALUES (100165, '京E9A7Y1', '116', 'customer', '13952078158', '2020年12月07日', '归还');
INSERT INTO `log` VALUES (100166, '津AP9830', '116', 'customer', '13952078158', '2020年12月07日', '租借');
INSERT INTO `log` VALUES (100167, '粤Q37FFJ', '129', '清酒凉茶', '13414311349', '2020年12月08日', '租借');
INSERT INTO `log` VALUES (100168, '陕J7M0Y7', '140', '飞舞流沙', '15651907555', '2020年12月08日', '租借');
INSERT INTO `log` VALUES (100169, '陕J7M0Y7', '140', '飞舞流沙', '15651907555', '2020年12月08日', '归还');
INSERT INTO `log` VALUES (100170, '粤ANP3O8', '140', '飞舞流沙', '15651907555', '2020年12月08日', '租借');
INSERT INTO `log` VALUES (100171, '豫A9S3L7', '118', '花开终落', '13058585848', '2020年12月08日', '租借');
INSERT INTO `log` VALUES (100172, '豫A9S3L7', '118', '花开终落', '13058585848', '2020年12月08日', '归还');
INSERT INTO `log` VALUES (100173, '浙BYIT6W', '117', '渡往彼岸', '18111828588', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100174, '浙BYIT6W', '117', '渡往彼岸', '18111828588', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100175, '青A9QM23', '117', '渡往彼岸', '18111828588', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100176, '鄂BRP45D', '120', '王者梦晓', '15618888398', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100177, '闽H5S41A', '147', '流星的眼淚', '13886029898', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100178, '川C34VXP', '146', '香草味、布丁', '13707176868', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100179, '川C34VXP', '146', '香草味、布丁', '13707176868', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100180, '辽HVZH5C', '146', '香草味、布丁', '13707176868', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100181, '浙BYIT6W', '156', '孤战', '15846287632', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100182, '苏AS2M5Z', '125', '一折悲画扇', '17378313555', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100183, '苏AS2M5Z', '125', '一折悲画扇', '17378313555', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100184, '青GV71PG', '125', '一折悲画扇', '17378313555', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100185, '青GV71PG', '125', '一折悲画扇', '17378313555', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100186, '川TE3LF06', '137', '盖世萝莉', '13127625666', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100187, '粤BF9EVT', '143', '被鱼叼着的猫', '13901675085', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100188, '粤BF9EVT', '143', '被鱼叼着的猫', '13901675085', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100189, '冀TY57TB', '143', '被鱼叼着的猫', '13901675085', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100190, '陕F8220P', '130', '宝石流云', '17675631888', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100191, '鄂QWE1TJ', '128', '承蒙时光不弃', '15062227890', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100192, '鄂QWE1TJ', '128', '承蒙时光不弃', '15062227890', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100193, '黑HJUAG9', '125', '一折悲画扇', '17378313555', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100194, '黑HJUAG9', '125', '一折悲画扇', '17378313555', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100195, '鄂BRP45D', '120', '王者梦晓', '15618888398', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100196, '辽HVZH5C', '146', '香草味、布丁', '13707176868', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100197, '津AP9830', '116', 'customer', '13952078158', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100198, '贵FBU3X5', '116', 'customer', '13952078158', '2020年12月09日', '租借');
INSERT INTO `log` VALUES (100199, '贵FBU3X5', '116', 'customer', '13952078158', '2020年12月09日', '归还');
INSERT INTO `log` VALUES (100200, '津AP9830', '116', 'customer', '13952078158', '2020年12月09日', '租借');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `percode` varchar(100) DEFAULT NULL COMMENT '权限标识符',
  PRIMARY KEY (`id`),
  KEY `percode` (`percode`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (5, 'change_admin');
INSERT INTO `permission` VALUES (4, 'edit_car');
INSERT INTO `permission` VALUES (2, 'enter_logOv');
INSERT INTO `permission` VALUES (8, 'manage_car');
INSERT INTO `permission` VALUES (7, 'manage_log');
INSERT INTO `permission` VALUES (10, 'manage_user');
INSERT INTO `permission` VALUES (11, 'rent_car');
INSERT INTO `permission` VALUES (9, 'view_admin');
INSERT INTO `permission` VALUES (1, 'view_car_info');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL,
  `identity_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '老板', '110101');
INSERT INTO `role` VALUES (2, '管理员', '110105');
INSERT INTO `role` VALUES (3, '客户', '440113');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '用户类型id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `rp_ibfk_1` (`role_id`),
  KEY `rp_ibfk_2` (`permission_id`),
  CONSTRAINT `rp_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `rp_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (1, 1, 9);
INSERT INTO `role_permission` VALUES (2, 2, 9);
INSERT INTO `role_permission` VALUES (3, 1, 2);
INSERT INTO `role_permission` VALUES (4, 2, 2);
INSERT INTO `role_permission` VALUES (5, 1, 8);
INSERT INTO `role_permission` VALUES (6, 1, 4);
INSERT INTO `role_permission` VALUES (8, 1, 10);
INSERT INTO `role_permission` VALUES (9, 1, 5);
INSERT INTO `role_permission` VALUES (10, 3, 11);
COMMIT;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `introduction` varchar(20) DEFAULT NULL COMMENT '个性签名',
  `identity_num` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `role_id` int(11) NOT NULL COMMENT '用户类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES (101, 'boss', '111111', '13127512979', '想不到吧，我是尊贵的老板哦～', '110105200303231011', 1);
INSERT INTO `userinfo` VALUES (103, '墨雨无痕', '111111', '19985017188', 'hhhhhhh', '110101200409121032', 2);
INSERT INTO `userinfo` VALUES (105, '梦如初', '111111', '15618888506', '用户很懒，还没有填写哦~', '440133202004201052', 2);
INSERT INTO `userinfo` VALUES (106, '闲愁予了谁', '111111', '18116235678', '什么时候才能攒够钱买车呢？真的太穷了！', '440133200508071063', 3);
INSERT INTO `userinfo` VALUES (107, '时光泡沫', '111111', '17685858008', '用户很懒，还没有填写哦~', '110105200511251072', 2);
INSERT INTO `userinfo` VALUES (108, '枫少', '111111', '13386188616', '用户很懒，还没有填写哦~', '110105200602161083', 3);
INSERT INTO `userinfo` VALUES (111, '流星永恒', '111111', '17745195599', '用户很懒，还没有填写哦~', '440133201906091112', 2);
INSERT INTO `userinfo` VALUES (113, '單刀血杀', '111111', '15618781838', '用户很懒，还没有填写哦~', '110105201910011132', 2);
INSERT INTO `userinfo` VALUES (114, '诗人偏爱雨天', '111111', '13162111162', '用户很懒，还没有填写哦~', '110105201911201143', 3);
INSERT INTO `userinfo` VALUES (116, 'customer', '111111', '13952078158', '用户很懒，还没有填写哦~', '440133202012010163', 3);
INSERT INTO `userinfo` VALUES (117, '渡往彼岸', '111111', '18111828588', '用户很懒，还没有填写哦~', '110105202003061173', 3);
INSERT INTO `userinfo` VALUES (118, '花开终落', '111111', '13058585848', '用户很懒，还没有填写哦~', '110105202003081183', 3);
INSERT INTO `userinfo` VALUES (119, '赠你一世星辰', '111111', '15618888392', '用户很懒，还没有填写哦~', '440113202003091193', 3);
INSERT INTO `userinfo` VALUES (120, '王者梦晓', '111111', '15618888398', '用户很懒，还没有填写哦~', '110105202003111203', 3);
INSERT INTO `userinfo` VALUES (121, '纵有风云起', '111111', '13412337766', '用户很懒，还没有填写哦~', '110105202003111213', 3);
INSERT INTO `userinfo` VALUES (122, '慕容吹雪', '111111', '18616171839', '用户很懒，还没有填写哦~', '110105202003111223', 3);
INSERT INTO `userinfo` VALUES (123, '夏日天浅蓝', '111111', '18111828588', '用户很懒，没有填写哦~', '110105202003121233', 3);
INSERT INTO `userinfo` VALUES (124, '雾,茫然', '111111', '13026102777', '用户很懒，还没有填写哦~', '110105202003131243', 3);
INSERT INTO `userinfo` VALUES (125, '一折悲画扇', '111111', '17378313555', '用户很懒，还没有填写哦~', '440113202003131253', 3);
INSERT INTO `userinfo` VALUES (126, '忧夏丶伊人恋花', '111111', '17323970111', '用户很懒，还没有填写哦~', '440113202003151263', 3);
INSERT INTO `userinfo` VALUES (127, '釉色清风', '111111', '15618888870', '用户很懒，还没有填写哦~', '110105202003151273', 3);
INSERT INTO `userinfo` VALUES (128, '承蒙时光不弃', '111111', '15062227890', '用户很懒，还没有填写哦~', '110105202003151283', 3);
INSERT INTO `userinfo` VALUES (129, '清酒凉茶', '111111', '13414311349', '用户很懒，还没有填写哦~', '440113202003151293', 3);
INSERT INTO `userinfo` VALUES (130, '宝石流云', '111111', '17675631888', '用户很懒，还没有填写哦~', '11010520200317303', 3);
INSERT INTO `userinfo` VALUES (133, '寂寞清仓', '111111', '13934567656', '用户很懒，还没有填写哦~', '440113202003211333', 3);
INSERT INTO `userinfo` VALUES (134, '尘封d回忆', '111111', '13995507888', '用户很懒，还没有填写哦~', '110105202003221343', 3);
INSERT INTO `userinfo` VALUES (135, '从未止步', '111111', '13818798918', '用户很懒，还没有填写哦~', '110105202003231353', 3);
INSERT INTO `userinfo` VALUES (136, '恋上你的吻', '111111', '13122871999', '用户很懒，还没有填写哦~', '440113202003231363', 3);
INSERT INTO `userinfo` VALUES (137, '盖世萝莉', '111111', '13127625666', '用户很懒，还没有填写哦~', '110105202003231373', 3);
INSERT INTO `userinfo` VALUES (138, '复活的童话', '111111', '18111857188', '用户很懒，还没有填写哦~', '440113202003261383', 3);
INSERT INTO `userinfo` VALUES (139, '寻梦旅人', '111111', '19985013377', '用户很懒，还没有填写哦~', '440113202004011393', 3);
INSERT INTO `userinfo` VALUES (140, '飞舞流沙', '111111', '15651907555', '用户很懒，还没有填写哦~', '110105202004021403', 3);
INSERT INTO `userinfo` VALUES (141, '玫瑰半成品', '111111', '16638188788', '用户很懒，还没有填写哦~', '110105202004051413', 3);
INSERT INTO `userinfo` VALUES (142, '风起时、花落', '111111', '16638188788', '用户很懒，还没有填写哦~', '110105202004051423', 3);
INSERT INTO `userinfo` VALUES (143, '被鱼叼着的猫', '111111', '13901675085', '用户很懒，还没有填写哦~', '110105202004061433', 3);
INSERT INTO `userinfo` VALUES (144, '一笑泯千仇', '111111', '15618888262', '用户很懒，还没有填写哦~', '110105202004061443', 3);
INSERT INTO `userinfo` VALUES (145, '任心荒芜', '111111', '19913810666', '用户很懒，还没有填写哦~', '440113202004061453', 3);
INSERT INTO `userinfo` VALUES (146, '香草味、布丁', '111111', '13707176868', '用户很懒，还没有填写哦~', '440113202004071463', 3);
INSERT INTO `userinfo` VALUES (147, '流星的眼淚', '111111', '13886029898', '用户很懒，还没有填写哦~', '440113202004081473', 3);
INSERT INTO `userinfo` VALUES (148, '雨灵', '111111', '13650162262', '用户很懒，还没有填写哦~', '110105202004131483', 3);
INSERT INTO `userinfo` VALUES (149, '背若芒刺', '111111', '13122838656', '用户很懒，还没有填写哦~', '110105202004151493', 3);
INSERT INTO `userinfo` VALUES (150, '此男子已醉', '111111', '15102781188', '用户很懒，还没有填写哦~', '440113202004151503', 3);
INSERT INTO `userinfo` VALUES (151, '半生战骨', '111111', '13431333993', '用户很懒，还没有填写哦~', '440133202004201513', 3);
INSERT INTO `userinfo` VALUES (156, '孤战', '111111', '15846287632', '天上流星，地上天星', '440133202004201563', 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
