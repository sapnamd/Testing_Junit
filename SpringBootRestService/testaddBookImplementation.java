import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class testaddBookImplementation {

    @Test
    public void testaddBookImplementation() {
        LibraryController controller = new LibraryController();
       
        ResponseEntity result = controller.addBookImplementation(null);
        
    }
}
