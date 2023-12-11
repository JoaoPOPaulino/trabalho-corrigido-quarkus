-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserir dados de Telefone
INSERT INTO telefone (codigoArea, numero) VALUES ('63', '5555-5555');
INSERT INTO telefone (codigoArea, numero) VALUES ('55', '1234-5678');
INSERT INTO telefone (codigoArea, numero) VALUES ('71', '9876-5432');
INSERT INTO telefone (codigoArea, numero) VALUES ('44', '2345-6789');
INSERT INTO Telefone (codigoArea, numero) VALUES ('11', '9999-8888');
INSERT INTO Telefone (codigoArea, numero) VALUES ('77', '7777-7777');

-- Inserir dados de Endereco
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado1', 'Cidade1', 'Quadra1', 'Rua1', 123);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado2', 'Cidade2', 'Quadra2', 'Rua2', 456);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado3', 'Cidade3', 'Quadra3', 'Rua3', 789);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado4', 'Cidade4', 'Quadra4', 'Rua4', 101);
INSERT INTO endereco (estado, cidade, quadra, rua, numero) VALUES ('Estado5', 'Cidade5', 'Quadra5', 'Rua5', 105);

-- Inserir dados de Usuario
INSERT INTO usuario (nome, email, login, perfil, senha) VALUES ('Elon Musk', 'musk@gmail.com', 2, 'DzdKfFtHned4y7fLASqK0gH9EqUAMZgn6HuhapPc6l0ycYnZ/AZB2mFjbV5ADHvCpr8u3Vm8SkIIJ55gmKQDdA==');
INSERT INTO usuario (nome, email, login, perfil, senha) VALUES ('Bill Gates', 'gates@gmail.com', 1, 'cQa5YaODDHhULIAmdDvDQ/YyU9jAzqqhz1hzmFU7LB1CHLRrUEgu9r/O5cyup6ghql/1J5J60tVChoWwa5XL6Q==');
INSERT INTO usuario (nome, email, login, perfil, senha) VALUES ('jp', 'jp@hotmail.com', 'jp', 1, 'jp');


-- Inserir dados de Quarto
INSERT INTO quarto (numero, tipoQuarto, preco, disponivel) VALUES (101, 1, 150.0, true);
INSERT INTO quarto (numero, tipoQuarto, preco, disponivel) VALUES (102, 2, 200.0, true);
INSERT INTO quarto (numero, tipoQuarto, preco, disponivel) VALUES (103, 3, 300.0, false);
INSERT INTO quarto (numero, tipoQuarto, preco, disponivel) VALUES (104, 4, 450.0, true);

-- Inserir Telefone no Usuario
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(1, 1);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(2, 2);
INSERT INTO usuario_telefone(id_usuario, id_telefone) VALUES(3, 3);

-- Inserir Endereco no Usuario
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(1, 1);
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(2, 2);
INSERT INTO usuario_endereco(id_usuario, id_endereco) VALUES(3, 3);

-- Inserir dados de Comentario
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 1', '2023-09-01 10:00:00', 1);
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 2', '2023-09-02 11:00:00', 2);
INSERT INTO comentario (conteudo, dataCriacao, id_usuarios) VALUES ('Comentário 3', '2023-09-03 12:00:00', 3);
-- Inserir dados de Reserva (corrigido)
INSERT INTO reserva (dataInicio, dataFim, preco, id_quarto, id_usuario) VALUES ('2023-09-10', '2023-09-15', 500.0, 1, 1);
INSERT INTO reserva (dataInicio, dataFim, preco, id_quarto, id_usuario) VALUES ('2023-10-01', '2023-10-05', 600.0, 2, 2);
INSERT INTO reserva (dataInicio, dataFim, preco, id_quarto, id_usuario) VALUES ('2023-10-10', '2023-10-15', 700.0, 3, 3);

-- Inserir dados de Servico
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 1', 'Descrição 1', '08:00', '10:00');
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 2', 'Descrição 2', '10:00', '12:00');
INSERT INTO servico (nome, descricao, horaInicio, horaFim) VALUES ('Serviço 3', 'Descrição 3', '12:00', '14:00');


-- Inserir dados de Pagamento
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-09-05 15:00:00', 500.0, 1, 1);
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-10-06 16:00:00', 600.0, 2, 2);
INSERT INTO pagamento (dataPagamento, valor, tipoPagamento, id_reserva) VALUES ('2023-10-16 17:00:00', 700.0, 3, 3);