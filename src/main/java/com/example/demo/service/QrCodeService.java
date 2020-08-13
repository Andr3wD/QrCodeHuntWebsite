package com.example.demo.service;

import java.util.Iterator;

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
	public QrCodeService(@Qualifier("qrCodeDaoDB") QrCodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public int insertCode(QrCode code) {
		return codeDao.insertCode(code);
	}

	public String getAllCodes() {
		String result = "";
		for (QrCode code : codeDao.getAllCodesIterable()) {
			result = result + String.format("{%d, %s, %s, %s}", code.getId(), code.getName(), code.getLocation(), code.getCreated().toString());
		}
		return result;
	}

	public QrCode getRandomCode() {
		return null;
	}

	public QrCode getNextCode(Short id) {
		return codeDao.getNextCode(id);
	}
	
	public QrCode getCodeByName(String name) {
		for (QrCode code : codeDao.getAllCodesIterable()) {
			if (code.getName().equals(name)) {
				return code;
			}
		}
		return null;
	}
	
	public QrCode getNextCodeByName(String name) {
		// Can't just get id and either increment or go to start, as there may be gaps.
		// By doing this, we skip 1 potential DB accesses from getting the next QrCode.
		Iterator<QrCode> iter = codeDao.getAllCodesIterable().iterator();
		QrCode front = null;
		while (iter.hasNext()) {
			QrCode code = iter.next();
			if (front == null) {
				front = code;
			}
			if (code.getName().equals(name)) {
				if (iter.hasNext()) {
					return iter.next();
				} else {
					return front;
				}
			}
		}
		return null;
	}
}
