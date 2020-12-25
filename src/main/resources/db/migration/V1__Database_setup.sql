CREATE TABLE "users" (
  "id" BIGSERIAL PRIMARY KEY,
  "username" varchar,
  "email" varchar,
  "birth_date" date,
  "password_digest" varchar,
  "delete_after_days" smallint,
  "rate" decimal,
  "times_rated" smallint,
  "created_at" date,
  "updated_at" timestamp,
  "last_login_at" timestamp
);

CREATE TABLE "contacts" (
  "user_id" bigint,
  "contact_id" bigint,
  "messages_shared" bigint,
  "contacts_overall" bigint
);

CREATE TABLE "posts" (
  "id" BIGSERIAL PRIMARY KEY,
  "author_id" bigint,
  "rate" decimal,
  "times_rated" smallint
);

CREATE TABLE "comments" (
  "post_id" bigint,
  "author_id" bigint,
  "message" varchar,
  "modified_at" timestamp
);

CREATE TABLE "categories" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar,
  "description" varchar
);

CREATE TABLE "users_categories" (
  "user_id" bigint,
  "category_id" smallint
);

CREATE TABLE "posts_categories" (
  "post_id" bigint,
  "category_id" smallint
);

ALTER TABLE "contacts" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "contacts" ADD FOREIGN KEY ("contact_id") REFERENCES "users" ("id");

ALTER TABLE "posts" ADD FOREIGN KEY ("author_id") REFERENCES "users" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "comments" ADD FOREIGN KEY ("author_id") REFERENCES "users" ("id");

ALTER TABLE "users_categories" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "users_categories" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "posts_categories" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "posts_categories" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

CREATE UNIQUE INDEX ON "contacts" ("user_id", "contact_id");

CREATE UNIQUE INDEX ON "comments" ("post_id", "author_id");