DROP TABLE IF EXISTS prices;

CREATE TABLE prices
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id   BIGINT     NOT NULL,
    start_date DATETIME   NOT NULL,
    end_date   DATETIME   NOT NULL,
    price_list BIGINT     NOT NULL,
    product_id BIGINT     NOT NULL,
    priority   INTEGER    NOT NULL,
    price      DOUBLE     NOT NULL,
    currency   VARCHAR(3) NOT NULL
);
