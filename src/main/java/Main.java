import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int MILLIS = 1000;
    public static final int SIZE = 3;
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();

        Thread atc = new Thread(null, callCenter::workATC, "АТС");

        List<Thread> operators = new ArrayList<>(3);
        for (int i = 1; i <= SIZE; i++) {
            operators.add(new Thread(null, callCenter::workByOperator, "Специалист " + i));
        }

        atc.start();

        for (Thread thread : operators) {
            thread.start();
            Thread.sleep(MILLIS);
        }

        atc.join();
        for (Thread thread : operators) {
            thread.join();
        }
    }
}
