CREATE TABLE ORDER_ITEM (
  product_code VARCHAR2(20) PRIMARY KEY,
  product_name VARCHAR2(100),
  quantity   NUMBER,
  cost number(7,2));