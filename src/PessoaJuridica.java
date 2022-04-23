package src;
public class PessoaJuridica extends Cliente{

    private String cnpj;

    public PessoaJuridica(String nome, String cnpj){
        super(nome);
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + ",\n CNPJ: " + this.cnpj + "";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}