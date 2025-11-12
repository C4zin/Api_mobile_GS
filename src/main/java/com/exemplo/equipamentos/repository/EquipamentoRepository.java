package com.exemplo.equipamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.equipamentos.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
