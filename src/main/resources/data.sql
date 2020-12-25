CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO roles VALUES (1,'USER') ON CONFLICT DO NOTHING;
INSERT INTO roles VALUES (2,'AUTHOR') ON CONFLICT DO NOTHING;
INSERT INTO roles VALUES (3,'ADMIN') ON CONFLICT DO NOTHING;

CREATE OR REPLACE PROCEDURE insert_admin_user(
    first_name varchar(255),
    last_name varchar(255),
    description varchar(255),
    email varchar(255),
    password varchar(255)
    )
AS
'
DECLARE
    u_id INTEGER := 0;
BEGIN
    INSERT INTO public.users
        ("first_name","last_name","description","email","enabled","password")
            VALUES (first_name, last_name, description,
                email, true, crypt(password, gen_salt(''bf'', 8))) ON CONFLICT DO NOTHING
    returning id into u_id;
    INSERT INTO public.users_roles (user_id,role_id) SELECT  u_id,3 WHERE
    NOT EXISTS (
        SELECT id FROM public.users WHERE id = 1
    );
END;' LANGUAGE plpgsql;

-- ON CONFLICT DO NOTHING
-- ON CONFLICT DO NOTHING;
CALL insert_admin_user('Neo','Chosen','Matrix','neo@gmail.com','hack');

