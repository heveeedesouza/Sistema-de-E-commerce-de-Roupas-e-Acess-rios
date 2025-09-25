create table entrega (
    idEntrega int primary key not null,
    dataPrevista date not null,
    status varchar (200),
    dataEfetiva date,
    transportadora varchar (200),
    codRastreio varchar(50)
);

create table pedido(
    idPedido int primary key not null,
    dataCompra date not null,
    valorTotal decimal(10,2) not null,
    status varchar (100) not null,
    formaDePagamento varchar (100) not null ,
    historico varchar (500),
    idEntrega int,
    foreign key (idEntrega) references entrega(idEntrega)
);

create table categoria(
    idCategoria int primary key not null,
    tipo varchar(100) not null,
    descricao varchar(200),
    idPedido int not null,
    foreign key (idPedido) references pedido(idPedido)
);

create table produto (
    idProduto int primary key not null,
    descricao varchar(200) not null,
    nome varchar (200) not null,
    preco decimal (10,2) not null,
    quantEstoque int not null,
    cor varchar (20) not null,
    tamanho varchar(20) not null,
   
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

create table cliente (
    idCliente int primary key not null,
    nome varchar (200) not null,
    cpf varchar(20) not null,
    telefone varchar(30) not null,
    rua varchar (100) not null,
    numero varchar(20) not null,
    cep varchar (20) not null
);

create table avaliacao(
    idAvaliacao int primary key not null,
    nota int not null,
    descricao varchar (200) not null,
    idProduto int not null ,
    idCliente int not null,
    foreign key (idProduto) references produto (idProduto),
    foreign key (idCliente) references cliente(idCliente)
);


create table cupomDesconto(
    idCupom int primary key not null,
    codigo varchar(100) not null,
    status varchar (100) not null,
    tipoDeDesconto varchar(100),
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


insert into cliente (idCliente, nome, cpf, telefone, numero, rua, cep)
values (1, 'Maria', '12345678901', '99887766', '123', 'Rua das Flores', '49000-000');

insert into fornecedor (idFornecedor, cnpj, contato, nome, numero, rua, cep)
values (1, '11222333000199', '99887766', 'Fornecedor1', '45', 'Rua Itabaiana', '49000-111');
