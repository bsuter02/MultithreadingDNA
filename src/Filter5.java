
public class Filter5 implements Runnable{
    private final Buffer inputBuffer;
    private boolean continueThread;

    Filter5(Buffer inputBuffer){
        this.inputBuffer = inputBuffer;
        this.continueThread = true;
    }

    @Override
    public void run() {
        try{
            while(continueThread){
                String input = inputBuffer.blockingGet();
                if(input.equals("SHUTDOWNTHREAD")){
                    continueThread = false;
                    break;
                }
                else{
                    findORF(input);
                }
            }
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
        System.out.println("----- All inputs read: Filter 5 -----");
    }

    public synchronized void findORF(String input) throws InterruptedException{
        StringBuffer output = new StringBuffer();
        for(char c: input.toCharArray()){
            if(c == '*'){
                if(output.length() >= 22){
                    System.out.println(output);
                }
                else{
                    output = new StringBuffer();
                }
            }
            else{
                output.append(c);
            }
        }
        if(output.length() >= 22){
            System.out.println("Filter 5: " +output);
        }
    }
}

