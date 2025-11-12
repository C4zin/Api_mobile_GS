package com.exemplo.equipamentos.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.exemplo.equipamentos.repository.EquipamentoRepository;
import com.exemplo.equipamentos.model.Equipamento;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repository;

    @GetMapping
    public List<Equipamento> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Equipamento buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
    }

    @PostMapping
    public Equipamento criar(@RequestBody Equipamento equipamento) {
        return repository.save(equipamento);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id, @RequestBody Equipamento novoEquipamento) {
        return repository.findById(id).map(equipamento -> {
            equipamento.setEquipamento(novoEquipamento.getEquipamento());
            equipamento.setDescricao(novoEquipamento.getDescricao());
            return repository.save(equipamento);
        }).orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
