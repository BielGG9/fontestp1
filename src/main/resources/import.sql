-- CONFIGURAÇÕES INICIAIS
-- Sequências para garantir IDs consistentes no teste
SELECT setval('pessoa_id_seq', 10, true);
SELECT setval('departamento_id_seq', 10, true);
SELECT setval('marca_id_seq', 10, true);
SELECT setval('modelo_id_seq', 10, true);
SELECT setval('enderecos_id_seq', 10, true);
SELECT setval('telefones_id_seq', 10, true);
SELECT setval('fonte_id_seq', 10, true);
SELECT setval('pedido_id_seq', 10, true);
SELECT setval('item_pedido_id_seq', 10, true);


-- 1. PRODUTOS (MARCA, MODELO, FONTE)
INSERT INTO marca(id, nome) VALUES(1, 'Corsair'); 
INSERT INTO marca(id, nome) VALUES(2, 'Seasonic');
INSERT INTO modelo(id, numeracao, marca_id) VALUES(1, 750, 1); -- Modelo ID 1 (Corsair 750)
INSERT INTO modelo(id, numeracao, marca_id) VALUES(2, 1000, 2); 
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, modelo_id) 
VALUES(1, 'RM750x', 750, 800.00, 50, 'GOLD', 1); -- FONTE ID 1 (Para pedidos)


-- 2. DEPARTAMENTO (Para Funcionários)
INSERT INTO departamento(id, sigla, descricao) VALUES (1, 'VND', 'Vendas');
INSERT INTO departamento(id, sigla, descricao) VALUES (2, 'TI', 'Tecnologia');

-- 3. PESSOAS (ID 1: CLIENTE - Base para Pedido)
-- KEYCLOAK_ID: 'test-user-id' (Mockado nos testes)
INSERT INTO Pessoa(id, nome, email, idKeycloak) VALUES (1, 'biel', 'gabrielbr1503@gmail.com', 'test-user-id');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (1, '111.111.111-11', '12345 SSP/SP');
INSERT INTO Cliente(id, dataCadastro) VALUES (1, '2025-01-01T10:00:00');

-- ID 2: FORNECEDOR (Base para Fonte)
INSERT INTO Pessoa(id, nome, email) VALUES (2, 'Tech Distribuidora', 'contato@tech.com');
INSERT INTO PessoaJuridica(id, cnpj, inscricaoEstadual) VALUES (2, '99.999.999/0001-99', 'ISENTO');
INSERT INTO Fornecedor(id, razaoSocial) VALUES (2, 'Tech Distribuidora Ltda');

-- ID 3: FUNCIONÁRIO (Base para CRUD Funcionario)
INSERT INTO Pessoa(id, nome, email) VALUES (3, 'Ana Dev', 'ana@email.com');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (3, '222.222.222-22', '54321 SSP/RJ');
-- NOTA: O password não é usado nos testes de CRUD/segurança, mas é mantido por precaução.
INSERT INTO Funcionario(id, cargo, departamento_id, password) VALUES (3, 'Desenvolvedora', 2, 'password');

-- 4. ENDEREÇOS E TELEFONES (ID 1: Para Pedido e CRUDs)
INSERT INTO enderecos(id, rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) 
VALUES (1, 'Rua das Flores', '10', 'Apto 1', 'Centro', 'Palmas', 'TO', '77000-000', 1);
INSERT INTO telefones(id, ddd, numero, pessoa_id) 
VALUES (1, '63', '98765-4321', 1);

-- 5. RELAÇÕES (MUITOS PARA MUITOS)
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (1, 2);

-- 6. CARGA DE PEDIDO (Para Teste de Histórico)
INSERT INTO pedido (id, data, total, id_usuario_keycloack, nomeClienteSnapshot, rua, numero, complemento, bairro, cidade, estado, cep)
VALUES (1, '2024-11-20 10:00:00', 1600.0, 'test-user-id', 'biel', 'Rua das Flores', '10', 'Apto 1', 'Centro', 'Palmas', 'TO', '77000-000');
INSERT INTO item_pedido (id, quantidade, preco, pedido_id, fonte_id)
VALUES (1, 2, 800.0, 1, 1);