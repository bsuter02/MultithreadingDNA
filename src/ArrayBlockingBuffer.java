import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingBuffer implements Buffer{
    private final ArrayBlockingQueue<String> buffer;

    ArrayBlockingBuffer(){
        buffer = new ArrayBlockingQueue<>(100);
    }

    @Override
    public synchronized void blockingPut(String value) throws InterruptedException {
        while(buffer.remainingCapacity() < 1){
            wait();
        }
        buffer.add(value);
        notifyAll();
    }

    @Override
    public synchronized String blockingGet() throws InterruptedException {
        while(buffer.isEmpty()){
            wait();
        }
        notifyAll();
        return buffer.take();
    }
}