import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class testgetBookByAuthorName {

    @Test
    public void testgetBookByAuthorName() {
        LibraryController controller = new LibraryController();
        // Set up necessary dependencies
        // Set up method arguments
        // Call the method and get the result
        List result = controller.getBookByAuthorName("Sample");
        // Assert the expected result
        // Assertions.assertEquals(expectedResult, result);
    }
}
