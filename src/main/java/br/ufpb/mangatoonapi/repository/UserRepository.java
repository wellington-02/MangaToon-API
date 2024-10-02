package br.ufpb.mangatoonapi.repository;

import br.ufpb.mangatoonapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}