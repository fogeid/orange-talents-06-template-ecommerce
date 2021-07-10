INSERT INTO tb_usuario (login, senha, data_criacao)
VALUES ('diego.batista@zup.com.br', '$2a$10$.OcRxcUUuRbkJAPmjjLC9O4U.29L4KMfOn859IegHZUumx717c3eu', '2021-07-07');

INSERT INTO tb_categoria (nome)
VALUES ('Celulares');
INSERT INTO tb_categoria (nome, categoria_mae_id)
VALUES ('Smartphones', 1);

INSERT INTO tb_produto (nome, valor, quantidade_disponivel, descricao, data_criacao, categoria_id, usuario_id)
VALUES ('iPhone 10', 5999.9, 10, 'Descrição teste', '2021-07-07', 2, 1);

INSERT INTO produto_caracteristicas (produto_id, caracteristicas, descricao)
VALUES (1, 'Tela', 'FullHD');
INSERT INTO produto_caracteristicas (produto_id, caracteristicas, descricao)
VALUES (1, 'Cor', 'Preto');
INSERT INTO produto_caracteristicas (produto_id, caracteristicas, descricao)
VALUES (1, 'Câmera', '300mpx');