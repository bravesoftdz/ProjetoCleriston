create database trabalhos;

use trabalhos;

create table Pessoas(
id  int     not null    primary key     auto_increment,
nome    varchar(50) not null
);

create table Alunos(
id_pessoa int primary key not null references Pessoas(id),
ra  int not null);

create table Professores(
id_pessoa int primary key not null references Pessoas(id),
salario decimal(10,2) not null
);

create table Disciplinas(
    id  int not null    primary key auto_increment,
    id_professor int not null references Professores(id),
    nome    varchar(50) not null
);

create table Atribuicoes(
    id_aluno int not null references Alunos(id),
    id_disciplina int not null references Disciplinas(id),
    primary key (id_aluno, id_disciplina) 
);

create table Trabalhos(
id  int not null primary key auto_increment,
arquivo blob,
nome varchar(50) not null,
descricao varchar(200),
nota_trabalho decimal(4,2),
obs_trabalho varchar(200),
id_disciplina int not null    references Disciplinas(id)
);


