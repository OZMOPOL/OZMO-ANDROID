
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLConnection;

	import org.apache.commons.codec.binary.Base64;

	public class BasicAuthentication {

		public static void main(String[] args) {

			try {
				String webPage = "http://192.168.1.1";
				String name = "admin";
				String password = "password";

				String authString = name + ":" + password;
				byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				System.out.println("Base64 encoded auth string: " + authStringEnc);

				URL url = new URL(webPage);
				URLConnection urlConnection = url.openConnection();
				urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
				InputStream inputStream = urlConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

				int numCharsRead;
				char[] charArray = new char[1024];
				StringBuffer sb = new StringBuffer();
				while ((numCharsRead = inputStreamReader.read(charArray)) > 0) {
					sb.append(charArray, 0, numCharsRead);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
