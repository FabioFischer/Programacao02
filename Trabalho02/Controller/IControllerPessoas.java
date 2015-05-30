package Controller;

import Model.Pessoa;
import java.io.IOException;
import java.util.Collection;

/**
 *  FURB - BCC - 3° Semestre
 *  Trabalho de Programação II
 * 
 *  Alunos:     Fábio Luiz Fischer;
                Matheus Felipe Klauberg             
 */

public interface IControllerPessoas {

    Pessoa incluirPessoa(String nome, String email, String cpf, String rg) throws IOException;

    Pessoa alterarPessoa(String cpf, Pessoa pessoa) throws IOException;

    void excluirPessoa(String pessoaCpf) throws IOException;

    Pessoa buscarPorCPF(String cpf);

    Pessoa buscarPorNome(String nome);
    
    Collection<Pessoa> getPessoas();

    void excluirPessoas() throws IOException;
}