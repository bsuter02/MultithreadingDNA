public class Filter1 implements Runnable{
    private final String input;
    private final Buffer outputBuffer;

    Filter1(String input, Buffer outputBuffer){
        this.input = input;
        this.outputBuffer = outputBuffer;
    }

    @Override
    public void run() {
        try{
            buildDNAStrand(input);
            outputBuffer.blockingPut("SHUTDOWNTHREAD");
            System.out.println("----- All inputs read: Filter 1 -----");
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void buildDNAStrand(String input) throws InterruptedException{
        StringBuffer newStr = new StringBuffer();
        for(char c: input.toCharArray()){
            if(c == 'N'){
                outputBuffer.blockingPut(newStr.toString());
                System.out.println("Filter 1: " + newStr);
                newStr = new StringBuffer();
            }
            if(c == 'A' || c == 'C' || c == 'G' || c == 'T'){
                newStr.append(c);
            }
        }
    }
}
