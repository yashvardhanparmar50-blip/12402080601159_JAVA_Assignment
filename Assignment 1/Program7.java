class Outer {
    private String msg = "Hello from Outer";

    class Inner {
        void display() {
            System.out.println(msg);
        }
    }
}

class Main7 {
    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.Inner i = o.new Inner();
        i.display();
    }
}