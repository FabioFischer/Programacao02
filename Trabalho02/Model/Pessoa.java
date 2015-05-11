package Model;

public class Pessoa implements java.io.Serializable{

    private static final long serialVersionUID = 1L; 
    private String cpf;
    private String nome;
    private String email;
    private int rg;

    public Pessoa(String nome, String cpf, int rg, String email) {
        super();
        this.nome = nome;        
        this.cpf = cpf;
        this.rg = rg;  
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null){
            throw new IllegalArgumentException("Todos os campos necessitam serem preenchidos!");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null){
            throw new IllegalArgumentException("Todos os campos necessitam serem preenchidos!");
        }
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf == null){
            throw new IllegalArgumentException("Todos os campos necessitam serem preenchidos!");
        }
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        if(rg <= 0 ){
            throw new IllegalArgumentException("O campo 'RG' esta inválido!");
        }
        this.rg = rg;
    }
    
    @Override
    public String toString(){
        return this.nome + " - " + this.cpf;
    }
}
