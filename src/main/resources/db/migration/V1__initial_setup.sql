-- table for each coin change request
CREATE TABLE coin_request (
   id SERIAL NOT NULL PRIMARY KEY,
   bill INT NOT NULL,
   mode VARCHAR(20) NOT NULL,
   requested_by VARCHAR(20),
   returned_coins TEXT NOT NULL,
   remaining_coins TEXT NOT NULL,
   create_date TIMESTAMP NOT NULL,
   modify_date TIMESTAMP NOT NULL
);

-- table to track coin counts
CREATE TABLE IF NOT EXISTS coin_box (
   coin_id INT NOT NULL PRIMARY KEY,
   max_amount INT NOT NULL,
   remaining_amount INT NOT NULL,
   create_date TIMESTAMP NOT NULL,
   modify_date TIMESTAMP NOT NULL
);
