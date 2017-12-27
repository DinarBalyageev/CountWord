import java.util.*;
import java.util.concurrent.*;

public class Main {
    static ConcurrentHashMap<String, Integer> wordCounter = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();
        list.add("C:\\In\\Горе от ума1.txt");
        list.add("asdfdsfsd");
        list.add("http://www.rulit.me/download-books-501464.html?t=txt");
        list.add("C:\\In\\Hello.txt");
        list.add("https://wexxwebb.github.io/Dostoevskiyi_F._BesyiII.txt");
        list.add("https://wexx.ru/Dostoevskiyi_F._BesyiII.txt");
        Thread outputToScreen = new OutputToScreen(wordCounter);
        outputToScreen.setDaemon(true);
        outputToScreen.start();
        ExecutorService service = Executors.newFixedThreadPool(1);
        for (String str : list) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    if (str.startsWith("C:\\")) {
                        NewThread thread = new NewThread(str);
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (str.startsWith("http")) {
                        URLThread thread = new URLThread(str);
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Найдена неправильная строка: " + str);
                    }
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        scanner.nextLine();
//        System.out.println(Main.wordCounter.keySet());
//        System.out.println(Main.wordCounter.values());
    }
}


