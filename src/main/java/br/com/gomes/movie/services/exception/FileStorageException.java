/**
 * 
 */
package br.com.gomes.movie.services.exception;

/**
 * @author gomes.bruno
 *
 */
public class FileStorageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7503527035176297863L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
