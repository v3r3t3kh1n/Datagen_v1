public class RandomNumericString {
    static String getRandomNumericString(int n) {
        String RandomNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (RandomNumericString.length() * Math.random());
            sb.append(RandomNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static String rs(String args) {
        String out = args;
        return RandomNumericString.getRandomNumericString(Integer.parseInt(out));
    }
}
