package bookmanagement.service.impl;

import bookmanagement.dao.AppointmentDao;
import bookmanagement.dao.BookDao;
import bookmanagement.dto.AppointExecution;
import bookmanagement.entity.Appointment;
import bookmanagement.entity.Book;
import bookmanagement.enums.AppointStateEnum;
import bookmanagement.exception.AppointException;
import bookmanagement.exception.NoNumberException;
import bookmanagement.exception.RepeatAppointException;
import bookmanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AppointmentDao appointmentDao;
    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    public List<Book> getList() {
        return bookDao.queryAll(0,1000);
    }
    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            int update = bookDao.reduceNumber(bookId);
            if(update < 0){
                throw new NoNumberException("repeat appoint");
            }
            else {
                int insert = appointmentDao.insertAppointment(bookId,studentId);
                if(insert <= 0){
                    throw new RepeatAppointException("repeat appoint");
                }
                else {
                    Appointment appointment = appointmentDao.queryByKeyWithBook(bookId,studentId);
                    return new AppointExecution(bookId,AppointStateEnum.SUCCESS,appointment);
                }
            }
        }catch (NoNumberException e1){
            throw e1;
        }
        catch (RepeatAppointException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }
}
