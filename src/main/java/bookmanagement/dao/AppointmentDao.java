package bookmanagement.dao;

import bookmanagement.entity.Appointment;
import org.apache.ibatis.annotations.Param;

public interface AppointmentDao {
    int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);
    Appointment queryByKeyWithBook(@Param("bookId")long bookId, @Param("studentId")long studentId);
}
