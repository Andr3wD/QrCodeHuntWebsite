package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.QrCode;

/**
 * This interface tells us how we should always be able to interact with our
 * database, no matter how it's implemented.
 * 
 * More on DAO pattern:
 * https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm
 * https://stackoverflow.com/questions/35078383/what-are-the-dao-dto-and-service-layers-in-spring-framework
 * 
 * @author Andrew
 *
 */
@Deprecated
public interface OldQrCodeDao {

	/**
	 * Inserts a QrCode in the DB.
	 * 
	 * @param id
	 * @param code
	 * @return 0 or null if failure or 1 if success.
	 */
	// TODO add random data creation by default.
	int insertCode(Short id, QrCode code);

	/**
	 * Deletes a QrCode in the DB.
	 * 
	 * @param id
	 * @return 0 or null if failure or 1 if success.
	 */
	int deleteCodeById(Short id);

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
	QrCode getNextCode(Short id);

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
