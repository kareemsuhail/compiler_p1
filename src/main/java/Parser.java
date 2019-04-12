import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

    Lexical lexical;
    private BufferedWriter outBuffer;

    public Parser(String inputPath, String resultPath) throws IOException {

        this.lexical = new Lexical(inputPath);
        FileWriter outFile = new FileWriter(resultPath);
        this.outBuffer = new BufferedWriter(outFile);
    }

    public void start() throws Exception {
        try {
            Expr();
        } catch (EOFException e) {
            closeConnectionWithOutput();
        }

    }

    private void Expr() throws Exception {
        term();
        rest();
    }

    private void rest() throws Exception {
        Token opToken = match(Token.Types.OP);
        term();
        writeToOutput(opToken.value);
        rest();
    }

    private void term() throws Exception {
        Token termToken;
        if (Character.isDigit(lexical.lookAhead)) {
            termToken = match(Token.Types.NUMBER);
        } else {
            termToken = match(Token.Types.ID);
        }
        writeToOutput(termToken.value);

    }

    private Token match(Token.Types type) throws Exception {

        Token tempToken = this.lexical.nextToken();
        if (type == tempToken.type) {
            return tempToken;
        } else {
            throw new Exception("incorrect code");
        }
    }

    private void writeToOutput(String data) throws IOException {
        this.outBuffer.write(data);

    }

    public void closeConnectionWithOutput() throws IOException {
        this.outBuffer.close();
    }


}
