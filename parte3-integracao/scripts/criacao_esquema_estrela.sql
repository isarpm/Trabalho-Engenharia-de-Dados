CREATE TABLE dw.dim_departamento (
    id_departamento SERIAL PRIMARY KEY,
    codigo_departamento VARCHAR(20) UNIQUE NOT NULL, 
    nome_departamento VARCHAR(150) NOT NULL
);

CREATE TABLE dw.dim_professor (
    id_professor SERIAL PRIMARY KEY,
    nome_professor VARCHAR(150) NOT NULL,
    tipo_jornada VARCHAR(50),
    formacao VARCHAR(100),
    codigo_departamento VARCHAR(100) 
);

CREATE TABLE dw.dim_disciplina (
    id_disciplina SERIAL PRIMARY KEY,
    codigo_disciplina VARCHAR(20) UNIQUE NOT NULL,
    nome_disciplina VARCHAR(150) NOT NULL,
    creditos_total INT NOT NULL,
    codigo_departamento VARCHAR(100) 
);

CREATE TABLE dw.dim_semestres (
    id_semestre SERIAL PRIMARY KEY,
    ano INT NOT NULL,
    periodo INT NOT NULL,
    UNIQUE (ano, periodo) 
);


CREATE TABLE dw.fato_turma (
    id_fato_turma SERIAL PRIMARY KEY,
    
    -- Chaves que apontam para as dimensões acima
    id_professor INT REFERENCES dw.dim_professor(id_professor),
    id_disciplina INT REFERENCES dw.dim_disciplina(id_disciplina),
    id_departamento INT REFERENCES dw.dim_departamento(id_departamento),
    id_semestre INT REFERENCES dw.dim_semestre(id_semestre),
    
    -- Métricas quantitativas 
    num_discentes_matricu INT NOT NULL DEFAULT 0,
    media_notas NUMERIC(4,2), 
    num_aprovados INT DEFAULT 0,
    num_reprovados INT DEFAULT 0
);