package scanner;



import java.io.File;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import static scanner.Read.flag;
import static scanner.Read.out;







public class ThreadScan implements Runnable{
    CyclicBarrier cbar;
    String URL;
    int id;
    int c =0;
   
    
    public ThreadScan(CyclicBarrier c, String n, int number) {
	cbar = c;
        id = number;
	URL = n;
	
    }
        
 
    @Override
    public void run() {
        
        try {
	    cbar.await();
            scan(URL);
	} catch (BrokenBarrierException | InterruptedException exc) {
	    System.out.println(exc);
	}
                
       
      // add the number of files to Results
        out.get(id).setC(c);
        
                
	
    }
    
    
    void scan (String url) throws InterruptedException{
        
        File file = new File(url);
        File[] s = file.listFiles();
                   
        // scann the directory   
        for (int j = 0; j < s.length; j++) {
            // Stop after keyEvent - ESCAPE
            if(flag ==true){ break;}
            
            if(!s[j].isDirectory()){
                c++;
            }
            if (s[j].isDirectory()){
                scan(s[j].getPath());
                
            }
        }
        
    }
    
    public ThreadScan() {
    }

    
    
    public CyclicBarrier getCbar() {
        return cbar;
    }

    public void setCbar(CyclicBarrier cbar) {
        this.cbar = cbar;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}


