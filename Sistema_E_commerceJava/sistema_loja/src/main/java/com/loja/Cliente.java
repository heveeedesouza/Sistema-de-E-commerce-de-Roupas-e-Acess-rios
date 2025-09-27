package com.loja;

/* esse record é usado em tranferência de dados(como o que estamos fazendo),
   não tem uma lógica complexa e por isso não precisa de métodos para manipular os atributos */
public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String rua;
    private String numero;
    private String cep;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String telefone, String rua, String numero, String cep) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", Nome: " + this.getNome() + ", CPF: " + this.getCpf() + ", Telefone: "
                + this.getTelefone() + ", Endereço: " + this.getRua() + ", " + this.getNumero() + ", CEP: "
                + this.getCep() + "\n";

    }

}
