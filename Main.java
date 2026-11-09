class NewsSourceTask implements Runnable {

    private String sourceName;
    private String[] newsTitles;

    public NewsSourceTask(String sourceName, String[] newsTitles) {
        this.sourceName = sourceName;
        this.newsTitles = newsTitles;
    }

    @Override
    public void run() {
        System.out.println("\n" + sourceName + " жаңалықтарды жүктеуді бастады...");

        for (int i = 0; i < newsTitles.length; i++) {
            try {
                Thread.sleep(1000);

                System.out.println(sourceName +
                        " жүктеді: " + newsTitles[i]);

            } catch (InterruptedException e) {
                System.out.println(sourceName + " жұмысы тоқтатылды.");
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(sourceName + " барлық жаңалықтарды жүктеді!");
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("=== ЖАҢАЛЫҚТАРДЫ ЖҮКТЕУ ЖҮЙЕСІ ===");

        // Бірінші жаңалық көзі
        String[] news1 = {
                "Қазақстанда жаңа жоба іске қосылды",
                "Спорт жаңалықтары",
                "Білім саласындағы өзгерістер"
        };


        String[] news2 = {
                "Әлемдегі соңғы жаңалықтар",
                "Экономикалық жаңалықтар",
                "Технология саласындағы жаңалықтар"
        };

    
        String[] news3 = {
                "Ауа райы болжамы",
                "Мәдениет жаңалықтары",
                "Ғылымдағы жаңа жетістіктер"
        };

        Thread source1 = new Thread(
                new NewsSourceTask("Khabar.kz", news1));

        Thread source2 = new Thread(
                new NewsSourceTask("Inform.kz", news2));

        Thread source3 = new Thread(
                new NewsSourceTask("Tengrinews.kz", news3));

 
        source1.setName("News Thread 1");
        source2.setName("News Thread 2");
        source3.setName("News Thread 3");

        source1.start();
        source2.start();
        source3.start();

        try {
            source1.join();
            source2.join();
            source3.join();

        } catch (InterruptedException e) {
            System.out.println("Негізгі ағын тоқтатылды.");
            Thread.currentThread().interrupt();
            return;
        }

      
        System.out.println("\n================================");
        System.out.println("Барлық жаңалықтар сәтті жүктелді!");
        System.out.println("Үш дереккөздің жұмысы аяқталды.");
        System.out.println("Бағдарлама аяқталды!");
    }
}
    }
