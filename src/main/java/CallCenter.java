import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    private ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public static final int CALL_MAX = 5;
    public static final int ITERATIONS_MAX = 5;

    public static final int MILLIS_FOR_ATC = 1000;
    public static final int MILLIS_FOR_OPERATOR = 3000;

    public void workATC() {
        int iterations = 1;
        try {
            while (iterations != ITERATIONS_MAX) {
                for (int i = 1; i < (CALL_MAX + 1); i++) {
                    String call = "запрос " + iterations + "/" + i;
                    queue.add(call);
                    System.out.printf("%s добавил в очередь %s\n", Thread.currentThread().getName(), call);
                }

                iterations++;
                Thread.sleep(MILLIS_FOR_ATC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void workByOperator() {
        try {
            while (true) {
                Thread.sleep(MILLIS_FOR_OPERATOR);
                if (queue.isEmpty()) {
                    break;
                }

                System.out.printf("%s обработал %s\n", Thread.currentThread().getName(), queue.poll());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
