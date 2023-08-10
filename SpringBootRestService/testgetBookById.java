import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class testgetBookById {

    @Test
    public void testgetBookById() {
        LibraryController controller = new LibraryController();
        // Set up necessary dependencies
        // Set up method arguments
        // Call the method and get the result
        Library result = controller.getBookById("Sample");
        // Assert the expected result
        // Assertions.assertEquals(expectedResult, result);
    }
}
