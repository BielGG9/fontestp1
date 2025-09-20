INSERT INTO cliente(id, nome, email) VALUES (1, 'João da Silva', 'joao.silva@email.com');
INSERT INTO endereco(id, rua, bairro, complemento, numero, cidade, estado, cep, cliente_id) VALUES (1, 'Rua das Flores', 'Jardim Primavera', 'Apto 101', '123', 'São Paulo', 'SP', '01000-001', 1);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (1, '11', '98765-4321', 1);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (2, '11', '5555-4444', 1);

INSERT INTO cliente(id, nome, email) VALUES (2, 'Maria Oliveira', 'maria.o@email.com');
INSERT INTO endereco(id, rua, bairro, complemento, numero, cidade, estado, cep, cliente_id) VALUES (2, 'Avenida Brasil', 'Centro', 'Sala 50', '1500', 'Rio de Janeiro', 'RJ', '20000-002', 2);
INSERT INTO endereco(id, rua, bairro, complemento, numero, cidade, estado, cep, cliente_id) VALUES (3, 'Travessa do Comércio', 'Lapa', 'Casa', '45', 'Rio de Janeiro', 'RJ', '20031-040', 2);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (3, '21', '91234-5678', 2);

INSERT INTO cliente(id, nome, email) VALUES (3, 'Ana Carolina', 'ana.carolina.to@email.com');
INSERT INTO endereco(id, rua, bairro, complemento, numero, cidade, estado, cep, cliente_id) VALUES (4, 'Quadra 104 Sul, Avenida LO-01', 'Plano Diretor Sul', 'Residencial Palmas', '33', 'Palmas', 'TO', '77020-020', 3);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (4, '63', '98450-1020', 3);

INSERT INTO marca(id, nome) VALUES(1, 'Corsair');
INSERT INTO marca(id, nome) VALUES(2, 'Seasonic');
INSERT INTO marca(id, nome) VALUES(3, 'Cooler Master');

INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(1, 'RM750x', 750, 799.90, 'GOLD', 1);
INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(2, 'SF600', 600, 650.00, 'PLATINUM', 1);
INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(3, 'PRIME TX-1000', 1000, 1890.50, 'TITANIUM', 2);

SELECT setval('cliente_id_seq', (SELECT MAX(id) FROM cliente));
SELECT setval('endereco_id_seq', (SELECT MAX(id) FROM endereco));
SELECT setval('telefones_id_seq', (SELECT MAX(id) FROM telefones));
SELECT setval('marca_id_seq', (SELECT MAX(id) FROM marca));
SELECT setval('fonte_id_seq', (SELECT MAX(id) FROM fonte));