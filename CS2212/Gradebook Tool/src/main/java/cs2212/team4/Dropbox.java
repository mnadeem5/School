package cs2212.team4;

import com.dropbox.core.*;
import java.io.*;
import java.util.Locale;

/**
*
* Dropbox class will be used to allow a used to sync their files to a valid dropbox account
*
* team4-gradebook application
*
* @author Zaid Albirawi
* @version 1.6 3/25/2014
*/

public class Dropbox {
	//The dropbox client
	private DbxClient client;
	//The dropbox application info
	private DbxAppInfo appInfo;
	//The dropbox request configuration
	private DbxRequestConfig config;
	//The dropbox web authentication
	private DbxWebAuthNoRedirect webAuth;
	//The dropbox authorization of URL
	private String authorizeUrl;

	/**
	 * Constructor that creates a Dropbox object to be used for the gradebook
	 * 
	 */
	public Dropbox() {
		final String APP_KEY = "ap5itf7kv6l5maq";
		final String APP_SECRET = "dm0n6jsq9o2gu61";

		appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		config = new DbxRequestConfig("team4-gradebook", Locale.getDefault()
				.toString());
		webAuth = new DbxWebAuthNoRedirect(config, appInfo);
	}

	/**
	 * Gets the authorized url
	 * 
	 * @return The authorized url
	 * 
	 */
	public String getAuthorizeUrl() {
		authorizeUrl = webAuth.start();
		return this.authorizeUrl;
	}

	/**
	 * Authenticates the passed code for dropbox functionality
	 * 
	 * @param code The code that needs to be authenticated, false otherwise
	 * @return true if the code was successfully authenticated.
	 * @throws DbxException A Dropbox exception
	 * 
	 */
	public boolean authenticate(String code) {
		DbxAuthFinish authFinish;
		try {
			authFinish = webAuth.finish(code);
		} catch (DbxException e) {
			return false;
		}
		String accessToken = authFinish.accessToken;
		this.client = new DbxClient(config, accessToken);
		return true;
	}

	/**
	 * Uploads the data file to the dropbox
	 * 
	 * @return true if the file was successfully uploaded, false if no file was uploaded
	 * @throws DbxException A Dropbox exception
	 * @throws FileNotFoundException A file not found exception
	 * @throws IOException An input/output exception
	 * 
	 */
	public boolean upload() {
		File dataFile = new File("gradebook-files/data.dat");
		try {
			FileInputStream FIS = new FileInputStream(dataFile);
			DbxEntry.File uploadedFile = client.uploadFile(
					"/gradebook/data.dat", DbxWriteMode.add(),
					dataFile.length(), FIS);
			if (uploadedFile != null) {
				FIS.close();
				return true;
			} else {
				FIS.close();
				return false;
			}
		} catch (DbxException e) {
			System.out.println(e);
			return false;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Downloads the data file from the dropbox
	 * 
	 * @return true if the file was successfully downloaded, false if no file was downloaded
	 * @throws DbxException A Dropbox exception
	 * @throws IOException An input/output exception
	 * 
	 */
	public boolean download() {
		try {
			OutputStream OS = new FileOutputStream("/gradebook-files/data.dat");
			DbxEntry.File downloadedFile = client.getFile(
					"/gradebook/data.dat", null, OS);
			if (downloadedFile != null) {
				OS.close();
				return true;
			} else {
				OS.close();
				return false;
			}
		} catch (DbxException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
}
