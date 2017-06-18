package scanner;



public class Result {
    
    int c;
    String URL;
    int id;

    
    public Result() {
    }

    public Result(int c, String URL, int id) {
        this.c = c;
        this.URL = URL;
        this.id = id;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
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
