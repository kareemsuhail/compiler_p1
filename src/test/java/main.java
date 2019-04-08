import java.io.EOFException;

public class main {
    public static void main(String[] args) {
       Parser parser = new Parser("code.txt");
        try {
            parser.start();
        }catch (EOFException e){
            System.out.printf("done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
