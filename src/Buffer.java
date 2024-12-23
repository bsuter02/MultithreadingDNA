public interface Buffer {
    void blockingPut(String value) throws InterruptedException;
    String blockingGet() throws InterruptedException;
}
