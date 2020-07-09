package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.QrCode;

/**
 * This interface tells us how we should always be able to interact with our
 * database, no matter how it's implemented.
 * 
 * @author Andrew
 *
 */
public interface QrCodeDao {

	/**
	 * Inserts a QrCode into the DB with a random UUID and Date.
	 * 
	 * @param code
	 * @return
	 */
	default int insertCode(QrCode code) {
		UUID id = UUID.randomUUID();
		Date now = new Date();
		code.setCreated(now);
		return insertCode(id, code);
	}

	/**
	 * Inserts a QrCode in the DB.
	 * 
	 * @param id
	 * @param code
	 * @return 0 or null if failure or 1 if success.
	 */
	// TODO add random data creation by default.
	int insertCode(UUID id, QrCode code);

	/**
	 * Deletes a QrCode in the DB.
	 * 
	 * @param id
	 * @return 0 or null if failure or 1 if success.
	 */
	int deleteCodeById(UUID id);

	/**
	 * Updates the QrCode with the given id. This is implemented in a way that the
	 * QrCode is just deleted from the DB and then re-added with the same UUID. If
	 * the code doesn't exist, then it's added.
	 * 
	 * @param id
	 * @param code
	 * @return 0 or null if failure or 1 if success.
	 */
	int updateCodeById(UUID id, QrCode code);

	/**
	 * Gets the QrCode with the given UUID.
	 * 
	 * @param id
	 * @return Optional QrCode, so you'll need to check if it exists or not.
	 */
	Optional<QrCode> getCodeById(UUID id);

	/**
	 * Gets a random QrCode.
	 * 
	 * @return Random QrCode from the DB.
	 */
	QrCode getRandomCode();

	/**
	 * Gets the QrCode after the given QrCode UUID.
	 * 
	 * @param id
	 * @return The QrCode following this QrCode UUID, or the first QrCode in the
	 *         list if there's no 'next.'
	 */
	QrCode getNextCode(UUID id);

	/**
	 * Gets all codes from the DB.
	 * 
	 * @deprecated Depreciated due to not following the model that only the DAO
	 *             should touch the entire DB at a time.
	 * @return
	 */
	// TODO phase out by being more specific and should plan for potentially large
	// DB, so try not to access the whole thing at the same time.
	List<QrCode> selectAllCodes();
}
