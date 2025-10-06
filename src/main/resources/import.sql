-- ==============================
-- INSERINDO ATIVIDADES
-- ==============================
INSERT INTO tb_atividade (nome, descricao) VALUES('Aula de Informática', 'Curso básico de informática para iniciantes, com foco em ferramentas do dia a dia.');
INSERT INTO tb_atividade (nome, descricao) VALUES('Oficina de Artesanato', 'Atividade voltada para a criação de peças decorativas e recicláveis.');
INSERT INTO tb_atividade (nome, descricao) VALUES('Acompanhamento Escolar', 'Auxílio aos alunos nas tarefas escolares e reforço em matérias básicas.');
INSERT INTO tb_atividade (nome, descricao) VALUES('Atendimento Psicológico', 'Sessões de apoio emocional e psicológico para alunos e familiares.');
INSERT INTO tb_atividade (nome, descricao) VALUES('Capacitação Profissional', 'Cursos de qualificação para o mercado de trabalho.');
INSERT INTO tb_atividade (nome, descricao) VALUES('Recreação Infantil', 'Atividades lúdicas e esportivas para crianças.');

INSERT INTO tb_aluno (data_nascimento, apelido, cpf, cpf_responsavel, nome, nome_responsavel, telefone_opcional, telefone_principal) VALUES ('2012-05-10', 'Léo', '123.456.789-00', '987.654.321-00', 'Leonardo Silva', 'Carlos Silva', '11988887777', '11999990000');
INSERT INTO tb_aluno (data_nascimento, apelido, cpf, cpf_responsavel, nome, nome_responsavel, telefone_opcional, telefone_principal) VALUES ('2011-08-22', 'Bia', '111.222.333-44', '444.555.666-77', 'Beatriz Souza', 'Fernanda Souza', '11977776666', '11966665555');
INSERT INTO tb_aluno (data_nascimento, apelido, cpf, cpf_responsavel, nome, nome_responsavel, telefone_opcional, telefone_principal) VALUES ('2013-01-15', 'Gui', '555.666.777-88', '888.999.000-11', 'Guilherme Rocha', 'Mariana Rocha', NULL, '11955554444');
INSERT INTO tb_aluno (data_nascimento, apelido, cpf, cpf_responsavel, nome, nome_responsavel, telefone_opcional, telefone_principal) VALUES ('2010-12-30', 'Nina', '222.333.444-55', '555.666.777-88', 'Nina Costa', 'Paula Costa', '11944443333', '11933332222');
INSERT INTO tb_aluno (data_nascimento, apelido, cpf, cpf_responsavel, nome, nome_responsavel, telefone_opcional, telefone_principal) VALUES ('2012-07-05', 'Theo', '999.888.777-66', '666.777.888-99', 'Theodoro Lima', 'Ricardo Lima', NULL, '11922221111');


INSERT INTO tb_professor (data_nascimento, area_atuacao, cpf, email, nome, senha, telefone, turma) VALUES ('1980-03-15', 'Matemática', '123.456.789-00', 'maria.silva@email.com', 'Maria Silva', 'senha123', '11999990000', 'Turma A');
INSERT INTO tb_professor (data_nascimento, area_atuacao, cpf, email, nome, senha, telefone, turma) VALUES ('1975-07-22', 'Português', '111.222.333-44', 'joao.souza@email.com', 'João Souza', 'senha456', '11988887777', 'Turma B');
INSERT INTO tb_professor (data_nascimento, area_atuacao, cpf, email, nome, senha, telefone, turma) VALUES ('1985-11-30', 'Artes', '555.666.777-88', 'ana.costa@email.com', 'Ana Costa', 'senha789', '11977776666', 'Turma C');
INSERT INTO tb_professor (data_nascimento, area_atuacao, cpf, email, nome, senha, telefone, turma) VALUES ('1990-01-12', 'Educação Física', '222.333.444-55', 'carlos.lima@email.com', 'Carlos Lima', 'senha321', '11966665555', 'Turma D');
INSERT INTO tb_professor (data_nascimento, area_atuacao, cpf, email, nome, senha, telefone, turma) VALUES ('1982-05-05', 'História', '999.888.777-66', 'beatriz.rocha@email.com', 'Beatriz Rocha', 'senha654', '11955554444', 'Turma E');

-- Maria Silva ministra Matemática e Artes
INSERT INTO tb_aluno_atividade (aluno_id, atividade_id) VALUES (1, 1);
INSERT INTO tb_aluno_atividade (aluno_id, atividade_id) VALUES (1, 3);

-- Ana participa apenas de Artes
INSERT INTO tb_aluno_atividade (aluno_id, atividade_id) VALUES (2, 2);

-- João participa de Matemática e Acompanhamento Escolar


INSERT INTO tb_professor_atividade (atividade_id, professor_id) VALUES (1, 1);
INSERT INTO tb_professor_atividade (atividade_id, professor_id) VALUES (2, 1);

-- Carlos Lima ministra Acompanhamento Escolar
INSERT INTO tb_professor_atividade (atividade_id, professor_id) VALUES (3, 2);