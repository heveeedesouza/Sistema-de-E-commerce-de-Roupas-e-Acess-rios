create table promocao (
idPromocao int primary key not null,
nome varchar(200) not null,
descricao varchar (200),
status varchar(100) not null,
dataInicio date not null,
dataFim date not null
);

create table produto (
idProduto int primary key not null,
descricao varchar(200) not null,
nome varchar (200) not null,
preco decimal (5,2) not null,
quantEstoque int not null,
cor varchar (20) not null ,
tamanho int not null,
categoria varchar (100) not null
);

create table fornecedor(
idFornecedor int primary key not null,
cnpj int not null,
contato int not null,
nome varchar (100) not null,
rua varchar (30) not null,
numero int not null,
cep varchar (20) not null
);

create table cliente (
idCliente int primary key not null,
nome varchar (200) not null,
cpf int not null,
telefone int not null,
rua varchar (30) not null,
numero int not null,
cep varchar (20) not null
 );

create table avaliacao(
idAvaliacao int primary key,
nota int not null,
descricao varchar (200) not null,
idProduto int not null ,
foreign key (idProduto) references produto (idProduto),
idCliente int not null,
foreign key (idCliente) references cliente(idCliente)
 );

 create table entrega (
 idEntrega int primary key not null,
 dataPrevista date not null,
 status varchar (200),
 dataEfetiva date not null ,
 transportadora varchar (200) not null ,
 codRastreio varchar(50) not null
 );

 create table pedido(
 idPedido int primary key not null,
 dataCompra date not null,
 valorTotal decimal(5,2) not null,
 status varchar (100) not null,
 formaDePagamento varchar (100) not null ,
 historico varchar (200) not null ,
 idEntrega int not null,
 foreign key (idEntrega) references entrega(idEntrega)
 );

 create table cupomDesconto(
 idCupom int primary key not null,
 codigo int not null,
 status varchar (100) not null,
 tipoDeDesconto varchar(100) 
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
 valor int not null,
 data date not null,
 modalidade varchar (100) not null, 
 idPedido int not null,
 foreign key (idPedido) references pedido (idPedido)
 );

create table ProdutoPromocao (
    idProduto int not null,
    idPromocao int not null,
    descricao varchar (200), 
    primary key (idProduto,idPromocao),
    foreign key (idProduto) references Produto(idProduto),
    foreign key (idPromocao) references Promocao(idPromocao)
);

create table ProdutoFornecedor (
    idProduto int not null,
    idFornecedor int not null,
    descricao varchar (200), 
    primary key (idProduto, idFornecedor),
    foreign key (idProduto) references produto(idProduto),
   foreign key (idFornecedor) references fornecedor(idFornecedor)
);

create table ItensPedido (
    idPedido INT NOT NULL,
    idProduto INT NOT NULL,
    descricao varchar (200)  NOT NULL,
    primary key (idPedido, idProduto),
    foreign key (idPedido) references Pedido(idPedido),
    foreign key (idProduto) references Produto(idProduto)
);

create table pedidoCupom (
    idPedido INT NOT NULL,
    idCupom INT NOT NULL,
    descricao varchar (200)  NOT NULL,
    primary key (idPedido, idCupom),
    foreign key (idPedido)references Pedido(idPedido),
   foreign key (idCupom) references CupomDesconto(idCupom)
);


insert into cliente (idCliente, nome, cpf, telefone, numero, rua, cep)
values (1, 'Maria', 12345678901, 99887766, 123, 'Rua das Flores', '49000-000');


insert into fornecedor (idFornecedor, cnpj, contato, nome, numero, rua, cep)
values (1, 11222333000199, 99887766, 'Fornecedor1', 45, 'Rua Itabaiana', '49000-111');
