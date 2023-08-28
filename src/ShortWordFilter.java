public class ShortWordFilter implements Filter {

    @Override
    public boolean accept(Object x) {
        // rejects all non-Strings and all empty Strings
        if (!(x instanceof String) || ((String) x).length() == 0) return false;
        return ((String) x).length() < 5;
    }
}
