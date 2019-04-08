public class Token {
    public static enum Types {
        NUMBER,
        ID,
        OP,
        EMPTY
    }

    String value;
    Types type;

    public Token(Types type, String value) {
        this.type = type;
        this.value = value;
    }

}
