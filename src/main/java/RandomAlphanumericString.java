public class RandomAlphanumericString {
    static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static String rs(String args) {
        return RandomAlphanumericString.getAlphaNumericString(Integer.parseInt(args));
    }
}
