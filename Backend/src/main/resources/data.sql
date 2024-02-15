INSERT INTO gender (gender) VALUES ('Masculino');
INSERT INTO gender (gender) VALUES ('Femenino');
INSERT INTO gender (gender) VALUES ('Prefiro não dizer');
-- Inserir Maria
INSERT INTO user_entity (name, email, bio, gender_id, confirmed) 
VALUES ('Maria', 'maria@example.com', 'Estudante de medicina', 2, true);

-- Inserir Ana
INSERT INTO user_entity (name, email, bio, gender_id, confirmed) 
VALUES ('Ana', 'ana@example.com', 'Desenvolvedora de software', 1, true);

-- Inserir Carlos
INSERT INTO user_entity (name, email, bio, gender_id, confirmed) 
VALUES ('Carlos', 'carlos@example.com', 'Vendedor de automóveis', 1, false);

-- Inserir João
INSERT INTO user_entity (name, email, bio, gender_id, confirmed) 
VALUES ('João', 'joao@example.com', 'Chef de cozinha', 1, true);

-- Inserir Laura
INSERT INTO user_entity (name, email, bio, gender_id, confirmed) 
VALUES ('Laura', 'laura@example.com', 'Estudante de medicina', 2, true);


