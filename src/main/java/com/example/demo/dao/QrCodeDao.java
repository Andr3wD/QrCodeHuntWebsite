package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.model.QrCode;

public interface QrCodeDao {
//TODO fix all javadocs
	/**
	 * Inserts a QrCode in the DB.
	 * 
	 * @param code
	 * @return 0 or null if failure or 1 if success.
	 */
	// TODO add random data creation by default.
	int insertCode(QrCode code);

	/**
	 * Deletes a QrCode in the DB.
	 * 
	 * @param id
	 * @return 0 or null if failure or 1 if success.
	 */
	int deleteCodeById(Short id); // Phasing out UUID due to possible size problems. Highly unlikely there will ever be more than 32k QrCodes.

	/**
	 * Updates the QrCode with the given id. This is implemented in a way that the
	 * QrCode is just deleted from the DB and then re-added with the same UUID. If
	 * the code doesn't exist, then it's added.
	 * 
	 * @param id
	 * @param code
	 * @return 0 or null if failure or 1 if success.
	 */
	int updateCodeById(Short id, QrCode code);

	/**
	 * Gets the QrCode with the given UUID.
	 * 
	 * @param id
	 * @return Optional QrCode, so you'll need to check if it exists or not.
	 */
	Optional<QrCode> getCodeById(Short id);

	/**
	 * Gets the QrCode after the given QrCode UUID.
	 * 
	 * @param id
	 * @return The QrCode following this QrCode UUID, or the first QrCode in the
	 *         list if there's no 'next.'
	 */
	QrCode getNextCode(Short id);
	
	Iterable<QrCode> getAllCodesIterable();
}
