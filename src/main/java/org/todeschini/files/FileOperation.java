package org.todeschini.files;

import org.todeschini.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Artur on 28/05/18.
 */
public class FileOperation implements Operation {

    private static Logger LOGGER = Logger.getLogger( FileOperation.class.getName() );

    private String delimiterByField = ";";
    private String delimiterByItem = ",";
    private String delimiterByAttrItem = "-";

    private Map<String, Salesman> salesmen = new HashMap<String, Salesman>();
    private Map<String, Customer> customers = new HashMap<String, Customer>();
    private Map<String, Sale> sales = new HashMap<String, Sale>();

    public FileOperation(String delimiterByField, String delimiterByItem, String delimiterByAttrItem) {
        this.delimiterByField = delimiterByField;
        this.delimiterByItem = delimiterByItem;
        this.delimiterByAttrItem = delimiterByAttrItem;
    }

    @Override
    public synchronized void categorize(String fileName) throws IOException {
        LOGGER.log(Level.INFO, fileName + " in File process");

        Path path = Paths.get(PATH_IN + "/" + fileName);

        int quantitySalesman = 0;
        int quantityCustomer = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = null;
            String data[] = null;
            String typeData = null;
            while ((line = reader.readLine()) != null) {
                data = line.trim().split(delimiterByField);

                typeData = data[0];

                if (typeData.equals(Category.TYPE_SALESMAN)) {

                    //0    1   2   3
                    //001çCPFçNameçSalary
                    Salesman salesman = salesmen.get(data[2]);

                    if (salesman == null) {
                        salesman = new Salesman(data[1], data[2], data[3]);
                        salesmen.put(data[2], salesman);

                    } else {
                        salesman.setName(data[2]);
                        salesman.setSalary(data[3]);

                        salesmen.replace(salesman.getCpf(), salesman);
                    }
                    quantitySalesman++;
                    //System.out.println( salesman.toString() );

                } else if (typeData.equals(Category.TYPE_CUSTOMER)) {

                    //0    1   2    3
                    //002çCNPJçNameçBusinessAre
                    Customer customer = customers.get(data[1]);

                    if (customer == null) {
                        customer = new Customer(data[1], data[2], data[3]);
                        customers.put(data[1], customer);
                    } else {

                        customer.setName(data[2]);
                        customer.setBusinessArea(data[3]);

                        customers.replace(data[1], customer);
                    }
                    quantityCustomer++;
                    //System.out.println( customer );

                } else if (typeData.equals(Category.TYPE_SALES)) {
                    //0    1      2                               3
                    //003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname
                    //in = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego";
                    //item
                    //[ItemID-ItemQuantity-ItemPrice]
                    // 0  1  2 //frist item || separete by -
                    //[1-10-100
                    // ,2-30-2.50,3-40-3.10]

                    Sale sale = sales.get(data[2]);

                    if (sale == null) {
                        String[] itensData = data[2].substring(1, data[2].length() - 1).split(delimiterByItem);

                        sale = new Sale(data[1], data[3]);

                        String[] item = null;
                        for (String str : itensData) {
                            item = str.split(delimiterByAttrItem);
                            sale.addItem(new Item(item[0], item[1], item[2]));
                        }
                    } else {

                        sale.clearItens();

                        String[] itensData = data[2].substring(1, data[2].length() - 1).split(delimiterByItem);

                        String[] item = null;
                        for (String str : itensData) {
                            item = str.split(delimiterByAttrItem);
                            sale.addItem(new Item(item[0], item[1], item[2]));
                        }
                    }
                    Salesman salesman = salesmen.get(sale.getSalesmanName());

                    if (salesman != null) {
                        salesman.addSalesValue(sale.getSum());
                    }

                    //System.out.println( sale );

                } else {
                    throw new RuntimeException("DATA TYPE NO ACCEPT " + typeData + " the first column must contain the values 001, 002, 003");
                }
            }

            this.processOutPut(fileName, quantitySalesman, quantityCustomer);

            //move
            File file = new File(PATH_IN + "/" + fileName);
            try {
                moveFile(file, new File(Operation.PATH_PROCESS + "/" + System.currentTimeMillis() + "-" + fileName));
                LOGGER.log(Level.INFO, "The File " + fileName + " has move to " +  Operation.PATH_PROCESS );
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
//                e.printStackTrace();
            }


            //del
            try {
                delete(file);
                LOGGER.log(Level.INFO, "The File " + fileName + " has delete in " +  Operation.PATH_PROCESS );
            } catch (Exception e) {
//                e.printStackTrace();
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    // Amount of clients in the input file
    // Amount of salesman in the input file
    // ID of the most expensive sale
    // Worst salesman ever
    public synchronized void processOutPut(String fileNameIn, int quantitySalesman, int quantityCustomer) {
        //Amount of clients in the input file
        List<String> lines = new LinkedList<>();
        lines.add( "Amount of clients in the input file is " + quantitySalesman );

        //Amount of salesman in the input file
        lines.add( "Amount of salesman in the input file is " +quantityCustomer );

        //ID of the most expensive sale
        lines.add( "The ID of the most expensive sale is "  + this.getIdMostExpensiveSale() );

        //Worst salesman ever
        Salesman s = this.getWorstSalesmanEver();
        lines.add( "Worst salesman ever is " + s != null ? s.toString() : "" );

        StringBuilder fileNameCreate = new StringBuilder()
                .append( Operation.PATH_OUT )
                .append( "/{" )
                .append( fileNameIn.replace(".dat","") )
                .append("}.done.dat");

        try {
            writeLargerTextFile( fileNameCreate.toString() , lines );
            LOGGER.log(Level.INFO, "File create " + fileNameCreate.toString() );
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, e.getMessage() );
        }
    }

    public Salesman getWorstSalesmanEver() {
        double sumMin = Double.MAX_VALUE;
        Salesman s = null;
        for ( Salesman salesman: salesmen.values() ) {
            if ( sumMin > salesman.getSumOfSales() ) {
                s = salesman;
            }
        }
        return s;
    }

    //Worst salesman ever
    // se houver duas vendas com o mesmo valor minino pegará a primeira
    public String getIdMostExpensiveSale() {
        double sumMax = Double.MIN_VALUE;
        Sale s = null;
        for (Sale sale : sales.values() ) {
            if ( sumMax < sale.getSum() ) {
                sumMax = sale.getSum();
                s = sale;
            }
        }

        return s != null ? s.getId() : "";
    }

    public void writeLargerTextFile(String aFileName, List<String> lines) {
        ////%HOMEPATH%/data/out. The filename must follow this pattern, {flat_file_name}.done.dat.
        Path path = Paths.get( aFileName );
        try {//(Files.write(path, charset, list, StandardOpenOption.APPEND);)
        //try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8) ) {
            Files.write(path, lines,  StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.log(Level.SEVERE, e.getMessage() );
        }
    }

    private void moveFile(File source, File dest) throws IOException {
        if( !dest.exists() ){
            dest.createNewFile();
        }
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);

            byte[] buf = new byte[1024];
            int len;

            while( (len = in.read(buf) ) > 0 ){
                out.write(buf, 0, len);
            }
        } finally{
            in.close();
            out.close();
        }
    }

    private boolean delete(File resource) throws IOException {
        return resource.delete();
    }

    @Override
    public void createDiretoryIfNotExist(String diretoryName) {
        File diretory = new File( diretoryName );
        try {
            if( !diretory.exists() ) {
                diretory.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.INFO, "Error to create the diretory " + diretoryName + "\n" + e.getMessage() );
        }
    }
}
