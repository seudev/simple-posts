CREATE TABLE app_user (
    id uuid NOT NULL,
    name varchar(50) NOT NULL,
    created_at timestamptz NOT NULL DEFAULT NOW(),

	CONSTRAINT app_user_pk PRIMARY KEY (id),
    CONSTRAINT app_user_name_uq UNIQUE (name)
);
