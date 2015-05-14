package Controller;

import Model.Pessoa;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JComboBox;

public class Controle {

    HashMap<String, Pessoa> pessoas;
    Path path = Paths.get("RegDados.ser");

    public Controle() {
       this.pessoas = new HashMap<String, Pessoa>();
    }

    public void criaPessoa(String nome, String cpf, int rg, String email) {
        if (pessoas.get(cpf) == null) {
            pessoas.put(cpf, new Pessoa(nome, cpf, rg, email));
        }
    }

    public Pessoa getPessoa(String cpf) {
        return pessoas.get(cpf);
    }

    public Collection<Pessoa> getPessoas() {
        return pessoas.values();
    }

    public String imprimePessoa(Pessoa p) {
        return ("\n------------------------------------------------------------------------"
                + "\nNome: " + p.getNome()
                + "\nCPF: " + p.getCpf()
                + "\nRG: " + p.getRg()
                + "\nEmail: " + p.getEmail()
                + "\n------------------------------------------------------------------------");
    }

    public String imprimePessoas() {
        String str = "";
        Collection<Pessoa> p = this.getPessoas();

        for (Pessoa pessoa : p) {
            str += "\n------------------------------------------------------------------------"
                    + "\nNome: " + pessoa.getNome()
                    + "\nCPF: " + pessoa.getCpf()
                    + "\nRG: " + pessoa.getRg()
                    + "\nEmail: " + pessoa.getEmail()
                    + "\n------------------------------------------------------------------------";
        }
        return str;
    }

    public void addPessoaCB(JComboBox jCB) {
        Collection<Pessoa> p = this.getPessoas();

        for (Pessoa pessoa : p) {
            jCB.addItem(pessoa);
        }
    }

    public void deletaPessoaCB(JComboBox jCB, Pessoa p) {
        if(p == null || p.equals(null)){
            throw new IllegalArgumentException("Ação inválida!");
        }
        jCB.removeItem(p);
    }

    public Pessoa selecionaPessoaCB(JComboBox jCB) {
        return (Pessoa) jCB.getSelectedItem();
    }
    
    public void alteraPessoa(String cpfOriginal, String nome, String cpfFinal, int rg, String email){
        Pessoa p = pessoas.get(cpfOriginal);
        
        p.setNome(nome);
        p.setCpf(cpfFinal);
        p.setRg(rg);
        p.setEmail(email);
    }

    public void excluiPessoa(String cpf) {
        if(pessoas.get(cpf) == null || pessoas.get(cpf).equals(null)){
            throw new IllegalArgumentException("Ação inválida!");
        }
        pessoas.remove(cpf);
    }

    public void salvarPessoas() throws IOException {
        FileOutputStream fos = new FileOutputStream(this.path.toString());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(pessoas);
        oos.close();
    }

    public void recuperarPessoas() throws IOException, ClassNotFoundException {
        ObjectInputStream ois
                = new ObjectInputStream(
                        new FileInputStream(this.path.toString()));
        Object objeto = ois.readObject();
        ois.close();
        pessoas = (HashMap<String, Pessoa>) objeto;
    }

    public void excluirDados() throws IOException {
        Files.deleteIfExists(this.path);
        pessoas.clear();
    }
}
