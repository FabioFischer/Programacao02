package Model;

public class Pessoa implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private String email;
    private String rg;

    private String mensagemCampoInvalido = "Campo {0} inválido!";

    public Pessoa(String nome, String cpf, String rg, String email) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setRg(rg);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "Nome"));
        }

        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "Email"));
        }

        if (!email.contains("@") || !email.contains(".com")) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "Email"));
        }

        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "CPF"));
        }

        if (cpf.length() != 11) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "CPF"));
        }

        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        if (rg == null || rg.isEmpty()) {
            throw new IllegalArgumentException(mensagemCampoInvalido.replace("{0}", "RG"));
        }

        this.rg = rg;
    }

    @Override
    public String toString() {
        return this.getNome() + " - " + this.getCpf();
    }

    public String imprimir() {
        return "\n Nome: " + this.getNome()
                + "\n Email: " + this.getEmail()
                + "\n CPF: " + this.getCpf()
                + "\n RG: " + this.getRg();
    }

    public boolean compara(Pessoa pessoa) {
        if (!this.getNome().equals(pessoa.getNome())) {
            return false;
        }

        if (!this.getEmail().equals(pessoa.getEmail())) {
            return false;
        }

        if (!this.getCpf().equals(pessoa.getCpf())) {
            return false;
        }

        if (!this.getRg().equals(pessoa.getRg())) {
            return false;
        }

        return true;
    }
}