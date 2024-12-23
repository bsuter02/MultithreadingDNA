
public class Filter3 implements Runnable{
    private final Buffer inputBuffer;
    private final Buffer outputBuffer;

    Filter3(Buffer inputBuffer, Buffer outputBuffer){
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
    }

    @Override
    public void run() {
        try{
            while(true){
                String first = inputBuffer.blockingGet();
                if(first.equals("SHUTDOWNTHREAD")){
                    break;
                }
                baseRemoval(first);
            }
            outputBuffer.blockingPut("SHUTDOWNTHREAD");
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
        System.out.println("----- All inputs read: Filter 3 -----");
    }

    public synchronized void baseRemoval(String input) throws InterruptedException{
        String second = input.substring(1);
        String third = second.substring(1);

        outputBuffer.blockingPut(input);
        outputBuffer.blockingPut(second);
        outputBuffer.blockingPut(third);

        System.out.println("Filter 3: " + input + "\nFilter 3: " + second + "\nFilter 3: " + third);
    }
}
