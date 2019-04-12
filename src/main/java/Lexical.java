import java.io.*;

public class Lexical {

    FileReader fileReader;
    char lookAhead;

    public Lexical(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File codeFile = new File(classLoader.getResource(path).getFile());
        try {
            this.fileReader = new FileReader(codeFile);
            this.lookAhead = (char) this.fileReader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Token nextToken() throws EOFException {
        if (this.lookAhead != -1) {

            try {
                if (Character.isDigit(this.lookAhead)) {
                    return matchNumber();
                } else if (Character.isAlphabetic(this.lookAhead)) {
                    return matchID();
                } else if (this.lookAhead == '+' || this.lookAhead == '-') {
                    return matchOp();
                } else if (this.lookAhead == ' ' || this.lookAhead == '\t' || this.lookAhead == '\n') {
                    this.lookAhead = (char) this.fileReader.read();
                    return this.nextToken();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        throw new EOFException();

    }

    public Token matchNumber() throws IOException {
        StringBuilder tempStr = new StringBuilder();
        if (this.lookAhead != -1) {
            tempStr.append(this.lookAhead);
            this.lookAhead = (char) this.fileReader.read();
            while (Character.isDigit(this.lookAhead)) {
                tempStr.append(this.lookAhead);
                this.lookAhead = (char) this.fileReader.read();
            }
            skipSpaces();
            return new Token(Token.Types.NUMBER,tempStr.toString());

        }
        throw new EOFException();
    }

    public Token matchID() throws IOException {
        StringBuilder tempStr = new StringBuilder();
        if (this.lookAhead != -1) {
            tempStr.append(this.lookAhead);
            this.lookAhead = (char) this.fileReader.read();
            while (Character.isAlphabetic(this.lookAhead) || Character.isDigit(this.lookAhead)) {
                tempStr.append(this.lookAhead);
                this.lookAhead = (char) this.fileReader.read();
            }
            skipSpaces();
            return new Token(Token.Types.ID,tempStr.toString());
        }
        throw new EOFException();
    }

    public Token matchOp() throws IOException {
        String tempStr = Character.toString(this.lookAhead);
        this.lookAhead = (char) this.fileReader.read();
        skipSpaces();
        return new Token(Token.Types.OP,tempStr.toString());

    }
    private void skipSpaces() throws IOException {
        if (this.lookAhead == ' ' || this.lookAhead == '\t' || this.lookAhead == '\n'){
            this.lookAhead = (char) this.fileReader.read();
            skipSpaces();
        }else {
            return;
        }
    }

}
