CREATE TABLE user
(
    id            VARCHAR(36) PRIMARY KEY NOT NULL,
    email         VARCHAR(255) UNIQUE     NOT NULL,
    name          VARCHAR(100),
    nickname      VARCHAR(100),
    phone         VARCHAR(100),
    role          ENUM('ADMIN', 'USER')   NOT NULL DEFAULT 'USER',
    status        ENUM('ACTIVE', 'BANNED', 'DELETED') NOT NULL DEFAULT 'ACTIVE',
    type          ENUM('KAKAO', 'NAVER')  NOT NULL,
    rtk           TEXT,
    last_login_at TIMESTAMP,
    created_at    TIMESTAMP               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_login_history
(
    id          VARCHAR(36) PRIMARY KEY NOT NULL,
    user_id     VARCHAR(36)             NOT NULL,
    ip_address  VARCHAR(45)             NOT NULL,
    login_type  ENUM('KAKAO', 'NAVER')  NOT NULL,
    login_at    TIMESTAMP               NOT NULL DEFAULT CURRENT_TIMESTAMP,
    logout_at   TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;