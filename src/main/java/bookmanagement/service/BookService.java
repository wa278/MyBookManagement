package bookmanagement.service;

import bookmanagement.dto.AppointExecution;
import bookmanagement.entity.Book;

import java.util.List;

public interface BookService  {
    Book getById(long bookId);
    List<Book> getList();
    AppointExecution appoint(long bookId, long studentId);
}
