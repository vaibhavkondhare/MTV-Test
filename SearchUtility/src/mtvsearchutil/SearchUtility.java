package mtvsearchutil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
/**
 *
 * @author vkondhare
 */
public class SearchUtility {

    public static void main(String[] args) {
         File folder = new File("C:\\Test");
         listAllFiles(folder);
    }
    
    // Uses listFiles method  
  public static void listAllFiles(File folder){
   // System.out.println("In listAllfiles(File) method");
    File[] fileNames = folder.listFiles();
    for(File file : fileNames){
      // if directory call the same method again
      if(file.isDirectory()){
         listAllFiles(file);
      }else{
        try {
          readContent(file);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }
  // Uses Files.walk method   
  public static void listAllFiles(String path){
    System.out.println("In listAllfiles(String path) method");
    try(Stream<Path> paths = Files.walk(Paths.get(path))) {
      paths.forEach(filePath -> {
        if (Files.isRegularFile(filePath)) {
          try {
            readContent(filePath);
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      });
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   } 
  }
     
  public static void readContent(File file) throws IOException{
    //System.out.println("read file " + file.getCanonicalPath() );
    try(BufferedReader br  = new BufferedReader(new FileReader(file))){
      String strLine;
      // Read lines from the file, returns null when end of stream 
      // is reached
      while((strLine = br.readLine()) != null){
      //System.out.println("Line is - " + strLine);
      if(strLine.contains("#include"))
      {
          System.out.println(strLine);
    	  System.out.println("Found in:"+file.getCanonicalPath());
      }
     /* if(strLine.contains("=uschsmvsc015") || strLine.contains("=USCHSMVSC015"))
      {
    	  System.out.println("USCHSMVSC015 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx000") || strLine.contains("=USCHSMVCX000"))
      {
    	  System.out.println("USCHSMVCX000 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx001") || strLine.contains("=USCHSMVCX001"))
      {
    	  System.out.println("USCHSMVCX001 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx002") || strLine.contains("=USCHSMVCX002"))
      {
    	  System.out.println("USCHSMVCX002 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx003") || strLine.contains("=USCHSMVCX003"))
      {
    	  System.out.println("USCHSMVCX003 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx012") || strLine.contains("=USCHSMVCX012"))
      {
    	  System.out.println("USCHSMVCX012 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvcx011") || strLine.contains("=USCHSMVCX011"))
      {
    	  System.out.println("USCHSMVCX011 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschvmvvm004") || strLine.contains("=USCHVMVVM004"))
      {
    	  System.out.println("USCHVMVVM004 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschvmvvm009") || strLine.contains("=USCHVMVVM009"))
      {
    	  System.out.println("USCHVMVVM009 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschvmvvm008") || strLine.contains("=USCHVMVVM008"))
      {
    	  System.out.println("USCHVMVVM008 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvsc001") || strLine.contains("=USCHSMVSC001"))
      {
    	  System.out.println("USCHSMVSC001 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uspnsvulm1018") || strLine.contains("=USPNSVULM1018"))
      {
    	  System.out.println("USPNSVULM1018 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvsc014") || strLine.contains("=USCHSMVSC014"))
      {
    	  System.out.println("USCHSMVSC014 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uspnsvulm1008") || strLine.contains("=USPNSVULM1008"))
      {
    	  System.out.println("USPNSVULM1008 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschvmvvm010") || strLine.contains("=USCHVMVVM010"))
      {
    	  System.out.println("USCHVMVVM010 Found in:"+file.getCanonicalPath());
      }
      if(strLine.contains("=uschsmvsc006") || strLine.contains("=USCHSMVSC006"))
      {
    	  System.out.println("USCHSMVSC006 Found in:"+file.getCanonicalPath());
      }*/
      }
    }
  }
     
  public static void readContent(Path filePath) throws IOException{
    System.out.println("read file " + filePath);
    List<String> fileList = Files.readAllLines(filePath);
    System.out.println("" + fileList);
  }
    
}
