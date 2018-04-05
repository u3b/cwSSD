import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
@ManagedBean
@ViewScoped



public class fileUpload {
 
 private Part uploadedFile;
 private String username;
 private String folder = ("/Users/ryan/Desktop/cwSSD-master/AuthLogIn/web/resources/images");
 
 public Part getUploadedFile() {
 return uploadedFile;
 }
 
 public void setUploadedFile(Part uploadedFile) {
 this.uploadedFile = uploadedFile;
 }
 
 
 public void saveFile(){

 try (InputStream input = uploadedFile.getInputStream()) {
 String fileName = uploadedFile.getSubmittedFileName();
        if(fileName.contains(".jpg")){
         Files.copy(input, new File(folder, fileName).toPath());
        }else{
            System.out.println("False");
        }
     }
     catch (IOException e) {
         e.printStackTrace();
     }
 }
 
}

