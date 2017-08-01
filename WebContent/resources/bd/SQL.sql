
/* Relatório de impressoras */

SELECT i.id, i.nome, (SELECT s FROM Setor AS s WHERE s.id = i.setor_id) AS setor
from Impressora AS i;

SELECT im.id, im.nome AS impressora, (SELECT s.nome FROM Setor AS s WHERE s.id = im.setor_id) AS setor, SUM(i.qnt_copias * i.qnt_paginas) AS total_impressao
FROM Impressao AS i
INNER JOIN Impressora AS im ON im.id = i.impressora_id
GROUP BY im.id
ORDER BY total_impressao DESC;


/* Relatório de usuários */

SELECT u.id, u.nome, u.usuario, u.perfil, u.ativo, s.nome AS setor
FROM Usuario AS u
INNER JOIN Setor AS s ON s.id = u.setor_id
ORDER BY u.nome;

/* Relatório de setores */

SELECT id, nome
FROM Setor
ORDER BY nome;