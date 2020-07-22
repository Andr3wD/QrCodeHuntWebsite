package com.example.demo.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.QrCode;
import com.example.demo.repositories.QrCodeRepository;

@Repository("qrCodeDaoDB")
public class QrCodeDaoDB implements QrCodeDao {
	
	private QrCodeRepository DB;
	
	@Autowired
	public QrCodeDaoDB(QrCodeRepository DB) {
		this.DB = DB;
	}

	@Override
	public int insertCode(QrCode code) {
		
		if (code.getId() == null) {
			Date now = new Date();
			code.setCreated(now);
			DB.save(code);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteCodeById(Short id) {
		if (DB.existsById(id)) {
			DB.deleteById(id);
			return 1;
		} else {
			return 0;
		}	
	}

	@Override
	public int updateCodeById(Short id, QrCode code) {
		code.setId(id);
		DB.save(code);
		return 0;
	}

	@Override
	public Optional<QrCode> getCodeById(Short id) {
		return DB.findById(id);
	}

	@Override
	public QrCode getNextCode(Short id) {
		return null;
	}

	@Override
	public Iterable<QrCode> getAllCodesIterable() {
		return DB.findAll();
	}

}
