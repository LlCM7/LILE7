/*
 Navicat Premium Data Transfer

 Source Server         : local-db-mysql
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : my_blog

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 30/12/2024 18:23:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `front_num` int(11) NULL DEFAULT NULL COMMENT '字数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (4, 9, '发现了自己的诸多漏洞', '<p>刚刚经历裁员，下个月会有工资、奖金和赔偿金入账，赔偿金不扣税，同时对于市场环境没有了解，比较乐观。首月的想法就是写简历，并开始投递，先投不想去的公司找面试经验；找学习资料、刷题；期望薪资是不需要涨薪，大概平薪就行。<br><br>首月面了三家公司，发现了自己的诸多漏洞，项目比较垂类，讲解过程混乱；基础知识复习不足，很多新出来的延展概念了解不够。</p>', 166, '2024-12-30 18:10:43');
INSERT INTO `blog` VALUES (5, 10, '下个月会有工资', '<p>刚刚经历裁员，下个月会有工资、奖金和赔偿金入账，赔偿金不扣税，同时对于市场环境没有了解，比较乐观。首月的想法就是写简历，并开始投递，先投不想去的公司找面试经验；找学习资料、刷题；期望薪资是不需要涨薪，大概平薪就行。<br><br>首月面了三家公司，发现了自己的诸多漏洞，项目比较垂类，讲解过程混乱；基础知识复习不足，很多新出来的延展概念了解不够。</p><p>刚刚经历裁员，下个月会有工资、奖金和赔偿金入账，赔偿金不扣税，同时对于市场环境没有了解，比较乐观。</p><p>首月的想法就是写简历，并开始投递，先投不想去的公司找面试经验；找学习资料、刷题；期望薪资是不需要涨薪，大概平薪就行。</p><p><img src=\"/api/blog/v1.0/file/getFile?fileName=257e972NjAwX2MxXzQwMA.webp\" alt=\"/api/blog/v1.0/file/getFile?fileName=257e972NjAwX2MxXzQwMA.webp\" data-href=\"/api/blog/v1.0/file/getFile?fileName=257e972NjAwX2MxXzQwMA.webp\" style=\"width: 50%;\"/><br><br>首月面了三家公司，发现了自己的诸多漏洞，项目比较垂类，讲解过程混乱；基础知识复习不足，很多新出来的延展概念了解不够。</p><p>刚刚经历裁员，下个月会有工资、奖金和赔偿金入账，赔偿金不扣税，同时对于市场环境没有了解，比较乐观。首月的想法就是写简历，并开始投递，先投不想去的公司找面试经验；找学习资料、刷题；期望薪资是不需要涨薪，大概平薪就行。<br><br>首月面了三家公司，发现了自己的诸多漏洞，项目比较垂类，讲解过程混乱；基础知识复习不足，很多新出来的延展概念了解不够。</p><p><br></p>', 501, '2024-12-30 18:21:01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户联系电话',
  `user_gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户性别（0：男；1：女）',
  `user_role` int(11) NULL DEFAULT NULL COMMENT '用户角色',
  `is_login` tinyint(1) NULL DEFAULT NULL COMMENT '可登录状态(0：可用，1：不可用)',
  `is_word` tinyint(1) NULL DEFAULT NULL COMMENT '禁言状态(0：可用，1：不可用)',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上一次登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'B站辰星', '14e1b600b1fd579f47433b88e8d85291', '/api/personal-heath/v1.0/file/getFile?fileName=1ecfe5apic_8.jpg', '1343243@qq.com', NULL, NULL, 1, 0, 0, '2024-07-19 15:48:06', '2024-12-30 12:53:05');
INSERT INTO `user` VALUES (3, 'yangshu', '黄河以北', '14e1b600b1fd579f47433b88e8d85291', '/api/personal-heath/v1.0/file/getFile?fileName=0b868a1pic_2.jpg', '1134123@qq.com', NULL, NULL, 2, 0, 0, NULL, '2024-12-30 12:53:05');

SET FOREIGN_KEY_CHECKS = 1;
