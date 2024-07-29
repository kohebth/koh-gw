-- vps_management.`user` definition
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `password` varchar(100) NOT NULL,
  CONSTRAINT user_pk PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- vps_management.`session` definition
CREATE TABLE `session` (
	`id` bigint(20) unsigned auto_increment NOT NULL,
	`user_id` bigint(20) unsigned NOT NULL,
	`session_id` varchar(128) NOT NULL,
	`device_id` varchar(128) NOT NULL,
	`version_id` varchar(16) NULL,
	`expire_time` timestamp NOT NULL,
	CONSTRAINT session_pk PRIMARY KEY (id),
	CONSTRAINT session_user_FK FOREIGN KEY (user_id) REFERENCES vps_management.`user`(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
