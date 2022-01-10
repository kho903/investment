DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products
(
    id                     bigint       NOT NULL AUTO_INCREMENT, --id
    title                  varchar(100) NOT NULL,                --상품명
    total_investing_amount bigint       NOT NULL,                --총 투자 모집금액
    started_at             datetime     NOT NULL,                --투자시작일시
    finished_at            datetime     NOT NULL,                --투자종료일시
    PRIMARY KEY (id)
);

CREATE TABLE investment
(
    id                  bigint          NOT NULL AUTO_INCREMENT,
    product_id          bigint          NOT NULL,
    invested_amount      bigint          NOT NULL,
    investment_status    varchar(100)    NOT NULL,
    invested_at          datetime        NOT NULL,
    user_id             bigint,
    foreign key (product_id) references products(id)
);