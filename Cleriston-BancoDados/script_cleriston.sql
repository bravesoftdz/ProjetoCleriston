drop table Atribuicoes;
drop table Trabalhos;
drop table Disciplinas;
drop table Professores;
drop table Alunos;

--------------------------------------------------
-- Criação das Tabelas
--------------------------------------------------

create table if not exists Alunos(
  id serial primary key,
  nome text,
  ra int not null
);

create table if not exists Professores(
  id serial primary key,
  nome text,
  salario decimal(10,2)
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

--------------------------------------------------
-- Inserts Iniciais
--------------------------------------------------

insert into alunos
(nome, ra)
values
('Vítor', '20166218'),
('Marciel', '20155104'),
('João', '20155555'),
('Gabriel', '20154444')
;

insert into professores
(nome, salario)
values
('Cleriston', 10000),
('Luciana', 5000)
;

insert into disciplinas
(id_professor,nome)
values
(1, 'Linguagem de Programação 2'),
(2, 'Redes Neurais e Artificiais');

----------------------------------------
select * from Atribuicoes;
select * from Trabalhos;
select * from Disciplinas;
select * from Professores;
select * from Alunos;




