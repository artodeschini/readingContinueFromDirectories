import org.todeschini.files.Operation;
import org.todeschini.model.Customer;
import org.todeschini.model.Item;
import org.todeschini.model.Sale;
import org.todeschini.model.Salesman;

/**
 * Created by Artur on 28/05/18.
 */
public class Test {

    public static void main(String[] args) {
        String in  = "001ç1234567891234çDiegoç50000";
        String[] data = in.split(Operation.DELIMITER_DEFAULT);

        Salesman salesman = new Salesman( data[1], data[2], data[3] );
        System.out.println( salesman );

        in = "002ç2345675434544345çJosedaSilvaçRural";

        data = in.split("ç");

        Customer customer = new Customer( data[1], data[2], data[3] );
        System.out.println( customer );
//            0    1  2                            3
        in = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
        //item
        //[ItemID-ItemQuantity-ItemPrice]
        // 0  1  2
        //[1-10-100
        // ,2-30-2.50,3-40-3.10]

        data = in.split(Operation.DELIMITER_DEFAULT);

        String[] itensData = data[2].substring(1,data[2].length()-1).split(Operation.ITEN_DELIMITER);


        Sale sale = new Sale( data[1], data[3] );

        Item i = null;
        String[] item = null;
        for (String s: itensData) {
            item = s.split(Operation.ITEM_DELIMITER_ATR);
            sale.addItem( new Item( item[0], item[1], item[2] ) );
        }

        System.out.println( sale );

    }


}
