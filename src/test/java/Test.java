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
        String[] data = in.split("ç");

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

        data = in.split("ç");

        String[] itensData = data[2].split(",");


        Sale sale = new Sale( data[1], data[2] );

        Item i = null;
        String[] item = null;
        for (String s: itensData) {
            item = s.split("-");
            sale.addItem( new Item( item[0], item[1], item[2] ) );
        }

        System.out.println( sale );

    }


}
