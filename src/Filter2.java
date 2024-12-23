import java.util.HashMap;

public class Filter2 implements Runnable{
    private final Buffer inputBuffer;
    private final Buffer outputBuffer;

    private final HashMap<Character,Character> inverter = new HashMap<>();

    Filter2(Buffer inputBuffer, Buffer outputBuffer){
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;

        inverter.put('A','T');
        inverter.put('C','G');
        inverter.put('T','A');
        inverter.put('G','C');
    }

    @Override
    public void run() {
        try{
            while(true){
                String input = inputBuffer.blockingGet();
                if(input.equals("SHUTDOWNTHREAD")){
                    break;
                }
                reverseCompliment(input);
            }
            outputBuffer.blockingPut("SHUTDOWNTHREAD");
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
        System.out.println("----- All inputs read: Filter 2 -----");
    }

    public synchronized void reverseCompliment(String input) throws InterruptedException{
        String revStr = new StringBuffer(input).reverse().toString();
        StringBuffer revComp = new StringBuffer();
        for(char c: revStr.toCharArray()){
            revComp.append(inverter.get(c));
        }
        System.out.println("Filter 2: " + revComp);
        outputBuffer.blockingPut(revComp.toString());
    }
}
