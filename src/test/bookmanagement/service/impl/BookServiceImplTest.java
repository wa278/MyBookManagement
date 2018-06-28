package bookmanagement.service.impl;


import bookmanagement.BaseTest;
import bookmanagement.dto.AppointExecution;
import bookmanagement.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImplTest extends BaseTest {
    @Autowired
    private BookService bookService;
    @Test
    public void testAppoint() throws Exception{
        long bookId = 1001;
        long studentId = 12345678912L;
        AppointExecution execution = bookService.appoint(bookId,studentId);
        System.out.println(execution);
    }

}