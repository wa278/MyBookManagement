package bookmanagement.web;

import bookmanagement.dto.AppointExecution;
import bookmanagement.dto.Result;
import bookmanagement.entity.Book;
import bookmanagement.enums.AppointStateEnum;
import bookmanagement.exception.NoNumberException;
import bookmanagement.exception.RepeatAppointException;
import bookmanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    private List<Book> list(){
        return bookService.getList();
    }
    @RequestMapping(value = "/{bookId}/detail",method = RequestMethod.GET)
    private Book detail(@PathVariable("bookId") Long bookId){
        if(bookId == null){
            return null;
        }
        Book book = bookService.getById(bookId);
        if(book == null){
            return null;
        }
        return book;
    }
    @RequestMapping(value = "/{bookId}/appoint",method = RequestMethod.POST,produces = {"application/json"})
    private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId){
        if(studentId == null || studentId.equals("")){
            return new Result<AppointExecution>(false,"学号不能为空");
        }
        AppointExecution  execution = null;
        try {
            execution = bookService.appoint(bookId,studentId);
        }catch (NoNumberException e1){
            execution = new AppointExecution(bookId,AppointStateEnum.No_NUMBER);
        }catch (RepeatAppointException e2){
            execution = new AppointExecution(bookId,AppointStateEnum.REPEAT_APPOINT);
        }catch (Exception e){
            execution = new AppointExecution(bookId,AppointStateEnum.INNER_ERROR);
        }
        return new Result<AppointExecution>(true,execution);
    }
}
