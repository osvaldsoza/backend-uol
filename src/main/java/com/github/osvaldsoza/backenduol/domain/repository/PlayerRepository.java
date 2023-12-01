package com.github.osvaldsoza.backenduol.domain.repository;

import com.github.osvaldsoza.backenduol.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

}
