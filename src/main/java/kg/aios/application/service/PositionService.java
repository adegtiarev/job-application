package kg.aios.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kg.aios.application.dao.PositionDAO;
import kg.aios.application.model.Position;

@Service
public class PositionService {

	private static final Logger logger = LoggerFactory.getLogger(PositionService.class);
	
	@Autowired
	private PositionDAO positionDao;

	@Transactional
	public List<Position> findAllPositions() {
		return positionDao.findAll();
	}
	
}
