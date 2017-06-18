package scanner;





import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import static scanner.consoleapp.fileread;
import static scanner.consoleapp.filewrite;



public class Read implements Runnable{
    
    public static boolean flag = false;
    public static List<Result> out;
   
    
    public void Readfile() throws InterruptedException, IOException{
        
        String URL;
        
        
        int i=0;
        String line=null;
        BufferedReader bufferedReader;
        // Read the input file.
        FileReader fileReader = new FileReader(fileread);
        bufferedReader = new BufferedReader(fileReader);
        while(bufferedReader.readLine()!=null){
            i++;
        }
        
        
     // Create Barrier for starting threads togeather
        CyclicBarrier cb = new CyclicBarrier(i);
         
        out = new ArrayList<>();
        
        bufferedReader.close();
        FileReader Reader = new FileReader(fileread);
        bufferedReader = new BufferedReader(Reader);
        
        Thread [] tarray = new Thread[i];
        
        for (int k = 0; k<i; k++){
            
            // Create new Thread for directory
            URL = bufferedReader.readLine();
             
            Thread threadobj = new Thread(new ThreadScan(cb, URL, k));
            threadobj.start();
            tarray[k] = threadobj;
            Result resobj = new Result();
            resobj.setURL(URL);
            out.add(resobj);
             
        }
        // wait all scanning threads
        for (Thread t: tarray){
            t.join();
        }
        // Write results to csv file 
        WriteCSV writer = new WriteCSV();
        writer.writeCsvFile(filewrite, out);
        
        for(Result r:out ){
            r.setId(out.indexOf(r)+1);
        }
        
        FXMLDocumentController f = new FXMLDocumentController();
        
        // View results to GUI 
        f.view(out);
        fileReader.close();
        bufferedReader.close();
        
        
        
        
        
        
    }

    @Override
    public void run() {
        try {
            Readfile();
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
