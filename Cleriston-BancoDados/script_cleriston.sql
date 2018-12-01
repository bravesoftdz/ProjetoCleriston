/*
    drop table Atribuicoes;
    drop table Trabalhos;
    drop table Disciplinas;
    drop table Professores;
    drop table Alunos;
*/

create table if not exists Alunos(
  id serial primary key,
  nome text,
  ra int not null
);

create table if not exists Professores(
  id serial primary key,
  nome text,
  salario decimal(10,2) not null
);

create table if not exists Disciplinas(
  id serial primary key,
  id_professor int not null references Professores(id),
  nome varchar(50) not null
);

create table if not exists Trabalhos(
  id serial primary key,
  nome text,
  id_disciplina int not null references Disciplinas(id)
);

create table if not exists Atribuicoes(
  id_aluno int not null references Alunos(id),
  id_trabalho int not null references Trabalhos(id),
  conteudo text,
  nota decimal(4,2),
  primary key (id_aluno, id_trabalho)
);


