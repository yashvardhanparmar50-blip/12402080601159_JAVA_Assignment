class Main3 {
    public static void main(String[] args) {

        // Wrapper Class
        Integer num = Integer.valueOf(10);
        int primitive = num.intValue();
        System.out.println("Wrapper to Primitive: " + primitive);

        // String (Immutable)
        String s = "Hello";
        s.concat(" World");
        System.out.println("String: " + s);

        // StringBuffer (Mutable)
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");
        System.out.println("StringBuffer: " + sb);
    }
}