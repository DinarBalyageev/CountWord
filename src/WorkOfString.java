public class WorkOfString {

    public static String clear(String input) {
        String otput = input.replaceAll("[^а-яА-ЯёЁ-]+", "\u0020");
        return otput;
    }

    public void counter(String str) {
        String untreatedString;
        synchronized (WorkOfString.class) {
            untreatedString = WorkOfString.clear(str);
        }
        String[] treatedString = untreatedString.split("\u0020");
        for (int y = 0; y < treatedString.length; y++) {
            if (!treatedString[y].equals("")&!treatedString[y].equals("-")) {
                Main.wordCounter.put(treatedString[y], Main.wordCounter.getOrDefault(treatedString[y], 0) + 1);
            }
        }
    }
}
