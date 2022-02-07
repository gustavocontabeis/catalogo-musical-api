INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Recebimento de Condominios');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com impostos');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com manutenção');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com funcionarios');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com eletricidade');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com limpeza');
INSERT INTO public.centro_de_custo (id_centro_de_custo, nome) values (nextval('public.seq_centro_de_custo'), 'Despesa com piscina');
INSERT INTO public.pessoa (id_pessoa, cpf, genero, nascimento, nome) values (nextval('public.seq_pessoa'), '901.687.800-97', 'MASCULINO', '1978-08-26', 'Gustavo da Silva');
INSERT INTO public.sindico(id_sindico, ate, de, id_pessoa)VALUES(nextval('public.seq_sindico'), null, '2020-01-01', currval('public.seq_pessoa'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Sierra del Mar', 'Rua Vicente Monteggia', '2132', 'Vila São José', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.faturamento(id_faturamento, periodo, id_condominio)VALUES(nextval('public.seq_faturamento'), '2022-01-01', currval('public.seq_condominio'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '101', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.boleto (id_boleto, juros, multa, pagamento, total, valor, vencimento, id_apartamento, id_faturamento, id_pessoa_titular) VALUES(nextval('public.seq_boleto'), 0.00, 0.00, '2022-01-01', 400.00, 400.00, '2022-01-01', currval('public.seq_apartamento'), currval('public.seq_faturamento'), currval('public.seq_pessoa'));
INSERT INTO public.boleto (id_boleto, juros, multa, pagamento, total, valor, vencimento, id_apartamento, id_faturamento, id_pessoa_titular) VALUES(nextval('public.seq_boleto'), 0.00, 0.00, NULL, 0.00, 400.00, '2022-02-01', currval('public.seq_apartamento'), currval('public.seq_faturamento'), currval('public.seq_pessoa'));
INSERT INTO public.boleto (id_boleto, juros, multa, pagamento, total, valor, vencimento, id_apartamento, id_faturamento, id_pessoa_titular) VALUES(nextval('public.seq_boleto'), 0.00, 0.00, NULL, 0.00, 400.00, '2022-03-01', currval('public.seq_apartamento'), currval('public.seq_faturamento'), currval('public.seq_pessoa'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '102', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '103', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '201', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '202', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.apartamento (id_apartamento, numero, id_bloco, id_pessoa_proprietario, id_pessoa_titular) VALUES (nextval('public.seq_apartamento'), '203', currval('public.seq_bloco'), currval('public.seq_pessoa'), currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 2', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 3', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 4', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Real', 'Rua Antônio Carneiro Pinto', '375', 'Bela Vista', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bella Vida', 'Praça Comendador Tavares', '1485', 'Pedra Redonda', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda dos Encantos', 'Rua Quatro Mil Quinhentos e Sessenta e Um', '670', 'Cristal', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Real', 'Avenida david Weinstein', '765', 'Vila São José', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Belle Giardino', 'Rua Alameda Alípio César', '1983', 'Higienópolis', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos do Cerrado', 'Avenida Maria Elaine Wotter', '2630', 'Cristal', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bella Rússia', 'Avenida Erasto Roxo de Araújo Corrêa', '558', 'Jardim Itu-Sabará', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bosque das Araucárias', 'Travessa José Conde', '163', 'Floresta', 'Guaíba', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bella Turquia', 'Rua Alameda Alípio César', '2033', 'Lomba do Pinheiro', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Alamedas dos Pêssegos', 'Praça Domingos Fernandes de Souza', '1634', 'Restinga', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Serra', 'Rua São Judas Tadeu', '2374', 'Tristeza', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda dos Encantos', 'Travessa Três Mil e Quatro', '2014', 'Jardim Floresta', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Edifício Bella Hungria', 'Rua Comendador Tavares', '2426', 'Jardim Itu', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Rua Antônio Carneiro Pinto', '412', 'Jardim do Salso', 'Guaíba', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Rua José Inácio', '2196', 'Menino Deus', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bella Vida', 'Avenida Três Mil e Quatro', '758', 'São Sebastião', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Belle Giardino', 'Rua Coronel Hélio Bezerra', '208', 'Centro', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela Alvorada', 'Rua Coronel Vicente', '2403', 'Farroupilha', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Real', 'Avenida Coronel Hélio Bezerra', '663', 'Campo Novo', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Alamedas dos Pêssegos', 'Rua Coronel Vicente', '2215', 'Ponta Grossa', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Edifício Bela Grécia', 'Praça Beco Vinte e Quatro', '1061', 'Restinga', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Avenida Alameda Alípio César', '2421', 'Rio Branco', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alamedas de Campos Residencial', 'Rua José Conde', '747', 'Jardim Dona Leopoldina', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda do Campo', 'Rua Adroaldo Novo Correa', '1290', 'Cascata', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bella Vida', 'Rua Acesso A - Três', '2620', 'Ipanema', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Avenida José Inácio', '2542', 'Jardim Sabará', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Condomínio Bella Áustria', 'Avenida Engenheiro Alberto Henrique Kruse', '2715', 'Humaitá', 'Porto Alegre', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela Suiça', 'Praça José Conde', '2923', 'Chácara das Pedras', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela', 'Rua Quarenta e Sete', '1802', 'Independência', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bosque dos Girassóis', 'Rua Raul Bopp', '63', 'MontSerrat', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Vida e Lazer', 'Rua Engenheiro Alberto Henrique Kruse', '2358', 'Morro Santana', 'Guaíba', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.pessoa (id_pessoa, cpf, genero, nascimento, nome) values (nextval('public.seq_pessoa'), '901.687.800-99', 'MASCULINO', '1978-08-26', 'Lautenir Jose da Silva');
INSERT INTO public.sindico(id_sindico, ate, de, id_pessoa)VALUES(nextval('public.seq_sindico'), null, '2020-01-01', currval('public.seq_pessoa'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela', 'Praça São Jorge', '1846', 'Mário Quintana', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Edifício Bela Grécia', 'Rua Mil Trezentos e Sete', '942', 'Lami', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Condomínio Bosque das Águas', 'Travessa José Carlos Dias de Oliveira', '296', 'Partenon', 'Guaíba', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Real', 'Avenida Mil Trezentos e Sete', '1996', 'Agronomia', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela Vista', 'Rua Antônio Carneiro Pinto', '1033', 'Jardim Botânico', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Sierra del Mar', 'Rua Coronel Vicente', '2139', 'Cristo Redentor', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Alameda Vida e Lazer', 'Rua Antônio Carneiro Pinto', '1223', 'Vila Conceição', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Condomínio Bella Áustria', 'Avenida José Inácio', '2167', 'Jardim Isabel', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Rua Rimolo Biagio', '2886', 'Hípica', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela Suiça', 'Avenida Professor Duplan', '2242', 'Farrapos', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bela Alvorada', 'Travessa José Carlos Dias de Oliveira', '2255', 'Costa e Silva', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Alamedas dos Pêssegos', 'Avenida Três Mil e Quatro', '786', 'Três Figueiras', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Arcos da Lapa Residencial', 'Avenida Três Mil e Quatro', '1879', 'Jardim Itu', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Alamedas dos Pêssegos', 'Rua Raul Bopp', '1305', 'Cristal', 'Relvado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Belle Giardino', 'Avenida Mil Trezentos e Sete', '595', 'Ipanema', 'Cachoeirinha', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bella Vida', 'Rua Alba Carvalho Degrazia', '925', 'Lageado', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Bella Rússia', 'Rua Gervásio da Rosa', '2824', 'Mário Quintana', 'Lajeado', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
INSERT INTO public.condominio (id_condominio, nome, logradouro, numero, bairro, cidade, id_sindico) values (nextval('public.seq_condominio'),'Residencial Bom Retiro', 'Rua da Cultura', '1639', 'Auxiliadora', 'Bom Retiro do Sul', currval('public.seq_pessoa'));
INSERT INTO public.bloco (id_bloco, nome, tipo, id_condominio) VALUES(nextval('public.seq_bloco'), 'Bloco 1', 'BLOCO', currval('public.seq_condominio'));
