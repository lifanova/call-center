public class Main {
    public static void main(String[] args) throws InterruptedException {
        CallCenter callCenter = new CallCenter();

        Thread atc = new Thread(null, callCenter::workATC, "АТС");

        Thread operator1 = new Thread(null, callCenter::workByOperator, "Специалист 1");
        Thread operator2 = new Thread(null, callCenter::workByOperator, "Специалист 2");
        Thread operator3 = new Thread(null, callCenter::workByOperator, "Специалист 3");

        atc.start();

        operator1.start();
        Thread.sleep(1000);
        operator2.start();
        Thread.sleep(1000);
        operator3.start();

        atc.join();
        operator1.join();
        operator2.join();
        operator3.join();
    }
}
