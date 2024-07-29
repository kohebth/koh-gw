-- vps_management.compose definition
CREATE TABLE `vps_management`.`compose` (
	`id` BIGINT auto_increment NOT NULL,
	`name` varchar(100) NOT NULL,
	`template` BLOB NOT NULL,
	CONSTRAINT compose_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;