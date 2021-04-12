package kg.aios.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kg.aios.application.model.Position;

@Repository
public interface PositionDAO extends JpaRepository<Position, Long> {
	
}
