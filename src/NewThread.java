import java.io.*;

public class NewThread extends Thread {
    public String fileName;

    NewThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        String str;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            WorkOfString wos = new WorkOfString();
            while ((str = reader.readLine()) != null) {
                wos.counter(str);
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("Не удается найти файл " + fileName);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Ошибка открытия файла по пути " + fileName);
        }
        try {
            if (!(reader == null)) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка закрытия потока");
            //e.printStackTrace();
        }

    }
}



