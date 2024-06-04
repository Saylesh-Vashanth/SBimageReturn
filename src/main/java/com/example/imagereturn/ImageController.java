package com.example.imagereturn;



import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {

	@GetMapping("/airtel")
    public ResponseEntity<Resource> getImage() throws Exception {
       
        Path path = Paths.get("src/main/resources/static/logo_5.jpeg");
        
        Resource resource = new UrlResource(path.toUri());
       
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
	}

        @GetMapping("/us")
        public ResponseEntity<Resource> getImage1() throws Exception {
           
            Path path = Paths.get("src/main/resources/static/fam.jpeg");
            
            Resource resource = new UrlResource(path.toUri());
           
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);

}
}

 