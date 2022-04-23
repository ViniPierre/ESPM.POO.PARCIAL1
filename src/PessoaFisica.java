package src;
public class PessoaFisica extends Cliente{
    
    private String cpf;

    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + ",\n CPF: " + this.cpf + "";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}