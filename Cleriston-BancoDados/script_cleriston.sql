
create table if not exists Pessoas(
  id serial primary key,
  nome varchar(50) not null
);

create table if not exists Alunos(
  id_pessoa int primary key not null references Pessoas(id),
  ra int not null
);

create table if not exists Professores(
  id_pessoa int primary key not null references Pessoas(id),
  salario decimal(10,2) not null
);

create table if not exists Disciplinas(
  id serial primary key,
  id_professor int not null references Professores(id_pessoa),
  nome varchar(50) not null
);

create table if not exists Atribuicoes(
  id_aluno int not null references Alunos(id_pessoa),
  id_disciplina int not null references Disciplinas(id),
  primary key (id_aluno, id_disciplina) 
);

create table if not exists Trabalhos(
  id serial primary key,
  nome varchar(50) not null,
  conteudo text,
  nota decimal(4,2),
  id_disciplina int not null references Disciplinas(id)
);



