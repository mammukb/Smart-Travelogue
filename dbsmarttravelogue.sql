/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.9 : Database - django_smart_travelogue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`django_smart_travelogue` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `django_smart_travelogue`;

/*Table structure for table `auth_group` */

DROP TABLE IF EXISTS `auth_group`;

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group` */

/*Table structure for table `auth_group_permissions` */

DROP TABLE IF EXISTS `auth_group_permissions`;

CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_group_id_b120cbf9` (`group_id`),
  KEY `auth_group_permissions_permission_id_84c5c92e` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_group_permissions` */

/*Table structure for table `auth_permission` */

DROP TABLE IF EXISTS `auth_permission`;

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  KEY `auth_permission_content_type_id_2f476e4b` (`content_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

/*Data for the table `auth_permission` */

insert  into `auth_permission`(`id`,`name`,`content_type_id`,`codename`) values 
(1,'Can add log entry',1,'add_logentry'),
(2,'Can change log entry',1,'change_logentry'),
(3,'Can delete log entry',1,'delete_logentry'),
(4,'Can view log entry',1,'view_logentry'),
(5,'Can add permission',2,'add_permission'),
(6,'Can change permission',2,'change_permission'),
(7,'Can delete permission',2,'delete_permission'),
(8,'Can view permission',2,'view_permission'),
(9,'Can add group',3,'add_group'),
(10,'Can change group',3,'change_group'),
(11,'Can delete group',3,'delete_group'),
(12,'Can view group',3,'view_group'),
(13,'Can add user',4,'add_user'),
(14,'Can change user',4,'change_user'),
(15,'Can delete user',4,'delete_user'),
(16,'Can view user',4,'view_user'),
(17,'Can add content type',5,'add_contenttype'),
(18,'Can change content type',5,'change_contenttype'),
(19,'Can delete content type',5,'delete_contenttype'),
(20,'Can view content type',5,'view_contenttype'),
(21,'Can add session',6,'add_session'),
(22,'Can change session',6,'change_session'),
(23,'Can delete session',6,'delete_session'),
(24,'Can view session',6,'view_session'),
(25,'Can add facility',7,'add_facility'),
(26,'Can change facility',7,'change_facility'),
(27,'Can delete facility',7,'delete_facility'),
(28,'Can view facility',7,'view_facility'),
(29,'Can add login',8,'add_login'),
(30,'Can change login',8,'change_login'),
(31,'Can delete login',8,'delete_login'),
(32,'Can view login',8,'view_login'),
(33,'Can add notification',9,'add_notification'),
(34,'Can change notification',9,'change_notification'),
(35,'Can delete notification',9,'delete_notification'),
(36,'Can view notification',9,'view_notification'),
(37,'Can add place',10,'add_place'),
(38,'Can change place',10,'change_place'),
(39,'Can delete place',10,'delete_place'),
(40,'Can view place',10,'view_place'),
(41,'Can add user',11,'add_user'),
(42,'Can change user',11,'change_user'),
(43,'Can delete user',11,'delete_user'),
(44,'Can view user',11,'view_user'),
(45,'Can add travelogue',12,'add_travelogue'),
(46,'Can change travelogue',12,'change_travelogue'),
(47,'Can delete travelogue',12,'delete_travelogue'),
(48,'Can view travelogue',12,'view_travelogue'),
(49,'Can add review',13,'add_review'),
(50,'Can change review',13,'change_review'),
(51,'Can delete review',13,'delete_review'),
(52,'Can view review',13,'view_review'),
(53,'Can add package',14,'add_package'),
(54,'Can change package',14,'change_package'),
(55,'Can delete package',14,'delete_package'),
(56,'Can view package',14,'view_package'),
(57,'Can add media',15,'add_media'),
(58,'Can change media',15,'change_media'),
(59,'Can delete media',15,'delete_media'),
(60,'Can view media',15,'view_media'),
(61,'Can add feedback',16,'add_feedback'),
(62,'Can change feedback',16,'change_feedback'),
(63,'Can delete feedback',16,'delete_feedback'),
(64,'Can view feedback',16,'view_feedback'),
(65,'Can add checkin',17,'add_checkin'),
(66,'Can change checkin',17,'change_checkin'),
(67,'Can delete checkin',17,'delete_checkin'),
(68,'Can view checkin',17,'view_checkin');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user` */

/*Table structure for table `auth_user_groups` */

DROP TABLE IF EXISTS `auth_user_groups`;

CREATE TABLE `auth_user_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_user_id_6a12ed8b` (`user_id`),
  KEY `auth_user_groups_group_id_97559544` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_groups` */

/*Table structure for table `auth_user_user_permissions` */

DROP TABLE IF EXISTS `auth_user_user_permissions`;

CREATE TABLE `auth_user_user_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_user_id_a95ead1b` (`user_id`),
  KEY `auth_user_user_permissions_permission_id_1fbb5f2c` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `auth_user_user_permissions` */

/*Table structure for table `django_admin_log` */

DROP TABLE IF EXISTS `django_admin_log`;

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_admin_log` */

/*Table structure for table `django_content_type` */

DROP TABLE IF EXISTS `django_content_type`;

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `django_content_type` */

insert  into `django_content_type`(`id`,`app_label`,`model`) values 
(1,'admin','logentry'),
(2,'auth','permission'),
(3,'auth','group'),
(4,'auth','user'),
(5,'contenttypes','contenttype'),
(6,'sessions','session'),
(7,'travelogue_app','facility'),
(8,'travelogue_app','login'),
(9,'travelogue_app','notification'),
(10,'travelogue_app','place'),
(11,'travelogue_app','user'),
(12,'travelogue_app','travelogue'),
(13,'travelogue_app','review'),
(14,'travelogue_app','package'),
(15,'travelogue_app','media'),
(16,'travelogue_app','feedback'),
(17,'travelogue_app','checkin');

/*Table structure for table `django_migrations` */

DROP TABLE IF EXISTS `django_migrations`;

CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `django_migrations` */

insert  into `django_migrations`(`id`,`app`,`name`,`applied`) values 
(1,'contenttypes','0001_initial','2024-02-09 08:52:17.995247'),
(2,'auth','0001_initial','2024-02-09 08:52:18.370048'),
(3,'admin','0001_initial','2024-02-09 08:52:18.459019'),
(4,'admin','0002_logentry_remove_auto_add','2024-02-09 08:52:18.467517'),
(5,'admin','0003_logentry_add_action_flag_choices','2024-02-09 08:52:18.478608'),
(6,'contenttypes','0002_remove_content_type_name','2024-02-09 08:52:18.528578'),
(7,'auth','0002_alter_permission_name_max_length','2024-02-09 08:52:18.552719'),
(8,'auth','0003_alter_user_email_max_length','2024-02-09 08:52:18.580707'),
(9,'auth','0004_alter_user_username_opts','2024-02-09 08:52:18.593612'),
(10,'auth','0005_alter_user_last_login_null','2024-02-09 08:52:18.618516'),
(11,'auth','0006_require_contenttypes_0002','2024-02-09 08:52:18.625056'),
(12,'auth','0007_alter_validators_add_error_messages','2024-02-09 08:52:18.635124'),
(13,'auth','0008_alter_user_username_max_length','2024-02-09 08:52:18.661992'),
(14,'auth','0009_alter_user_last_name_max_length','2024-02-09 08:52:18.703250'),
(15,'auth','0010_alter_group_name_max_length','2024-02-09 08:52:18.733543'),
(16,'auth','0011_update_proxy_permissions','2024-02-09 08:52:18.745187'),
(17,'auth','0012_alter_user_first_name_max_length','2024-02-09 08:52:18.785754'),
(18,'sessions','0001_initial','2024-02-09 08:52:18.817937'),
(19,'travelogue_app','0001_initial','2024-02-09 08:52:19.309176'),
(20,'travelogue_app','0002_remove_review_photo2','2024-02-15 11:52:37.184464'),
(21,'travelogue_app','0003_auto_20240216_0843','2024-02-16 03:13:13.859525');

/*Table structure for table `django_session` */

DROP TABLE IF EXISTS `django_session`;

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `django_session` */

insert  into `django_session`(`session_key`,`session_data`,`expire_date`) values 
('3mb4p4tukcj7zwt6kj9vftcjvo9xmghp','eyJsb2dpbl9pZCI6MX0:1rZMnk:23EgiKJlS-yGwK6vw8nyuMlzM4nn42WmGCO_AqWDfgg','2024-02-26 03:16:12.057104'),
('7diwxcixu0c25tkw3857ug8yeo9bi7b4','eyJsb2dpbl9pZCI6MX0:1ra65G:fr_zDK5vXabDmaYgHb0P3cIpdmW8vnXXDX6lv-yBXVU','2024-02-28 03:37:18.826300'),
('6lgakq4ipm1vmdqke7icjf31m40xvlww','eyJsb2dpbl9pZCI6MX0:1ra7Yz:5Afmys2JRBulgBSyukZ0TRTW0fsb1jYul1cZ2xxumZA','2024-02-28 05:12:05.502667'),
('z7tvr8jal956kpqt8uuq01xvpw7y9bsk','eyJsb2dpbl9pZCI6MX0:1raSKq:z_hEjDiaKD-u7jGeZaY3noSu6-_Z2GVZAcXuk12v6SI','2024-02-29 03:22:52.789389'),
('i858ywogmlv5rj7ycikll31z2oahiaxy','eyJsb2dpbl9pZCI6MX0:1rbBDL:zm0aMFaQ0uceVENQ4lz_prYjsnsJ06ZXIO6fSsGLOEw','2024-03-02 03:18:07.488186'),
('hnr3kght3j3yorupi38j63wij4p0q18m','eyJsb2dpbl9pZCI6MX0:1rbZVr:oYOFbM1tphkT0UoiBDknLyJfApYXGfR7KKhRawMT_zY','2024-03-03 05:14:51.048770'),
('42t0ifxr5ipdgxr9v8oibucv0yvk01md','eyJsb2dpbl9pZCI6MX0:1rbeyq:U7HJk_BIp6oYOM7imr2JS9jrVswdVKUO2Da1yW8C2U4','2024-03-03 11:05:08.748195');

/*Table structure for table `travelogue_app_checkin` */

DROP TABLE IF EXISTS `travelogue_app_checkin`;

CREATE TABLE `travelogue_app_checkin` (
  `checkin_id` int(11) NOT NULL AUTO_INCREMENT,
  `overall_exp` varchar(200) NOT NULL,
  `facility_id` int(11) NOT NULL,
  `travelogue_id` int(11) NOT NULL,
  PRIMARY KEY (`checkin_id`),
  KEY `travelogue_app_checkin_facility_id_85a29810` (`facility_id`),
  KEY `travelogue_app_checkin_travelogue_id_e1559a5b` (`travelogue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_checkin` */

/*Table structure for table `travelogue_app_facility` */

DROP TABLE IF EXISTS `travelogue_app_facility`;

CREATE TABLE `travelogue_app_facility` (
  `facility_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(200) NOT NULL,
  `f_name` varchar(200) NOT NULL,
  `f_place` varchar(200) NOT NULL,
  `lati` varchar(200) NOT NULL,
  `longi` varchar(200) NOT NULL,
  `city` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  PRIMARY KEY (`facility_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_facility` */

insert  into `travelogue_app_facility`(`facility_id`,`type`,`f_name`,`f_place`,`lati`,`longi`,`city`,`state`,`phone`,`email`,`photo`) values 
(4,'dinner','candle light','thrissur','10.515565305953626','76.21799737215042','thrissur','kerala','7788665566','ssjj@gmail.com','hotel.jpg'),
(5,'food','breakfast','kochi','9.98047557681004','76.2997924734366','ernakulam','kerala','889977666','kk@gmail.com','63791082_f7PiRB1.jpg'),
(6,'csdh','sdbh','sd','10.515568435279608','76.21803152277862','thirssur','kerala','9988776655','hh@gmil.com','chavakkad-beach.jpg');

/*Table structure for table `travelogue_app_feedback` */

DROP TABLE IF EXISTS `travelogue_app_feedback`;

CREATE TABLE `travelogue_app_feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `feedback` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `travelogue_app_feedback_user_id_5d1fb4ce` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_feedback` */

insert  into `travelogue_app_feedback`(`feedback_id`,`feedback`,`date`,`user_id`) values 
(1,'superview','12/2/2024',1);

/*Table structure for table `travelogue_app_login` */

DROP TABLE IF EXISTS `travelogue_app_login`;

CREATE TABLE `travelogue_app_login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `utype` varchar(200) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_login` */

insert  into `travelogue_app_login`(`login_id`,`username`,`password`,`utype`) values 
(1,'admin','000','admin'),
(2,'ammu','000','user'),
(3,'anu','000','user'),
(4,'liya','000','user'),
(5,'elha','123','user');

/*Table structure for table `travelogue_app_media` */

DROP TABLE IF EXISTS `travelogue_app_media`;

CREATE TABLE `travelogue_app_media` (
  `media_id` int(11) NOT NULL AUTO_INCREMENT,
  `media_type` varchar(200) NOT NULL,
  `file` varchar(100) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `place_id` int(11) NOT NULL,
  `travelogue_id` int(11) NOT NULL,
  PRIMARY KEY (`media_id`),
  KEY `travelogue_app_media_place_id_11e85d35` (`place_id`),
  KEY `travelogue_app_media_travelogue_id_3b35569c` (`travelogue_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_media` */

insert  into `travelogue_app_media`(`media_id`,`media_type`,`file`,`title`,`description`,`place_id`,`travelogue_id`) values 
(1,'mp4','file.mp4','snsn','description',1,1),
(2,'mp3','file.mp3','snsn','description',1,1),
(3,'jpg','file.jpg','image','description',1,1),
(4,'mp4','file_sIWlbet.mp4','sjns','djsj',3,2),
(5,'mp4','file_PTbV6y5.mp4','hshs','sussus',3,2),
(6,'mp4','file_6YROAtM.mp4','hai','rheh',3,3);

/*Table structure for table `travelogue_app_notification` */

DROP TABLE IF EXISTS `travelogue_app_notification`;

CREATE TABLE `travelogue_app_notification` (
  `noti_id` int(11) NOT NULL AUTO_INCREMENT,
  `notification` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  PRIMARY KEY (`noti_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_notification` */

insert  into `travelogue_app_notification`(`noti_id`,`notification`,`date`) values 
(9,'hey;)','2024-02-18'),
(8,'ggsdfsd','2024-02-18');

/*Table structure for table `travelogue_app_package` */

DROP TABLE IF EXISTS `travelogue_app_package`;

CREATE TABLE `travelogue_app_package` (
  `package_id` int(11) NOT NULL AUTO_INCREMENT,
  `package_name` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `valid_from` varchar(200) NOT NULL,
  `valid_till` varchar(200) NOT NULL,
  `price` varchar(200) NOT NULL,
  `facility_id` int(11) NOT NULL,
  PRIMARY KEY (`package_id`),
  KEY `travelogue_app_package_facility_id_702c1326` (`facility_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_package` */

insert  into `travelogue_app_package`(`package_id`,`package_name`,`description`,`valid_from`,`valid_till`,`price`,`facility_id`) values 
(3,'sdvbhj','hbqjhdjh','bhjbhjb','hjbhb','778',4);

/*Table structure for table `travelogue_app_place` */

DROP TABLE IF EXISTS `travelogue_app_place`;

CREATE TABLE `travelogue_app_place` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(200) NOT NULL,
  `city` varchar(200) NOT NULL,
  `landmark` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `lati` varchar(200) NOT NULL,
  `longi` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_place` */

insert  into `travelogue_app_place`(`place_id`,`place_name`,`city`,`landmark`,`state`,`lati`,`longi`,`status`) values 
(1,'shakthan','Thrissur','sscd','kerala','10.51556191','76.21805205','pending'),
(3,'marine drive','kochi','marin beach','kerala','9.981841381865937','76.29957575671388','pending');

/*Table structure for table `travelogue_app_review` */

DROP TABLE IF EXISTS `travelogue_app_review`;

CREATE TABLE `travelogue_app_review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `review` varchar(200) NOT NULL,
  `rating` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `photo1` varchar(100) NOT NULL,
  `place_id` int(11) NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `travelogue_app_review_place_id_4d44cad7` (`place_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_review` */

insert  into `travelogue_app_review`(`review_id`,`review`,`rating`,`date`,`photo1`,`place_id`) values 
(1,'nice view ','2.5','2024-02-17','abc_d6J0K1S.jpg',1),
(2,'woww','4.5','2024-02-17','abc_7kTiDSx.jpg',3),
(3,'woww','4.5','2024-02-17','abc_4I6CPU1.jpg',3),
(4,'swwowowo','1.5','2024-02-17','abc_wm2yzLh.jpg',1);

/*Table structure for table `travelogue_app_travelogue` */

DROP TABLE IF EXISTS `travelogue_app_travelogue`;

CREATE TABLE `travelogue_app_travelogue` (
  `travelogue_id` int(11) NOT NULL AUTO_INCREMENT,
  `travel_title` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`travelogue_id`),
  KEY `travelogue_app_travelogue_user_id_0d701849` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_travelogue` */

insert  into `travelogue_app_travelogue`(`travelogue_id`,`travel_title`,`description`,`user_id`) values 
(1,'jsjsj','djdjdjd',1),
(2,'my experience ','it just looking like a wow!',1),
(3,'djdj','djdjd',1),
(4,'hshss','jxj',4);

/*Table structure for table `travelogue_app_user` */

DROP TABLE IF EXISTS `travelogue_app_user`;

CREATE TABLE `travelogue_app_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `place` varchar(200) NOT NULL,
  `contact` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `login_id` int(11) NOT NULL,
  `lati` varchar(200) NOT NULL,
  `longi` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `travelogue_app_user_login_id_cec594ff` (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `travelogue_app_user` */

insert  into `travelogue_app_user`(`user_id`,`name`,`place`,`contact`,`email`,`photo`,`login_id`,`lati`,`longi`) values 
(1,'jini','Kunnamkulam ','7012134815','jini@gmail.com','abc.jpg',2,'10.51557088','76.21805868'),
(2,'anu ','Thrissur ','9876768765','anu@gmail.com','abc_WsRsnJh.jpg',3,'pending','pending'),
(3,'liya','Thrissur ','8987656578','liya@gmail.com','abc_gLliL0R.jpg',4,'pending','pending'),
(4,'elha','Thrissur ','7846574859','elha@gmail.com','abc_KJyQkVQ.jpg',5,'10.51557271','76.2180529');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
