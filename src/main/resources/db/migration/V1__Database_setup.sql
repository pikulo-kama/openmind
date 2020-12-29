CREATE TABLE IF NOT EXISTS "users" (
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

CREATE TABLE IF NOT EXISTS "user_passwords" (
 "user_id" BIGSERIAL,
 "old_password_digest" VARCHAR
);

CREATE TABLE IF NOT EXISTS "posts" (
  "id" BIGSERIAL PRIMARY KEY,
  "author_id" bigint,
  "rate" decimal,
  "times_rated" smallint
);

CREATE TABLE IF NOT EXISTS "comments" (
  "post_id" bigint,
  "author_id" bigint,
  "message" varchar,
  "modified_at" timestamp
);

CREATE TABLE IF NOT EXISTS "categories" (
  "id" SERIAL PRIMARY KEY,
  "title" varchar,
  "description" varchar
);

CREATE TABLE IF NOT EXISTS "users_categories" (
  "user_id" bigint,
  "category_id" smallint
);

CREATE TABLE IF NOT EXISTS "posts_categories" (
  "post_id" bigint,
  "category_id" smallint
);

ALTER TABLE "posts" DROP CONSTRAINT IF EXISTS posts_author_id_fkey;
ALTER TABLE "posts" ADD FOREIGN KEY  ("author_id") REFERENCES "users" ("id");

-- ALTER TABLE "user_passwords" DROP CONSTRAINT user_passwords;
ALTER TABLE "user_passwords" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "comments" DROP CONSTRAINT IF EXISTS comments_post_id_fkey;
ALTER TABLE "comments" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "comments" DROP CONSTRAINT IF EXISTS comments_author_id_fkey;
ALTER TABLE "comments" ADD FOREIGN KEY ("author_id") REFERENCES "users" ("id");

ALTER TABLE "users_categories" DROP CONSTRAINT IF EXISTS users_categories_user_id_fkey;
ALTER TABLE "users_categories" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "users_categories" DROP CONSTRAINT IF EXISTS users_categories_category_id_fkey;
ALTER TABLE "users_categories" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "posts_categories" DROP CONSTRAINT IF EXISTS posts_categories_post_id_fkey;
ALTER TABLE "posts_categories" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id");

ALTER TABLE "posts_categories" DROP CONSTRAINT IF EXISTS posts_categories_category_id_fkey;
ALTER TABLE "posts_categories" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

CREATE UNIQUE INDEX ON "comments" ("post_id", "author_id");