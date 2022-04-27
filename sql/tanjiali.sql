/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.29-log : Database - z_treeshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`z_treeshop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tttt`;

/*Table structure for table `cms_help` */

DROP TABLE IF EXISTS `cms_help`;

CREATE TABLE `cms_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `read_count` int(1) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助表';

/*Data for the table `cms_help` */

/*Table structure for table `cms_help_category` */

DROP TABLE IF EXISTS `cms_help_category`;

CREATE TABLE `cms_help_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '分类图标',
  `help_count` int(11) DEFAULT NULL COMMENT '专题数量',
  `show_status` int(2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助分类表';

/*Data for the table `cms_help_category` */

/*Table structure for table `cms_member_report` */

DROP TABLE IF EXISTS `cms_member_report`;

CREATE TABLE `cms_member_report` (
  `id` bigint(20) DEFAULT NULL,
  `report_type` int(1) DEFAULT NULL COMMENT '举报类型：0->商品评价；1->话题内容；2->用户评论',
  `report_member_name` varchar(100) DEFAULT NULL COMMENT '举报人',
  `create_time` datetime DEFAULT NULL,
  `report_object` varchar(100) DEFAULT NULL,
  `report_status` int(1) DEFAULT NULL COMMENT '举报状态：0->未处理；1->已处理',
  `handle_status` int(1) DEFAULT NULL COMMENT '处理结果：0->无效；1->有效；2->恶意',
  `note` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户举报表';

/*Data for the table `cms_member_report` */

/*Table structure for table `cms_prefrence_area` */

DROP TABLE IF EXISTS `cms_prefrence_area`;

CREATE TABLE `cms_prefrence_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `pic` varbinary(500) DEFAULT NULL COMMENT '展示图片',
  `sort` int(11) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='优选专区';

/*Data for the table `cms_prefrence_area` */

insert  into `cms_prefrence_area`(`id`,`name`,`sub_title`,`pic`,`sort`,`show_status`) values (1,'让音质更出众','音质不打折 完美现场感',NULL,NULL,1),(2,'让音质更出众22','让音质更出众22',NULL,NULL,NULL),(3,'让音质更出众33',NULL,NULL,NULL,NULL),(4,'让音质更出众44',NULL,NULL,NULL,NULL);

/*Table structure for table `cms_prefrence_area_product_relation` */

DROP TABLE IF EXISTS `cms_prefrence_area_product_relation`;

CREATE TABLE `cms_prefrence_area_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prefrence_area_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='优选专区和产品关系表';

/*Data for the table `cms_prefrence_area_product_relation` */

insert  into `cms_prefrence_area_product_relation`(`id`,`prefrence_area_id`,`product_id`) values (1,1,12),(2,1,13),(3,1,14),(4,1,18),(5,1,7),(6,2,7),(7,1,22),(24,1,23);

/*Table structure for table `cms_subject` */

DROP TABLE IF EXISTS `cms_subject`;

CREATE TABLE `cms_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(500) DEFAULT NULL COMMENT '专题主图',
  `product_count` int(11) DEFAULT NULL COMMENT '关联产品数量',
  `recommend_status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `collect_count` int(11) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `album_pics` varchar(1000) DEFAULT NULL COMMENT '画册图片用逗号分割',
  `description` varchar(1000) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `content` text,
  `forward_count` int(11) DEFAULT NULL COMMENT '转发数',
  `category_name` varchar(200) DEFAULT NULL COMMENT '专题分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='专题表';

/*Data for the table `cms_subject` */

insert  into `cms_subject`(`id`,`category_id`,`title`,`pic`,`product_count`,`recommend_status`,`create_time`,`collect_count`,`read_count`,`comment_count`,`album_pics`,`description`,`show_status`,`content`,`forward_count`,`category_name`) values (1,1,'轮廓分明，双摄手机映现细腻美照','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t26434/217/1381240043/254214/290f9d5b/5bc6c11cN54567a27.jpg!q70.jpg',NULL,1,'2018-11-11 13:26:55',100,1000,100,NULL,'手机对于拍照党来说，已经不仅仅是通讯工具那么简单了。因为有时TA还充当着“单反”的角色，来不断地带给那些喜欢拍照的人惊喜。所以，在这里准备一波高颜值的双摄手机来给大家。让TA们灵敏捕捉影像的能力，为你展现出轮廓更加分明、且画质更加细腻的美照。',1,NULL,NULL,'精选专题'),(2,1,'交通拥挤有妙招，电动车小巧穿行无障碍','https://img11.360buyimg.com/mobilecms/s1500x600_jfs/t14224/229/529700229/74868/a1cc7364/5a314f85N5bfd22c7.jpg!q70.jpg',NULL,1,'2018-11-12 13:27:00',100,1000,100,NULL,'随着人们消费水平的提高，公路上的小车是越来越多了，导致每到上下班高峰期的时候，大路的车辆堵了一环又一环，而且你根本不知道它到底会塞多久，所以我们需要变通一下，不妨骑上电动车来个绿色出行，它够小巧玲珑，即使交通再怎么拥挤，也总有它可以通过的地方。',1,NULL,NULL,'精选专题'),(3,1,'无酒不成席，甘香白酒开怀助兴','https://img12.360buyimg.com/mobilecms/s1500x600_jfs/t1/881/4/12265/114011/5bd1446fEc71114bf/68925bfb4a2adc44.jpg!q70.jpg',NULL,1,'2018-11-13 13:27:05',100,1000,100,NULL,'白酒是由各种优质的高粱，小麦，大米等谷物为原料，经过传统工艺的长时间酿造，酒液在这样的环境中慢慢发酵，最终变成清香浓郁的白酒，被摆上人们的餐桌，供人畅饮，是一种受到大众喜爱的绝佳饮品。',1,NULL,NULL,'精选专题'),(4,2,'真规划不盲扫，全域清洁净无尘','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t15205/35/2539924281/429185/72fa7857/5aab2c4bN6a32a6c5.png',NULL,1,'2018-11-01 13:27:09',100,1000,100,NULL,'科技时代，聪明女人会选择用智慧来减负，和繁琐的家务挥手再见，才能腾出更多休闲时间。规划式扫地机器人设计个性化，它能够跟据房间布置呈现清扫路线，实现规划式覆盖性清洁，少遗漏不盲扫，从而大幅度降低损耗，侦测技术遇到家具及时避让，杜绝猛烈撞击，任它灵巧穿梭于低矮空间，坐享居家净无尘。',1,NULL,NULL,'家电专题'),(5,2,'抑菌更纯净，直饮净水保家人健康','https://img11.360buyimg.com/mobilecms/s1500x600_jfs/t11428/340/1504074828/20474/1e8cab1e/5a0305d3Nb1e7a762.jpg!q70.jpg',NULL,1,'2018-11-06 13:27:18',100,1000,100,NULL,'在城里居住，首先要担心的是饮水问题。桶装水太贵不够经济，还不一定是干净的。自己去干净的水源地取水也不切实际。此时只有选择在家里安装一台净水器才实在。装上一台直饮式的净水器，方便安全，关键是水质过滤得比较纯净，很大限度地处理了大部分的废弃物，留下健康的矿物质水。好生活，从一口干净的纯净水开始。',1,NULL,NULL,'家电专题'),(6,2,'储鲜冷冻灵活变，多门无霜更贴心','https://img12.360buyimg.com/mobilecms/s1500x600_jfs/t13015/356/2397552584/605232/46c88e6e/5a5321bcN6a8866f0.png',NULL,1,'2018-11-07 13:27:21',100,1000,100,NULL,'春节临近，每个家庭都要储备不少食材，但各种食材的保鲜方式与温度不尽相同，而多门风冷冰箱能轻松满足您。它们容积大占地小，拥有多个可以独立调节温度的温区，满足对不同类食材的存放需求，同时省去除霜烦恼，还可以通过温度调节满足您对大冷藏及大冷冻的需求变化，从而带来更好的保鲜冷冻体验，为新年宴请保驾护航。',1,NULL,NULL,'家电专题'),(7,2,'想喝健康水，就用304不锈钢热水壶','https://img13.360buyimg.com/mobilecms/s1500x600_jfs/t12541/90/443985343/33603/65d6e884/5a0bb77aNff08550a.jpg!q70.jpg',NULL,1,'2019-01-29 11:21:55',100,1000,100,NULL,'大冬天的喝一口热水，不仅能够暧身还可以给身体补充足够的水份，但是对于热水壶的购买却是需要慎之又慎，材质不好的热水壶在烧水的过程当中极容易产生对身体不利的有害物，极大影响人们的身体健康。304不锈钢热水壶选用食品级不不锈钢，确保水质安全，烧水健康好水，为您的饮水健康保驾护航。',1,NULL,NULL,'家电专题'),(8,2,'你尽情赖床！早餐“煲”在它身上','https://img14.360buyimg.com/mobilecms/s1500x600_jfs/t15949/363/1450937723/89513/7d8c1219/5a531d28N2aaec2a6.jpg!q70.jpg',NULL,1,'2019-01-29 13:07:13',100,1000,100,NULL,'赖床不想起，饿了的时候想吃饭又要现做等待简直饥肠辘辘让人心烦，所以一款带有预约功能的电饭煲简直不要太贴心，你睡懒觉的时候它已经给你做好了香滑软糯的粥，起床就能享美味是不是很贴心呐。',1,NULL,NULL,'家电专题'),(9,2,'小白变大厨，微波炉为实力加持','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t1/8774/21/11460/38908/5c2cbfcfEdab1ef03/d5800f0f7cf05b38.jpg!q70.jpg',NULL,1,'2019-01-29 13:08:18',100,1000,100,NULL,'对于厨艺小白而言，没有什么能比掌握好火候更来得困难的了！毕竟烹饪出食物的味道好坏，很大程度上还是对火候的掌控，想要轻松掌握火候，当然少不了一款微波炉的撑场，内设多功能，满足不同的烹饪需求，简单方便易操作，让厨艺小白秒变大师！',1,NULL,NULL,'家电专题'),(10,2,'十秒鲜榨，冬日把爱浓缩成杯果汁','https://img11.360buyimg.com/mobilecms/s1500x600_jfs/t13708/126/308291722/542847/26ee6edd/5a07dc72N3252a9e0.png',NULL,1,'2019-01-29 13:10:02',100,1000,100,NULL,'寒瑟冬日女友不喜欢吃水果，用便携迷你果汁机，撩妹又养胃。一按一转，碾压切割，简单快速制作，压榨出纯原味果汁。一键启动，十秒出汁，保留食物营养，轻轻松松粉碎食物，营养在手，说走就走。',1,NULL,NULL,'家电专题'),(11,3,'饭点未到肚已空？美味饼干先充饥','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t13240/79/443357432/38567/94792c4c/5a0ba054N89388654.jpg!q70.jpg',NULL,1,'2019-01-29 13:10:45',100,1000,100,NULL,'一上午都把精力集中在工作中，刚闲下来就发现自己已是饥肠辘辘了，可饭点却还没到，怎么办呢？不妨让这些美味饼干先帮你充充饥吧！酥香松脆有营养，每一口都让人回味无穷，既能满足你挑剔的味蕾又能起到果腹效果，快快为自己备上吧！',1,NULL,NULL,'美食专题'),(12,3,'赖床无罪，香酥面包营养又便捷','https://img11.360buyimg.com/mobilecms/s1500x600_jfs/t9775/33/1197239610/38547/34899011/59ddbd01N0bd693bb.jpg!q70.jpg',NULL,1,'2019-01-29 13:11:41',100,1000,100,NULL,'赖床是很多年轻人的通病，特别是秋冬季节，都会恋恋不舍温暖的被窝。对于苦逼的上班族来说，就算再多睡几分钟，还是要起床的，甚至来不及吃早餐。面包营养丰富，能快速饱腹，且携带方便，再搭配一盒牛奶，一顿简单而不失营养的早餐能提供一天的能量，让赖床族多睡几分钟也无妨。',1,NULL,NULL,'美食专题'),(13,3,'夹心饼干，予多重滋味交织舌尖','https://img12.360buyimg.com/mobilecms/s1500x600_jfs/t18877/139/652452758/27262/36e6ed6e/5a9d5b6dN327150e8.jpg!q70.jpg',NULL,1,'2019-01-29 13:12:38',100,1000,100,NULL,'饼干味道香脆可口，深受不少人的青睐。饼干的种类多样，而夹心饼干就是其中一种，相比普通饼干而言，夹心饼干有着更丰富的口感，当肚子空空如也的时候，来一些美味的夹心饼干，既能解馋，又能扛饿。下面就为大家推荐一组好吃的夹心饼干，作为办公室小零食非常不错。',1,NULL,NULL,'美食专题'),(14,4,'户外Party，便携音箱烘气氛','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t17125/265/644948348/42066/6f2dc610/5a9c9da1N9a95ee2c.jpg!q70.jpg',NULL,1,'2019-01-29 13:13:53',100,1000,100,NULL,'初春的季节，除了户外的各种踏青旅行，在户外开异常Party也是很惬意。户外派对，气氛的烘托肯定不能离开音箱的衬托，选择一款户外的便携音箱，无线蓝牙连接，免去有线的束缚，携带使用更方便。',1,NULL,NULL,'数码专题'),(15,5,'今冬潮包look，凹出绚丽女王范','https://img10.360buyimg.com/mobilecms/s1500x600_jfs/t8365/191/1901440450/575969/c71560c9/59c3144dNe6d8dd2f.png',NULL,1,'2019-01-29 13:15:12',100,1000,100,NULL,'潮流时尚的美包，搭配潮服，会让你魅力一直在线。因为潮包一直是女性出游扮美的秘籍，它不仅能够帮你收纳日常小物件，还能让你解放双手，这里推荐的时尚美包，随意搭配一件服饰，都可以让你潮范十足，凹出绚丽女王范。',1,NULL,NULL,'服饰专题');

/*Table structure for table `cms_subject_category` */

DROP TABLE IF EXISTS `cms_subject_category`;

CREATE TABLE `cms_subject_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '分类图标',
  `subject_count` int(11) DEFAULT NULL COMMENT '专题数量',
  `show_status` int(2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='专题分类表';

/*Data for the table `cms_subject_category` */

insert  into `cms_subject_category`(`id`,`name`,`icon`,`subject_count`,`show_status`,`sort`) values (1,'精选专题','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jingxuan.png',3,1,100),(2,'家电专题','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jiadian.png',7,1,0),(3,'美食专题','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_meishi.png',3,1,0),(4,'数码专题','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_shouji.png',1,1,0),(5,'服饰专题','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_waitao.png',1,1,0);

/*Table structure for table `cms_subject_comment` */

DROP TABLE IF EXISTS `cms_subject_comment`;

CREATE TABLE `cms_subject_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `member_icon` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题评论表';

/*Data for the table `cms_subject_comment` */

/*Table structure for table `cms_subject_product_relation` */

DROP TABLE IF EXISTS `cms_subject_product_relation`;

CREATE TABLE `cms_subject_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='专题商品关系表';

/*Data for the table `cms_subject_product_relation` */

insert  into `cms_subject_product_relation`(`id`,`subject_id`,`product_id`) values (1,1,26),(2,1,27),(3,1,28),(4,1,29),(5,2,30),(6,2,31),(7,2,35),(29,2,36),(30,2,32),(31,3,33),(38,3,34);

/*Table structure for table `cms_topic` */

DROP TABLE IF EXISTS `cms_topic`;

CREATE TABLE `cms_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `attend_count` int(11) DEFAULT NULL COMMENT '参与人数',
  `attention_count` int(11) DEFAULT NULL COMMENT '关注人数',
  `read_count` int(11) DEFAULT NULL,
  `award_name` varchar(100) DEFAULT NULL COMMENT '奖品名称',
  `attend_type` varchar(100) DEFAULT NULL COMMENT '参与方式',
  `content` text COMMENT '话题内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题表';

/*Data for the table `cms_topic` */

/*Table structure for table `cms_topic_category` */

DROP TABLE IF EXISTS `cms_topic_category`;

CREATE TABLE `cms_topic_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '分类图标',
  `subject_count` int(11) DEFAULT NULL COMMENT '专题数量',
  `show_status` int(2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题分类表';

/*Data for the table `cms_topic_category` */

/*Table structure for table `cms_topic_comment` */

DROP TABLE IF EXISTS `cms_topic_comment`;

CREATE TABLE `cms_topic_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `member_icon` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专题评论表';

/*Data for the table `cms_topic_comment` */

/*Table structure for table `oms_cart_item` */

DROP TABLE IF EXISTS `oms_cart_item`;

CREATE TABLE `oms_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_sku_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '添加到购物车的价格',
  `sp1` varchar(200) DEFAULT NULL COMMENT '销售属性1',
  `sp2` varchar(200) DEFAULT NULL COMMENT '销售属性2',
  `sp3` varchar(200) DEFAULT NULL COMMENT '销售属性3',
  `product_pic` varchar(1000) DEFAULT NULL COMMENT '商品主图',
  `product_name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `product_sub_title` varchar(500) DEFAULT NULL COMMENT '商品副标题（卖点）',
  `product_sku_code` varchar(200) DEFAULT NULL COMMENT '商品sku条码',
  `member_nickname` varchar(500) DEFAULT NULL COMMENT '会员昵称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(1) DEFAULT '0' COMMENT '是否删除',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类',
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(200) DEFAULT NULL,
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='购物车表';

/*Data for the table `oms_cart_item` */

insert  into `oms_cart_item`(`id`,`product_id`,`product_sku_id`,`member_id`,`quantity`,`price`,`sp1`,`sp2`,`sp3`,`product_pic`,`product_name`,`product_sub_title`,`product_sku_code`,`member_nickname`,`create_date`,`modify_date`,`delete_status`,`product_category_id`,`product_brand`,`product_sn`,`product_attr`) values (12,26,90,1,1,'3788.00','金色','16G',NULL,NULL,'华为 HUAWEI P20','AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待','201806070026001','windir','2018-08-27 16:53:44',NULL,0,19,NULL,NULL,NULL),(13,27,98,1,3,'2699.00','黑色','32G',NULL,NULL,'小米8','骁龙845处理器，红外人脸解锁，AI变焦双摄，AI语音助手小米6X低至1299，点击抢购','201808270027001','windir','2018-08-27 17:11:53',NULL,0,19,NULL,NULL,NULL),(14,28,102,1,1,'649.00','金色','16G',NULL,NULL,'红米5A','8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购','201808270028001','windir','2018-08-27 17:18:02',NULL,0,19,NULL,NULL,NULL),(15,28,103,1,1,'699.00','金色','32G',NULL,NULL,'红米5A','8天超长待机，137g轻巧机身，高通骁龙处理器小米6X低至1299，点击抢购','201808270028001','windir','2018-08-28 10:22:45',NULL,0,19,NULL,NULL,NULL),(16,29,106,1,1,'5499.00','金色','32G',NULL,NULL,'Apple iPhone 8 Plus','【限时限量抢购】Apple产品年中狂欢节，好物尽享，美在智慧！速来 >> 勾选[保障服务][原厂保2年]，获得AppleCare+全方位服务计划，原厂延保售后无忧。','201808270029001','windir','2018-08-28 10:50:50',NULL,0,19,NULL,NULL,NULL),(17,7,229,12,10,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-09 01:36:34','2022-04-09 01:36:34',1,7,'万和','No86577',NULL),(18,26,210,12,1,'4999.00','金色','16G','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20 ','信4G手机 双卡双待手机 双卡双待','1','谭佳利','2022-04-09 01:50:01','2022-04-09 01:50:01',1,19,'华为','6946605',NULL),(19,29,219,12,1,'5499.00','金色','32G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus ','【限时限量抢购】Apple产品年中狂欢节','201808270029001','谭佳利','2022-04-09 23:47:32','2022-04-09 23:47:32',1,19,'苹果','7437799',NULL),(20,7,229,12,1,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-10 20:22:13','2022-04-10 20:22:13',1,7,'万和','No86577',NULL),(21,26,210,12,1,'8888.00','金色','16G','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20 ','信4G手机 双卡双待手机 双卡双待','1','谭佳利','2022-04-10 20:22:26','2022-04-10 20:22:26',1,19,'华为','6946605',NULL),(22,28,208,12,1,'649.00','银色','16G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028003','谭佳利','2022-04-10 20:22:38','2022-04-10 20:22:38',1,19,'小米','7437789',NULL),(23,29,221,12,1,'5499.00','银色','32G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus ','【限时限量抢购】Apple产品年中狂欢节','201808270029003','谭佳利','2022-04-10 20:22:46','2022-04-10 20:22:46',1,19,'苹果','7437799',NULL),(24,29,220,12,1,'6299.00','金色','64G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus ','【限时限量抢购】Apple产品年中狂欢节','201808270029002','谭佳利','2022-04-13 00:59:48','2022-04-13 00:59:48',1,19,'苹果','7437799',NULL),(25,7,229,12,2,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-13 18:30:43','2022-04-13 18:30:43',1,7,'万和','No86577',NULL),(26,26,210,12,1,NULL,'金色','16G','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20 ','信4G手机 双卡双待手机 双卡双待','1','谭佳利','2022-04-13 18:31:09','2022-04-13 18:31:09',1,19,'华为','6946605',NULL),(27,7,229,12,1,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-14 18:29:17','2022-04-14 18:29:17',1,7,'万和','No86577',NULL),(28,7,229,12,2,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-14 21:46:09','2022-04-14 21:46:09',1,7,'万和','No86577',NULL),(29,28,206,12,1,'649.00','金色','16G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028001','谭佳利','2022-04-14 22:00:14','2022-04-14 22:00:14',1,19,'小米','7437789',NULL),(30,7,229,12,1,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-14 22:04:28','2022-04-14 22:04:28',1,7,'万和','No86577',NULL),(31,7,229,12,1,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-14 22:18:10','2022-04-14 22:18:10',1,7,'万和','No86577',NULL),(32,28,209,12,1,'699.00','银色','32G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028004','谭佳利','2022-04-14 22:28:24','2022-04-14 22:28:24',1,19,'小米','7437789',NULL),(33,28,208,12,1,'649.00','银色','16G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028003','谭佳利','2022-04-14 22:28:27','2022-04-14 22:28:27',1,19,'小米','7437789',NULL),(34,28,206,12,1,'649.00','金色','16G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028001','谭佳利','2022-04-14 22:28:30','2022-04-14 22:28:30',1,19,'小米','7437789',NULL),(35,28,207,12,1,'699.00','金色','32G',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','137g轻巧机身，高通骁龙处理器小米6X','201808270028002','谭佳利','2022-04-14 22:28:32','2022-04-14 22:28:32',1,19,'小米','7437789',NULL),(36,7,229,12,1,'122.00','M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','111','谭佳利','2022-04-14 23:03:26','2022-04-14 23:03:26',1,7,'万和','No86577',NULL),(37,7,257,12,1,'199.00','X','红色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','匠心剪裁，垂感质地','NO.911','谭佳利','2022-04-17 22:05:44','2022-04-17 22:05:44',1,7,'万和','No86577',NULL),(38,30,224,12,1,'122.00','M',NULL,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg','HLA海澜之家简约动物印花短袖T恤','即分享赢大奖','99','谭佳利','2022-04-18 02:36:56','2022-04-18 02:36:56',1,8,'海澜之家','HNTBJ2E042A',NULL);

/*Table structure for table `oms_company_address` */

DROP TABLE IF EXISTS `oms_company_address`;

CREATE TABLE `oms_company_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_name` varchar(200) DEFAULT NULL COMMENT '地址名称',
  `send_status` int(1) DEFAULT NULL COMMENT '默认发货地址：0->否；1->是',
  `receive_status` int(1) DEFAULT NULL COMMENT '是否默认收货地址：0->否；1->是',
  `name` varchar(64) DEFAULT NULL COMMENT '收发货人姓名',
  `phone` varchar(64) DEFAULT NULL COMMENT '收货人电话',
  `province` varchar(64) DEFAULT NULL COMMENT '省/直辖市',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `region` varchar(64) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='公司收发货地址表';

/*Data for the table `oms_company_address` */

insert  into `oms_company_address`(`id`,`address_name`,`send_status`,`receive_status`,`name`,`phone`,`province`,`city`,`region`,`detail_address`) values (1,'深圳发货点',1,1,'大梨','18000000000','广东省','深圳市','南山区','科兴科学园'),(2,'北京发货点',0,0,'大梨','18000000000','北京市',NULL,'南山区','科兴科学园'),(3,'南京发货点',0,0,'大梨','18000000000','江苏省','南京市','南山区','科兴科学园');

/*Table structure for table `oms_order` */

DROP TABLE IF EXISTS `oms_order`;

CREATE TABLE `oms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(20) NOT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户帐号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵扣金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `source_type` int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `order_type` int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `delivery_company` varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  `integration` int(11) DEFAULT NULL COMMENT '可以获得的积分',
  `growth` int(11) DEFAULT NULL COMMENT '可以活动的成长值',
  `promotion_info` varchar(100) DEFAULT NULL COMMENT '活动信息',
  `bill_type` int(1) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  `bill_header` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `bill_content` varchar(200) DEFAULT NULL COMMENT '发票内容',
  `bill_receiver_phone` varchar(32) DEFAULT NULL COMMENT '收票人电话',
  `bill_receiver_email` varchar(64) DEFAULT NULL COMMENT '收票人邮箱',
  `receiver_name` varchar(100) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `confirm_status` int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  `delete_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  `use_integration` int(11) DEFAULT NULL COMMENT '下单时使用的积分',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `oms_order` */

insert  into `oms_order`(`id`,`member_id`,`coupon_id`,`order_sn`,`create_time`,`member_username`,`total_amount`,`pay_amount`,`freight_amount`,`promotion_amount`,`integration_amount`,`coupon_amount`,`discount_amount`,`pay_type`,`source_type`,`status`,`order_type`,`delivery_company`,`delivery_sn`,`auto_confirm_day`,`integration`,`growth`,`promotion_info`,`bill_type`,`bill_header`,`bill_content`,`bill_receiver_phone`,`bill_receiver_email`,`receiver_name`,`receiver_phone`,`receiver_post_code`,`receiver_province`,`receiver_city`,`receiver_region`,`receiver_detail_address`,`note`,`confirm_status`,`delete_status`,`use_integration`,`payment_time`,`delivery_time`,`receive_time`,`comment_time`,`modify_time`) values (12,1,2,'201809150101000001','2022-04-15 12:24:27','test','18732.00','16377.75','20.00','2344.25','0.00','10.00','10.00',0,0,4,0,'','',15,13284,13284,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','江苏省','常州市','天宁区','东晓街道','xxx',0,0,NULL,NULL,NULL,NULL,NULL,'2018-10-30 14:43:49'),(13,1,2,'201809150102000002','2022-03-15 14:24:29','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',1,0,2,0,'韵达快递','123',15,13284,13284,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,1000,'2018-10-11 14:04:19',NULL,NULL,NULL,NULL),(14,1,2,'201809130101000001','2022-04-13 16:57:40','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',2,0,2,0,'顺丰快递','201707196398345',15,13284,13284,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道','aa',0,0,NULL,'2018-10-13 13:44:04','2018-10-16 13:43:41',NULL,NULL,NULL),(15,1,2,'201809130102000002','2022-04-13 17:03:00','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',1,0,3,0,'顺丰快递','201707196398346',15,13284,13284,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,1,0,NULL,'2018-10-13 13:44:54','2018-10-16 13:45:01','2018-10-18 14:05:31',NULL,NULL),(16,1,2,'201809140101000001','2022-04-14 16:16:16','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',2,0,4,0,NULL,NULL,15,13284,13284,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(17,1,2,'201809150101000003','2022-03-15 12:24:27','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',0,0,4,0,'顺丰快递','201707196398345',15,NULL,NULL,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,'2018-10-12 14:01:28',NULL,NULL,NULL),(18,1,2,'201809150102000004','2022-03-15 14:24:29','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',1,0,5,0,'圆通快递','xx',15,NULL,NULL,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道','关闭订单',0,0,1000,NULL,'2018-10-16 14:42:17',NULL,NULL,NULL),(19,1,2,'201809130101000003','2022-03-13 16:57:40','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',2,0,2,0,NULL,NULL,15,NULL,NULL,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(20,1,2,'201809130102000004','2022-03-13 17:03:00','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',1,0,3,0,NULL,NULL,15,NULL,NULL,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(22,1,2,'201809150101000005','2022-01-15 12:24:27','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',0,0,4,0,'顺丰快递','201707196398345',15,0,0,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道','111',0,0,NULL,NULL,'2018-10-12 14:01:28',NULL,NULL,NULL),(23,1,2,'201809150102000006','2022-04-15 14:24:29','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',1,0,1,0,'顺丰快递','xxx',15,0,0,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,1000,NULL,'2018-10-16 14:41:28',NULL,NULL,NULL),(24,1,2,'201809130101000005','2022-03-13 16:57:40','test','199.00','16377.75','0.00','2344.25','0.00','10.00','0.00',2,0,2,0,NULL,NULL,15,18682,18682,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(25,1,2,'201809130102000006','2022-02-13 17:03:00','test','18732.00','16377.75','10.00','2344.25','0.00','10.00','5.00',1,0,4,0,NULL,NULL,15,18682,18682,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨22','18033441849','518000','北京市','北京城区','东城区','东城街道','xxx',0,0,NULL,NULL,NULL,NULL,NULL,'2018-10-30 15:08:31'),(26,1,2,'201809140101000003','2022-03-14 16:16:16','test','18732.00','16377.75','0.00','2344.25','0.00','10.00','0.00',0,0,4,0,NULL,NULL,15,18682,18682,'单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠',NULL,NULL,NULL,NULL,NULL,'大梨','18033441849','518000','广东省','深圳市','福田区','东晓街道',NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(27,12,2,'20220413010000011','2022-04-13 00:55:13','yyds','6148.00','6148.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(28,12,2,'20220413010000022','2022-04-13 01:00:20','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三','13697665790','100000','黑龙江省','鸡西市','鸡冠区','xx街道','1212',NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(29,12,2,'20220413010000033','2022-04-13 01:46:15','yyds','100.00','6299.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三','13697665790','100000','黑龙江省','鸡西市','鸡冠区','xx街道',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(30,12,2,'20220413010000033','2022-04-13 14:28:43','yyds','8888.00','8898.00','10.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是','1111',NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(31,12,2,'20220413010000044','2022-04-13 18:34:06','yyds','244.00','244.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(32,12,2,'20220414010000011','2022-04-14 18:29:38','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-21 23:21:03'),(33,12,2,'20220414010000022','2022-04-14 21:54:40','yyds','244.00','244.00','0.00',NULL,NULL,NULL,NULL,1,0,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,'2022-04-14 21:56:00'),(34,12,2,'20220414010000033','2022-04-14 22:00:23','yyds','649.00','649.00','0.00',NULL,NULL,NULL,NULL,2,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,'2022-04-14 22:03:03',NULL,NULL,NULL,'2022-04-14 22:00:23'),(35,12,2,'20220414010000044','2022-04-14 22:04:38','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,2,0,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三','13697665790','100000','黑龙江省','鸡西市','鸡冠区','xx街道','马上到了，记得早点好评哦~~~',NULL,0,NULL,'2022-04-14 22:04:40',NULL,NULL,NULL,'2022-04-14 22:04:38'),(36,12,2,'20220414010000055','2022-03-14 22:22:52','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,2,0,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,'2022-04-14 22:27:12',NULL,NULL,NULL,'2022-04-14 22:22:52'),(37,12,2,'20220414010000066','2022-04-14 23:03:39','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,1,0,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'张三','13697665790','100000','黑龙江省','鸡西市','鸡冠区','xx街道',NULL,NULL,0,NULL,'2022-04-14 23:03:45',NULL,NULL,NULL,'2022-04-14 23:03:39'),(38,12,2,'20220417010000011','2022-04-17 22:06:16','yyds','199.00','209.00','10.00',NULL,NULL,NULL,NULL,1,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,'2022-04-17 22:06:19',NULL,NULL,NULL,'2022-04-17 22:06:16'),(39,12,2,'20220418010000011','2022-04-18 02:37:07','yyds','122.00','122.00','0.00',NULL,NULL,NULL,NULL,2,0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'谭佳利','187774546597','470001','湖南省','张家界市','桑植县','cg节点123是',NULL,NULL,0,NULL,'2022-04-18 02:37:08',NULL,NULL,NULL,'2022-04-18 02:37:07');

/*Table structure for table `oms_order_item` */

DROP TABLE IF EXISTS `oms_order_item`;

CREATE TABLE `oms_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `product_id` bigint(20) DEFAULT NULL,
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(64) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '销售价格',
  `product_quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku编号',
  `product_sku_code` varchar(50) DEFAULT NULL COMMENT '商品sku条码',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `sp1` varchar(100) DEFAULT NULL COMMENT '商品的销售属性',
  `sp2` varchar(100) DEFAULT NULL,
  `sp3` varchar(100) DEFAULT NULL,
  `promotion_name` varchar(200) DEFAULT NULL COMMENT '商品促销名称',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '商品促销分解金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券优惠分解金额',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分优惠分解金额',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '该商品经过优惠后的分解金额',
  `gift_integration` int(11) DEFAULT '0',
  `gift_growth` int(11) DEFAULT '0',
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='订单中所包含的商品';

/*Data for the table `oms_order_item` */

insert  into `oms_order_item`(`id`,`order_id`,`order_sn`,`product_id`,`product_pic`,`product_name`,`product_brand`,`product_sn`,`product_price`,`product_quantity`,`product_sku_id`,`product_sku_code`,`product_category_id`,`sp1`,`sp2`,`sp3`,`promotion_name`,`promotion_amount`,`coupon_amount`,`integration_amount`,`real_amount`,`gift_integration`,`gift_growth`,`product_attr`) values (21,12,'201809150101000001',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','6946605','3788.00',1,90,'201806070026001',19,NULL,NULL,NULL,'单品促销','200.00','2.02','0.00','3585.98',3788,3788,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(22,12,'201809150101000001',27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','7437788','2699.00',3,98,'201808270027001',19,NULL,NULL,NULL,'打折优惠：满3件，打7.50折','674.75','1.44','0.00','2022.81',2699,2699,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(23,12,'201809150101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','649.00',1,102,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','57.60','0.35','0.00','591.05',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(24,12,'201809150101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','699.00',1,103,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','62.40','0.37','0.00','636.23',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(25,12,'201809150101000001',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus','苹果','7437799','5499.00',1,106,'201808270029001',19,NULL,NULL,NULL,'无优惠','0.00','2.94','0.00','5496.06',5499,5499,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(26,13,'201809150102000002',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','6946605','3788.00',1,90,'201806070026001',19,NULL,NULL,NULL,'单品促销','200.00','2.02','0.00','3585.98',3788,3788,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(27,13,'201809150102000002',27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','7437788','2699.00',3,98,'201808270027001',19,NULL,NULL,NULL,'打折优惠：满3件，打7.50折','674.75','1.44','0.00','2022.81',2699,2699,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(28,13,'201809150102000002',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','649.00',1,102,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','57.60','0.35','0.00','591.05',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(29,13,'201809150102000002',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','699.00',1,103,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','62.40','0.37','0.00','636.23',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(30,13,'201809150102000002',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus','苹果','7437799','5499.00',1,106,'201808270029001',19,NULL,NULL,NULL,'无优惠','0.00','2.94','0.00','5496.06',5499,5499,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(31,14,'201809130101000001',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','6946605','3788.00',1,90,'201806070026001',19,NULL,NULL,NULL,'单品促销','200.00','2.02','0.00','3585.98',3788,3788,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(32,14,'201809130101000001',27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','7437788','2699.00',3,98,'201808270027001',19,NULL,NULL,NULL,'打折优惠：满3件，打7.50折','674.75','1.44','0.00','2022.81',2699,2699,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(33,14,'201809130101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','649.00',1,102,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','57.60','0.35','0.00','591.05',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(34,14,'201809130101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','699.00',1,103,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','62.40','0.37','0.00','636.23',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(35,14,'201809130101000001',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus','苹果','7437799','5499.00',1,106,'201808270029001',19,NULL,NULL,NULL,'无优惠','0.00','2.94','0.00','5496.06',5499,5499,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(36,15,'201809130101000001',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','6946605','3788.00',1,90,'201806070026001',19,NULL,NULL,NULL,'单品促销','200.00','2.02','0.00','3585.98',3788,3788,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(37,15,'201809130101000001',27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','7437788','2699.00',3,98,'201808270027001',19,NULL,NULL,NULL,'打折优惠：满3件，打7.50折','674.75','1.44','0.00','2022.81',2699,2699,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(38,15,'201809130101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','649.00',1,102,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','57.60','0.35','0.00','591.05',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(39,15,'201809130101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','699.00',1,103,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','62.40','0.37','0.00','636.23',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(40,15,'201809130101000001',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus','苹果','7437799','5499.00',1,106,'201808270029001',19,NULL,NULL,NULL,'无优惠','0.00','2.94','0.00','5496.06',5499,5499,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(41,16,'201809140101000001',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','6946605','3788.00',1,90,'201806070026001',19,NULL,NULL,NULL,'单品促销','200.00','2.02','0.00','3585.98',3788,3788,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(42,16,'201809140101000001',27,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','7437788','2699.00',3,98,'201808270027001',19,NULL,NULL,NULL,'打折优惠：满3件，打7.50折','674.75','1.44','0.00','2022.81',2699,2699,'[{\"key\":\"颜色\",\"value\":\"黑色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(43,16,'201809140101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','649.00',1,102,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','57.60','0.35','0.00','591.05',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"16G\"}]'),(44,16,'201809140101000001',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','7437789','699.00',1,103,'201808270028001',19,NULL,NULL,NULL,'满减优惠：满1000.00元，减120.00元','62.40','0.37','0.00','636.23',649,649,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(45,16,'201809140101000001',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus','苹果','7437799','5499.00',1,106,'201808270029001',19,NULL,NULL,NULL,'无优惠','0.00','2.94','0.00','5496.06',5499,5499,'[{\"key\":\"颜色\",\"value\":\"金色\"},{\"key\":\"容量\",\"value\":\"32G\"}]'),(46,27,'20220413010000011',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','小米','7437789','649.00',1,208,'201808270028003',19,'银色','16G',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(47,27,'20220413010000011',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus ','苹果','7437799','5499.00',1,221,'201808270029003',19,'银色','32G',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(48,28,'20220413010000022',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',1,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(49,29,'20220413010000033',29,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','Apple iPhone 8 Plus ','苹果','7437799','6299.00',1,220,'201808270029002',19,'金色','64G',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(50,30,'20220413010000033',26,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20 ','华为','6946605','8888.00',1,210,'1',19,'金色','16G','',NULL,NULL,NULL,NULL,NULL,0,0,NULL),(51,31,'20220413010000044',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',2,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(52,32,'20220414010000011',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',1,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(53,33,'20220414010000022',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',2,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(54,34,'20220414010000033',28,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','小米 红米5A 全网通','小米','7437789','649.00',1,206,'201808270028001',19,'金色','16G',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(55,35,'20220414010000044',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',1,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(56,36,'20220414010000055',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',1,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(57,37,'20220414010000066',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','122.00',1,229,'111',7,'M','黑色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(58,38,'20220417010000011',7,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','女式超柔软拉毛运动开衫','万和','No86577','199.00',1,257,'NO.911',7,'X','红色',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),(59,39,'20220418010000011',30,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg','HLA海澜之家简约动物印花短袖T恤','海澜之家','HNTBJ2E042A','122.00',1,224,'99',8,'M',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL);

/*Table structure for table `oms_order_operate_history` */

DROP TABLE IF EXISTS `oms_order_operate_history`;

CREATE TABLE `oms_order_operate_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `order_status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录';

/*Data for the table `oms_order_operate_history` */

insert  into `oms_order_operate_history`(`id`,`order_id`,`operate_man`,`create_time`,`order_status`,`note`) values (5,12,'后台管理员','2018-10-12 14:01:29',2,'完成发货'),(6,13,'后台管理员','2018-10-12 14:01:29',2,'完成发货'),(7,12,'后台管理员','2018-10-12 14:13:10',4,'订单关闭:买家退货'),(8,13,'后台管理员','2018-10-12 14:13:10',4,'订单关闭:买家退货'),(9,22,'后台管理员','2018-10-15 16:31:48',4,'订单关闭:xxx'),(10,22,'后台管理员','2018-10-15 16:35:08',4,'订单关闭:xxx'),(11,22,'后台管理员','2018-10-15 16:35:59',4,'订单关闭:xxx'),(12,17,'后台管理员','2018-10-15 16:43:40',4,'订单关闭:xxx'),(13,25,'后台管理员','2018-10-15 16:52:14',4,'订单关闭:xxx'),(14,26,'后台管理员','2018-10-15 16:52:14',4,'订单关闭:xxx'),(15,23,'后台管理员','2018-10-16 14:41:28',2,'完成发货'),(16,13,'后台管理员','2018-10-16 14:42:17',2,'完成发货'),(17,18,'后台管理员','2018-10-16 14:42:17',2,'完成发货'),(18,26,'后台管理员','2018-10-30 14:37:44',4,'订单关闭:关闭订单'),(19,25,'后台管理员','2018-10-30 15:07:01',0,'修改收货人信息'),(20,25,'后台管理员','2018-10-30 15:08:13',0,'修改费用信息'),(21,25,'后台管理员','2018-10-30 15:08:31',0,'修改备注信息：xxx'),(22,25,'后台管理员','2018-10-30 15:08:39',4,'订单关闭:2222');

/*Table structure for table `oms_order_return_apply` */

DROP TABLE IF EXISTS `oms_order_return_apply`;

CREATE TABLE `oms_order_return_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `company_address_id` bigint(20) DEFAULT NULL COMMENT '收货地址表id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '退货商品id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '会员用户名',
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `return_name` varchar(100) DEFAULT NULL COMMENT '退货人姓名',
  `return_phone` varchar(100) DEFAULT NULL COMMENT '退货人电话',
  `status` int(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `product_pic` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `product_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `product_brand` varchar(200) DEFAULT NULL COMMENT '商品品牌',
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性：颜色：红色；尺码：xl;',
  `product_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `product_real_price` decimal(10,2) DEFAULT NULL COMMENT '商品实际支付单价',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `proof_pics` varchar(1000) DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
  `handle_note` varchar(500) DEFAULT NULL COMMENT '处理备注',
  `handle_man` varchar(100) DEFAULT NULL COMMENT '处理人员',
  `receive_man` varchar(100) DEFAULT NULL COMMENT '收货人',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_note` varchar(500) DEFAULT NULL COMMENT '收货备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='订单退货申请';

/*Data for the table `oms_order_return_apply` */

insert  into `oms_order_return_apply`(`id`,`order_id`,`company_address_id`,`product_id`,`order_sn`,`create_time`,`member_username`,`return_amount`,`return_name`,`return_phone`,`status`,`handle_time`,`product_pic`,`product_name`,`product_brand`,`product_attr`,`product_count`,`product_price`,`product_real_price`,`reason`,`description`,`proof_pics`,`handle_note`,`handle_man`,`receive_man`,`receive_time`,`receive_note`) values (3,12,1,26,'201809150101000001','2018-10-17 14:34:57','test','100000.00','大梨','18000000000',1,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg',NULL,'admin','admin',NULL,NULL),(4,12,2,27,'201809150101000001','2018-10-17 14:40:21','test','3585.98','大梨','18000000000',1,'2018-10-18 13:54:10','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','','已经处理了','admin',NULL,NULL,NULL),(5,12,3,28,'201809150101000001','2018-10-17 14:44:18','test','3585.98','大梨','18000000000',2,'2018-10-18 13:55:28','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','','已经处理了','admin','admin','2018-10-18 13:55:58','已经处理了'),(8,13,NULL,28,'201809150102000002','2018-10-17 14:44:18','test',NULL,'大梨','18000000000',3,'2018-10-18 13:57:12','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','','理由不够充分','admin',NULL,NULL,NULL),(9,14,2,26,'201809130101000001','2018-10-17 14:34:57','test','3500.00','大梨','18000000000',2,'2018-10-24 15:44:56','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','','呵呵','admin','admin','2018-10-24 15:46:35','收货了'),(10,14,NULL,27,'201809130101000001','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',3,'2018-10-24 15:46:57','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','','就是不退','admin',NULL,NULL,NULL),(11,14,2,28,'201809130101000001','2018-10-17 14:44:18','test','591.05','大梨','18000000000',1,'2018-10-24 17:09:04','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','','可以退款','admin',NULL,NULL,NULL),(12,15,3,26,'201809130102000002','2018-10-17 14:34:57','test','3500.00','大梨','18000000000',2,'2018-10-24 17:22:54','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','','退货了','admin','admin','2018-10-24 17:23:06','收货了'),(13,15,NULL,27,'201809130102000002','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',3,'2018-10-24 17:23:30','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','','无法退货','admin',NULL,NULL,NULL),(15,16,1,26,'201809140101000001','2018-10-17 14:34:57','test','0.00','大梨','18000000000',2,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','',NULL,'admin','admin',NULL,NULL),(16,16,NULL,27,'201809140101000001','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','',NULL,NULL,NULL,NULL,NULL),(17,16,NULL,28,'201809140101000001','2018-10-17 14:44:18','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','',NULL,NULL,NULL,NULL,NULL),(18,17,NULL,26,'201809150101000003','2018-10-17 14:34:57','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','',NULL,NULL,NULL,NULL,NULL),(19,17,NULL,27,'201809150101000003','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','',NULL,NULL,NULL,NULL,NULL),(20,17,NULL,28,'201809150101000003','2018-10-17 14:44:18','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','',NULL,NULL,NULL,NULL,NULL),(21,18,NULL,26,'201809150102000004','2018-10-17 14:34:57','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','',NULL,NULL,NULL,NULL,NULL),(22,18,NULL,27,'201809150102000004','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','',NULL,NULL,NULL,NULL,NULL),(23,18,NULL,28,'201809150102000004','2018-10-17 14:44:18','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','',NULL,NULL,NULL,NULL,NULL),(24,19,NULL,26,'201809130101000003','2018-10-17 14:34:57','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','华为 HUAWEI P20','华为','颜色：金色;内存：16G',1,'3788.00','3585.98','质量问题','老是卡','',NULL,NULL,NULL,NULL,NULL),(25,19,NULL,27,'201809130101000003','2018-10-17 14:40:21','test',NULL,'大梨','18000000000',0,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','小米8','小米','颜色：黑色;内存：32G',1,'2699.00','2022.81','质量问题','不够高端','',NULL,NULL,NULL,NULL,NULL),(26,19,1,28,'201809130101000003','2018-10-17 14:44:18','test','0.00','大梨','18000000000',1,NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','红米5A','小米','颜色：金色;内存：16G',1,'649.00','591.05','质量问题','颜色太土','',NULL,'admin','admin',NULL,NULL);

/*Table structure for table `oms_order_return_reason` */

DROP TABLE IF EXISTS `oms_order_return_reason`;

CREATE TABLE `oms_order_return_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '退货类型',
  `sort` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态：0->不启用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `num` int(11) DEFAULT '0' COMMENT '退回产品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='退货原因表';

/*Data for the table `oms_order_return_reason` */

insert  into `oms_order_return_reason`(`id`,`name`,`sort`,`status`,`create_time`,`num`) values (1,'质量问题',1,1,'2022-03-17 10:00:45',0),(2,'尺码太大',1,1,'2022-03-17 10:01:03',0),(3,'颜色不喜欢',1,1,'2022-03-17 10:01:13',0),(4,'7天无理由退货',1,1,'2022-03-17 10:01:47',0),(5,'价格问题',1,1,'2022-03-17 10:01:57',0),(12,'发票问题',0,1,'2022-03-19 16:28:36',0),(13,'其他问题',0,1,'2022-03-19 16:28:51',0),(14,'物流问题',0,1,'2022-03-19 16:29:01',0),(15,'售后问题',2,1,'2022-03-19 16:29:11',0);

/*Table structure for table `oms_order_setting` */

DROP TABLE IF EXISTS `oms_order_setting`;

CREATE TABLE `oms_order_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flash_order_overtime` int(11) DEFAULT NULL COMMENT '秒杀订单超时关闭时间(分)',
  `normal_order_overtime` int(11) DEFAULT NULL COMMENT '正常订单超时时间(分)',
  `confirm_overtime` int(11) DEFAULT NULL COMMENT '发货后自动确认收货时间（天）',
  `finish_overtime` int(11) DEFAULT NULL COMMENT '自动完成交易时间，不能申请售后（天）',
  `comment_overtime` int(11) DEFAULT NULL COMMENT '订单完成后自动好评时间（天）',
  `uid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='订单设置表';

/*Data for the table `oms_order_setting` */

insert  into `oms_order_setting`(`id`,`flash_order_overtime`,`normal_order_overtime`,`confirm_overtime`,`finish_overtime`,`comment_overtime`,`uid`) values (3,1,60,22,2,4,3),(4,1,1,1,1,1,7);

/*Table structure for table `pms_album` */

DROP TABLE IF EXISTS `pms_album`;

CREATE TABLE `pms_album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `cover_pic` varchar(1000) DEFAULT NULL,
  `pic_count` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='相册表';

/*Data for the table `pms_album` */

/*Table structure for table `pms_album_pic` */

DROP TABLE IF EXISTS `pms_album_pic`;

CREATE TABLE `pms_album_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `album_id` bigint(20) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画册图片表';

/*Data for the table `pms_album_pic` */

/*Table structure for table `pms_brand` */

DROP TABLE IF EXISTS `pms_brand`;

CREATE TABLE `pms_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `first_letter` varchar(8) DEFAULT NULL COMMENT '首字母',
  `sort` int(11) DEFAULT NULL,
  `factory_status` int(1) DEFAULT NULL COMMENT '是否为品牌制造商：0->不是；1->是',
  `show_status` int(1) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL COMMENT '产品数量',
  `product_comment_count` int(11) DEFAULT NULL COMMENT '产品评论数量',
  `logo` varchar(255) DEFAULT NULL COMMENT '品牌logo',
  `big_pic` varchar(255) DEFAULT NULL COMMENT '专区大图',
  `brand_story` text COMMENT '品牌故事',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='品牌表';

/*Data for the table `pms_brand` */

insert  into `pms_brand`(`id`,`name`,`first_letter`,`sort`,`factory_status`,`show_status`,`product_count`,`product_comment_count`,`logo`,`big_pic`,`brand_story`) values (1,'万和','W',0,1,0,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(5).jpg','','Victoria\'s Secret的故事'),(2,'三星','S',100,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (1).jpg',NULL,'三星的故事'),(3,'华为','H',100,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/17f2dd9756d9d333bee8e60ce8c03e4c_222_222.jpg',NULL,'Victoria\'s Secret的故事'),(4,'格力','G',30,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/dc794e7e74121272bbe3ce9bc41ec8c3_222_222.jpg',NULL,'Victoria\'s Secret的故事'),(5,'方太','F',20,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg (4).jpg',NULL,'Victoria\'s Secret的故事'),(6,'小米','M',500,1,1,100,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/1e34aef2a409119018a4c6258e39ecfb_222_222.png','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180518/5afd7778Nf7800b75.jpg','小米手机的故事'),(21,'OPPO','O',0,1,1,88,500,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg(6).jpg','','string'),(49,'七匹狼','S',200,1,1,77,400,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/18d8bc3eb13533fab466d702a0d3fd1f40345bcd.jpg',NULL,'BOOB的故事'),(50,'海澜之家','H',200,1,1,66,300,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/99d3279f1029d32b929343b09d3c72de_222_222.jpg','','海澜之家的故事'),(51,'苹果','A',200,1,1,55,200,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg',NULL,'苹果的故事'),(58,'NIKE','N',0,1,1,33,100,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/timg (51).jpg','','NIKE的故事');

/*Table structure for table `pms_comment` */

DROP TABLE IF EXISTS `pms_comment`;

CREATE TABLE `pms_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `star` int(3) DEFAULT NULL COMMENT '评价星数：0->5',
  `member_ip` varchar(64) DEFAULT NULL COMMENT '评价的ip',
  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  `product_attribute` varchar(255) DEFAULT NULL COMMENT '购买时的商品属性',
  `collect_couont` int(11) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `content` text,
  `pics` varchar(1000) DEFAULT NULL COMMENT '上传图片地址，以逗号隔开',
  `member_icon` varchar(255) DEFAULT NULL COMMENT '评论用户头像',
  `replay_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

/*Data for the table `pms_comment` */

/*Table structure for table `pms_comment_replay` */

DROP TABLE IF EXISTS `pms_comment_replay`;

CREATE TABLE `pms_comment_replay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT NULL,
  `member_nick_name` varchar(255) DEFAULT NULL,
  `member_icon` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '评论人员类型；0->会员；1->管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品评价回复表';

/*Data for the table `pms_comment_replay` */

/*Table structure for table `pms_feight_template` */

DROP TABLE IF EXISTS `pms_feight_template`;

CREATE TABLE `pms_feight_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `charge_type` int(1) DEFAULT NULL COMMENT '计费类型:0->按重量；1->按件数',
  `first_weight` decimal(10,2) DEFAULT NULL COMMENT '首重kg',
  `first_fee` decimal(10,2) DEFAULT NULL COMMENT '首费（元）',
  `continue_weight` decimal(10,2) DEFAULT NULL,
  `continme_fee` decimal(10,2) DEFAULT NULL,
  `dest` varchar(255) DEFAULT NULL COMMENT '目的地（省、市）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费模版';

/*Data for the table `pms_feight_template` */

/*Table structure for table `pms_member_price` */

DROP TABLE IF EXISTS `pms_member_price`;

CREATE TABLE `pms_member_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `member_level_id` bigint(20) DEFAULT NULL,
  `member_price` decimal(10,2) DEFAULT NULL COMMENT '会员价格',
  `member_level_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 COMMENT='商品会员价格表';

/*Data for the table `pms_member_price` */

insert  into `pms_member_price`(`id`,`product_id`,`member_level_id`,`member_price`,`member_level_name`) values (26,7,1,'500.00',NULL),(27,8,1,'500.00',NULL),(28,9,1,'500.00',NULL),(29,10,1,'500.00',NULL),(30,11,1,'500.00',NULL),(31,12,1,'500.00',NULL),(32,13,1,'500.00',NULL),(33,14,1,'500.00',NULL),(37,18,1,'500.00',NULL),(44,7,2,'480.00',NULL),(45,7,3,'450.00',NULL),(52,22,1,NULL,NULL),(53,22,2,NULL,NULL),(54,22,3,NULL,NULL),(58,24,1,NULL,NULL),(59,24,2,NULL,NULL),(60,24,3,NULL,NULL),(112,23,1,'88.00','黄金会员'),(113,23,2,'88.00','白金会员'),(114,23,3,'66.00','钻石会员'),(142,31,1,NULL,'黄金会员'),(143,31,2,NULL,'白金会员'),(144,31,3,NULL,'钻石会员'),(148,32,1,NULL,'黄金会员'),(149,32,2,NULL,'白金会员'),(150,32,3,NULL,'钻石会员'),(154,33,1,NULL,'黄金会员'),(155,33,2,NULL,'白金会员'),(156,33,3,NULL,'钻石会员'),(169,36,1,NULL,'黄金会员'),(170,36,2,NULL,'白金会员'),(171,36,3,NULL,'钻石会员'),(172,35,1,NULL,'黄金会员'),(173,35,2,NULL,'白金会员'),(174,35,3,NULL,'钻石会员'),(175,34,1,NULL,'黄金会员'),(176,34,2,NULL,'白金会员'),(177,34,3,NULL,'钻石会员'),(178,30,1,NULL,'黄金会员'),(179,30,2,NULL,'白金会员'),(180,30,3,NULL,'钻石会员'),(186,26,1,NULL,'黄金会员'),(187,26,2,NULL,'白金会员'),(188,26,3,NULL,'钻石会员'),(192,27,1,NULL,'黄金会员'),(193,27,2,NULL,'白金会员'),(194,27,3,NULL,'钻石会员'),(195,28,1,NULL,'黄金会员'),(196,28,2,NULL,'白金会员'),(197,28,3,NULL,'钻石会员'),(198,29,1,NULL,'黄金会员'),(199,29,2,NULL,'白金会员'),(200,29,3,NULL,'钻石会员'),(201,37,1,'999.00','黄金会员'),(202,37,2,'599.00','白金会员'),(203,37,3,'199.00','钻石会员');

/*Table structure for table `pms_product` */

DROP TABLE IF EXISTS `pms_product`;

CREATE TABLE `pms_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  `feight_template_id` bigint(20) DEFAULT NULL,
  `product_attribute_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `product_sn` varchar(64) NOT NULL COMMENT '货号',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
  `publish_status` int(1) DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) DEFAULT NULL COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) DEFAULT NULL COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) DEFAULT NULL COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  `price` decimal(10,2) DEFAULT NULL,
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '促销价格',
  `gift_growth` int(11) DEFAULT '0' COMMENT '赠送的成长值',
  `gift_point` int(11) DEFAULT '0' COMMENT '赠送的积分',
  `use_point_limit` int(11) DEFAULT NULL COMMENT '限制使用的积分数',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `description` text COMMENT '商品描述',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '库存预警值',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `weight` decimal(10,2) DEFAULT NULL COMMENT '商品重量，默认为克',
  `preview_status` int(1) DEFAULT NULL COMMENT '是否为预告商品：0->不是；1->是',
  `service_ids` varchar(64) DEFAULT NULL COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `album_pics` varchar(255) DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_title` varchar(255) DEFAULT NULL,
  `detail_desc` text,
  `detail_html` text COMMENT '产品详情网页内容',
  `detail_mobile_html` text COMMENT '移动端网页详情',
  `promotion_start_time` datetime DEFAULT NULL COMMENT '促销开始时间',
  `promotion_end_time` datetime DEFAULT NULL COMMENT '促销结束时间',
  `promotion_per_limit` int(11) DEFAULT NULL COMMENT '活动限购数量',
  `promotion_type` int(1) DEFAULT NULL COMMENT '促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='商品信息';

/*Data for the table `pms_product` */

insert  into `pms_product`(`id`,`brand_id`,`product_category_id`,`feight_template_id`,`product_attribute_category_id`,`name`,`pic`,`product_sn`,`delete_status`,`publish_status`,`new_status`,`recommand_status`,`verify_status`,`sort`,`sale`,`price`,`promotion_price`,`gift_growth`,`gift_point`,`use_point_limit`,`sub_title`,`description`,`original_price`,`stock`,`low_stock`,`unit`,`weight`,`preview_status`,`service_ids`,`keywords`,`note`,`album_pics`,`detail_title`,`detail_desc`,`detail_html`,`detail_mobile_html`,`promotion_start_time`,`promotion_end_time`,`promotion_per_limit`,`promotion_type`,`brand_name`,`product_category_name`) values (7,1,7,0,1,'女式超柔软拉毛运动开衫','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','No86577',0,1,1,1,0,0,0,'249.00','0.00',0,100,0,'匠心剪裁，垂感质地','匠心剪裁，垂感质地','299.00',606,0,'件','0.00',0,'1,2,3','女式超柔软拉毛运动开衫','string','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/sa.jpg,https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040621/a2.jpg','string','string','<p><img class=\"wscnph\" src=\"https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040621/a2.jpg\" /></p>','<p>string</p>','2018-04-26 10:41:03','2018-04-26 10:41:03',0,0,'万和','外套'),(26,3,19,0,3,'华为 HUAWEI P20 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','6946605',0,1,0,1,0,100,0,'3788.00',NULL,3788,3788,0,'信4G手机 双卡双待手机 双卡双待','','4288.00',1000,0,'件','0.00',1,'2,3,1','','','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg','','','<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44f1cNf51f3bb0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa8Nfcf71c10.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad44fa9N40e78ee0.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f4N1c94bdda.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ad457f5Nd30de41d.jpg\" /><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5b10fb0eN0eb053fb.jpg\" /></p>','',NULL,NULL,0,1,'华为','手机通讯'),(27,6,19,0,3,'小米8 全面屏游戏智能手机 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','7437788',0,1,1,1,0,0,0,'2699.00',NULL,2699,2699,0,'骁龙845处理器','小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待','2699.00',100,0,'','0.00',0,'1','','','','','','<p><img class=\"wscnph\" src=\"http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b2254e8N414e6d3a.jpg\" width=\"500\" /></p>','',NULL,NULL,0,3,'小米','手机通讯'),(28,6,19,0,3,'小米 红米5A 全网通','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','7437789',0,1,1,1,0,0,0,'649.00',NULL,649,649,0,'137g轻巧机身，高通骁龙处理器小米6X','','649.00',100,0,'','0.00',0,'2','','','','','','','',NULL,NULL,0,4,'小米','手机通讯'),(29,51,19,0,3,'Apple iPhone 8 Plus ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5acc5248N6a5f81cd.jpg','7437799',0,1,1,1,0,0,0,'5499.00',NULL,5499,5499,0,'【限时限量抢购】Apple产品年中狂欢节','','5499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'苹果','手机通讯'),(30,50,8,0,1,'HLA海澜之家简约动物印花短袖T恤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ad83a4fN6ff67ecd.jpg!cc_350x449.jpg','HNTBJ2E042A',0,1,0,1,0,0,0,'98.00',NULL,0,0,0,'即分享赢大奖','','98.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(31,50,8,0,1,'HLA海澜之家蓝灰花纹圆领针织布短袖T恤','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5ac98b64N70acd82f.jpg!cc_350x449.jpg','HNTBJ2E080A',0,1,0,1,0,0,0,'98.00',NULL,0,0,0,'80A 蓝灰花纹80 175/92A/L','','98.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(32,50,8,0,1,'HLA海澜之家短袖T恤男基础款','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a51eb88Na4797877.jpg','HNTBJ2E153A',0,1,0,1,0,0,0,'68.00',NULL,0,0,0,'恤男基础款简约圆领HNTBJ2E153A藏青','','68.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'海澜之家','T恤'),(33,6,35,0,3,'小米（MI）小米电视4A ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg','4609652',0,1,0,1,0,0,0,'2499.00',NULL,0,0,0,'小米（MI）小米电视4A 55英寸 K超高清 人工智能网络液晶平板电视','','2499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'小米','手机数码'),(34,6,35,0,3,'小米（MI）小米电视4A 65英寸','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b028530N51eee7d4.jpg','4609660',0,1,0,1,0,0,0,'3999.00',NULL,0,0,0,' 4K超高清 人工智能网络液晶平板电视','','3999.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'小米','电视'),(35,58,29,0,2,'耐克NIKE 男子 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b235bb9Nf606460b.jpg','6799342',0,1,0,1,0,0,0,'369.00',NULL,0,0,0,' 休闲鞋 ROSHE RUN 运动鞋 -010黑色41码','','369.00',100,0,'','0.00',0,'3','','','','','','<p>啊啊啊</p>','',NULL,NULL,0,0,'NIKE','男鞋'),(36,58,29,0,2,'耐克NIKE 男子 ','http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b19403eN9f0b3cb8.jpg','6799345',0,1,0,1,0,0,0,'499.00',NULL,0,0,0,' AJ1285-101白色41码','','499.00',100,0,'','0.00',0,'','','','','','','','',NULL,NULL,0,0,'NIKE','男鞋');

/*Table structure for table `pms_product_attribute` */

DROP TABLE IF EXISTS `pms_product_attribute`;

CREATE TABLE `pms_product_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_attribute_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `select_type` int(1) DEFAULT NULL COMMENT '属性选择类型：0->唯一；1->单选；2->多选',
  `input_type` int(1) DEFAULT NULL COMMENT '属性录入方式：0->手工录入；1->从列表中选取',
  `input_list` varchar(255) DEFAULT NULL COMMENT '可选值列表，以逗号隔开',
  `sort` int(11) DEFAULT NULL COMMENT '排序字段：最高的可以单独上传图片',
  `filter_type` int(1) DEFAULT NULL COMMENT '分类筛选样式：1->普通；1->颜色',
  `search_type` int(1) DEFAULT NULL COMMENT '检索类型；0->不需要进行检索；1->关键字检索；2->范围检索',
  `related_status` int(1) DEFAULT NULL COMMENT '相同属性产品是否关联；0->不关联；1->关联',
  `hand_add_status` int(1) DEFAULT NULL COMMENT '是否支持手动新增；0->不支持；1->支持',
  `type` int(1) DEFAULT NULL COMMENT '属性的类型；0->规格；1->参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='商品属性参数表';

/*Data for the table `pms_product_attribute` */

insert  into `pms_product_attribute`(`id`,`product_attribute_category_id`,`name`,`select_type`,`input_type`,`input_list`,`sort`,`filter_type`,`search_type`,`related_status`,`hand_add_status`,`type`) values (1,1,'尺寸',2,1,'M,X,XL,2XL,3XL,4XL,8XL',0,0,0,0,0,0),(7,1,'颜色',2,1,'黑色,红色,白色,粉色',100,0,0,0,1,0),(13,0,'上市年份',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(14,0,'上市年份1',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(15,0,'上市年份2',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(16,0,'上市年份3',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(17,0,'上市年份4',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(18,0,'上市年份5',1,1,'2013年,2014年,2015年,2016年,2017年',0,0,0,0,0,1),(19,0,'适用对象',1,1,'青年女性,中年女性',0,0,0,0,0,1),(20,0,'适用对象1',2,1,'青年女性,中年女性',0,0,0,0,0,1),(21,0,'适用对象3',2,1,'青年女性,中年女性',0,0,0,0,0,1),(24,1,'商品编号',1,0,'',0,0,0,0,0,1),(25,1,'适用季节',1,1,'春季,夏季,秋季,冬季',0,0,0,0,0,1),(32,2,'适用人群',0,1,'老年,青年,中年',0,0,0,0,0,1),(33,2,'风格',0,1,'嘻哈风格,基础大众,商务正装',0,0,0,0,0,1),(35,2,'颜色',0,0,'',100,0,0,0,1,0),(37,1,'适用人群',1,1,'儿童,青年,中年,老年',0,0,0,0,0,1),(38,1,'上市时间',1,1,'2017年秋,2017年冬,2018年春,2018年夏',0,0,0,0,0,1),(39,1,'袖长',1,1,'短袖,长袖,中袖',0,0,0,0,0,1),(40,2,'尺码',0,1,'29,30,31,32,33,34',0,0,0,0,0,0),(41,2,'适用场景',0,1,'居家,运动,正装',0,0,0,0,0,1),(42,2,'上市时间',0,1,'2018年春,2018年夏',0,0,0,0,0,1),(43,3,'颜色',0,0,'',100,0,0,0,1,0),(44,3,'容量',0,1,'16G,32G,64G,128G',0,0,0,0,0,0),(45,3,'屏幕尺寸',0,0,'',0,0,0,0,0,1),(46,3,'网络',0,1,'3G,4G',0,0,0,0,0,1),(47,3,'系统',0,1,'Android,IOS',0,0,0,0,0,1),(48,3,'电池容量',0,0,'',0,0,0,0,0,1),(51,5,'a1',0,0,'',0,0,0,0,1,0),(52,5,'a2',0,0,'',0,0,0,0,1,0),(53,5,'a3',0,0,'',0,0,0,0,1,0),(54,5,'a4',0,0,'',0,0,0,0,1,0),(55,5,'a5',0,0,'',0,0,0,0,1,0);

/*Table structure for table `pms_product_attribute_category` */

DROP TABLE IF EXISTS `pms_product_attribute_category`;

CREATE TABLE `pms_product_attribute_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `attribute_count` int(11) DEFAULT '0' COMMENT '属性数量',
  `param_count` int(11) DEFAULT '0' COMMENT '参数数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品属性分类表';

/*Data for the table `pms_product_attribute_category` */

insert  into `pms_product_attribute_category`(`id`,`name`,`attribute_count`,`param_count`) values (1,'服装-T恤',2,5),(2,'服装-裤装',2,4),(3,'手机数码-手机通讯',2,4),(4,'配件',0,0),(5,'居家',5,0),(6,'洗护',0,0),(10,'测试',0,0);

/*Table structure for table `pms_product_attribute_value` */

DROP TABLE IF EXISTS `pms_product_attribute_value`;

CREATE TABLE `pms_product_attribute_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_attribute_id` bigint(20) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL COMMENT '手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8 COMMENT='存储产品参数信息的表';

/*Data for the table `pms_product_attribute_value` */

insert  into `pms_product_attribute_value`(`id`,`product_id`,`product_attribute_id`,`value`) values (1,9,1,'X'),(2,10,1,'X'),(3,11,1,'X'),(4,12,1,'X'),(5,13,1,'X'),(6,14,1,'X'),(7,18,1,'X'),(11,22,7,'x,xx'),(12,22,24,'no110'),(13,22,25,'春季'),(14,22,37,'青年'),(15,22,38,'2018年春'),(16,22,39,'长袖'),(124,23,7,'米白色,浅黄色'),(125,23,24,'no1098'),(126,23,25,'春季'),(127,23,37,'青年'),(128,23,38,'2018年春'),(129,23,39,'长袖'),(130,1,13,NULL),(131,1,14,NULL),(132,1,15,NULL),(133,1,16,NULL),(134,1,17,NULL),(135,1,18,NULL),(136,1,19,NULL),(137,1,20,NULL),(138,1,21,NULL),(139,2,13,NULL),(140,2,14,NULL),(141,2,15,NULL),(142,2,16,NULL),(143,2,17,NULL),(144,2,18,NULL),(145,2,19,NULL),(146,2,20,NULL),(147,2,21,NULL),(228,37,35,'黑色,红色'),(229,37,32,'青年'),(230,37,33,'嘻哈风格'),(231,37,41,'运动'),(232,37,42,'2018年夏'),(279,38,7,'red'),(280,38,24,'111111'),(281,38,25,'春季'),(282,38,37,'儿童'),(283,38,38,'2018年夏'),(284,38,39,'短袖'),(285,27,43,'黑色,蓝色'),(286,27,45,'5.8'),(287,27,46,'4G'),(288,27,47,'Android'),(289,27,48,'3000ml'),(301,40,51,'1,2'),(302,40,52,'a,b'),(303,40,53,'@'),(304,40,54,'!!'),(305,40,55,'~~'),(328,36,35,'白'),(329,36,32,'老年'),(330,36,33,'嘻哈风格'),(331,36,41,'居家'),(332,36,42,'2018年夏'),(333,35,35,'黑'),(334,35,32,NULL),(335,35,33,NULL),(336,35,41,NULL),(337,35,42,NULL),(343,28,43,'金色,银色'),(344,28,45,'5.0'),(345,28,46,'4G'),(346,28,47,'Android'),(347,28,48,'2800ml'),(353,29,43,'金色,银色'),(354,29,45,'4.7'),(355,29,46,'4G'),(356,29,47,'IOS'),(357,29,48,'1960ml'),(358,31,24,NULL),(359,31,25,'夏季'),(360,31,37,'青年'),(361,31,38,'2018年夏'),(362,31,39,'短袖'),(363,30,24,NULL),(364,30,25,'夏季'),(365,30,37,'青年'),(366,30,38,'2018年夏'),(367,30,39,'短袖'),(368,32,24,'88'),(369,32,25,'春季'),(370,32,37,'儿童'),(371,32,38,'2017年秋'),(372,32,39,'中袖'),(373,33,45,NULL),(374,33,46,NULL),(375,33,47,NULL),(376,33,48,NULL),(377,34,45,NULL),(378,34,46,NULL),(379,34,47,NULL),(380,34,48,NULL),(404,26,43,'金色,银色'),(405,26,45,'5.0'),(406,26,46,'4G'),(407,26,47,'Android'),(408,26,48,'3000'),(409,7,7,'红色,黑色'),(410,7,24,'12'),(411,7,25,'春季'),(412,7,37,'儿童'),(413,7,38,'2017年秋'),(414,7,39,'短袖');

/*Table structure for table `pms_product_category` */

DROP TABLE IF EXISTS `pms_product_category`;

CREATE TABLE `pms_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
  `name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
  `product_count` int(11) DEFAULT NULL,
  `product_unit` varchar(64) DEFAULT NULL,
  `nav_status` int(1) DEFAULT NULL COMMENT '是否显示在导航栏：0->不显示；1->显示',
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `keywords` varchar(255) DEFAULT NULL,
  `description` text COMMENT '描述',
  `enable` varchar(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='产品分类';

/*Data for the table `pms_product_category` */

insert  into `pms_product_category`(`id`,`parent_id`,`name`,`level`,`product_count`,`product_unit`,`nav_status`,`show_status`,`sort`,`icon`,`keywords`,`description`,`enable`) values (1,0,'服装',0,100,'件',1,1,2,NULL,'服装','服装分类','0'),(2,0,'手机数码',0,100,'件',1,1,1,NULL,'手机数码','手机数码','0'),(3,0,'家用电器',0,100,'件',1,1,1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jiadian.png','家用电器','家用电器','0'),(4,0,'家具家装',0,100,'件',1,1,1,NULL,'家具家装','家具家装','0'),(5,0,'汽车用品',0,100,'件',1,1,1,NULL,'汽车用品','汽车用品','0'),(6,0,'食品',0,100,'件',1,1,1,'','食品','食品，生活用品','0'),(7,1,'外套',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_waitao.png','外套','外套','0'),(8,1,'T恤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_tshirt.png','T恤','T恤','0'),(9,1,'休闲裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xiuxianku.png','休闲裤','休闲裤','0'),(10,1,'牛仔裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_niuzaiku.png','牛仔裤','牛仔裤','0'),(11,1,'衬衫',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_chenshan.png','衬衫','衬衫分类','0'),(13,12,'家电子分类1',1,1,'string',0,1,0,'string','string','string','0'),(14,12,'家电子分类2',1,1,'string',0,1,0,'string','string','string','0'),(19,2,'手机通讯',1,0,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_shouji.png','手机通讯','手机通讯','0'),(29,1,'男鞋',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xie.png','','','0'),(30,2,'手机配件',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_peijian.png','手机配件','手机配件','0'),(31,2,'摄影摄像',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_sheying.png','','','0'),(32,2,'影音娱乐',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','','','0'),(33,2,'数码配件',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png','','','0'),(34,2,'智能设备',1,0,'',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_zhineng.png','','','0'),(35,3,'电视',1,0,'',1,1,0,'','','','0'),(36,3,'空调',1,0,'',1,1,0,'','','','0'),(37,3,'洗衣机',1,0,'',1,1,0,'','','','0'),(38,3,'冰箱',1,0,'',1,1,0,'','','','0'),(39,3,'厨卫大电',1,0,'',1,1,0,'','','','0'),(40,3,'厨房小电',1,0,'',0,0,0,'','','','0'),(41,3,'生活电器',1,0,'',0,0,0,'','','','0'),(42,3,'个护健康',1,0,'',0,0,0,'','','','0'),(43,4,'厨房卫浴',1,0,'',1,1,0,'','','','0'),(44,4,'灯饰照明',1,0,'',1,1,0,'','','','0'),(45,4,'五金工具',1,0,'',1,1,0,'','','','0'),(46,4,'卧室家具',1,0,'',1,1,0,'','','','0'),(47,4,'客厅家具',1,0,'',1,1,0,'','','','0'),(48,5,'全新整车',1,0,'',1,1,0,'','','','0'),(49,5,'车载电器',1,0,'',1,1,0,'','','','0'),(50,5,'维修保养',1,0,'',1,1,0,'','','','0'),(51,5,'汽车装饰',1,0,'',1,1,0,'','','','0'),(53,5,'轮胎',1,0,'个',0,1,0,'','胎','汽车用品','0'),(57,6,'饮料',1,0,'瓶、杯',0,1,0,'','牛奶、可乐','饮料','0'),(58,0,'文具',0,0,'件',1,1,4,'','文具、学习用品','学习用品','0');

/*Table structure for table `pms_product_category_attribute_relation` */

DROP TABLE IF EXISTS `pms_product_category_attribute_relation`;

CREATE TABLE `pms_product_category_attribute_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_category_id` bigint(20) DEFAULT NULL,
  `product_attribute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）';

/*Data for the table `pms_product_category_attribute_relation` */

insert  into `pms_product_category_attribute_relation`(`id`,`product_category_id`,`product_attribute_id`) values (1,24,24),(5,26,24),(7,28,24),(9,25,24),(10,25,25);

/*Table structure for table `pms_product_full_reduction` */

DROP TABLE IF EXISTS `pms_product_full_reduction`;

CREATE TABLE `pms_product_full_reduction` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `full_price` decimal(10,2) DEFAULT NULL,
  `reduce_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='产品满减表(只针对同商品)';

/*Data for the table `pms_product_full_reduction` */

insert  into `pms_product_full_reduction`(`id`,`product_id`,`full_price`,`reduce_price`) values (1,7,'100.00','20.00'),(2,8,'100.00','20.00'),(3,9,'100.00','20.00'),(4,10,'100.00','20.00'),(5,11,'100.00','20.00'),(6,12,'100.00','20.00'),(7,13,'100.00','20.00'),(8,14,'100.00','20.00'),(9,18,'100.00','20.00'),(10,7,'200.00','50.00'),(11,7,'300.00','100.00'),(14,22,'0.00','0.00'),(16,24,'0.00','0.00'),(34,23,'0.00','0.00'),(44,31,'0.00','0.00'),(46,32,'0.00','0.00'),(48,33,'0.00','0.00'),(53,36,'0.00','0.00'),(54,35,'0.00','0.00'),(55,34,'0.00','0.00'),(56,30,'0.00','0.00'),(57,26,'0.00','0.00'),(59,27,'0.00','0.00'),(62,29,'0.00','0.00'),(63,37,'0.00','0.00'),(64,28,'500.00','50.00'),(65,28,'1000.00','120.00');

/*Table structure for table `pms_product_ladder` */

DROP TABLE IF EXISTS `pms_product_ladder`;

CREATE TABLE `pms_product_ladder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '满足的商品数量',
  `discount` decimal(10,2) DEFAULT NULL COMMENT '折扣',
  `price` decimal(10,2) DEFAULT NULL COMMENT '折后价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='产品阶梯价格表(只针对同商品)';

/*Data for the table `pms_product_ladder` */

insert  into `pms_product_ladder`(`id`,`product_id`,`count`,`discount`,`price`) values (1,7,3,'0.70','0.00'),(2,8,3,'0.70','0.00'),(3,9,3,'0.70','0.00'),(4,10,3,'0.70','0.00'),(5,11,3,'0.70','0.00'),(6,12,3,'0.70','0.00'),(7,13,3,'0.70','0.00'),(8,14,3,'0.70','0.00'),(12,18,3,'0.70','0.00'),(14,7,4,'0.60','0.00'),(15,7,5,'0.50','0.00'),(18,22,0,'0.00','0.00'),(20,24,0,'0.00','0.00'),(48,31,0,'0.00','0.00'),(50,32,0,'0.00','0.00'),(52,33,0,'0.00','0.00'),(57,36,0,'0.00','0.00'),(58,35,0,'0.00','0.00'),(59,34,0,'0.00','0.00'),(60,30,0,'0.00','0.00'),(61,26,0,'0.00','0.00'),(66,28,0,'0.00','0.00'),(67,29,0,'0.00','0.00'),(68,37,0,'0.00','0.00'),(69,38,11,'20.00','0.00'),(76,38,11,'20.00','0.00'),(77,38,11,'20.00','0.00'),(78,38,11,'20.00','0.00'),(79,27,2,'0.80','0.00'),(80,27,3,'0.75','0.00'),(81,27,2,'0.80','0.00'),(82,27,3,'0.75','0.00'),(83,27,2,'0.80','0.00'),(84,27,3,'0.75','0.00'),(85,27,2,'0.80','0.00'),(86,27,3,'0.75','0.00');

/*Table structure for table `pms_product_operate_log` */

DROP TABLE IF EXISTS `pms_product_operate_log`;

CREATE TABLE `pms_product_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `price_old` decimal(10,2) DEFAULT NULL,
  `price_new` decimal(10,2) DEFAULT NULL,
  `sale_price_old` decimal(10,2) DEFAULT NULL,
  `sale_price_new` decimal(10,2) DEFAULT NULL,
  `gift_point_old` int(11) DEFAULT NULL COMMENT '赠送的积分',
  `gift_point_new` int(11) DEFAULT NULL,
  `use_point_limit_old` int(11) DEFAULT NULL,
  `use_point_limit_new` int(11) DEFAULT NULL,
  `operate_man` varchar(64) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pms_product_operate_log` */

/*Table structure for table `pms_product_vertify_record` */

DROP TABLE IF EXISTS `pms_product_vertify_record`;

CREATE TABLE `pms_product_vertify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `vertify_man` varchar(64) DEFAULT NULL COMMENT '审核人',
  `status` int(1) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL COMMENT '反馈详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品审核记录';

/*Data for the table `pms_product_vertify_record` */

insert  into `pms_product_vertify_record`(`id`,`product_id`,`create_time`,`vertify_man`,`status`,`detail`) values (1,1,'2018-04-27 16:36:41','test',1,'验证通过'),(2,2,'2018-04-27 16:36:41','test',1,'验证通过');

/*Table structure for table `pms_sku_stock` */

DROP TABLE IF EXISTS `pms_sku_stock`;

CREATE TABLE `pms_sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `sku_code` varchar(64) NOT NULL COMMENT 'sku编码',
  `price` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `low_stock` int(11) DEFAULT NULL COMMENT '预警库存',
  `sp1` varchar(64) DEFAULT NULL COMMENT '销售属性1',
  `sp2` varchar(64) DEFAULT NULL,
  `sp3` varchar(64) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL COMMENT '展示图片',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  `promotion_price` decimal(10,2) DEFAULT NULL COMMENT '单品促销价格',
  `lock_stock` int(11) DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8 COMMENT='sku的库存';

/*Data for the table `pms_sku_stock` */

insert  into `pms_sku_stock`(`id`,`product_id`,`sku_code`,`price`,`stock`,`low_stock`,`sp1`,`sp2`,`sp3`,`pic`,`sale`,`promotion_price`,`lock_stock`) values (2,8,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(3,9,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(4,10,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(5,11,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(6,12,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(7,13,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(8,14,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(9,18,'string','100.00',0,5,'string','string','string','string',0,NULL,0),(12,22,'111','99.00',0,NULL,'x','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(13,22,'112','99.00',0,NULL,'xx','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2018032614134591_20180326141345650 (4).png',NULL,NULL,0),(78,23,'201806070023001','99.00',0,NULL,'米白色','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(79,23,'201806070023002','99.00',0,NULL,'米白色','X',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/1522738681.jpg',NULL,NULL,0),(80,23,'201806070023003','99.00',0,NULL,'浅黄色','M',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2017091716493787_20170917164937650 (1).png',NULL,NULL,0),(81,23,'201806070023004','99.00',0,NULL,'浅黄色','X',NULL,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180604/2017091716493787_20170917164937650 (1).png',NULL,NULL,0),(110,37,'10','199.00',4,7,'29','黑色',NULL,NULL,NULL,NULL,0),(111,37,'11','199.00',5,7,'29','红色',NULL,NULL,NULL,NULL,0),(156,38,'11','12.00',12,12,'X','red',NULL,NULL,NULL,NULL,0),(157,27,'201808270027001','2699.00',97,NULL,'黑色','32G',NULL,NULL,NULL,NULL,-24),(158,27,'201808270027002','2999.00',100,NULL,'黑色','64G',NULL,NULL,NULL,NULL,0),(159,27,'201808270027003','2699.00',100,NULL,'蓝色','32G',NULL,NULL,NULL,NULL,0),(160,27,'201808270027004','2999.00',100,NULL,'蓝色','64G',NULL,NULL,NULL,NULL,0),(161,27,'201808270027001','2699.00',97,NULL,'黑色','32G',NULL,NULL,NULL,NULL,-24),(162,27,'201808270027002','2999.00',100,NULL,'黑色','64G',NULL,NULL,NULL,NULL,0),(163,27,'201808270027003','2699.00',100,NULL,'蓝色','32G',NULL,NULL,NULL,NULL,0),(164,27,'201808270027004','2999.00',100,NULL,'蓝色','64G',NULL,NULL,NULL,NULL,0),(165,27,'201808270027001','2699.00',97,NULL,'黑色','32G',NULL,NULL,NULL,NULL,-24),(166,27,'201808270027002','2999.00',100,NULL,'黑色','64G',NULL,NULL,NULL,NULL,0),(167,27,'201808270027003','2699.00',100,NULL,'蓝色','32G',NULL,NULL,NULL,NULL,0),(168,27,'201808270027004','2999.00',100,NULL,'蓝色','64G',NULL,NULL,NULL,NULL,0),(169,27,'201808270027001','2699.00',97,NULL,'黑色','32G',NULL,NULL,NULL,NULL,-24),(170,27,'201808270027002','2999.00',100,NULL,'黑色','64G',NULL,NULL,NULL,NULL,0),(171,27,'201808270027003','2699.00',100,NULL,'蓝色','32G',NULL,NULL,NULL,NULL,0),(172,27,'201808270027004','2999.00',100,NULL,'蓝色','64G',NULL,NULL,NULL,NULL,0),(183,40,'a',NULL,0,NULL,'1','a','@',NULL,NULL,NULL,0),(184,40,'s',NULL,0,NULL,'1','b','@',NULL,NULL,NULL,0),(185,40,'d',NULL,0,NULL,'2','a','@',NULL,NULL,NULL,0),(186,40,'f',NULL,0,NULL,'2','b','@',NULL,NULL,NULL,0),(199,36,'22','33.00',50,NULL,'29','白',NULL,NULL,NULL,NULL,0),(200,36,'22','33.00',50,NULL,'29','白',NULL,NULL,NULL,NULL,0),(201,35,'1222','29.00',54,NULL,'30','黑',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040620/f.jpg',NULL,NULL,0),(206,28,'201808270028001','649.00',97,NULL,'金色','16G',NULL,NULL,NULL,NULL,-9),(207,28,'201808270028002','699.00',99,NULL,'金色','32G',NULL,NULL,NULL,NULL,-8),(208,28,'201808270028003','649.00',100,NULL,'银色','16G',NULL,NULL,NULL,NULL,1),(209,28,'201808270028004','699.00',100,NULL,'银色','32G',NULL,NULL,NULL,NULL,0),(219,29,'201808270029001','5499.00',99,NULL,'金色','32G',NULL,NULL,NULL,NULL,-8),(220,29,'201808270029002','6299.00',100,NULL,'金色','64G',NULL,NULL,NULL,NULL,1),(221,29,'201808270029003','5499.00',100,NULL,'银色','32G',NULL,NULL,NULL,NULL,0),(222,29,'201808270029004','6299.00',100,NULL,'银色','64G',NULL,NULL,NULL,NULL,0),(223,31,'9','22.00',200,NULL,'M',NULL,NULL,NULL,NULL,NULL,0),(224,30,'99','122.00',4,NULL,'M',NULL,NULL,NULL,NULL,NULL,0),(225,32,'0','6000.00',30,NULL,'M',NULL,NULL,NULL,NULL,NULL,0),(226,33,'0','2999.00',100,NULL,'16G',NULL,NULL,NULL,NULL,NULL,0),(227,34,'0','8999.00',20,NULL,'16G',NULL,NULL,NULL,NULL,NULL,0),(245,26,'1','4999.00',20,50,'金色','16G','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/1.jpg',NULL,NULL,1),(246,26,'2','4999.00',20,50,'金色','32G','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/1.jpg',NULL,NULL,0),(247,26,'3','4999.00',20,50,'金色','s','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/1.jpg',NULL,NULL,0),(248,26,'4','4999.00',20,50,'银色','16G','',NULL,NULL,NULL,0),(249,26,'5','4999.00',20,50,'银色','32G','',NULL,NULL,NULL,0),(250,26,'6','4999.00',20,50,'银色','s','',NULL,NULL,NULL,0),(251,26,'7','4999.00',20,50,'16G','16G','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/19fefd.jpg',NULL,NULL,0),(252,26,'7','4999.00',20,50,'16G','32G','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/19fefd.jpg',NULL,NULL,0),(253,26,'8','4999.00',20,50,'16G','s','','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/19fefd.jpg',NULL,NULL,0),(254,7,'NO.911','199.00',100,20,'M','黑色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg',NULL,NULL,1),(255,7,'NO.911','199.00',50,20,'M','红色',NULL,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg',NULL,NULL,1),(256,7,'NO.911','199.00',100,20,'X','黑色',NULL,NULL,NULL,NULL,1),(257,7,'NO.911','199.00',99,20,'X','红色',NULL,NULL,NULL,NULL,1),(258,7,'NO.911','199.00',100,20,'XL','黑色',NULL,'',NULL,NULL,1),(259,7,'NO.911','199.00',100,20,'XL','红色',NULL,'',NULL,NULL,1);

/*Table structure for table `sms_coupon` */

DROP TABLE IF EXISTS `sms_coupon`;

CREATE TABLE `sms_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '优惠卷类型；0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券',
  `name` varchar(100) DEFAULT NULL,
  `platform` int(1) DEFAULT NULL COMMENT '使用平台：0->全部；1->移动；2->PC',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `per_limit` int(11) DEFAULT NULL COMMENT '每人限领张数',
  `min_point` decimal(10,2) DEFAULT NULL COMMENT '使用门槛；0表示无门槛',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `use_type` int(1) DEFAULT NULL COMMENT '使用类型：0->全场通用；1->指定分类；2->指定商品',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `publish_count` int(11) DEFAULT NULL COMMENT '发行数量',
  `use_count` int(11) DEFAULT NULL COMMENT '已使用数量',
  `receive_count` int(11) DEFAULT NULL COMMENT '领取数量',
  `enable_time` datetime DEFAULT NULL COMMENT '可以领取的日期',
  `code` varchar(64) DEFAULT NULL COMMENT '优惠码',
  `member_level` int(1) DEFAULT NULL COMMENT '可领取的会员类型：0->无限时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='优惠卷表';

/*Data for the table `sms_coupon` */

insert  into `sms_coupon`(`id`,`type`,`name`,`platform`,`count`,`amount`,`per_limit`,`min_point`,`start_time`,`end_time`,`use_type`,`note`,`publish_count`,`use_count`,`receive_count`,`enable_time`,`code`,`member_level`) values (2,0,'全品类通用券',0,92,'10.00',1,'100.00','2018-08-27 16:40:47','2018-11-23 16:40:47',0,'满100减10',100,0,8,'2018-08-27 16:40:47',NULL,NULL),(3,0,'小米手机专用券',0,92,'50.00',1,'1000.00','2018-08-27 16:40:47','2018-11-16 16:40:47',2,'小米手机专用优惠券',100,0,8,'2018-08-27 16:40:47',NULL,NULL),(4,0,'手机品类专用券',0,92,'300.00',1,'2000.00','2018-08-27 16:40:47','2018-09-15 16:40:47',1,'手机分类专用优惠券',100,0,8,'2018-08-27 16:40:47',NULL,NULL),(7,0,'T恤分类专用优惠券',0,93,'50.00',1,'500.00','2018-08-27 16:40:47','2018-08-15 16:40:47',1,'满500减50',100,0,7,'2018-08-27 16:40:47',NULL,NULL),(8,0,'新优惠券',0,100,'100.00',1,'1000.00','2018-11-08 00:00:00','2018-11-27 00:00:00',0,'测试',100,0,1,NULL,NULL,NULL),(9,0,'全品类通用券',0,100,'5.00',1,'100.00','2018-11-08 00:00:00','2018-11-10 00:00:00',0,NULL,100,0,1,NULL,NULL,NULL),(10,0,'全品类通用券',0,100,'15.00',1,'200.00','2018-11-08 00:00:00','2018-11-10 00:00:00',0,NULL,100,0,1,NULL,NULL,NULL),(11,0,'全品类通用券',0,1000,'50.00',1,'1000.00','2018-11-08 00:00:00','2018-11-10 00:00:00',0,NULL,1000,0,0,NULL,NULL,NULL),(12,0,'移动端全品类通用券',1,1,'10.00',1,'100.00','2018-11-08 00:00:00','2018-11-10 00:00:00',0,NULL,100,0,0,NULL,NULL,NULL),(19,0,'手机分类专用',0,100,'100.00',1,'1000.00','2018-11-09 00:00:00','2018-11-17 00:00:00',1,'手机分类专用',100,0,0,NULL,NULL,NULL),(20,0,'小米手机专用',0,100,'200.00',1,'1000.00','2018-11-09 00:00:00','2018-11-24 00:00:00',2,'小米手机专用',100,0,0,NULL,NULL,NULL),(22,0,'新手礼包',0,NULL,'10.00',1,'10.10','2022-03-23 00:00:00','2023-03-30 00:00:00',2,'新人优惠价',20,NULL,NULL,NULL,NULL,NULL),(29,0,'会员牛牛券',0,NULL,'2000.00',1,'10000.00','2022-03-25 00:00:00','2023-03-31 00:00:00',2,'谢谢惠顾',100,NULL,NULL,NULL,NULL,NULL),(30,0,'帅哥专用券',0,100,'10.00',1,'100.00','2018-11-09 00:00:00','2018-11-30 00:00:00',1,NULL,100,0,0,NULL,NULL,NULL),(31,0,'xxxx',0,NULL,'222.00',1,'21.00','2022-03-31 00:00:00','2022-03-01 00:00:00',1,NULL,1,NULL,NULL,NULL,NULL,NULL),(32,0,'sssssssssssss',0,NULL,'21.00',1,'211.00','2022-04-06 00:00:00','2022-05-13 00:00:00',2,'ggg',2,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sms_coupon_history` */

DROP TABLE IF EXISTS `sms_coupon_history`;

CREATE TABLE `sms_coupon_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `coupon_code` varchar(64) DEFAULT NULL,
  `member_nickname` varchar(64) DEFAULT NULL COMMENT '领取人昵称',
  `get_type` int(1) DEFAULT NULL COMMENT '获取类型：0->后台赠送；1->主动获取',
  `create_time` datetime DEFAULT NULL,
  `use_status` int(1) DEFAULT NULL COMMENT '使用状态：0->未使用；1->已使用；2->已过期',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单编号',
  `order_sn` varchar(100) DEFAULT NULL COMMENT '订单号码',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`) USING BTREE,
  KEY `idx_coupon_id` (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='优惠券使用、领取历史表';

/*Data for the table `sms_coupon_history` */

insert  into `sms_coupon_history`(`id`,`coupon_id`,`member_id`,`coupon_code`,`member_nickname`,`get_type`,`create_time`,`use_status`,`use_time`,`order_id`,`order_sn`) values (2,2,1,'4931048380330002','windir',1,'2018-08-29 14:04:12',0,'2019-03-21 15:03:40',12,'212144433'),(3,3,1,'4931048380330003','windir',1,'2018-08-29 14:04:29',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(4,4,1,'4931048380330004','windir',1,'2018-08-29 14:04:32',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(11,7,1,'4931048380330001','windir',1,'2018-09-04 16:21:50',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(12,2,4,'0340981248320004','zhensan',1,'2018-11-12 14:16:50',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(13,3,4,'0342977234360004','zhensan',1,'2018-11-12 14:17:10',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(14,4,4,'0343342928830004','zhensan',1,'2018-11-12 14:17:13',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(15,2,5,'0351883832180005','lisi',1,'2018-11-12 14:18:39',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(16,3,5,'0352201672680005','lisi',1,'2018-11-12 14:18:42',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(17,4,5,'0352505810180005','lisi',1,'2018-11-12 14:18:45',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(18,2,6,'0356114588380006','wangwu',1,'2018-11-12 14:19:21',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(19,3,6,'0356382856920006','wangwu',1,'2018-11-12 14:19:24',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(20,4,6,'0356656798470006','wangwu',1,'2018-11-12 14:19:27',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(21,2,3,'0363644984620003','windy',1,'2018-11-12 14:20:36',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(22,3,3,'0363932820300003','windy',1,'2018-11-12 14:20:39',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(23,4,3,'0364238275840003','windy',1,'2018-11-12 14:20:42',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(24,2,7,'0385034833070007','lion',1,'2018-11-12 14:24:10',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(25,3,7,'0385350208650007','lion',1,'2018-11-12 14:24:13',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(26,4,7,'0385632733900007','lion',1,'2018-11-12 14:24:16',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(27,2,8,'0388779132990008','shari',1,'2018-11-12 14:24:48',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(28,3,8,'0388943658810008','shari',1,'2018-11-12 14:24:49',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(29,4,8,'0389069398320008','shari',1,'2018-11-12 14:24:51',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(30,2,9,'0390753935250009','aewen',1,'2018-11-12 14:25:08',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(31,3,9,'0390882954470009','aewen',1,'2018-11-12 14:25:09',0,'2019-03-21 15:03:40',NULL,'201809150101000001'),(32,4,9,'0391025542810009','aewen',1,'2018-11-12 14:25:10',0,'2019-03-21 15:03:40',NULL,'201809150101000001');

/*Table structure for table `sms_coupon_product_category_relation` */

DROP TABLE IF EXISTS `sms_coupon_product_category_relation`;

CREATE TABLE `sms_coupon_product_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  `product_category_name` varchar(200) DEFAULT NULL COMMENT '产品分类名称',
  `parent_category_name` varchar(200) DEFAULT NULL COMMENT '父分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='优惠券和产品分类关系表';

/*Data for the table `sms_coupon_product_category_relation` */

insert  into `sms_coupon_product_category_relation`(`id`,`coupon_id`,`product_category_id`,`product_category_name`,`parent_category_name`) values (4,19,30,'手机配件','手机数码'),(5,22,57,'饮料','食品'),(15,29,7,'外套','服装'),(16,29,8,'T恤','服装'),(17,29,9,'休闲裤','服装'),(18,29,57,'饮料','食品'),(19,29,37,'洗衣机','家用电器'),(20,30,37,'洗衣机','家用电器'),(21,31,50,'维修保养','汽车用品'),(22,31,37,'洗衣机','家用电器'),(23,31,57,'饮料','食品'),(24,31,7,'外套','服装'),(25,31,30,'手机配件','手机数码'),(26,31,45,'五金工具','家具家装');

/*Table structure for table `sms_coupon_product_relation` */

DROP TABLE IF EXISTS `sms_coupon_product_relation`;

CREATE TABLE `sms_coupon_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `product_sn` varchar(200) DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='优惠券和产品的关系表';

/*Data for the table `sms_coupon_product_relation` */

insert  into `sms_coupon_product_relation`(`id`,`coupon_id`,`product_id`,`product_name`,`product_sn`) values (10,22,26,'华为 HUAWEI P20 ','6946605'),(11,22,33,'小米（MI）小米电视4A ','4609652'),(24,29,30,'HLA海澜之家简约动物印花短袖T恤','HNTBJ2E042A'),(25,29,26,'华为 HUAWEI P20 ','6946605'),(26,29,28,'小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待','7437789'),(27,29,33,'小米（MI）小米电视4A ','4609652'),(29,30,33,'小米（MI）小米电视4A ','4609652'),(30,32,26,'华为 HUAWEI P20 ','6946605'),(31,32,33,'小米（MI）小米电视4A ','4609652');

/*Table structure for table `sms_flash_promotion` */

DROP TABLE IF EXISTS `sms_flash_promotion`;

CREATE TABLE `sms_flash_promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `start_date` date DEFAULT NULL COMMENT '开始日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `status` int(1) DEFAULT NULL COMMENT '上下线状态',
  `create_time` datetime DEFAULT NULL COMMENT '秒杀时间段名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='限时购表';

/*Data for the table `sms_flash_promotion` */

insert  into `sms_flash_promotion`(`id`,`title`,`start_date`,`end_date`,`status`,`create_time`) values (2,'春季家电家具疯狂秒杀1','2022-03-21','2022-03-24',1,'2018-11-16 11:12:13'),(3,'前端测试专用活动','2022-03-22','2022-04-29',1,'2018-11-16 11:11:31'),(4,'春季家电家具疯狂秒杀3','2022-05-19','2022-06-17',1,'2018-11-16 11:12:19'),(5,'春季家电家具疯狂秒杀4','2022-03-22','2022-03-31',1,'2018-11-16 11:12:24'),(7,'春季家电家具疯狂秒杀6','2022-04-14','2022-03-25',1,'2018-11-16 11:12:35'),(8,'春季家电家具疯狂秒杀7','2022-05-10','2022-05-12',1,'2018-11-16 11:12:39'),(9,'春季家电家具疯狂秒杀8','2022-03-23','2024-03-27',1,'2018-11-16 11:12:42'),(13,'测试','2010-03-31','2030-11-30',1,'2018-11-19 10:34:24');

/*Table structure for table `sms_flash_promotion_log` */

DROP TABLE IF EXISTS `sms_flash_promotion_log`;

CREATE TABLE `sms_flash_promotion_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `member_phone` varchar(64) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `subscribe_time` datetime DEFAULT NULL COMMENT '会员订阅时间',
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='限时购通知记录';

/*Data for the table `sms_flash_promotion_log` */

/*Table structure for table `sms_flash_promotion_product_relation` */

DROP TABLE IF EXISTS `sms_flash_promotion_product_relation`;

CREATE TABLE `sms_flash_promotion_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `flash_promotion_id` bigint(20) DEFAULT NULL,
  `flash_promotion_session_id` bigint(20) DEFAULT NULL COMMENT '编号',
  `product_id` bigint(20) DEFAULT NULL,
  `flash_promotion_price` decimal(10,2) DEFAULT NULL COMMENT '限时购价格',
  `flash_promotion_count` int(11) DEFAULT NULL COMMENT '限时购数量',
  `flash_promotion_limit` int(11) DEFAULT NULL COMMENT '每人限购数量',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='商品限时购与商品关系表';

/*Data for the table `sms_flash_promotion_product_relation` */

insert  into `sms_flash_promotion_product_relation`(`id`,`flash_promotion_id`,`flash_promotion_session_id`,`product_id`,`flash_promotion_price`,`flash_promotion_count`,`flash_promotion_limit`,`sort`) values (1,2,1,26,'3000.00',10,1,1),(2,2,1,27,'2000.00',10,1,20),(3,2,1,28,'599.00',19,1,0),(4,2,1,29,'4999.00',10,1,100),(9,2,2,26,'2999.00',100,1,0),(10,2,2,27,NULL,21,1,1),(11,2,2,28,NULL,NULL,NULL,4),(12,2,2,29,NULL,NULL,NULL,3),(13,2,2,30,NULL,NULL,NULL,2),(14,2,3,31,NULL,NULL,NULL,NULL),(15,2,3,32,NULL,NULL,NULL,NULL),(16,2,4,33,NULL,NULL,NULL,NULL),(17,2,4,34,NULL,NULL,NULL,NULL),(18,2,5,36,NULL,NULL,NULL,NULL),(19,2,6,33,NULL,NULL,NULL,NULL),(20,2,6,34,NULL,NULL,NULL,NULL),(21,3,1,26,'3000.00',100,1,NULL),(22,3,1,27,'1999.00',10,1,NULL),(23,3,1,28,'599.00',10,1,NULL),(24,3,1,29,'4999.00',10,1,NULL),(25,3,2,31,'90.00',10,1,NULL),(26,3,2,32,'60.00',10,1,NULL),(27,3,2,33,'2299.00',10,1,NULL),(28,3,2,34,'3888.00',10,1,NULL),(29,3,3,36,NULL,NULL,NULL,NULL),(30,3,3,35,NULL,NULL,NULL,NULL),(31,3,3,31,NULL,NULL,NULL,NULL),(32,3,3,32,NULL,NULL,NULL,NULL),(33,3,4,26,NULL,NULL,NULL,NULL),(34,3,4,27,NULL,NULL,NULL,NULL),(35,3,4,28,NULL,NULL,NULL,NULL),(36,3,4,29,NULL,NULL,NULL,NULL),(37,3,5,26,'3688.00',100,1,NULL),(38,3,5,27,'2599.00',10,1,NULL),(39,3,5,28,'599.00',10,1,NULL),(40,3,5,29,'4999.00',10,1,NULL),(41,3,6,26,NULL,NULL,NULL,NULL),(42,3,6,27,NULL,NULL,NULL,NULL),(43,3,6,28,NULL,NULL,NULL,NULL),(44,3,6,29,NULL,NULL,1,NULL),(50,3,6,37,NULL,NULL,NULL,NULL),(55,4,1,NULL,NULL,NULL,NULL,NULL),(56,4,5,NULL,NULL,NULL,NULL,NULL),(70,7,1,NULL,NULL,NULL,NULL,NULL),(71,7,1,31,'11.00',1,1,1),(76,5,1,NULL,NULL,NULL,NULL,NULL),(77,5,1,29,NULL,NULL,NULL,NULL),(82,7,6,NULL,NULL,NULL,NULL,NULL),(83,7,3,NULL,NULL,NULL,NULL,NULL),(84,7,3,37,NULL,NULL,NULL,NULL),(85,7,3,26,NULL,NULL,NULL,NULL),(86,7,3,38,NULL,NULL,NULL,NULL);

/*Table structure for table `sms_flash_promotion_session` */

DROP TABLE IF EXISTS `sms_flash_promotion_session`;

CREATE TABLE `sms_flash_promotion_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) DEFAULT NULL COMMENT '场次名称',
  `start_time` time DEFAULT NULL COMMENT '每日开始时间',
  `end_time` time DEFAULT NULL COMMENT '每日结束时间',
  `status` int(1) DEFAULT NULL COMMENT '启用状态：0->不启用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='限时购场次表';

/*Data for the table `sms_flash_promotion_session` */

insert  into `sms_flash_promotion_session`(`id`,`name`,`start_time`,`end_time`,`status`,`create_time`) values (1,'8:00','08:00:00','10:00:33',1,'2018-11-16 13:22:17'),(2,'10:00','10:00:00','12:00:00',1,'2018-11-16 13:22:34'),(3,'12:00','12:00:00','14:00:00',1,'2018-11-16 13:22:37'),(4,'14:00','14:00:00','16:00:00',0,'2018-11-16 13:22:41'),(5,'16:00','16:00:00','18:00:00',1,'2018-11-16 13:22:45'),(6,'18:00','18:00:00','20:00:00',1,'2018-11-16 13:21:34'),(7,'20:00','20:00:33','21:00:33',0,'2018-11-16 13:22:55');

/*Table structure for table `sms_home_advertise` */

DROP TABLE IF EXISTS `sms_home_advertise`;

CREATE TABLE `sms_home_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播',
  `pic` varchar(500) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
  `click_count` int(11) DEFAULT NULL COMMENT '点击数',
  `order_count` int(11) DEFAULT NULL COMMENT '下单数',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='首页轮播广告表';

/*Data for the table `sms_home_advertise` */

insert  into `sms_home_advertise`(`id`,`name`,`type`,`pic`,`start_time`,`end_time`,`status`,`click_count`,`order_count`,`url`,`note`,`sort`) values (2,'夏季大热促销',1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/xiaomi.jpg','2018-11-01 14:01:37','2018-11-15 14:01:37',1,0,0,'谢谢谢谢','夏季大热促销',0),(3,'夏季大热促销1',1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf58Ndefaac16.jpg','2018-11-13 14:01:37','2018-11-13 14:01:37',1,0,0,'xsxss','夏季大热促销1',0),(4,'夏季大热促销2',1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5a9d248cN071f4959.jpg','2018-11-13 14:01:37','2018-11-13 14:01:37',1,0,0,NULL,'夏季大热促销2',0),(9,'电影推荐广告',0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20181113/movie_ad.jpg','2018-11-01 00:00:00','2018-11-24 00:00:00',1,0,0,'www.baidu.com','电影推荐广告',100),(10,'汽车促销广告',0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20181113/car_ad.jpg','2018-11-13 00:00:00','2018-11-24 00:00:00',1,0,0,'xxx',NULL,99),(11,'汽车推荐广告',0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20181113/car_ad2.jpg','2018-11-13 00:00:00','2018-11-30 00:00:00',1,0,0,'xxx',NULL,98);

/*Table structure for table `sms_home_brand` */

DROP TABLE IF EXISTS `sms_home_brand`;

CREATE TABLE `sms_home_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(20) DEFAULT NULL,
  `brand_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='首页推荐品牌表';

/*Data for the table `sms_home_brand` */

insert  into `sms_home_brand`(`id`,`brand_id`,`brand_name`,`recommend_status`,`sort`) values (6,6,'小米',1,300),(8,5,'方太',1,100),(32,50,'海澜之家',1,0),(33,51,'苹果',1,0),(35,3,'华为',1,0),(36,4,'格力',1,0),(37,5,'方太',1,0),(39,21,'OPPO',1,0),(45,58,'NIKE',1,0),(51,1,'万和',0,0),(52,2,'三星',0,0);

/*Table structure for table `sms_home_category` */

DROP TABLE IF EXISTS `sms_home_category`;

CREATE TABLE `sms_home_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL COMMENT '二级分类id',
  `category_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '广告位置：0->PC广告首页；1->app广告轮播',
  `status` int(1) DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
  `pic` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '链接地址',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

/*Data for the table `sms_home_category` */

insert  into `sms_home_category`(`id`,`category_id`,`category_name`,`type`,`status`,`pic`,`url`,`sort`) values (1,19,'手机',0,1,'https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/phone.jpg','http://localhost:8081/#/detail/26',20),(2,7,'外套',0,1,'https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/clothes.jpg','http://localhost:8081/#/detail/30',10),(3,35,'家用电器',0,1,'https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/phone.jpg','c',30),(4,8,'T恤',0,1,'https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/phone.jpg','c',25),(5,29,'男鞋',0,1,'https://tulingmall-xushu.oss-cn-chengdu.aliyuncs.com/phone.jpg','c',22),(6,36,'名牌',0,0,'https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022041502/wallhaven-72ggyy.jpg','http//www.baidu.com',100);

/*Table structure for table `sms_home_new_product` */

DROP TABLE IF EXISTS `sms_home_new_product`;

CREATE TABLE `sms_home_new_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL,
  `sort` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='新鲜好物表';

/*Data for the table `sms_home_new_product` */

insert  into `sms_home_new_product`(`id`,`product_id`,`product_name`,`recommend_status`,`sort`) values (8,26,'华为 HUAWEI P20 ',1,0),(10,28,'小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待',1,0),(11,29,'Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机',1,0),(12,30,'HLA海澜之家简约动物印花短袖T恤',1,0),(15,27,'小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待xxx',0,0);

/*Table structure for table `sms_home_recommend_product` */

DROP TABLE IF EXISTS `sms_home_recommend_product`;

CREATE TABLE `sms_home_recommend_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL,
  `sort` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='人气推荐商品表';

/*Data for the table `sms_home_recommend_product` */

insert  into `sms_home_recommend_product`(`id`,`product_id`,`product_name`,`recommend_status`,`sort`) values (3,26,'华为 HUAWEI P20 ',1,0),(4,27,'小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待',1,0),(5,28,'小米 红米5A 全网通版 3GB+32GB 香槟金 移动联通电信4G手机 双卡双待',1,0),(6,29,'Apple iPhone 8 Plus 64GB 红色特别版 移动联通电信4G手机',0,1),(10,30,'HLA海澜之家简约动物印花短袖T恤',0,0);

/*Table structure for table `sms_home_recommend_subject` */

DROP TABLE IF EXISTS `sms_home_recommend_subject`;

CREATE TABLE `sms_home_recommend_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `subject_name` varchar(64) DEFAULT NULL,
  `recommend_status` int(1) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='首页推荐专题表';

/*Data for the table `sms_home_recommend_subject` */

insert  into `sms_home_recommend_subject`(`id`,`subject_id`,`subject_name`,`recommend_status`,`sort`) values (15,2,'大牌手机低价秒',1,0),(16,3,'晓龙845新品上市',1,0),(17,4,'夏天应该穿什么',0,200),(18,5,'夏季精选',1,100),(19,6,'品牌手机降价',1,0),(21,2,'交通拥挤有妙招，电动车小巧穿行无障碍',0,0),(23,3,'无酒不成席，甘香白酒开怀助兴',0,0),(24,4,'真规划不盲扫，全域清洁净无尘',0,0),(25,5,'抑菌更纯净，直饮净水保家人健康',0,0),(29,1,'轮廓分明，双摄手机映现细腻美照',0,0);

/*Table structure for table `ums_admin` */

DROP TABLE IF EXISTS `ums_admin`;

CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

/*Data for the table `ums_admin` */

insert  into `ums_admin`(`id`,`username`,`password`,`icon`,`email`,`nick_name`,`note`,`create_time`,`login_time`,`status`) values (1,'test','$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','test@qq.com','测试账号',NULL,'2018-09-29 13:55:30','2018-09-29 13:55:39',0),(3,'admin','$2a$10$4JY0LCyWbXb/1Pc5hGbGg.yDCnEFn1SA2T31bIFBazle80EIqOXLC','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','admin@163.com','系统管理员','系统管理员','2018-10-08 13:32:47','2019-04-20 12:45:16',1),(4,'macro','$2a$10$Bx4jZPR7GhEpIQfefDQtVeS58GfT5n6mxs/b4nLLK65eMFa16topa','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','macro@qq.com','macro','macro专用','2019-10-06 15:53:51','2020-02-03 14:55:55',0),(6,'productAdmin','$2a$10$6/.J.p.6Bhn7ic4GfoB5D.pGd7xSiD1a9M6ht6yO0fxzlKJPjRAGm','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','product@qq.com','商品管理员','只有商品权限','2020-02-07 16:15:08',NULL,0),(7,'orderAdmin','$2a$10$UqEhA9UZXjHHA3B.L9wNG.6aerrBjC6WHTtbv1FdvYPUI.7lkL6E.','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','order@qq.com','订单管理员','只有订单管理权限','2020-02-07 16:15:50',NULL,0),(10,'ceshi','$2a$10$jt6w1tWEdUn33/21Ze2zK.B7n06h.ZLPwrltr.tlJxGyomMSVZsxi','https://tjl-my-file.oss-cn-beijing.aliyuncs.com/picture/2022040619/22.jpg','ceshi@qq.com','ceshi',NULL,'2020-03-13 16:23:30',NULL,1);

/*Table structure for table `ums_admin_login_log` */

DROP TABLE IF EXISTS `ums_admin_login_log`;

CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=357 DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';

/*Data for the table `ums_admin_login_log` */

insert  into `ums_admin_login_log`(`id`,`admin_id`,`create_time`,`ip`,`address`,`user_agent`) values (285,3,'2020-08-24 14:05:21','0:0:0:0:0:0:0:1',NULL,NULL),(286,10,'2020-08-24 14:05:39','0:0:0:0:0:0:0:1',NULL,NULL),(287,3,'2022-04-06 22:48:49','0:0:0:0:0:0:0:1',NULL,NULL),(288,3,'2022-04-06 22:50:48','0:0:0:0:0:0:0:1',NULL,NULL),(289,3,'2022-04-06 23:00:45','0:0:0:0:0:0:0:1',NULL,NULL),(290,3,'2022-04-06 23:59:30','0:0:0:0:0:0:0:1',NULL,NULL),(291,3,'2022-04-06 23:59:33','0:0:0:0:0:0:0:1',NULL,NULL),(292,3,'2022-04-07 00:01:26','0:0:0:0:0:0:0:1',NULL,NULL),(293,3,'2022-04-07 00:09:23','0:0:0:0:0:0:0:1',NULL,NULL),(294,3,'2022-04-07 00:15:19','0:0:0:0:0:0:0:1',NULL,NULL),(295,3,'2022-04-07 00:18:16','0:0:0:0:0:0:0:1',NULL,NULL),(296,3,'2022-04-07 00:22:49','0:0:0:0:0:0:0:1',NULL,NULL),(297,3,'2022-04-14 23:09:30','0:0:0:0:0:0:0:1',NULL,NULL),(298,3,'2022-04-14 23:22:28','0:0:0:0:0:0:0:1',NULL,NULL),(299,3,'2022-04-14 23:58:16','0:0:0:0:0:0:0:1',NULL,NULL),(300,3,'2022-04-15 00:13:02','0:0:0:0:0:0:0:1',NULL,NULL),(301,3,'2022-04-15 00:55:41','0:0:0:0:0:0:0:1',NULL,NULL),(302,3,'2022-04-15 01:56:12','0:0:0:0:0:0:0:1',NULL,NULL),(303,3,'2022-04-15 02:06:53','0:0:0:0:0:0:0:1',NULL,NULL),(304,3,'2022-04-15 02:38:24','0:0:0:0:0:0:0:1',NULL,NULL),(305,3,'2022-04-15 06:05:29','0:0:0:0:0:0:0:1',NULL,NULL),(306,3,'2022-04-15 06:42:02','0:0:0:0:0:0:0:1',NULL,NULL),(307,3,'2022-04-15 23:06:48','0:0:0:0:0:0:0:1',NULL,NULL),(308,3,'2022-04-16 00:25:10','0:0:0:0:0:0:0:1',NULL,NULL),(309,3,'2022-04-17 22:44:13','0:0:0:0:0:0:0:1',NULL,NULL),(310,10,'2022-04-17 22:53:14','0:0:0:0:0:0:0:1',NULL,NULL),(311,3,'2022-04-17 22:53:41','0:0:0:0:0:0:0:1',NULL,NULL),(312,3,'2022-04-18 00:38:00','0:0:0:0:0:0:0:1',NULL,NULL),(313,3,'2022-04-18 00:40:15','0:0:0:0:0:0:0:1',NULL,NULL),(314,3,'2022-04-18 00:51:05','0:0:0:0:0:0:0:1',NULL,NULL),(315,3,'2022-04-18 00:51:33','0:0:0:0:0:0:0:1',NULL,NULL),(316,10,'2022-04-18 00:52:01','0:0:0:0:0:0:0:1',NULL,NULL),(317,3,'2022-04-18 00:57:00','0:0:0:0:0:0:0:1',NULL,NULL),(318,3,'2022-04-18 01:12:51','0:0:0:0:0:0:0:1',NULL,NULL),(319,3,'2022-04-18 01:15:18','0:0:0:0:0:0:0:1',NULL,NULL),(320,3,'2022-04-18 01:19:02','0:0:0:0:0:0:0:1',NULL,NULL),(321,3,'2022-04-18 01:34:35','0:0:0:0:0:0:0:1',NULL,NULL),(322,3,'2022-04-18 01:34:37','0:0:0:0:0:0:0:1',NULL,NULL),(323,3,'2022-04-18 01:35:24','0:0:0:0:0:0:0:1',NULL,NULL),(324,3,'2022-04-18 01:35:49','0:0:0:0:0:0:0:1',NULL,NULL),(325,3,'2022-04-18 03:03:55','0:0:0:0:0:0:0:1',NULL,NULL),(326,10,'2022-04-18 03:04:19','0:0:0:0:0:0:0:1',NULL,NULL),(327,10,'2022-04-18 03:04:26','0:0:0:0:0:0:0:1',NULL,NULL),(328,3,'2022-04-18 03:04:37','0:0:0:0:0:0:0:1',NULL,NULL),(329,10,'2022-04-18 03:05:13','0:0:0:0:0:0:0:1',NULL,NULL),(330,10,'2022-04-18 03:05:18','0:0:0:0:0:0:0:1',NULL,NULL),(331,10,'2022-04-18 03:05:56','0:0:0:0:0:0:0:1',NULL,NULL),(332,10,'2022-04-18 03:06:00','0:0:0:0:0:0:0:1',NULL,NULL),(333,3,'2022-04-18 03:06:24','0:0:0:0:0:0:0:1',NULL,NULL),(334,10,'2022-04-18 03:08:22','0:0:0:0:0:0:0:1',NULL,NULL),(335,3,'2022-04-18 03:09:01','0:0:0:0:0:0:0:1',NULL,NULL),(336,3,'2022-04-19 01:03:11','0:0:0:0:0:0:0:1',NULL,NULL),(337,10,'2022-04-19 01:04:15','0:0:0:0:0:0:0:1',NULL,NULL),(338,3,'2022-04-19 01:04:50','0:0:0:0:0:0:0:1',NULL,NULL),(339,10,'2022-04-19 01:05:31','0:0:0:0:0:0:0:1',NULL,NULL),(340,3,'2022-04-19 01:05:51','0:0:0:0:0:0:0:1',NULL,NULL),(341,3,'2022-04-19 17:18:17','0:0:0:0:0:0:0:1',NULL,NULL),(342,3,'2022-04-19 17:26:00','0:0:0:0:0:0:0:1',NULL,NULL),(343,3,'2022-04-19 19:31:20','0:0:0:0:0:0:0:1',NULL,NULL),(344,3,'2022-04-20 00:22:33','0:0:0:0:0:0:0:1',NULL,NULL),(345,3,'2022-04-20 20:28:02','0:0:0:0:0:0:0:1',NULL,NULL),(346,3,'2022-04-21 16:05:05','0:0:0:0:0:0:0:1',NULL,NULL),(347,3,'2022-04-21 20:01:48','0:0:0:0:0:0:0:1',NULL,NULL),(348,3,'2022-04-21 20:34:42','0:0:0:0:0:0:0:1',NULL,NULL),(349,3,'2022-04-22 20:09:00','0:0:0:0:0:0:0:1',NULL,NULL),(350,3,'2022-04-25 15:30:05','0:0:0:0:0:0:0:1',NULL,NULL),(351,10,'2022-04-25 15:32:21','0:0:0:0:0:0:0:1',NULL,NULL),(352,3,'2022-04-25 15:32:43','0:0:0:0:0:0:0:1',NULL,NULL),(353,10,'2022-04-25 15:33:19','0:0:0:0:0:0:0:1',NULL,NULL),(354,3,'2022-04-25 15:34:47','0:0:0:0:0:0:0:1',NULL,NULL),(355,10,'2022-04-25 15:35:47','0:0:0:0:0:0:0:1',NULL,NULL),(356,3,'2022-04-25 15:36:14','0:0:0:0:0:0:0:1',NULL,NULL);

/*Table structure for table `ums_admin_permission_relation` */

DROP TABLE IF EXISTS `ums_admin_permission_relation`;

CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

/*Data for the table `ums_admin_permission_relation` */

/*Table structure for table `ums_admin_role_relation` */

DROP TABLE IF EXISTS `ums_admin_role_relation`;

CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

/*Data for the table `ums_admin_role_relation` */

insert  into `ums_admin_role_relation`(`id`,`admin_id`,`role_id`) values (27,6,1),(28,7,2),(29,1,5),(30,4,5),(31,8,5),(34,12,6),(38,13,5),(40,3,9),(44,10,1);

/*Table structure for table `ums_growth_change_history` */

DROP TABLE IF EXISTS `ums_growth_change_history`;

CREATE TABLE `ums_growth_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='成长值变化历史记录表';

/*Data for the table `ums_growth_change_history` */

insert  into `ums_growth_change_history`(`id`,`member_id`,`create_time`,`change_type`,`change_count`,`operate_man`,`operate_note`,`source_type`) values (1,1,'2018-08-29 17:16:35',0,1000,'test','测试使用',1);

/*Table structure for table `ums_integration_change_history` */

DROP TABLE IF EXISTS `ums_integration_change_history`;

CREATE TABLE `ums_integration_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分变化历史记录表';

/*Data for the table `ums_integration_change_history` */

/*Table structure for table `ums_integration_consume_setting` */

DROP TABLE IF EXISTS `ums_integration_consume_setting`;

CREATE TABLE `ums_integration_consume_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deduction_per_amount` int(11) DEFAULT NULL COMMENT '每一元需要抵扣的积分数量',
  `max_percent_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高抵用百分比',
  `use_unit` int(11) DEFAULT NULL COMMENT '每次使用积分最小单位100',
  `coupon_status` int(1) DEFAULT NULL COMMENT '是否可以和优惠券同用；0->不可以；1->可以',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='积分消费设置';

/*Data for the table `ums_integration_consume_setting` */

insert  into `ums_integration_consume_setting`(`id`,`deduction_per_amount`,`max_percent_per_order`,`use_unit`,`coupon_status`) values (1,100,50,100,1);

/*Table structure for table `ums_member` */

DROP TABLE IF EXISTS `ums_member`;

CREATE TABLE `ums_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_level_id` bigint(20) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
  `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='会员表';

/*Data for the table `ums_member` */

insert  into `ums_member`(`id`,`member_level_id`,`username`,`password`,`nickname`,`phone`,`status`,`create_time`,`icon`,`gender`,`birthday`,`city`,`job`,`personalized_signature`,`source_type`,`integration`,`growth`,`luckey_count`,`history_integration`) values (10,4,'tanjiali','$2a$10$6MqsmvSzPNaJpnKD2BW8..JmjvR5wEfKy1YrFIFs4FvCGLWdsy8/2','ss','15774549732',1,'2022-04-07 22:54:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,4,'ttyy','$2a$10$mmQbnxq2t6XAY4mQ6lQVQOmt/2A3e5pwxGfsNsp3JCsVXKTefiSdS','mm','18774549742',1,'2022-04-08 00:45:10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,4,'yyds','$2a$10$87zYESZV0vTox0XXTbW0v.vOtDsRXLQTOL0TqX9YVvRKAtYpu7rta','谭佳利','18142693662',1,'2022-04-08 00:50:01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,4,'yyds1111','$2a$10$ev5VkLTJN61wjAUEolcVzug7glQBq3KLl83iwYjADlzEBO4Ar4kTe','ff','18774549732',1,'2022-04-08 01:07:49',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `ums_member_level` */

DROP TABLE IF EXISTS `ums_member_level`;

CREATE TABLE `ums_member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级：0->不是；1->是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
  `priviledge_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
  `priviledge_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
  `priviledge_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
  `priviledge_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
  `priviledge_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
  `priviledge_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

/*Data for the table `ums_member_level` */

insert  into `ums_member_level`(`id`,`name`,`growth_point`,`default_status`,`free_freight_point`,`comment_growth_point`,`priviledge_free_freight`,`priviledge_sign_in`,`priviledge_comment`,`priviledge_promotion`,`priviledge_member_price`,`priviledge_birthday`,`note`) values (1,'黄金会员',1000,0,'199.00',5,1,1,1,1,1,1,NULL),(2,'白金会员',5000,0,'99.00',10,1,1,1,1,1,1,NULL),(3,'钻石会员',15000,0,'69.00',15,1,1,1,1,1,1,NULL),(4,'普通会员',1,1,'199.00',20,1,1,1,1,0,0,NULL);

/*Table structure for table `ums_member_login_log` */

DROP TABLE IF EXISTS `ums_member_login_log`;

CREATE TABLE `ums_member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `login_type` int(1) DEFAULT NULL COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序',
  `province` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='会员登录记录';

/*Data for the table `ums_member_login_log` */

insert  into `ums_member_login_log`(`id`,`member_id`,`create_time`,`ip`,`city`,`login_type`,`province`) values (1,10,'2022-04-08 00:44:31','0:0:0:0:0:0:0:1',NULL,NULL,NULL),(2,11,'2022-04-08 00:47:19','127.0.0.1',NULL,NULL,NULL),(3,12,'2022-04-08 00:50:24','127.0.0.1',NULL,NULL,NULL),(4,12,'2022-04-08 00:50:39','127.0.0.1',NULL,NULL,NULL),(5,12,'2022-04-08 00:51:49','127.0.0.1',NULL,NULL,NULL),(6,12,'2022-04-08 00:52:21','127.0.0.1',NULL,NULL,NULL),(7,12,'2022-04-08 00:52:21','127.0.0.1',NULL,NULL,NULL),(8,12,'2022-04-08 00:57:39','127.0.0.1',NULL,NULL,NULL),(9,12,'2022-04-08 01:01:48','127.0.0.1',NULL,NULL,NULL),(10,12,'2022-04-08 01:01:48','127.0.0.1',NULL,NULL,NULL),(11,12,'2022-04-08 01:01:48','127.0.0.1',NULL,NULL,NULL),(12,12,'2022-04-08 01:01:48','127.0.0.1',NULL,NULL,NULL),(13,12,'2022-04-08 01:02:25','127.0.0.1',NULL,NULL,NULL),(14,12,'2022-04-08 01:02:36','127.0.0.1',NULL,NULL,NULL),(15,12,'2022-04-08 01:04:29','127.0.0.1',NULL,NULL,NULL),(16,10,'2022-04-08 01:05:22','0:0:0:0:0:0:0:1',NULL,NULL,NULL),(17,10,'2022-04-08 01:05:37','0:0:0:0:0:0:0:1',NULL,NULL,NULL),(18,10,'2022-04-08 01:07:03','0:0:0:0:0:0:0:1',NULL,NULL,NULL),(19,12,'2022-04-08 01:07:19','127.0.0.1',NULL,NULL,NULL),(20,12,'2022-04-08 01:07:28','127.0.0.1',NULL,NULL,NULL),(21,12,'2022-04-08 01:07:37','127.0.0.1',NULL,NULL,NULL),(22,12,'2022-04-08 01:16:48','127.0.0.1',NULL,NULL,NULL),(23,12,'2022-04-08 01:17:01','127.0.0.1',NULL,NULL,NULL),(24,12,'2022-04-08 01:20:29','127.0.0.1',NULL,NULL,NULL),(25,12,'2022-04-08 01:21:02','127.0.0.1',NULL,NULL,NULL),(26,12,'2022-04-08 01:22:40','127.0.0.1',NULL,NULL,NULL),(27,12,'2022-04-08 20:01:20','127.0.0.1',NULL,NULL,NULL),(28,12,'2022-04-08 21:37:21','127.0.0.1',NULL,NULL,NULL),(29,12,'2022-04-08 21:42:18','127.0.0.1',NULL,NULL,NULL),(30,12,'2022-04-09 00:52:17','127.0.0.1',NULL,NULL,NULL),(31,12,'2022-04-09 00:54:18','127.0.0.1',NULL,NULL,NULL),(32,12,'2022-04-09 00:59:07','127.0.0.1',NULL,NULL,NULL),(33,12,'2022-04-09 00:59:51','127.0.0.1',NULL,NULL,NULL),(34,12,'2022-04-09 01:01:26','127.0.0.1',NULL,NULL,NULL),(35,12,'2022-04-09 01:02:28','127.0.0.1',NULL,NULL,NULL),(36,12,'2022-04-09 01:03:40','127.0.0.1',NULL,NULL,NULL),(37,12,'2022-04-09 01:36:14','127.0.0.1',NULL,NULL,NULL),(38,12,'2022-04-09 01:39:51','127.0.0.1',NULL,NULL,NULL),(39,12,'2022-04-09 01:40:07','127.0.0.1',NULL,NULL,NULL),(40,12,'2022-04-09 01:42:21','127.0.0.1',NULL,NULL,NULL),(41,12,'2022-04-09 01:48:50','127.0.0.1',NULL,NULL,NULL),(42,12,'2022-04-09 01:52:26','127.0.0.1',NULL,NULL,NULL),(43,12,'2022-04-09 01:52:36','127.0.0.1',NULL,NULL,NULL),(44,12,'2022-04-09 01:52:38','127.0.0.1',NULL,NULL,NULL),(45,12,'2022-04-09 01:52:39','127.0.0.1',NULL,NULL,NULL),(46,12,'2022-04-09 01:52:39','127.0.0.1',NULL,NULL,NULL),(47,12,'2022-04-09 01:52:40','127.0.0.1',NULL,NULL,NULL),(48,12,'2022-04-09 01:52:52','127.0.0.1',NULL,NULL,NULL),(49,12,'2022-04-09 01:54:35','127.0.0.1',NULL,NULL,NULL),(50,12,'2022-04-09 01:54:50','127.0.0.1',NULL,NULL,NULL),(51,12,'2022-04-09 01:56:12','127.0.0.1',NULL,NULL,NULL),(52,12,'2022-04-09 02:03:23','127.0.0.1',NULL,NULL,NULL),(53,12,'2022-04-09 02:19:26','127.0.0.1',NULL,NULL,NULL),(54,12,'2022-04-09 02:33:19','127.0.0.1',NULL,NULL,NULL),(55,12,'2022-04-09 02:36:25','127.0.0.1',NULL,NULL,NULL),(56,12,'2022-04-09 16:48:59','127.0.0.1',NULL,NULL,NULL),(57,12,'2022-04-09 17:07:48','127.0.0.1',NULL,NULL,NULL),(58,12,'2022-04-09 23:44:09','127.0.0.1',NULL,NULL,NULL),(59,12,'2022-04-09 23:52:06','127.0.0.1',NULL,NULL,NULL),(60,12,'2022-04-09 23:54:20','127.0.0.1',NULL,NULL,NULL),(61,12,'2022-04-09 23:55:25','127.0.0.1',NULL,NULL,NULL),(62,12,'2022-04-10 00:19:27','127.0.0.1',NULL,NULL,NULL),(63,12,'2022-04-11 00:48:07','127.0.0.1',NULL,NULL,NULL),(64,12,'2022-04-11 00:48:24','127.0.0.1',NULL,NULL,NULL),(65,12,'2022-04-11 00:48:56','127.0.0.1',NULL,NULL,NULL),(66,12,'2022-04-11 00:49:13','127.0.0.1',NULL,NULL,NULL),(67,12,'2022-04-11 00:51:06','127.0.0.1',NULL,NULL,NULL),(68,12,'2022-04-11 00:53:59','127.0.0.1',NULL,NULL,NULL),(69,12,'2022-04-12 19:26:26','127.0.0.1',NULL,NULL,NULL),(70,12,'2022-04-13 22:19:24','127.0.0.1',NULL,NULL,NULL),(71,12,'2022-04-14 22:22:41','127.0.0.1',NULL,NULL,NULL),(72,12,'2022-04-14 23:12:55','127.0.0.1',NULL,NULL,NULL),(73,12,'2022-04-16 00:39:56','127.0.0.1',NULL,NULL,NULL),(74,12,'2022-04-16 23:40:17','127.0.0.1',NULL,NULL,NULL),(75,12,'2022-04-16 23:40:27','127.0.0.1',NULL,NULL,NULL),(76,12,'2022-04-17 02:05:26','127.0.0.1',NULL,NULL,NULL),(77,12,'2022-04-17 02:10:59','127.0.0.1',NULL,NULL,NULL),(78,12,'2022-04-17 02:17:41','127.0.0.1',NULL,NULL,NULL),(79,12,'2022-04-17 03:10:29','127.0.0.1',NULL,NULL,NULL),(80,12,'2022-04-17 03:11:15','127.0.0.1',NULL,NULL,NULL),(81,12,'2022-04-17 03:14:53','127.0.0.1',NULL,NULL,NULL),(82,12,'2022-04-17 03:26:42','127.0.0.1',NULL,NULL,NULL),(83,12,'2022-04-17 04:01:17','127.0.0.1',NULL,NULL,NULL),(84,12,'2022-04-17 04:05:08','127.0.0.1',NULL,NULL,NULL),(85,12,'2022-04-17 04:07:15','127.0.0.1',NULL,NULL,NULL),(86,12,'2022-04-17 04:08:30','127.0.0.1',NULL,NULL,NULL),(87,12,'2022-04-17 04:12:32','127.0.0.1',NULL,NULL,NULL),(88,12,'2022-04-18 02:37:47','127.0.0.1',NULL,NULL,NULL),(89,12,'2022-04-21 23:23:18','127.0.0.1',NULL,NULL,NULL);

/*Table structure for table `ums_member_member_tag_relation` */

DROP TABLE IF EXISTS `ums_member_member_tag_relation`;

CREATE TABLE `ums_member_member_tag_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和标签关系表';

/*Data for the table `ums_member_member_tag_relation` */

/*Table structure for table `ums_member_product_category_relation` */

DROP TABLE IF EXISTS `ums_member_product_category_relation`;

CREATE TABLE `ums_member_product_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员与产品分类关系表（用户喜欢的分类）';

/*Data for the table `ums_member_product_category_relation` */

/*Table structure for table `ums_member_receive_address` */

DROP TABLE IF EXISTS `ums_member_receive_address`;

CREATE TABLE `ums_member_receive_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `region` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

/*Data for the table `ums_member_receive_address` */

insert  into `ums_member_receive_address`(`id`,`member_id`,`name`,`phone_number`,`default_status`,`post_code`,`province`,`city`,`region`,`detail_address`) values (1,1,'大梨','18033441849',0,'518000','广东省','深圳市','南山区','科兴科学园'),(3,1,'大梨','18033441849',0,'518000','广东省','深圳市','福田区','清水河街道'),(4,1,'大梨','18033441849',1,'518000','广东省','深圳市','福田区','东晓街道'),(7,12,'谭佳利','187774546597',0,'470001','湖南省','张家界市','桑植县','cg节点123是'),(10,12,'张三','13697665790',0,'100000','黑龙江省','鸡西市','鸡冠区','xx街道');

/*Table structure for table `ums_member_rule_setting` */

DROP TABLE IF EXISTS `ums_member_rule_setting`;

CREATE TABLE `ums_member_rule_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `continue_sign_day` int(11) DEFAULT NULL COMMENT '连续签到天数',
  `continue_sign_point` int(11) DEFAULT NULL COMMENT '连续签到赠送数量',
  `consume_per_point` decimal(10,2) DEFAULT NULL COMMENT '每消费多少元获取1个点',
  `low_order_amount` decimal(10,2) DEFAULT NULL COMMENT '最低获取点数的订单金额',
  `max_point_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高获取点数',
  `type` int(1) DEFAULT NULL COMMENT '类型：0->积分规则；1->成长值规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分成长规则表';

/*Data for the table `ums_member_rule_setting` */

/*Table structure for table `ums_member_statistics_info` */

DROP TABLE IF EXISTS `ums_member_statistics_info`;

CREATE TABLE `ums_member_statistics_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `consume_amount` decimal(10,2) DEFAULT NULL COMMENT '累计消费金额',
  `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
  `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评价数',
  `return_order_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `attend_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `collect_product_count` int(11) DEFAULT NULL,
  `collect_subject_count` int(11) DEFAULT NULL,
  `collect_topic_count` int(11) DEFAULT NULL,
  `collect_comment_count` int(11) DEFAULT NULL,
  `invite_friend_count` int(11) DEFAULT NULL,
  `recent_order_time` datetime DEFAULT NULL COMMENT '最后一次下订单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员统计信息';

/*Data for the table `ums_member_statistics_info` */

/*Table structure for table `ums_member_tag` */

DROP TABLE IF EXISTS `ums_member_tag`;

CREATE TABLE `ums_member_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `finish_order_count` int(11) DEFAULT NULL COMMENT '自动打标签完成订单数量',
  `finish_order_amount` decimal(10,2) DEFAULT NULL COMMENT '自动打标签完成订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签表';

/*Data for the table `ums_member_tag` */

/*Table structure for table `ums_member_task` */

DROP TABLE IF EXISTS `ums_member_task`;

CREATE TABLE `ums_member_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth` int(11) DEFAULT NULL COMMENT '赠送成长值',
  `intergration` int(11) DEFAULT NULL COMMENT '赠送积分',
  `type` int(1) DEFAULT NULL COMMENT '任务类型：0->新手任务；1->日常任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员任务表';

/*Data for the table `ums_member_task` */

/*Table structure for table `ums_menu` */

DROP TABLE IF EXISTS `ums_menu`;

CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

/*Data for the table `ums_menu` */

insert  into `ums_menu`(`id`,`parent_id`,`create_time`,`title`,`level`,`sort`,`name`,`icon`,`hidden`) values (1,0,'2020-02-02 14:50:36','商品',0,0,'pms','product',0),(2,1,'2020-02-02 14:51:50','商品列表',1,0,'product','product-list',0),(3,1,'2020-02-02 14:52:44','添加商品',1,0,'addProduct','product-add',0),(4,1,'2020-02-02 14:53:51','商品分类',1,0,'productCate','product-cate',0),(5,1,'2020-02-02 14:54:51','商品类型',1,0,'productAttr','product-attr',0),(6,1,'2020-02-02 14:56:29','品牌管理',1,0,'brand','product-brand',0),(7,0,'2020-02-02 16:54:07','订单',0,0,'oms','order',0),(8,7,'2020-02-02 16:55:18','订单列表',1,0,'order','product-list',0),(9,7,'2020-02-02 16:56:46','订单设置',1,0,'orderSetting','order-setting',0),(10,7,'2020-02-02 16:57:39','退货申请处理',1,0,'returnApply','order-return',0),(11,7,'2020-02-02 16:59:40','退货原因设置',1,0,'returnReason','order-return-reason',0),(12,0,'2020-02-04 16:18:00','营销',0,0,'sms','sms',0),(13,12,'2020-02-04 16:19:22','秒杀活动列表',1,0,'flash','sms-flash',0),(14,12,'2020-02-04 16:20:16','优惠券列表',1,0,'coupon','sms-coupon',0),(16,12,'2020-02-07 16:22:38','品牌推荐',1,0,'homeBrand','product-brand',0),(17,12,'2020-02-07 16:23:14','新品推荐',1,0,'homeNew','sms-new',0),(18,12,'2020-02-07 16:26:38','人气推荐',1,0,'homeHot','sms-hot',0),(19,12,'2020-02-07 16:28:16','专题推荐',1,0,'homeSubject','sms-subject',0),(20,12,'2020-02-07 16:28:42','广告列表',1,0,'homeAdvertise','sms-ad',0),(21,0,'2020-02-07 16:29:13','权限',0,0,'ums','ums',0),(22,21,'2020-02-07 16:29:51','用户列表',1,0,'admin','ums-admin',0),(23,21,'2020-02-07 16:30:13','角色列表',1,0,'role','ums-role',0),(24,21,'2020-02-07 16:30:53','菜单列表',1,0,'menu','ums-menu',0),(25,21,'2020-02-07 16:31:13','资源列表',1,0,'resource','ums-resource',0),(26,12,'2022-04-15 00:17:29','分类推荐',1,0,'homeCategory','ums-menu',0);

/*Table structure for table `ums_permission` */

DROP TABLE IF EXISTS `ums_permission`;

CREATE TABLE `ums_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

/*Data for the table `ums_permission` */

insert  into `ums_permission`(`id`,`pid`,`name`,`value`,`icon`,`type`,`uri`,`status`,`create_time`,`sort`) values (1,0,'商品',NULL,NULL,0,NULL,1,'2018-09-29 16:15:14',0),(2,1,'商品列表','pms:product:read',NULL,1,'/pms/product/index',1,'2018-09-29 16:17:01',0),(3,1,'添加商品','pms:product:create',NULL,1,'/pms/product/add',1,'2018-09-29 16:18:51',0),(4,1,'商品分类','pms:productCategory:read',NULL,1,'/pms/productCate/index',1,'2018-09-29 16:23:07',0),(5,1,'商品类型','pms:productAttribute:read',NULL,1,'/pms/productAttr/index',1,'2018-09-29 16:24:43',0),(6,1,'品牌管理','pms:brand:read',NULL,1,'/pms/brand/index',1,'2018-09-29 16:25:45',0),(7,2,'编辑商品','pms:product:update',NULL,2,'/pms/product/updateProduct',1,'2018-09-29 16:34:23',0),(8,2,'删除商品','pms:product:delete',NULL,2,'/pms/product/delete',1,'2018-09-29 16:38:33',0),(9,4,'添加商品分类','pms:productCategory:create',NULL,2,'/pms/productCate/create',1,'2018-09-29 16:43:23',0),(10,4,'修改商品分类','pms:productCategory:update',NULL,2,'/pms/productCate/update',1,'2018-09-29 16:43:55',0),(11,4,'删除商品分类','pms:productCategory:delete',NULL,2,'/pms/productAttr/delete',1,'2018-09-29 16:44:38',0),(12,5,'添加商品类型','pms:productAttribute:create',NULL,2,'/pms/productAttr/create',1,'2018-09-29 16:45:25',0),(13,5,'修改商品类型','pms:productAttribute:update',NULL,2,'/pms/productAttr/update',1,'2018-09-29 16:48:08',0),(14,5,'删除商品类型','pms:productAttribute:delete',NULL,2,'/pms/productAttr/delete',1,'2018-09-29 16:48:44',0),(15,6,'添加品牌','pms:brand:create',NULL,2,'/pms/brand/add',1,'2018-09-29 16:49:34',0),(16,6,'修改品牌','pms:brand:update',NULL,2,'/pms/brand/update',1,'2018-09-29 16:50:55',0),(17,6,'删除品牌','pms:brand:delete',NULL,2,'/pms/brand/delete',1,'2018-09-29 16:50:59',0),(18,0,'首页',NULL,NULL,0,NULL,1,'2018-09-29 16:51:57',0);

/*Table structure for table `ums_resource` */

DROP TABLE IF EXISTS `ums_resource`;

CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

/*Data for the table `ums_resource` */

insert  into `ums_resource`(`id`,`create_time`,`name`,`url`,`description`,`category_id`) values (1,'2020-02-04 17:04:55','商品品牌管理','/brand/**',NULL,1),(2,'2020-02-04 17:05:35','商品属性分类管理','/productAttribute/**',NULL,1),(3,'2020-02-04 17:06:13','商品属性管理','/productAttribute/**',NULL,1),(4,'2020-02-04 17:07:15','商品分类管理','/productCategory/**',NULL,1),(5,'2020-02-04 17:09:16','商品管理','/product/**',NULL,1),(6,'2020-02-04 17:09:53','商品库存管理','/sku/**',NULL,1),(8,'2020-02-05 14:43:37','订单管理','/order/**','',2),(9,'2020-02-05 14:44:22',' 订单退货申请管理','/returnApply/**','',2),(10,'2020-02-05 14:45:08','退货原因管理','/returnReason/**','',2),(11,'2020-02-05 14:45:43','订单设置管理','/orderSetting/**','',2),(12,'2020-02-05 14:46:23','收货地址管理','/companyAddress/**','',2),(13,'2020-02-07 16:37:22','优惠券管理','/coupon/**','',3),(14,'2020-02-07 16:37:59','优惠券领取记录管理','/couponHistory/**','',3),(15,'2020-02-07 16:38:28','限时购活动管理','/flash/**','',3),(16,'2020-02-07 16:38:59','限时购商品关系管理','/flashProductRelation/**','',3),(17,'2020-02-07 16:39:22','限时购场次管理','/flashSession/**','',3),(18,'2020-02-07 16:40:07','首页轮播广告管理','/home/advertise/**','',3),(19,'2020-02-07 16:40:34','首页品牌管理','/home/brand/**','',3),(20,'2020-02-07 16:41:06','首页新品管理','/home/newProduct/**','',3),(21,'2020-02-07 16:42:16','首页人气推荐管理','/home/recommendProduct/**','',3),(22,'2020-02-07 16:42:48','首页专题推荐管理','/home/recommendSubject/**','',3),(23,'2020-02-07 16:44:56',' 商品优选管理','/prefrenceArea/**','',5),(24,'2020-02-07 16:45:39','商品专题管理','/subject/**','',5),(25,'2020-02-07 16:47:34','后台用户管理','/admin/**','',4),(26,'2020-02-07 16:48:24','后台用户角色管理','/role/**','',4),(27,'2020-02-07 16:48:48','后台菜单管理','/menu/**','',4),(28,'2020-02-07 16:49:18','后台资源分类管理','/resourceCategory/**','',4),(29,'2020-02-07 16:49:45','后台资源管理','/resource/**','',4),(30,'2022-04-14 23:25:29','阿里云','/aliyun/**','图片上传',4),(31,'2022-04-14 23:28:02','会员','/memberLevel/**','',1),(32,'2022-04-15 01:57:05','首页展示内容','/home/**','',1);

/*Table structure for table `ums_resource_category` */

DROP TABLE IF EXISTS `ums_resource_category`;

CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

/*Data for the table `ums_resource_category` */

insert  into `ums_resource_category`(`id`,`create_time`,`name`,`sort`) values (1,'2020-02-05 10:21:44','商品模块',0),(2,'2020-02-05 10:22:34','订单模块',0),(3,'2020-02-05 10:22:48','营销模块',0),(4,'2020-02-05 10:23:04','权限模块',0),(5,'2020-02-07 16:34:27','内容模块',0),(6,'2020-02-07 16:35:49','其他模块',0);

/*Table structure for table `ums_role` */

DROP TABLE IF EXISTS `ums_role`;

CREATE TABLE `ums_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

/*Data for the table `ums_role` */

insert  into `ums_role`(`id`,`name`,`description`,`admin_count`,`create_time`,`status`,`sort`) values (1,'商品管理员','只能查看及操作商品',0,'2020-02-03 16:50:37',1,0),(2,'订单管理员','只能查看及操作订单',0,'2018-09-30 15:53:45',0,0),(5,'超级管理员','拥有所有查看和操作功能',0,'2020-02-02 15:11:05',0,0),(8,'权限管理员','用于权限模块所有操作功能',0,'2020-08-24 10:57:35',0,0),(9,'系统管理员','开发者模式',0,'2022-04-07 00:16:28',1,0);

/*Table structure for table `ums_role_menu_relation` */

DROP TABLE IF EXISTS `ums_role_menu_relation`;

CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

/*Data for the table `ums_role_menu_relation` */

insert  into `ums_role_menu_relation`(`id`,`role_id`,`menu_id`) values (53,2,7),(54,2,8),(55,2,9),(56,2,10),(57,2,11),(72,5,1),(73,5,2),(74,5,3),(75,5,4),(76,5,5),(77,5,6),(78,5,7),(79,5,8),(80,5,9),(81,5,10),(82,5,11),(83,5,12),(84,5,13),(85,5,14),(86,5,16),(87,5,17),(88,5,18),(89,5,19),(90,5,20),(91,5,21),(92,5,22),(93,5,23),(94,5,24),(95,5,25),(96,6,21),(97,6,22),(98,6,23),(99,6,24),(100,6,25),(101,7,21),(102,7,22),(103,7,23),(104,7,24),(105,7,25),(106,8,21),(107,8,22),(108,8,23),(109,8,24),(110,8,25),(135,9,1),(136,9,2),(137,9,3),(138,9,4),(139,9,5),(140,9,6),(141,9,7),(142,9,8),(143,9,9),(144,9,10),(145,9,11),(146,9,12),(147,9,13),(148,9,14),(149,9,16),(150,9,17),(151,9,18),(152,9,19),(153,9,20),(154,9,26),(155,9,21),(156,9,22),(157,9,23),(158,9,24),(159,9,25),(171,1,1),(172,1,2),(173,1,3),(174,1,4),(175,1,5),(176,1,6);

/*Table structure for table `ums_role_permission_relation` */

DROP TABLE IF EXISTS `ums_role_permission_relation`;

CREATE TABLE `ums_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

/*Data for the table `ums_role_permission_relation` */

insert  into `ums_role_permission_relation`(`id`,`role_id`,`permission_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,7),(5,1,8),(6,2,4),(7,2,9),(8,2,10),(9,2,11),(10,3,5),(11,3,12),(12,3,13),(13,3,14),(14,4,6),(15,4,15),(16,4,16),(17,4,17);

/*Table structure for table `ums_role_resource_relation` */

DROP TABLE IF EXISTS `ums_role_resource_relation`;

CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=396 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

/*Data for the table `ums_role_resource_relation` */

insert  into `ums_role_resource_relation`(`id`,`role_id`,`resource_id`) values (103,2,8),(104,2,9),(105,2,10),(106,2,11),(107,2,12),(142,5,1),(143,5,2),(144,5,3),(145,5,4),(146,5,5),(147,5,6),(148,5,8),(149,5,9),(150,5,10),(151,5,11),(152,5,12),(153,5,13),(154,5,14),(155,5,15),(156,5,16),(157,5,17),(158,5,18),(159,5,19),(160,5,20),(161,5,21),(162,5,22),(163,5,23),(164,5,24),(165,5,25),(166,5,26),(167,5,27),(168,5,28),(169,5,29),(178,6,25),(179,6,26),(180,6,27),(181,6,28),(182,6,29),(205,7,25),(206,7,26),(207,7,27),(208,7,28),(209,7,29),(210,7,31),(211,8,25),(212,8,26),(213,8,27),(214,8,28),(215,8,29),(340,9,1),(341,9,2),(342,9,3),(343,9,4),(344,9,5),(345,9,6),(346,9,8),(347,9,9),(348,9,10),(349,9,11),(350,9,12),(351,9,13),(352,9,14),(353,9,15),(354,9,16),(355,9,17),(356,9,18),(357,9,19),(358,9,20),(359,9,21),(360,9,22),(361,9,23),(362,9,24),(363,9,25),(364,9,26),(365,9,27),(366,9,28),(367,9,29),(368,9,30),(369,9,31),(370,9,32),(387,1,1),(388,1,2),(389,1,3),(390,1,4),(391,1,5),(392,1,6),(393,1,25),(394,1,31),(395,1,32);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
