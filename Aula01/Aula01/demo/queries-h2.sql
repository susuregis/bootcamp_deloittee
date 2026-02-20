-- Scripts SQL para testar diretamente no Console H2
-- Acesse: http://localhost:8080/h2-console
-- JDBC URL: jdbc:h2:mem:usuariodb
-- Username: sa
-- Password: (deixe em branco)

-- 1. Verificar estrutura da tabela
SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'USUARIOS';

-- 2. Listar todos os usuários
SELECT * FROM USUARIOS;

-- 3. Inserir usuários manualmente (caso queira testar pelo H2)
INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Carlos Mendes', 'carlos@email.com');
INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Ana Paula', 'ana.paula@email.com');
INSERT INTO USUARIOS (NOME, EMAIL) VALUES ('Roberto Lima', 'roberto@email.com');

-- 4. Buscar usuário por ID
SELECT * FROM USUARIOS WHERE ID = 1;

-- 5. Buscar usuário por email
SELECT * FROM USUARIOS WHERE EMAIL = 'carlos@email.com';

-- 6. Contar total de usuários
SELECT COUNT(*) AS TOTAL_USUARIOS FROM USUARIOS;

-- 7. Atualizar usuário
UPDATE USUARIOS SET NOME = 'Carlos Mendes Silva', EMAIL = 'carlos.silva@email.com' WHERE ID = 1;

-- 8. Remover usuário
DELETE FROM USUARIOS WHERE ID = 3;

-- 9. Buscar usuários por parte do nome
SELECT * FROM USUARIOS WHERE NOME LIKE '%Silva%';

-- 10. Buscar usuários por domínio de email
SELECT * FROM USUARIOS WHERE EMAIL LIKE '%@email.com';

-- 11. Limpar toda a tabela (cuidado!)
-- DELETE FROM USUARIOS;

-- 12. Verificar estrutura completa da tabela
SHOW COLUMNS FROM USUARIOS;
