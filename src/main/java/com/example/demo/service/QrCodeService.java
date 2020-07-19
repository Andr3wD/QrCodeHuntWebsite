package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QrCodeDao;
import com.example.demo.model.QrCode;

/**
 * This class essentially bridges the gap between the data access service and us
 * actually using the code.
 * 
 * @author Andrew
 *
 */
@Service
public class QrCodeService {

	private final QrCodeDao codeDao;

	/*
	 * This is how spring works. The @Qualifier("qrCodeDao") says to spring that we
	 * want a repository with the name "qrCodeDao" to be made and placed in the
	 * codeDao var. This repository is currently QrCodeDataAccessService.java. At
	 * the very top there is a "@Repository("qrCodeDao")" This allows for easy
	 * hot-swapping of databases. For example, when we eventually switch to a sql
	 * database.
	 */
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
