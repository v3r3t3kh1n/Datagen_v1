public class RandomAlphabeticString {
    static String getAlphabeticString(int n) {
        String RandomAlphabeticString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (RandomAlphabeticString.length() * Math.random());
            sb.append(RandomAlphabeticString.charAt(index));
        }
        return sb.toString();
    }

    public static String rs(String args) {
        return RandomAlphabeticString.getAlphabeticString(Integer.parseInt(args));
    }
}
