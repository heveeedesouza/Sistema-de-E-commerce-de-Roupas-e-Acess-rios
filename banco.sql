create table entrega (
    idEntrega int primary key not null,
    dataPrevista date not null,
    status varchar (200),
    dataEfetiva date,
    transportadora varchar (200),
    codRastreio varchar(50)
);

create table cliente (
    idCliente int primary key not null,
    nome varchar (200) not null,
    cpf varchar(20) not null,
    telefone varchar(30) not null,
    rua varchar (100) not null,
    numero varchar(20) not null,
    cep varchar (20) not null
);

create table pedido(
    idPedido int primary key not null,
    dataCompra date not null,
    valorTotal decimal(10,2) not null,
    status varchar (100) not null,
    formaDePagamento varchar (100) not null,
    historico varchar (500),
    idEntrega int,
    idCliente int not null,
    foreign key (idEntrega) references entrega(idEntrega),
    foreign key (idCliente) references cliente(idCliente)
);

create table categoria(
    idCategoria int primary key not null,
    tipo varchar(100) not null,
    descricao varchar(200)
);

create table produto (
    idProduto int primary key not null,
    descricao varchar(200) not null,
    nome varchar (200) not null,
    preco decimal (10,2) not null,
    quantEstoque int not null,
    cor varchar (20) not null,
    tamanho varchar(20) not null,
    idCategoria int not null,
    foreign key (idCategoria) references categoria(idCategoria)
);

create table promocao (
    idPromocao int primary key not null,
    nome varchar(200) not null,
    descricao varchar (200),
    status varchar(100) not null,
    dataInicio date not null,
    dataFim date not null,
    idCategoria int,
    foreign key (idCategoria) references categoria(idCategoria)
);

create table RelacaoPromocao (
    idProduto int not null,
    idPromocao int not null,
    descricao varchar (200),
    primary key (idProduto,idPromocao),
    foreign key (idProduto) references produto(idProduto),
    foreign key (idPromocao) references promocao(idPromocao)
);

create table fornecedor(
    idFornecedor int primary key not null,
    cnpj varchar(20) not null,
    contato varchar(50) not null,
    nome varchar (100) not null,
    rua varchar (100) not null,
    numero varchar(20) not null,
    cep varchar (20) not null
);

create table Fornece (
    idProduto int not null,
    idFornecedor int not null,
    descricao varchar (200),
    primary key (idProduto, idFornecedor),
    foreign key (idFornecedor) references fornecedor(idFornecedor),
    foreign key (idProduto) references produto(idProduto)
);

create table avaliacao(
    idAvaliacao int primary key not null,
    nota int not null,
    descricao varchar (200) not null,
    idProduto int not null,
    idCliente int not null,
    foreign key (idProduto) references produto (idProduto),
    foreign key (idCliente) references cliente(idCliente)
);

create table cupomDesconto(
    idCupom int primary key not null,
    codigo varchar(100) not null,
    status varchar (100) not null,
    tipoDeDesconto varchar(100),
    dataValidade date not null
);

create table devolucao(
    idDevolucao int primary key not null,
    status varchar (100) not null,
    motivo varchar (200) not null,
    idPedido int not null,
    foreign key (idPedido) references pedido (idPedido)
);

create table pagamento(
    idPagamento int primary key not null,
    status varchar (100) not null,
    valor decimal(10,2) not null,
    data date not null,
    modalidade varchar (100) not null,
    idPedido int not null,
    foreign key (idPedido) references pedido (idPedido)
);

create table ItensPedido (
    idPedido INT NOT NULL,
    idProduto INT NOT NULL,
    descricao varchar (200) not null,
    primary key (idPedido, idProduto),
    foreign key (idPedido) references pedido(idPedido),
    foreign key (idProduto) references produto(idProduto)
);

create table Ganha (
    idPedido INT NOT NULL,
    idCupom INT NOT NULL,
    descricao varchar(200) not null,
    primary key (idPedido, idCupom),
    foreign key (idPedido) references pedido(idPedido),
    foreign key (idCupom) references cupomDesconto(idCupom)
);

create table troca (
    idTroca int primary key not null,
    status varchar (200) not null,
    produtoDevolvido varchar (200) not null,
    produtoEntregue varchar (200) not null,
    motivo varchar (200) not null,
    idPedido int not null,
    foreign key (idPedido) references pedido (idPedido)
);

DROP TABLE Ganha,
           ItensPedido,
           pagamento,
           devolucao,
           troca,
           avaliacao,
           Fornece,
           RelacaoPromocao,
           promocao,
           produto,
           pedido,
           cupomDesconto,
           fornecedor,
           cliente,
           entrega,
           categoria;


-------------------------------------------------------
-- INSERÇÕES INICIAIS
-------------------------------------------------------

-- Inserir cliente
insert into cliente (idCliente, nome, cpf, telefone, numero, rua, cep)
values (1, 'Maria', '12345678901', '99887766', '123', 'Rua das Flores', '49000-000');

-- Inserir fornecedor
insert into fornecedor (idFornecedor, cnpj, contato, nome, numero, rua, cep)
values (1, '11222333000199', '99887766', 'Fornecedor1', '45', 'Rua Itabaiana', '49000-111');

-- Inserir produto (precisa que a categoria seja criada antes)
insert into produto (idProduto, descricao, nome, preco, quantEstoque, cor, tamanho)
values (1, 'Vestido Azul longo', 'Vestido Azul', 199.90, 10, 'Azul', 'M');

-- Relacionar produto com fornecedores
insert into Fornece (idProduto, idFornecedor, descricao)
values (1, 1, 'Fornecedor oficial do Vestido Azul');

insert into Fornece (idProduto, idFornecedor, descricao)
values (1, 2, 'Fornecedor alternativo do Vestido Azul');

----------------------------------------------------------
-- Deletar cliente 
---------------------------------------------------------- 
Delete from cliente
where idCliente = 1;
-------------------------------------------------------
-- ATUALIZAÇÕES E REMOÇÕES
-------------------------------------------------------

-- Atualizar preço do produto
update produto set preco = 179.90 where idProduto = 1;

-- Atualizar estoque (diminuir 1 unidade)
update produto set quantEstoque = quantEstoque - 1 where idProduto = 1;

-- Excluir produto
delete from produto where idProduto = 1;

-------------------------------------------------------
-- PEDIDOS E RELACIONAMENTOS
-------------------------------------------------------

-- Inserir pedido
insert into pedido (idPedido, dataCompra, valorTotal, status, formaDePagamento, historico, idEntrega)
values (1, getdate(), 0, 'pendente de pagamento', 'cartão', 'Pedido criado', null);

-- Inserir itens do pedido
insert into ItensPedido (idPedido, idProduto, descricao)
values (1, 1, 'Vestido Azul tamanho M'),
       (1, 2, 'Bolsa Preta Couro');

-- Atualizar status do pedido
update pedido set status = 'em processamento' where idPedido = 1;

-- Inserir pagamento
insert into pagamento (idPagamento, status, valor, data, modalidade, idPedido)
values (1, 'confirmado', 199.90, getdate(), 'cartão', 1);

-- Inserir cupom de desconto
insert into cupomDesconto (idCupom, codigo, status, tipoDeDesconto, dataValidade)
values (1, 'DESC10', 'ativo', '10%', '2025-12-31');

-- Relacionar cupom com pedido
insert into Ganha (idPedido, idCupom, descricao)
values (1, 1, 'Desconto aplicado de 10%');

-- Inserir promoção (precisa que a categoria seja criada antes)
insert into promocao (idPromocao, nome, descricao, status, dataInicio, dataFim, idCategoria)
values (1, 'Promo Verão', 'Desconto roupas', 'ativa', '2025-09-01', '2025-09-30', 1);

-- Inserir entrega
insert into entrega (idEntrega, dataPrevista, status, dataEfetiva, transportadora, codRastreio)
values (1, '2025-09-30', 'pendente', NULL, 'Correios', 'BR123456789');

-- Inserir devolução
insert into devolucao (idDevolucao, status, motivo, idPedido)
values (1, 'em análise', 'Tamanho incorreto', 1);

-- Inserir avaliação
insert into avaliacao (idAvaliacao, nota, descricao, idProduto, idCliente)
values (1, 5, 'Vestido lindo e confortável!', 1, 1);

-------------------------------------------------------
-- CONSULTAS (SELECTS)
-------------------------------------------------------

-- Pedidos com seus itens
select * from pedido where idPedido in (
  select idPedido from ItensPedido ip
  join produto p on ip.idProduto = p.idProduto
);

-- Nome do produto e descrição do item do pedido
select p.nome, ip.descricao
from ItensPedido ip
join produto p on ip.idProduto = p.idProduto
where ip.idPedido = 1;

-- Pagamento do pedido
select valor, modalidade from pagamento where idPedido = 1;

-- Pedidos pendentes de pagamento
select * from pedido where status = 'pendente de pagamento';

-- Pedidos entregues dentro de setembro de 2025
select * from pedido
where status = 'entregue'
and dataCompra between '2025-09-01' and '2025-09-30';

-- Produtos em promoção ativa
select p.nome, pr.nome as Promocao
from RelacaoPromocao rp
join produto p on rp.idProduto = p.idProduto
join promocao pr on rp.idPromocao = pr.idPromocao
where pr.status = 'ativa';

-- Pedidos com cupom aplicado
select distinct g.idPedido, c.codigo
from Ganha g
join cupomDesconto c on g.idCupom = c.idCupom;

-- Clientes que avaliaram produtos
select distinct c.nome
from cliente c
join avaliacao a on c.idCliente = a.idCliente;

-- Clientes com pedidos não entregues ou sem entrega
select distinct c.nome
from cliente c
join pedido p on p.idEntrega is null or p.status <> 'entregue';

-- Fornecedores de determinado produto
select f.nome
from Fornece fn
join fornecedor f on fn.idFornecedor = f.idFornecedor
where fn.idProduto = 1;

-- Produtos fornecidos por determinado fornecedor
select p.nome
from Fornece fn
join produto p on fn.idProduto = p.idProduto
where fn.idFornecedor = 1;

-- Produtos agrupados por categoria com maior preço
select c.tipo, p.nome, max(p.preco) as MaiorPreco
from produto p
join categoria c on p.idCategoria = c.idCategoria
group by c.tipo, p.nome;

-- Total de pedidos no período
select count(*) as TotalPedidos
from pedido
where dataCompra between '2025-09-01' and '2025-09-30';

-- Valor total de vendas no período
select sum(valorTotal) as TotalVendas
from pedido
where dataCompra between '2025-09-01' and '2025-09-30';

-- Ranking de clientes com mais pedidos em setembro/2025
select c.nome, count(p.idPedido) as TotalPedidos
from cliente c
join pedido p on c.idCliente = p.idCliente
where month(p.dataCompra) = 9 and year(p.dataCompra) = 2025
group by c.nome
order by TotalPedidos DESC;

-- Produtos mais vendidos por categoria
select c.tipo, p.nome, count(ip.idProduto) as QuantidadeVendida
from ItensPedido ip
join produto p on ip.idProduto = p.idProduto
join categoria c on p.idCategoria = c.idCategoria
group by c.tipo, p.nome
order by QuantidadeVendida desc;

-- Clientes que nunca compraram
select c.nome
from cliente c
where c.idCliente not in (select distinct idCliente from pedido);

-- Clientes que usaram cupons
select distinct cl.nome
from cliente cl
join pedido p on cl.idCliente = p.idCliente
join Ganha g on p.idPedido = g.idPedido;

-- Clientes que fizeram devoluções
select distinct cl.nome
from cliente cl
join pedido p on cl.idCliente = p.idCliente
join devolucao d on p.idPedido = d.idPedido;

-- Produtos sem avaliação
select p.nome
from produto p
where p.idProduto not in (select idProduto from avaliacao);

-- Clientes que compraram em todos os meses de 2025
select c.nome
from cliente c
join pedido p on c.idCliente = p.idCliente
where year(p.dataCompra) = 2025
group by c.nome
having count(distinct month(p.dataCompra)) = 12;

-- Fornecedores que não forneceram produtos
select f.nome
from fornecedor f
where f.idFornecedor not in (select distinct idFornecedor from Fornece);

-- Produtos em estoque que nunca foram pedidos
select p.nome
from produto p
where p.quantEstoque > 0
and p.idProduto not in (select distinct idProduto from ItensPedido);
