package br.com.pessoal.isaque.teste.specification.controller;

import br.com.pessoal.isaque.teste.specification.model.Funcionario;
import br.com.pessoal.isaque.teste.specification.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public List<Funcionario> obterTodos(){
        return repository.findAll();
    };

}
