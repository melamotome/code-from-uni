public interface StringTransformer {
    //1.1
    String transform(String s);

    //1.2
    default String transform(String s, int times) {
        for (int i = 0; i < times; i++) {
            s = transform(s);
        }
        return s;
    }
}
