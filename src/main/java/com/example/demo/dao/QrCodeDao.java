package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.QrCode;

public interface QrCodeDao {

	default int insertCode(QrCode code) {
		UUID id = UUID.randomUUID();
		Date now = new Date();
		code.setCreated(now);
		return insertCode(id, code);
	}
	
	int insertCode(UUID id, QrCode code);
	
	int deleteCodeById(UUID id);
	
	int updateCodeById(UUID id, QrCode code);
	
	Optional<QrCode> getCodeById(UUID id);
	
	QrCode getRandomCode();
	
	QrCode getNextCode(UUID id);

	List<QrCode> selectAllCodes();
}
