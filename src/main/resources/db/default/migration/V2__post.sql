CREATE TABLE post (
    id uuid NOT NULL,
    author_id uuid NOT NULL,
    message varchar(150) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT NOW(),

	CONSTRAINT post_pk PRIMARY KEY (id),
    CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES app_user(id)
);

CREATE TABLE post_tag (
    post_id uuid NOT NULL,
    tag varchar(30) NOT NULL,

    CONSTRAINT post_id_fk FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
    CONSTRAINT post_tag_uq UNIQUE (post_id, tag)
);

CREATE INDEX post_idx ON post(author_id, created_at DESC);
