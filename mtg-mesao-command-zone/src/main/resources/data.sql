insert into Deck
(id_Deck, nome_Commandante, cmc_Commander, preco_total_deck, tribo_Commandante)
values
(null, 'Pé-de-Limo', 3, 299.50, 'Golgari'),
(null, 'Atraxa', 4, 430.20,'Crescimento '),
(null, 'Fenax', 5, 500.20,'Dimir');

insert into Carta
(id_carta, nome_carta, cor_tribo, habilidade, preco_carta, FK_DECK)
values
(null,'Danação', 'Preto', 'Destrua todas as criaturas. Elas não podem ser regeneradas.',  25.99, 1),
(null, 'Saprofita', 'Verde', '',  2.00, 1),
(null, 'Cólera de Deus', 'Branco', 'Destrua todas as criaturas.', 200.30, 2),
(null, 'Iona, Shield of Emeria', 'Branco', 'Escolha uma cor. Oponentes não podem conjurar mágicas daquela cor.', 132.99, 2),
(null, 'Procura no Tomo', 'Azul', 'Compre um card, tombe 2 no cemitério.', 3.40, 3),
(null, 'Segredos Afogados','Azul', 'Quando uma mágica azul resolver, tombe 2', 5.50, 3),
(null, 'Yavimaya Sapherd', 'Verde', 'Entre em campo, crie uma ficha saprófita 1/1', 3.99, 1);


