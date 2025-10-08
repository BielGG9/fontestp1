INSERT INTO marca(id, nome) VALUES(1, 'Corsair');
INSERT INTO marca(id, nome) VALUES(2, 'Seasonic');
INSERT INTO marca(id, nome) VALUES(3, 'Cooler Master');

INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(1, 'RM750x', 750, 799.90, 50, 'GOLD', 1);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(2, 'SF600', 600, 650.00, 35, 'PLATINUM', 1);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(3, 'PRIME TX-1000', 1000, 1890.50, 15, 'TITANIUM', 2);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(4, 'MWE 550', 550, 399.00, 80, 'BRONZE', 3);
INSERT INTO fonte(id, nome, potencia, preco, estoque, certificacao, marca_id) VALUES(5, 'V850 SFX', 850, 950.00, 25, 'GOLD', 3);

INSERT INTO fornecedores(id, nome, email, razao_social, cnpj) VALUES (1, 'Componentes Express', 'contato@componentesexpress.com', 'Componentes Express Ltda', '11.222.333/0001-44');
INSERT INTO fornecedores(id, nome, email, razao_social, cnpj) VALUES (2, 'InfoPeças', 'vendas@infopecas.com.br', 'InfoPeças Distribuidora S.A.', '55.666.777/0001-88');
INSERT INTO fornecedores(id, nome, email, razao_social, cnpj) VALUES (3, 'Bits & Bytes', 'comercial@bitsbytes.com', 'Bits & Bytes Comércio de Eletrônicos', '99.888.777/0001-66');

INSERT INTO funcionarios(id, nome, email, cargo, data_admissao) VALUES (4, 'Carlos Andrade', 'carlos.andrade@empresa.com', 'Gerente de Vendas');
INSERT INTO funcionarios(id, nome, email, cargo, data_admissao) VALUES (5, 'Patricia Mendes', 'patricia.mendes@empresa.com', 'Analista Financeiro');
INSERT INTO funcionarios(id, nome, email, cargo, data_admissao) VALUES (6, 'Fernando Costa', 'fernando.costa@empresa.com', 'Desenvolvedor Pleno');
INSERT INTO funcionarios(id, nome, email, cargo, data_admissao) VALUES (7, 'Juliana Lima', 'juliana.lima@empresa.com', 'Desenvolvedora Sênior');

INSERT INTO departamento(id, sigla, descricao, statusDepartamento, funcionario_id) VALUES (1, 'VND', 'Departamento de Vendas', 'ATIVO', 4);
INSERT INTO departamento(id, sigla, descricao, statusDepartamento, funcionario_id) VALUES (2, 'FIN', 'Departamento Financeiro', 'ATIVO', 5);
INSERT INTO departamento(id, sigla, descricao, statusDepartamento, funcionario_id) VALUES (3, 'TI', 'Departamento de Tecnologia da Informação', 'ATIVO', 7);
INSERT INTO departamento(id, sigla, descricao, statusDepartamento, funcionario_id) VALUES (4, 'RH', 'Departamento de Recursos Humanos', 'INATIVO', 4);

INSERT INTO cliente(id, nome, email, dataCadastro) VALUES (8, 'João da Silva', 'joao.silva@email.com', '2024-03-10T10:00:00');
INSERT INTO cliente(id, nome, email, dataCadastro) VALUES (9, 'Maria Oliveira', 'maria.o@email.com', '2024-05-15T15:30:00');
INSERT INTO cliente(id, nome, email, dataCadastro) VALUES (10, 'Ana Carolina', 'ana.carolina.to@email.com', '2024-07-20T11:00:00');

INSERT INTO endereco(id, rua, numero, complemento, bairro, cidade, estado, cep, cliente_id) VALUES (1, 'Rua das Flores', '123', 'Apto 101', 'Jardim Primavera', 'São Paulo', 'SP', '01000-001', 8);
INSERT INTO endereco(id, rua, numero, complemento, bairro, cidade, estado, cep, cliente_id) VALUES (2, 'Avenida Brasil', '1500', 'Sala 50', 'Centro', 'Rio de Janeiro', 'RJ', '20000-002', 9);
INSERT INTO endereco(id, rua, numero, complemento, bairro, cidade, estado, cep, cliente_id) VALUES (3, 'Travessa do Comércio', '45', 'Casa', 'Lapa', 'Rio de Janeiro', 'RJ', '20031-040', 9);
INSERT INTO endereco(id, rua, numero, complemento, bairro, cidade, estado, cep, cliente_id) VALUES (4, 'Quadra 104 Sul, Avenida LO-01', '33', 'Residencial Palmas', 'Plano Diretor Sul', 'Palmas', 'TO', '77020-020', 10);

INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (1, '11', '98765-4321', 8);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (2, '11', '5555-4444', 8);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (3, '21', '91234-5678', 9);
INSERT INTO telefones(id, ddd, numero, cliente_id) VALUES (4, '63', '98450-1020', 10);