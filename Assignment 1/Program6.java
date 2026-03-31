abstract class Cipher {
    abstract String encrypt(String text);
}

class CaesarCipher extends Cipher {
    public String encrypt(String text) {
        String result = "";
        for (char c : text.toCharArray()) {
            result += (char)(c + 3);
        }
        return result;
    }
}

class Main6 {
    public static void main(String[] args) {
        Cipher c = new CaesarCipher();
        System.out.println("Encrypted: " + c.encrypt("HELLO"));
    }
}