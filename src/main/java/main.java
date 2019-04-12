import java.io.EOFException;
import java.io.IOException;

public class main {
    public static void main(String[] args) {


        try {
            Parser parser = new Parser("code.txt","result.txt");
            parser.start();


        }catch (EOFException e){
            System.out.printf("done");
        } catch (IOException e){
            System.out.println("sorry can't create output file ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
