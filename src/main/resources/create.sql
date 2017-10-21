CREATE TABLE post (
	id BIGINT auto_increment PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
	message TEXT NOT NULL,
	posttime DATE NOT NULL
);
CREATE INDEX post_user ON post (username);

CREATE TABLE subscription (
	username VARCHAR(32) NOT NULL,
	subscription VARCHAR(32) NOT NULL,
	PRIMARY KEY(username, subscription)
);
