INSERT INTO marca(nome) VALUES('Corsair');
INSERT INTO marca(nome) VALUES('Seasonic');
INSERT INTO marca(nome) VALUES('Cooler Master');

INSERT INTO departamento(sigla, descricao) VALUES ('VND', 'Departamento de Vendas');
INSERT INTO departamento(sigla, descricao) VALUES ('FIN', 'Departamento Financeiro');
INSERT INTO departamento(sigla, descricao) VALUES ('TI', 'Departamento de Tecnologia da Informação');
INSERT INTO departamento(sigla, descricao) VALUES ('RH', 'Departamento de Recursos Humanos');

-- Modelos inseridos primeiro (dependem de Marca)
INSERT INTO modelo(numeracao, marca_id) VALUES(750, 1); 
INSERT INTO modelo(numeracao, marca_id) VALUES(600, 1); 
INSERT INTO modelo(numeracao, marca_id) VALUES(1000, 2); 
INSERT INTO modelo(numeracao, marca_id) VALUES(550, 3);
INSERT INTO modelo(numeracao, marca_id) VALUES(850, 3);

INSERT INTO Pessoa(nome, email) VALUES ('João da Silva', 'joao.silva@email.com');
INSERT INTO Pessoa(nome, email) VALUES ('Maria Oliveira', 'maria.o@email.com');
INSERT INTO Pessoa(nome, email) VALUES ('Ana Carolina', 'ana.carolina.to@email.com');
INSERT INTO Pessoa(nome, email) VALUES ('Carlos Andrade', 'carlos.andrade@empresa.com');
INSERT INTO Pessoa(nome, email) VALUES ('Patricia Mendes', 'patricia.mendes@empresa.com');
INSERT INTO Pessoa(nome, email) VALUES ('Fernando Costa', 'fernando.costa@empresa.com');
INSERT INTO Pessoa(nome, email) VALUES ('Juliana Lima', 'juliana.lima@empresa.com');
INSERT INTO Pessoa(nome, email) VALUES ('Componentes Express', 'contato@componentesexpress.com');
INSERT INTO Pessoa(nome, email) VALUES ('InfoPeças', 'vendas@infopecas.com.br');
INSERT INTO Pessoa(nome, email) VALUES ('Bits & Bytes', 'comercial@bitsbytes.com');

INSERT INTO PessoaFisica(id, cpf, rg) VALUES (1, '111.111.111-11', '1111111 SSP/SP');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (2, '222.222.222-22', '2222222 SSP/RJ');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (3, '333.333.333-33', '3333333 SSP/TO');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (4, '444.444.444-44', '4444444 SSP/SP');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (5, '555.555.555-55', '5555555 SSP/MG');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (6, '666.666.666-66', '6666666 SSP/BA');
INSERT INTO PessoaFisica(id, cpf, rg) VALUES (7, '777.777.777-77', '7777777 SSP/RS');

INSERT INTO PessoaJuridica(id, cnpj, inscricaoEstadual) VALUES (8, '11.222.333/0001-44', 'ISENTO');
INSERT INTO PessoaJuridica(id, cnpj, inscricaoEstadual) VALUES (9, '55.666.777/0001-88', '123456789');
INSERT INTO PessoaJuridica(id, cnpj, inscricaoEstadual) VALUES (10, '99.888.777/0001-66', '987654321');

INSERT INTO Cliente(id, dataCadastro) VALUES (1, '2024-03-10T10:00:00');
INSERT INTO Cliente(id, dataCadastro) VALUES (2, '2024-05-15T15:30:00');
INSERT INTO Cliente(id, dataCadastro) VALUES (3, '2024-07-20T11:00:00');

INSERT INTO Funcionario(id, cargo, departamento_id) VALUES (4, 'Gerente de Vendas', 1);
INSERT INTO Funcionario(id, cargo, departamento_id) VALUES (5, 'Analista Financeiro', 2);
INSERT INTO Funcionario(id, cargo, departamento_id) VALUES (6, 'Desenvolvedor Pleno', 3);
INSERT INTO Funcionario(id, cargo, departamento_id) VALUES (7, 'Desenvolvedora Sênior', 3);

INSERT INTO Fornecedor(id, razaoSocial) VALUES (8, 'Componentes Express Ltda');
INSERT INTO Fornecedor(id, razaoSocial) VALUES (9, 'InfoPeças Distribuidora S.A.');
INSERT INTO Fornecedor(id, razaoSocial) VALUES (10, 'Bits & Bytes Comércio de Eletrônicos');

INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) VALUES('RM750x', 750, 799.90, 50, 'GOLD', 1);
INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) VALUES('SF600', 600, 650.00, 35, 'PLATINUM', 2);
INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) VALUES('PRIME TX-1000', 1000, 1890.50, 15, 'TITANIUM', 3);
INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) VALUES('MWE 550', 550, 399.00, 80, 'BRONZE', 4);
INSERT INTO fonte(nome, potencia, preco, estoque, certificacao, modelo_id) VALUES('V850 SFX', 850, 950.00, 25, 'GOLD', 5);

INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Rua das Flores', '123', 'Apto 101', 'Jardim Primavera', 'São Paulo', 'SP', '01000-001', 1);
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Avenida Brasil', '1500', 'Sala 50', 'Centro', 'Rio de Janeiro', 'RJ', '20000-002', 2);
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Travessa do Comércio', '45', 'Casa', 'Lapa', 'Rio de Janeiro', 'RJ', '20031-040', 2);
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Quadra 104 Sul, Avenida LO-01', '33', 'Residencial Palmas', 'Plano Diretor Sul', 'Palmas', 'TO', '77020-020', 3);
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Rua da Empresa', '1000', null, 'Centro', 'São Paulo', 'SP', '01001-000', 4);
INSERT INTO enderecos(rua, numero, complemento, bairro, cidade, estado, cep, pessoa_id) VALUES ('Av. Principal', '500', 'Bloco B', 'Comercial', 'Campinas', 'SP', '13000-000', 8);

INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('11', '98765-4321', 1);
INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('11', '5555-4444', 1);
INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('21', '91234-5678', 2);
INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('63', '98450-1020', 3);
INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('11', '3030-4040', 4);
INSERT INTO telefones(ddd, numero, pessoa_id) VALUES ('19', '3232-5050', 8);

INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (1, 8);
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (1, 9);
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (2, 8);
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (3, 9);
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (4, 10);
INSERT INTO fonte_fornecedor(fonte_id, fornecedor_id) VALUES (5, 10);