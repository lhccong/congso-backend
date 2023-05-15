# 建表脚本
# @author <a href="https://github.com/liyupi">程序员鱼皮</a>
# @from <a href="https://yupi.icu">编程导航知识星球</a>

-- 创建库
create database if not exists cong_so;

-- 切换库
use cong_so;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('user1', 'password1', 'unionid1', 'mpopenid1', '张三', 'https://example.com/avatar1.jpg', '我是张三', 'user', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('user2', 'password2', 'unionid2', 'mpopenid2', '李四', 'https://example.com/avatar2.jpg', '我是李四', 'user', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('user3', 'password3', 'unionid3', 'mpopenid3', '王五', 'https://example.com/avatar3.jpg', '我是王五', 'user', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('admin1', 'password4', null, null, '管理员1', 'https://example.com/avatar4.jpg', '我是管理员1', 'admin', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('admin2', 'password5', null, null, '管理员2', 'https://example.com/avatar5.jpg', '我是管理员2', 'admin', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('ban1', 'password6', 'unionid4', null, '被封禁1', 'https://example.com/avatar6.jpg', '我是被封禁1', 'user', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('ban2', 'password7', 'unionid5', null, '被封禁2', 'https://example.com/avatar7.jpg', '我是被封禁2', 'user', 0);

INSERT INTO `user` (`userAccount`, `userPassword`, `unionId`, `mpOpenId`, `userName`, `userAvatar`, `userProfile`, `userRole`, `isDelete`)
VALUES ('ban3', 'password8', 'unionid6', null, '被封禁3', 'https://example.com/avatar8.jpg', '我是被封禁3', 'user', 0);

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';
