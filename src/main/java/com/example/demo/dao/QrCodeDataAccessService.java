package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.QrCode;

@Repository("qrCodeDao")
public class QrCodeDataAccessService implements QrCodeDao {

	private static List<QrCode> DB = new ArrayList<>();

	@Override
	public int insertCode(UUID id, QrCode code) {
		QrCode temp = new QrCode(id, code.getLocation(), code.getHint(), code.getName());
		temp.setCreated(new Date());
		DB.add(temp);
		return 1;
	}

	@Override
	public int deleteCodeById(UUID id) {
		Optional<QrCode> temp = getCodeById(id);
		if (!temp.isEmpty()) {
			DB.remove(temp.get());
			return 1;
		}
		return 0;
	}

	@Override
	public int updateCodeById(UUID id, QrCode code) {
		Optional<QrCode> temp = getCodeById(id);
		if (!temp.isEmpty()) {
			DB.remove(temp.get());
			DB.add(code);
			return 1;
		} else {
			DB.add(code);
			return 1;
		}
	}

	@Override
	public Optional<QrCode> getCodeById(UUID id) {
		return DB.stream().filter(q -> q.getId().equals(id)).findFirst();
	}

	@Override
	public QrCode getRandomCode() {
		return DB.get((int) Math.random() * (DB.size() - 1)); // TODO change to allow last entry in DB to be accessed
	}

	@Override
	public QrCode getNextCode(UUID id) { //For if no account. Just go around list.
		//LOOKAT improve efficiency
		Optional<QrCode> temp = getCodeById(id);
		if (!temp.isEmpty()) {
			int newindex = (DB.indexOf(temp.get())+1)%DB.size();
			return DB.get(newindex);
		} else {
			return DB.get(0); //WARN will always return first code, even if inputted code doesn't exist
		}
	}

	@Override
	public List<QrCode> selectAllCodes() {
		return DB;
	}

}