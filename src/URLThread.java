import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLThread extends Thread {
    public String URL;

    URLThread(String URL) {
        this.URL = URL;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        URL destination = null;
        String str;
        try {
            destination = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) destination.openConnection();
            reader = new BufferedReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
            WorkOfString wos = new WorkOfString();
            while ((str = reader.readLine()) != null) {
                wos.counter(str);
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("Неверная строка " + URL);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Ошибка открытия " + URL);
        }
        try {
            if (!(reader == null)) {
                reader.close();
            }
        } catch (IOException ex) {
            System.out.println("Ошибка закрытия потока");
            //ex.printStackTrace();
        }
    }
}



