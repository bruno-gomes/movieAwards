/**
 * 
 */
package br.com.gomes.movie.services.exception;

/**
 * @author gomes.bruno
 *
 */
public class FileExtensionException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2600848135955370161L;

	public FileExtensionException(String msg) {
        super(msg);
    }

    public FileExtensionException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
