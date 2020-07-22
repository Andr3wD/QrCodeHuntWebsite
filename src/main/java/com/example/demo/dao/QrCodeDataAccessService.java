package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.QrCode;
/**
 * This class implements the QrCodeDao.java interface and implements it in
 * whatever way we want. We could eventually use a different DB, so we would
 * just make a new AccessService class and switch the "@Qualifier("qrCodeDao")"
 * in QrCodeService.java to the new repo name.
 * 
 * Everything that has to do with accessing data from the db should be done
 * here, and NOT in a service. We can call the methods from the service to get the data.
 * 
 * @author Andrew
 *
 */
@Deprecated
@Repository("qrCodeDao") // why not @component? https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in
public class QrCodeDataAccessService implements OldQrCodeDao {

	private static List<QrCode> DB = new ArrayList<>();

	@Override
	public int insertCode(Short id, QrCode code) {
		QrCode temp = new QrCode(id, code.getLocation(), code.getHint(), code.getName());
		temp.setCreated(new Date());
		DB.add(temp);
		return 1;
	}

	@Override
	public int deleteCodeById(Short id) {
		Optional<QrCode> temp = getCodeById(id);
		if (!temp.isEmpty()) {
			DB.remove(temp.get());
			return 1;
		}
		return 0;
	}

	@Override
	public int updateCodeById(Short id, QrCode code) {
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
	public Optional<QrCode> getCodeById(Short id) {
		return DB.stream().filter(q -> q.getId().equals(id)).findFirst();
	}

	@Override
	public QrCode getRandomCode() {
		return DB.get((int) Math.random() * (DB.size() - 1)); // TODO change to allow last entry in DB to be accessed
	}

	@Override
	public QrCode getNextCode(Short id) { // For if no account. Just go around list.
		// LOOKAT improve efficiency
		Optional<QrCode> temp = getCodeById(id);
		if (!temp.isEmpty()) {
			int newindex = (DB.indexOf(temp.get()) + 1) % DB.size();
			return DB.get(newindex);
		} else {
			return DB.get(0); // WARN will always return first code, even if inputted code doesn't exist
		}
	}

	@Override
	public List<QrCode> selectAllCodes() {
		return DB;
	}

}
