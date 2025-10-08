
INSERT INTO departamento(id, sigla, descricao, status) VALUES (1, 'VND', 'Departamento de Vendas', 'ATIVO');
INSERT INTO departamento(id, sigla, descricao, status) VALUES (2, 'FIN', 'Departamento Financeiro', 'ATIVO');
INSERT INTO departamento(id, sigla, descricao, status) VALUES (3, 'TI', 'Departamento de Tecnologia da Informação', 'ATIVO');
INSERT INTO departamento(id, sigla, descricao, status) VALUES (4, 'RH', 'Departamento de Recursos Humanos', 'INATIVO');



INSERT INTO funcionario(id, nome, email, cargo, data_admissao, departamento_id) VALUES (1, 'Carlos Andrade', 'carlos.andrade@empresa.com', 'Gerente de Vendas', '2022-05-10', 1);
INSERT INTO funcionario(id, nome, email, cargo, data_admissao, departamento_id) VALUES (2, 'Patricia Mendes', 'patricia.mendes@empresa.com', 'Analista Financeiro', '2023-01-15', 2);
INSERT INTO funcionario(id, nome, email, cargo, data_admissao, departamento_id) VALUES (3, 'Fernando Costa', 'fernando.costa@empresa.com', 'Desenvolvedor Pleno', '2021-11-20', 3);
INSERT INTO funcionario(id, nome, email, cargo, data_admissao, departamento_id) VALUES (4, 'Juliana Lima', 'juliana.lima@empresa.com', 'Desenvolvedora Sênior', '2020-08-01', 3);


INSERT INTO fornecedor(id, nome, email, razao_social, cnpj) VALUES (1, 'Componentes Express', 'contato@componentesexpress.com', 'Componentes Express Ltda', '11.222.333/0001-44');
INSERT INTO fornecedor(id, nome, email, razao_social, cnpj) VALUES (2, 'InfoPeças', 'vendas@infopecas.com.br', 'InfoPeças Distribuidora S.A.', '55.666.777/0001-88');
INSERT INTO fornecedor(id, nome, email, razao_social, cnpj) VALUES (3, 'Bits & Bytes', 'comercial@bitsbytes.com', 'Bits & Bytes Comércio de Eletrônicos', '99.888.777/0001-66');


INSERT INTO cliente(id, nome, email, data_cadastro) VALUES (1, 'João da Silva', 'joao.silva@email.com', '2024-10-01T10:00:00');
INSERT INTO cliente(id, nome, email, data_cadastro) VALUES (2, 'Maria Oliveira', 'maria.o@email.com', '2024-10-05T15:30:00');
INSERT INTO cliente(id, nome, email, data_cadastro) VALUES (3, 'Ana Carolina', 'ana.carolina.to@email.com', '2024-10-08T11:00:00');


INSERT INTO endereco(id, rua, cep, cliente_id) VALUES (1, 'Rua das Flores, 123', '01000-001', 1);
INSERT INTO endereco(id, rua, cep, cliente_id) VALUES (2, 'Avenida Brasil, 1500', '20000-002', 2);
INSERT INTO endereco(id, rua, cep, cliente_id) VALUES (3, 'Travessa do Comércio, 45', '20031-040', 2);
INSERT INTO endereco(id, rua, cep, cliente_id) VALUES (4, 'Quadra 104 Sul, Avenida LO-01, 33', '77020-020', 3);


INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (1, '11', '98765-4321', 1);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (2, '11', '5555-4444', 1);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (3, '21', '91234-5678', 2);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (4, '63', '98450-1020', 3);



INSERT INTO marca(id, nome) VALUES(1, 'Corsair');
INSERT INTO marca(id, nome) VALUES(2, 'Seasonic');
INSERT INTO marca(id, nome) VALUES(3, 'Cooler Master');


INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(1, 'RM750x', 750, 799.90, 50, 'GOLD', 1);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(2, 'SF600', 600, 650.00, 35, 'GOLD', 1); -- Ajustado de PLATINUM para GOLD conforme Enum
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(3, 'PRIME TX-1000', 1000, 1890.50, 15, 'GOLD', 2); -- Ajustado de TITANIUM para GOLD conforme Enum
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(4, 'MWE 550', 550, 399.00, 80, 'BRONZE', 3);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(5, 'V850 SFX', 850, 950.00, 25, 'GOLD', 3);



SELECT setval('pessoa_id_seq', (SELECT MAX(id) FROM (SELECT id FROM cliente UNION SELECT id FROM funcionario UNION SELECT id FROM fornecedor) AS all_person_ids));
SELECT setval('departamento_id_seq', (SELECT MAX(id) FROM departamento));
SELECT setval('endereco_id_seq', (SELECT MAX(id) FROM endereco));
SELECT setval('telefones_id_seq', (SELECT MAX(id) FROM telefones));
SELECT setval('marca_id_seq', (SELECT MAX(id) FROM marca));
SELECT setval('fonte_id_seq', (SELECT MAX(id) FROM fonte));