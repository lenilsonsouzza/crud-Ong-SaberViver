-- ==============================
-- INSERINDO ATIVIDADES
-- ==============================
INSERT INTO tb_atividade (nome, descricao) VALUES ('FUTEBOL', 'ESCOLINHA DE FUTEBOL');
INSERT INTO tb_atividade (nome, descricao) VALUES ('CAPOEIRA', 'TREINAMENTO CAPOEIRA');
INSERT INTO tb_atividade (nome, descricao) VALUES ('AULA PORTUGUES', 'AULA NA ESCOLINHA');
INSERT INTO tb_atividade (nome, descricao) VALUES ('ARTESANATO', 'AULA DE ARTESANATO');

INSERT INTO tb_aluno (nome,senha,cpf,data_nascimento,nome_responsavel,cpf_responsavel) VALUES ('Juninho','juninhi@gmail',12345,051308580,'1992-08-30','Rafaela maria',4523462);
INSERT INTO tb_aluno (nome,senha,cpf,data_nascimento,nome_responsavel,cpf_responsavel) VALUES ('Lenilson','lenilson@gmail',12322,324324320,'1991-02-20','Tereza Benta',547345);
INSERT INTO tb_aluno (nome,senha,cpf,data_nascimento,nome_responsavel,cpf_responsavel) VALUES ('Suelen','Suele@gmail',12345,023308580,'1990-02-4','Josefa Lima',3653462);
INSERT INTO tb_aluno (nome,senha,cpf,data_nascimento,nome_responsavel,cpf_responsavel) VALUES ('Jose' , 'jose@gmail' ,12329,124324320,'1991-02-20','Priscila souza',976345);


INSERT INTO tb_colaborador (nome, senha, cpf, data_nascimento,funcao) VALUES ('Rafael','rafael@gmail',23452,4325634,'1985-06-30','PROFESSOR');
INSERT INTO tb_colaborador (nome, senha, cpf, data_nascimento,funcao) VALUES ('MARCOS','marcos@gmail',12345,3455634,'1980-02-10','LIMPEZA');

INSERT INTO tb_aluno_atividade(aluno_id,atividade_id) VALUES (1,2);
INSERT INTO tb_aluno_atividade(aluno_id,atividade_id) VALUES (1,1);