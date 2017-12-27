import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OutputToScreen extends Thread {
    ConcurrentHashMap iMap;

    public OutputToScreen(ConcurrentHashMap iMap) {
        this.iMap = iMap;
    }

    public void run() {
        while (true) {
            if (Main.wordCounter.size() > 0) {
                List<Map.Entry<String, Integer>> sort =
                        Main.wordCounter.entrySet().stream().sorted(
                                (a, b) -> a.getValue() > b.getValue() ? -1 : a.getValue() < b.getValue() ? 1 : 0
                        ).collect(Collectors.toList());
                for (int i = 0; i < 15; i++) {
                    System.out.println(sort.get(i));
                }
                System.out.println("---------------------------------------------");
            }
        }
    }
}
