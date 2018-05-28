package org.todeschini.files;

import org.todeschini.model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Artur on 28/05/18.
 */
public class FileOperation implements Operation {

    private static Logger LOGGER = Logger.getLogger( FileOperation.class.getName() );

    private String delimiter = Operation.DELIMITER_DEFAULT;

    private Map<String, Salesman> salesmen = new HashMap<String, Salesman>();
    private Map<String, Customer> customers = new HashMap<String, Customer>();
    private Map<String, Sale> sales = new HashMap<String, Sale>();



    /**
     * @author Artur
     *
     * Get the encode of the file to read the acents
     *
     * @return BufferedReader
     *
     * @throws RuntimeException
     */
    private BufferedReader getBufferedReader(File file) throws RuntimeException {
        try {

            FileReader fileReader = new FileReader(file);
            String encode = fileReader.getEncoding();

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode) );
            return reader;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao ler o arquivo");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao ler o arquivo");
        }
    }

    @Override
    public void categorize(String fileName) {
        File file = new File( PATH_IN + "/" + fileName);
        BufferedReader br = this.getBufferedReader(file);

        String[] prorpiedades = null;
        String s;


        try {
            String line = br.readLine();
            String[] data = null;
            String typeData = null;

            while ( line != null ) {
                data = line.split( delimiter );

                typeData = data[0];

                if (typeData == Category.TYPE_SALESMAN ) {

                    //0      1   2   3
                    //001çCPFçNameçSalary
                    Salesman salesman = salesmen.get( data[ 1 ] );

                    if ( salesman == null ) {
                        salesmen.put( data[2], new Salesman( data[1], data[2], data[3] ) );

                    } else {
                        salesman.setName( data[2] );
                        salesman.setSalary( data[3] );

                        salesmen.replace( salesman.getCpf() , salesman );
                    }

                    System.out.println( salesman.toString() );

                } else if ( typeData == Category.TYPE_CUSTOMER ) {

                    //0    1   2    3
                    //002çCNPJçNameçBusinessAre
                    Customer customer = customers.get( data[1] );

                    if ( customer == null ) {
                        customers.put( data[1], new Customer( data[1], data[2], data[3] ) );
                    } else {

                        customer.setName( data[2 ] );
                        customer.setBusinessArea( data[3] );

                        customers.replace( data[1], customer );
                    }

                    System.out.println( customer );

                } else if ( typeData == Category.TYPE_SALES ) {
                    //0    1      2                               3
                    //003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname
                    Sale sale = sales.get( data [1] );

                    String item = data[2].substring(1, data[2].length() -1 );


                    if ( sale == null ) {
                        sale = new Sale( data[1], data[3] , "", "", "" );
                    } else {
                        //
                        //sale.addItem( );
                        sales.replace( sale.getId(), sale );
                    }

                } else {
                    throw new RuntimeException("DATA TYPE NO ACCEPT " + typeData);
                }
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() );
        }

    }

//● Amount of clients in the input file
//● Amount of salesman in the input file
//● ID of the most expensive sale
//● Worst salesman ever
}
