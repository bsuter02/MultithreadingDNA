import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingDNADriver {
    public static void main(String[] args) throws InterruptedException{
        Buffer sharedBuffer1 = new ArrayBlockingBuffer();
        Buffer sharedBuffer2 = new ArrayBlockingBuffer();
        Buffer sharedBuffer3 = new ArrayBlockingBuffer();
        Buffer sharedBuffer4 = new ArrayBlockingBuffer();

        String input = "AGCTAAGCTAGCATAGCTAGCATAGCTAGACTAGCTAGCTAAGCTAGCTAAGCTANAGCTAAGCTAGCTAAGCTAN";

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Filter1(input,sharedBuffer1));
        executorService.execute(new Filter2(sharedBuffer1,sharedBuffer2));
        executorService.execute(new Filter3(sharedBuffer2,sharedBuffer3));
        executorService.execute(new Filter4(sharedBuffer3,sharedBuffer4));
        executorService.execute(new Filter5(sharedBuffer4));

        //executorService.awaitTermination(20, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}