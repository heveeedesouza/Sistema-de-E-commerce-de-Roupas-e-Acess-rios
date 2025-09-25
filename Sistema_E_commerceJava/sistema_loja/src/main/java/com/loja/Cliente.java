package com.loja;

/* esse record é usado em tranferência de dados(como o que estamos fazendo),
   não tem uma lógica complexa e por isso não precisa de métodos para manipular os atributos */
public record Cliente(Long id, String nome, String cpf, String telefone, String rua, String numero, String cep) {

    public Cliente(Long id, String nome, String cpf, String telefone, String rua, String numero, String cep) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

}
