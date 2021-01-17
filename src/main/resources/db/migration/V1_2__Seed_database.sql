INSERT INTO users (username, email, birth_date, password_digest,
                   delete_after_days, likes, dislikes, created_at, updated_at) VALUES
    ('ajerigoju', 'jfjf@fdf.com', '2001-03-12', 'pass', 30, 230, 124, '2001-05-12', '2001-05-12'),
    ('pumba', 'jfjfwerw@fdf.com', '2001-03-12', 'pass', 30, 23, 124, '2001-05-12', '2001-05-12'),
    ('shuriken', 'showup@fdf.com', '2001-03-12', 'pass', 30, 230, 124, '2001-05-12', '2001-05-12');


INSERT INTO posts  (author_id, topic, likes, dislikes) VALUES
    (1, 'Post1', 423, 3),
    (1, 'Post2', 63, 43),
    (2, 'Post3', 89, 11),
    (2, 'Post4', 435, 91),
    (2, 'Post5', 90, 23),
    (3, 'Post6', 92, 5);

INSERT INTO categories (title, description) VALUES
    ('IT', 'Learn more about cyber-world'),
    ('Nature', 'Save our beautiful mother nature'),
    ('Business', 'Think and get richer');

INSERT INTO posts_categories (post_id, category_id) VALUES
    (1, 2),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 2),
    (6, 1);