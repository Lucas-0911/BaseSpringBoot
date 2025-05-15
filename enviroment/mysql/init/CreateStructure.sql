-- =============================================
-- CREATE STRUCTURE
-- =============================================
DROP DATABASE IF EXISTS department_manager;
CREATE DATABASE department_manager
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

USE department_manager;

-- create table 1: department
CREATE TABLE department (
                            id 					SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            `name` 				NVARCHAR(100) UNIQUE KEY NOT NULL,
                            member_size			INT UNSIGNED DEFAULT 0,
                            manager_id			INT UNSIGNED,
                            creator_id			INT UNSIGNED NOT NULL,
                            created_date_time	DATETIME NOT NULL DEFAULT NOW(),
                            modifier_id			INT UNSIGNED NOT NULL,
                            updated_date_time	DATETIME NOT NULL DEFAULT NOW()
);

-- create table 2: account
CREATE TABLE `account` (
                           id 								INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                           firstname 						NVARCHAR(50) NOT NULL,
                           lastname 						NVARCHAR(50) NOT NULL,
                           email 							VARCHAR(50) UNIQUE KEY NOT NULL,
                           username						VARCHAR(20) UNIQUE KEY NOT NULL,
                           `password`						VARCHAR(800) NOT NULL,
                           `status`						BIT NOT NULL DEFAULT 0, -- 0: Block, 1: Active
                           department_id					SMALLINT UNSIGNED,
                           `role`							ENUM('ADMIN', 'EMPLOYEE','MANAGER') DEFAULT 'EMPLOYEE',
                           last_change_password_date_time	DATETIME NOT NULL DEFAULT NOW(),
                           creator_id						INT UNSIGNED,
                           created_date_time				DATETIME NOT NULL DEFAULT NOW(),
                           modifier_id						INT UNSIGNED,
                           updated_date_time				DATETIME NOT NULL DEFAULT NOW(),
                           FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE SET NULL,
                           FOREIGN KEY (creator_id) REFERENCES `account`(id),
                           FOREIGN KEY (modifier_id) REFERENCES `account`(id)
);

-- create table 3: group
CREATE TABLE `group` (
                         id 					SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         `name`				NVARCHAR(100) UNIQUE KEY NOT NULL,
                         member_size 		INT UNSIGNED DEFAULT 0,
                         creator_id			INT UNSIGNED NOT NULL,
                         created_date_time	DATETIME NOT NULL DEFAULT NOW(),
                         modifier_id			INT UNSIGNED NOT NULL,
                         updated_date_time	DATETIME NOT NULL DEFAULT NOW(),
                         FOREIGN KEY (creator_id) REFERENCES `account`(id),
                         FOREIGN KEY (modifier_id) REFERENCES `account`(id)
);

-- create table 4: group_account
CREATE TABLE group_account (
                               group_id 		SMALLINT UNSIGNED NOT NULL,
                               account_id 		INT UNSIGNED NOT NULL,
                               join_date_time	DATETIME NOT NULL DEFAULT NOW(),
                               PRIMARY KEY(group_id, account_id),
                               FOREIGN KEY (group_id) REFERENCES `group`(id) ON DELETE CASCADE,
                               FOREIGN KEY (account_id) REFERENCES `account`(id) ON DELETE CASCADE
);

-- create table 5: token
CREATE TABLE `token`(
                        id						INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        account_id				INT UNSIGNED NOT NULL,
                        `key` 					VARCHAR(100) NOT NULL UNIQUE,
                        `type` 					ENUM('REFRESH_TOKEN','REGISTER','FORGOT_PASSWORD') NOT NULL,
                        expired_date_time		DATETIME NOT NULL,
                        FOREIGN KEY(account_id) REFERENCES `Account`(id) ON DELETE CASCADE
);


INSERT INTO department  (`name`,				manager_id,		member_size,	creator_id,		created_date_time, 			modifier_id,	updated_date_time		)
VALUES
    ('Marketing',				8,				3,				1,			NOW()					,		2,			NOW()					),
    ('Sale',					5,				2,				5,			'2021-07-07 12:45:56'	,		1,			'2021-07-07 12:45:56'	),
    ('Sercurity',				10,				4,				2,			'2020-07-07 12:45:56'	,		2,			'2020-07-07 12:45:56'	),
    ('Technical',				3,				1,				1,			'2022-07-07 12:45:56'	,		4,			'2022-07-07 12:45:56'	),
    ('Accounting ',				NULL,			0,				3,			NOW()					,		5,			NOW()					),
    ('Director',				NULL,			1,				1,			NOW()					,		3,			NOW()					),
    ('Audit',					NULL,			0,				5,			'2019-07-07 12:45:56'	,		5,			'2019-07-07 12:45:56'	),
    ('Purchasing',				NULL,			0,				6,			NOW()					,		2,			NOW()					),
    ('Training',				NULL,			0,				1,			NOW()					,		5,			NOW()					),
    ('Administration',			NULL,			0,				6,			NOW()					,		3,			NOW()					),
    ('HR',						NULL,			3,				3,			'2018-07-07 12:45:56'	,		5,			'2018-07-07 12:45:56'	),
    ('Customer Service',		NULL,			0,				5,			NOW()					,		4,			NOW()					),
    ('Vice director',			NULL,			0,				4,			'2017-07-07 12:45:56'	,		3,			'2017-07-07 12:45:56'	),
    ('IT',						NULL,			0,				2,			'2016-07-07 12:45:56'	,		6,			'2016-07-07 12:45:56'	),
    ('IT help desk',			NULL,			0,				8,			NOW()					,		2,			NOW()					),
    ('International Payment',	NULL,			0,				7,			'2022-05-07 12:45:56'	,		7,			'2022-05-07 12:45:56'	),
    ('Financial',				NULL,			0,				8,			NOW()					,		8,			NOW()					),
    ('International Relations',	NULL,			0,				2,			'2023-02-07 12:45:56'	,		6,			'2023-02-07 12:45:56'	),
    ('Local Payment',			NULL,			0,				3,			'2021-12-07 12:45:56'	,		5,			'2021-12-07 12:45:56'	),
    ('Public Relations',		NULL,			0,				4,			NOW()					,		1,			NOW()					);

-- password: 123456
INSERT INTO  `account`  (	firstname,			lastname, 			email, 						username,									`password`, 									`status`,		department_id, 	`role`,			created_date_time		,		updated_date_time )
VALUES
    (	N'Le Quang',		N'Phuc',			'phuclq0911@gmail.com',			'phuclq',				'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				6,			'Admin',		'2019-10-01 12:45:56'	,	'2019-10-01 12:45:56'),
    (	N'Trần Văn',		N'Thiết',		'thiettv98@gmail.com',			'thiettv98',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				3,			'Employee',		'2020-11-02 12:45:56'	,	'2020-11-02 12:45:56'),
    (	N'Nguyễn Thế',		N'Đức',			'ntd19795@gmail.com',			'duc.nguyenthe',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				4,			'Manager',		'2017-05-09 12:45:56'	,	'2017-05-09 12:45:56'),
    (	N'Vũ Hoàng',		N'Hiệp',		'Vuhoanghiepnb@gmail.com',		'hiep.vuhoang',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				3,			'Employee',		'2016-04-09 12:45:56'	,	'2016-04-09 12:45:56'),
    (	N'Nguyễn Thị Mỹ',	N'Nhung', 		'nhung.nguyenthi@gmail.com',	'nhung.nguyenthi',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				2,			'Manager',		'2015-11-09 12:45:56'	,	'2015-11-09 12:45:56'),
    (	N'Tống Thị',		N'Nhung',		'nhung.tongthi@vpt.com.vn',		'nhung.tongthi',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				11,			'Employee',		NOW()					,	NOW()				 ),
    (	N'Mai Chiến',		N'Thắng',		'thang.maichien@vpt.com.vn',	'thang.maichien',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			0,				3,			'Employee',		NOW()					,	NOW()				 ),
    (	N'Đinh Thu',		N'Thảo',		'thao.dinhthu@gmail.com.vn',	'thao.dinhthu',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				1,			'Manager',		'2020-11-11 12:45:56'	,	'2020-11-11 12:45:56'),
    (	N'Nguyễn Thị',		N'Linh',		'linhnt@gmail.com',				'linh.nguyenthi',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		NOW()					,	NOW()				 ),
    (	N'Văn Thị',			N'Linh',		'linhvt@gmail.com',				'linh.vanthi',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				3,			'Manager',		'2023-01-22 12:45:56'	,	'2023-01-22 12:45:56'),
    (	N'Trần Văn',		N'Đạt',			'dattv@gmail.com',				'dat.tranvan',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				2,			'Employee',		NOW()					,	NOW()				 ),
    (	N'Nguyễn Xuân',		N'Tùng',		'tungnx@gmail.com',				'tung.nguyenxuan',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		NOW()					,	NOW()				 ),
    (	N'Nguyễn Tùng',		N'Lâm',			'lamnt@gmail.com',				'lam.nguyentung',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		'2015-05-21 12:45:56'	,	'2015-05-21 12:45:56'),
    (	N'Trịnh Thị Thu',	N'Hiền', 		'hienttt@gmail.com',			'hien.trinhthithu',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		'2023-02-17 12:45:56'	,	'2023-02-17 12:45:56'),
    (	N'Phạm Tuấn',		N'Đạt',			'datpt@gmail.com.vn',			'dat.phamtuan',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				1,			'Employee',		'2022-07-07 12:45:56'	,	'2022-07-07 12:45:56'),
    (	N'Nguyễn Văn',		N'Hưởng',		'huongnv@gmail.com.vn',			'huong.nguyenvan',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		NOW()					,	NOW()				 ),
    (	N'Tran Văn',		N'Nam',			'namnv@gmail.com.vn',			'nam.tranvan',			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				1,			'Employee',		'2021-04-12 12:45:56'	,	'2021-04-12 12:45:56'),
    (	N'Nguyễn Thế',		N'Hưng',		'hungnt@gmail.com.vn',			'hung.nguyenthe',		'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				null,		'Employee',		'2022-06-25 12:45:56'	,	'2022-06-25 12:45:56'),
    (	N'Lê Thị',			N'Na',			'Nalt@gmail.com.vn',			'le.thina',				'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			0,				11,			'Employee',		'2023-02-17 12:45:56'	,	'2023-02-17 12:45:56'),
    (	N'Lê Văn',			N'Nam',			'Namlv@gmail.com.vn',			'le.vanna',				'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi',			1,				11,			'Employee',		'2023-02-17 12:45:56'	,	'2023-02-17 12:45:56');

INSERT INTO `group` (`name`, 		member_size,	creator_id, 		created_date_time,	modifier_id,	updated_date_time	)
VALUES
    ('2NYNE',			3,				15,			'2020-11-02 12:45:56'	,	15,		'2020-11-02 12:45:56'	),
    ('aespa',			1,				7,			'2019-03-15 12:45:56'	,	7,		'2019-03-15 12:45:56'	),
    ('B.O.Y',			4,				3,			'2018-11-02 12:45:56'	,	3,		'2018-11-02 12:45:56'	),
    ('BAE173',			1,				10,			'2011-07-09 12:45:56'	,	10,		'2011-07-09 12:45:56'	),
    ('Bling Bling',		3,				5,			'2012-08-20 12:45:56'	,	5,		'2012-08-20 12:45:56'	),
    ('Craxy',			0,				2,			'2020-11-11 12:45:56'	,	2,		'2020-11-11 12:45:56'	),
    ('DBSK',			0,				1,			'2021-12-20 12:45:56'	,	1,		'2021-12-20 12:45:56'	),
    ('ZAM',				1,				3,			'2023-01-05 12:45:56'	,	3,		'2023-01-05 12:45:56'	),
    ('Blackpink',		1,				13,			'2023-02-14 12:45:56'	,	13,		'2023-02-14 12:45:56'	),
    ('BTS',				0,				7,			'2021-10-02 12:45:56'	,	7,		'2021-10-02 12:45:56'	),
    ('EXO',				2,				20,			'2022-01-03 12:45:56'	,	20,		'2022-01-03 12:45:56'	),
    ('Red Velvet',		2,				4,			'2022-09-25 12:45:56'	,	4,		'2022-09-25 12:45:56'	),
    ('Girl Generation',	2,				4,			'2020-10-14 12:45:56'	,	4,		'2020-10-14 12:45:56'	),
    ('TWICE',			0,				3,			'2021-05-20 12:45:56'	,	3,		'2021-05-20 12:45:56'	),
    ('2NE1',			0,				9,			'2018-02-02 12:45:56'	,	9,		'2018-02-02 12:45:56'	),
    ('TVXQ',			0,				11,			'2015-01-21 12:45:56'	,	11,		'2015-01-21 12:45:56'	),
    ('NCT',				0,				13,			'2019-08-24 12:45:56'	,	13,		'2019-08-24 12:45:56'	),
    ('Shinee',			0,				4,			'2011-11-11 12:45:56'	,	4,		'2011-11-11 12:45:56'	),
    ('Mamamoo',			0,				12,			'2022-12-20 12:45:56'	,	12,		'2022-12-20 12:45:56'	),
    ('NCT2',			0,				3,			'2017-08-17 12:45:56'	,	3,		'2017-08-17 12:45:56'	);

INSERT INTO group_account 	(group_id,	account_id, 	join_date_time		)
VALUES
    (5, 			10	,	'2020-11-02 12:45:56'	),
    (2, 			4	,	'2019-03-15 12:45:56'	),
    (11, 			7	,	'2018-11-02 12:45:56'	),
    (1, 			3	,	'2011-07-09 12:45:56'	),
    (5, 			1	,	'2012-08-20 12:45:56'	),
    (8, 			13	,	'2020-11-11 12:45:56'	),
    (3, 			11	,	'2021-12-20 12:45:56'	),
    (5, 			3	,	'2023-01-05 12:45:56'	),
    (12, 			14	,	'2023-02-14 12:45:56'	),
    (1, 			7	,	'2021-10-02 12:45:56'	),
    (1, 			15	,	'2022-01-03 12:45:56'	),
    (12, 			2	,	'2022-09-25 12:45:56'	),
    (13, 			13	,	'2020-10-14 12:45:56'	),
    (3, 			12	,	'2023-02-14 12:45:56'	),
    (3, 			1	,	'2021-10-02 12:45:56'	),
    (11, 			13	,	'2022-01-03 12:45:56'	),
    (9, 			15	,	'2022-09-25 12:45:56'	),
    (4, 			2	,	'2020-10-14 12:45:56'	),
    (13, 			3	,	'2022-09-25 12:45:56'	),
    (3, 			5	,	'2020-10-14 12:45:56'	);

ALTER TABLE department
    ADD FOREIGN KEY (manager_id) REFERENCES `account`(id);
ALTER TABLE department
    ADD FOREIGN KEY (creator_id) REFERENCES `account`(id);
ALTER TABLE department
    ADD FOREIGN KEY (modifier_id) REFERENCES `account`(id);