-- 1. Dados Básicos
INSERT INTO marca(nome) VALUES('Corsair'); 
INSERT INTO marca(nome) VALUES('Seasonic');
INSERT INTO modelo(numeracao, marca_id) VALUES(750, 1); 
INSERT INTO modelo(numeracao, marca_id) VALUES(1000, 2); 
INSERT INTO departamento(sigla, descricao) VALUES ('VND', 'Vendas');

-- 2. CLIENTE (O IMPORTANTE)
INSERT INTO Pessoa(nome, email) VALUES ('biel', 'gabrielbr1503@gmail.com');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (1, '111.111.111-11', '12345 SSP/SP');
INSERT INTO Cliente(id, dataCadastro) VALUES (1, '2025-01-01T10:00:00');

-- 3. ENDEREÇO (Vinculado ao Cliente 'biel')
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) 
VALUES ('Rua das Flores', '10', 'Apto 1', 'Centro', 'Palmas', 'TO', '77000-000', 1);

-- 4. Produtos e Fornecedores
INSERT INTO Pessoa(nome, email) VALUES ('Tech Distribuidora', 'contato@tech.com');
INSERT INTO PessoaJuridica(id, cnpj, inscricaoEstadual) VALUES (2, '99.999.999/0001-99', 'ISENTO');
INSERT INTO Fornecedor(id, razaoSocial) VALUES (2, 'Tech Distribuidora Ltda');
INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) 
VALUES('RM750x', 750, 800, 50, 'GOLD', 1); 
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (1, 2);