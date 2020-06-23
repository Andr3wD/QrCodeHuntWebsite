package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QrCodeDao;
import com.example.demo.model.QrCode;

@Service
public class QrCodeService {
	
	private final QrCodeDao codeDao;

	@Autowired
	public QrCodeService(@Qualifier("qrCodeDao") QrCodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public int insertCode(QrCode code) {
		return codeDao.insertCode(code);
	}
	
	public List<QrCode> getAllCodes() {
		return codeDao.selectAllCodes();
	}
	
	public QrCode getRandomCode() {
		return codeDao.getRandomCode();
	}
	
	public QrCode getNextCode(UUID id) {
		return codeDao.getNextCode(id);
	}
}
