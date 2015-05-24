package Controller;

import Model.Pessoa;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

public class Controle implements IControllerPessoas {

    HashMap<String, Pessoa> pessoas;
    Path path = Paths.get("RegDados.ser");

    public Controle() throws IOException, ClassNotFoundException {
        this.pessoas = new HashMap<>();
        recuperarDados();
    }

    @Override
    public Pessoa incluirPessoa(String nome, String email, String cpf, String rg) throws IOException {
        Pessoa novaPessoa = new Pessoa(nome, cpf, rg, email);

        boolean pessoaJaExistente = pessoas.get(novaPessoa.getCpf()) == null;
        if (!pessoaJaExistente) {
            throw new IllegalArgumentException("Já existe uma pessoa cadastrada com este mesmo CPF");
        }

        pessoas.put(novaPessoa.getCpf(), novaPessoa);
        salvarPessoas();

        return novaPessoa;
    }

    @Override
    public Pessoa alterarPessoa(String cpf, Pessoa pessoa) throws IOException {
        boolean pessoaJaExistente = pessoas.get(cpf) == null;
        if (pessoaJaExistente) {
            throw new IllegalArgumentException("Pessoa de CPF " + cpf + " não cadastrada!");
        }

        pessoas.remove(cpf);
        salvarPessoas();

        return incluirPessoa(pessoa.getNome(), pessoa.getEmail(), pessoa.getCpf(), pessoa.getRg());
    }

    @Override
    public void excluirPessoa(String pessoaCpf) throws IOException {
        boolean pessoaJaExistente = pessoas.get(pessoaCpf) == null;
        if (pessoaJaExistente) {
            throw new IllegalArgumentException("Pessoa de CPF " + pessoaCpf + " não cadastrada!");
        }

        pessoas.remove(pessoaCpf);
        salvarPessoas();
    }

    @Override
    public Pessoa buscarPorCPF(String cpf) {
        Pessoa pessoa = pessoas.get(cpf);
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa de CPF " + cpf + " não cadastrada!");
        }

        return pessoa;
    }

    @Override
    public Pessoa buscarPorNome(String nome) {
        Collection<Pessoa> registro = this.getPessoas();
        boolean achou = false;

        for (Pessoa p : registro) {
            if (p.getNome().equals(nome)) {
                achou = true;
                return p;
            }
        }
        if (!achou) {
            throw new IllegalArgumentException("Pessoa de nome " + nome + " não cadastrada!");
        }
        return null;
    }

    @Override
    public Collection<Pessoa> getPessoas() {
        return this.pessoas.values();
    }

    private void salvarPessoas() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(this.path.toString());
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pessoas);
        }
    }

    @Override
    public void excluirPessoas() throws IOException {
        Files.deleteIfExists(this.path);
        pessoas.clear();
    }

    private void recuperarDados() throws IOException, ClassNotFoundException {
        if (!Files.exists(path)) {
            return;
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.path.toString()));
        Object objeto = ois.readObject();
        ois.close();
        pessoas = (HashMap<String, Pessoa>) objeto;
    }
}
