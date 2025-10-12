CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
CREATE TABLE atividade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500)
);

-- =============================
-- Tabela de Alunos
-- =============================
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    apelido VARCHAR(255),
    cpf VARCHAR(20),
    data_nascimento DATE NOT NULL,
    nome_responsavel VARCHAR(255) NOT NULL,
    cpf_responsavel VARCHAR(20) NOT NULL,
    telefone_principal VARCHAR(20) NOT NULL,
    telefone_secundario VARCHAR(20),
    termo_autorizacao BOOLEAN NOT NULL DEFAULT FALSE
);

-- Tabela de associação aluno <-> atividades (N:M)
CREATE TABLE aluno_atividade (
    aluno_id INT NOT NULL,
    atividade_id INT NOT NULL,
    PRIMARY KEY (aluno_id, atividade_id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
);

-- =============================
-- Tabela de Voluntários
-- =============================
CREATE TABLE voluntario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    data_nascimento DATE,
    email VARCHAR(255) NOT NULL,
    area_atuacao VARCHAR(255) NOT NULL,
    turma VARCHAR(50),
    atividade_id INT, -- opcional
    telefone VARCHAR(20) NOT NULL,
    FOREIGN KEY (atividade_id) REFERENCES atividade(id)
);

-- =============================
-- Tabela de Administradores
-- =============================
CREATE TABLE administrador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    area_atuacao VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    data_nascimento DATE
);