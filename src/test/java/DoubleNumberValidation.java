import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Artur on 29/05/18.
 */
public class DoubleNumberValidation {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String price = "-55555.55555";

        boolean b = isDouble(price);
        System.out.println("Result of isDouble Method :  "+b);

    }
    public static final boolean isDouble(String itemPrice) {
        Pattern pattern = Pattern.compile("^[-+]?\\d+(\\.{0,1}(\\d+?))?$");
        Matcher matcher = pattern.matcher(itemPrice);
        boolean result = matcher.matches();
        return result;
    }
}