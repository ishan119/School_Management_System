package lk.ijse.School.Dao;

import lk.ijse.School.Dao.Custom.Impl.*;
import org.apache.poi.hssf.record.formula.functions.T;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        if(daoFactory==null)daoFactory=new DaoFactory();
        return daoFactory;
    }

    public <T extends SuperDao>T getDao(DaoType daoType){
        switch (daoType){
            case CLASS:return (T)new ClassDaoImpl();
            case EXAM:return (T)new ExamDaoImpl();
            case STUDENTROOM:return (T)new  StudentRoomdaoImpl();
            case STUDENTEXAM:return (T)new  StudentExamDaoImpl();
            case TEACHER:return (T)new  TeachersDaoImpl();
            case SUBJECT:return (T)new subjectDaoImpl();
            case STUDENT:return (T)new StudentDaoImpl();
            case SECTION:return (T)new SectionDaoImpl();
            case NEWUSER:return (T)new NewUserDaoImpl();
            case HOSTAL:return (T)new HostalDaoImpl();
            case ROOM:return (T)new RoomDaoImpl();

        }
        return null;
    }
}
