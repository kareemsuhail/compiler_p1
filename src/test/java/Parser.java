public class Parser {

    Lexical lexical;
    public Parser(String path){
        this.lexical = new Lexical(path);

    }
    public void start() throws Exception {
        Expr();
    }
    private void Expr() throws Exception {
        term();rest();
    }
    private void rest() throws Exception {
        match(Token.Types.OP);term();rest();
    }
    private void term() throws Exception {

        if(Character.isDigit(lexical.lookAhead)) {
            match(Token.Types.NUMBER);
        }else{
            match(Token.Types.ID);
        }
    }
    private void match(Token.Types type) throws Exception {
        if(type == this.lexical.nextToken().type){
            return;
        }else{
            throw new Exception("incorrect code");
        }
    }


}
