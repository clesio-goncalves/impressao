insert into Setor(id, nome)
values (1,'TI');

insert into Setor(id, nome)
values (2,'DAP');

insert into Usuario(id, nome, usuario, senha, ativo, setor_id)
values (1, 'Clésio de Araújo Gonçalves',
    'clesio','$2a$10$TnUVSh6oF5dsbeJ4jRor3OI/aI99wh9FAFTQLewDlydxAUm8KDaIO',
    1, 1);
    
insert into Usuario(id, nome, usuario, senha, ativo, setor_id)
values (2, 'Thalita Oliveira',
    'thalita','$2a$10$HdHZhU/ImOJ5EQNykqj2gu11/LZdaTXH.DhRD9ZE8KuVkulU8gQlq',
    1, 2);
    
insert into Permissao (id, nome)
values (1, 'ROLE_ADMIN');

insert into Permissao (id, nome)
values (2, 'ROLE_COORDENADOR');

insert into Usuario_Permissao (usuario_id, permissao_id)
values (1,1);

insert into Usuario_Permissao (usuario_id, permissao_id)
values (2,2);