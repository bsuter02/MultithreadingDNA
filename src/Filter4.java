import java.util.HashMap;

public final class Filter4 implements Runnable{
    private final Buffer inputBuffer;
    private final Buffer outputBuffer;
    private final HashMap<String, Character> aminoConversion = new HashMap<>();
    Filter4(Buffer inputBuffer, Buffer outputBuffer){
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        convFill();
    }
    @Override
    public void run() {
        try{
            while(true){
                String input = inputBuffer.blockingGet();
                if(input.equals("SHUTDOWNTHREAD")){
                    outputBuffer.blockingPut(input);
                    break;
                }
                else{
                    String inputStr = input.replace('T','U');
                    convertToAmino(inputStr);
                }
            }
        }
        catch (InterruptedException exception){
            Thread.currentThread().interrupt();
        }
        System.out.println("----- All inputs read: Filter 4 -----");
    }
    public synchronized void convertToAmino(String input) throws InterruptedException{
        StringBuffer output = new StringBuffer();
        for(int i = 0; i + 3 < input.length(); i+=3){
            if(aminoConversion.containsKey(input.substring(i,i+3))){
                output.append(aminoConversion.get(input.substring(i,i+3)));
            }
        }
        System.out.println("Filter 4: " + output);
        outputBuffer.blockingPut(output.toString());
    }
    public void convFill(){
        aminoConversion.put("UUU",'F'); aminoConversion.put("UCU",'S'); aminoConversion.put("UAU",'Y'); aminoConversion.put("UGU",'C');
        aminoConversion.put("UUC",'F'); aminoConversion.put("UCC",'S'); aminoConversion.put("UAC",'Y'); aminoConversion.put("UGC",'C');
        aminoConversion.put("UUA",'L'); aminoConversion.put("UCA",'S'); aminoConversion.put("UAA",'*'); aminoConversion.put("UGA",'*');
        aminoConversion.put("UUG",'L'); aminoConversion.put("UCG",'S'); aminoConversion.put("UAG",'*'); aminoConversion.put("UGG",'W');

        aminoConversion.put("CUU",'L'); aminoConversion.put("CCU",'P'); aminoConversion.put("CAU",'H'); aminoConversion.put("CGU",'R');
        aminoConversion.put("CUC",'L'); aminoConversion.put("CCC",'P'); aminoConversion.put("CAC",'H'); aminoConversion.put("CGC",'R');
        aminoConversion.put("CUA",'L'); aminoConversion.put("CCA",'P'); aminoConversion.put("CAA",'Q'); aminoConversion.put("CGA",'R');
        aminoConversion.put("CUG",'L'); aminoConversion.put("CCG",'P'); aminoConversion.put("CAG",'Q'); aminoConversion.put("CGG",'R');

        aminoConversion.put("AUU",'I'); aminoConversion.put("ACU",'T'); aminoConversion.put("AAU",'N'); aminoConversion.put("AGU",'S');
        aminoConversion.put("AUC",'I'); aminoConversion.put("ACC",'T'); aminoConversion.put("AAC",'N'); aminoConversion.put("AGC",'S');
        aminoConversion.put("AUA",'I'); aminoConversion.put("ACA",'T'); aminoConversion.put("AAA",'K'); aminoConversion.put("AGA",'R');
        aminoConversion.put("AUG",'M'); aminoConversion.put("ACG",'T'); aminoConversion.put("AAG",'K'); aminoConversion.put("AGG",'R');

        aminoConversion.put("GUU",'V'); aminoConversion.put("GCU",'A'); aminoConversion.put("GAU",'D'); aminoConversion.put("GGU",'G');
        aminoConversion.put("GUC",'V'); aminoConversion.put("GCC",'A'); aminoConversion.put("GAC",'D'); aminoConversion.put("GGC",'G');
        aminoConversion.put("GUA",'V'); aminoConversion.put("GCA",'A'); aminoConversion.put("GAA",'E'); aminoConversion.put("GGA",'G');
        aminoConversion.put("GUG",'V'); aminoConversion.put("GCG",'A'); aminoConversion.put("GAG",'E'); aminoConversion.put("GGG",'G');
    }
}
