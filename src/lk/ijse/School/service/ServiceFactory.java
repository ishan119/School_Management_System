package lk.ijse.School.service;

import lk.ijse.School.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        if(serviceFactory==null)serviceFactory=new ServiceFactory();
        return serviceFactory;
    }
    public <T extends SuperService>T getService(ServiceType serviceType){
        switch (serviceType){
            case EXAM:return (T)new ExamServiceImpl();
            case ROOM:return (T)new RoomServiceImpl();
            case CLASS:return (T)new ClassServiceImpl();
            case HOSTAL:return (T)new HostalServiceImpl();
            case NEWUSER:return (T)new NewUserServiceImpl();
            case SECTION:return (T)new SectionServiceImpl();
            case STUDENT:return (T)new StudentServiceImpl();
            case SUBJECT:return (T)new SubjectServiceImpl();
            case TEACHER:return (T)new TeachersServiceImpl();
            case STUDENTEXAM:return (T)new StudentExamServiceImpl();
            case STUDENTROOM:return (T)new StudentRoomServiceImpl();
        }
        return null;
    }
}
