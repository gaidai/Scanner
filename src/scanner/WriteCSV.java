package scanner;




import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


public class WriteCSV {
    
  
    private static final String NEW_LINE_SEPARATOR = "\n";
    //CSV file header
    private static final Object [] FILE_HEADER = {"URL;Files"};
    //Delimiter used in CSV file
    static String del = ";";
    public static void writeCsvFile(String fileName, List<Result> results) {
        
        
        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
       
        try {
            //initialize FileWriter object

            fileWriter = new FileWriter(fileName);

            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header

            csvFilePrinter.printRecord(FILE_HEADER);

            //Write a new object list to the CSV file
            for (Result result : results) {
                List DataRecord = new ArrayList();

                DataRecord.add(result.getURL()+del+result.getC());
                
                csvFilePrinter.printRecord(DataRecord);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();

        } finally {
            
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();

            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();

            }

        }

    }
    
    
}
