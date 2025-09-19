-- Versão simplificada com IDs fixos para teste
INSERT INTO marca(id, nome) VALUES(1, 'Corsair');
INSERT INTO marca(id, nome) VALUES(2, 'Seasonic');
INSERT INTO marca(id, nome) VALUES(3, 'Cooler Master');

INSERT INTO cliente(id, nome, email, telefone) VALUES(1, 'Gabriel', 'gabriel@example.com', '123456789');
INSERT INTO cliente(id, nome, email, telefone) VALUES(2, 'Lucas', 'lucas@example.com', '987654321');

INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(1, 'RM750x', 750, 799.90, 'GOLD', 1);
INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(2, 'SF600', 600, 650.00, 'PLATINUM', 1);
INSERT INTO fonte(id, nome, potencia, preco, certificacao, marca_id) VALUES(3, 'PRIME TX-1000', 1000, 1890.50, 'TITANIUM', 2);
