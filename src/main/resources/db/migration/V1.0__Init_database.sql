CREATE TABLE `USERS` (`id` integer PRIMARY KEY AUTO_INCREMENT,
                      `email` varchar(255),
                      `name` varchar(255),
                      `password` varchar(255),
                      `created_at` timestamp,
                      `updated_at` timestamp);

CREATE TABLE `RENTALS` (`id` integer PRIMARY KEY AUTO_INCREMENT,
                        `name` varchar(255),
                        `surface` numeric,
                        `price` numeric,
                        `picture` varchar(255),
                        `description` varchar(2000),
                        `owner_id` integer NOT NULL,
                        `created_at` timestamp,
                        `updated_at` timestamp);

CREATE TABLE `MESSAGES` (`id` integer PRIMARY KEY AUTO_INCREMENT,
                         `rental_id` integer,
                         `user_id` integer,
                         `message` varchar(2000),
                         `created_at` timestamp,
                         `updated_at` timestamp);

CREATE TABLE `ROLES` (`id` integer PRIMARY KEY AUTO_INCREMENT,
                      `name` varchar(255) NOT NULL);

CREATE TABLE `USERS_ROLES` (`user_id` integer NOT NULL,
                            `role_id` integer NOT NULL,
                            PRIMARY KEY (`user_id`,`role_id`));

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);
CREATE UNIQUE INDEX `ROLES_index` ON `ROLES` (`name`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);

ALTER TABLE `USERS_ROLES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
ALTER TABLE `USERS_ROLES` ADD FOREIGN KEY (`role_id`) REFERENCES `ROLES` (`id`);