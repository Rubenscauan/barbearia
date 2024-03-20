package com.example.barbearia.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.barbearia.models.Cliente;

@Repository
public interface ClienteDao extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByNome(String nome);
}
