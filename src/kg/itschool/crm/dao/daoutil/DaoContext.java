package kg.itschool.crm.dao.daoutil;

import kg.itschool.crm.dao.*;
import kg.itschool.crm.dao.impl.*;

public abstract class DaoContext {
    static {
        try {
        System.out.println("Loading driver...");
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed");
            e.printStackTrace();
        }
    }


    private static ManagerDao managerDao;
    private static MentorDao mentorDao;
    private static StudentDao studentDao;
    private static CourseDao courseDao;
    private static GroupDao groupDao;
    private static AddressDao addressDao;

    public static CrudDao<?> autowired(String qualifier , String scope){
        if (!scope.equals("prototype") && !scope.equals("singleton")){
            throw new RuntimeException("Invalid scope of bean " + scope);

        }switch (qualifier){
            case "ManagerDao":
                return getManagerDaoSQL(scope);
            case "MentorDao":
                return getMentorDaoSQL(scope);
            case "StudentDao":
                return getStudentDaoSQL(scope);
            case "CourseDao":
                return getCourseDaoSQL(scope);
            case "GroupDao":
                return getGroupDaoSQL(scope);
            case "AddressDao":
                return getAddressDaoSQL(scope);
            default:
                throw new RuntimeException("Can not find bean for autowiring: " + qualifier);


        }


    }

    private static ManagerDao getManagerDaoSQL(String scope) {
       if(scope.equals("prototype")){
           return new ManagerDaoImpl();

       }
       if (managerDao == null){
           managerDao = new ManagerDaoImpl();

       }

        return managerDao;
    }

    private static MentorDao getMentorDaoSQL(String scope) {
        if(scope.equals("prototype")){
           return new MentorDaoImpl();
        }if (mentorDao == null){
            mentorDao = new MentorDaoImpl();
        }

        return mentorDao;
    }

    private static StudentDao getStudentDaoSQL(String scope) {
        if (scope.equals("prototype")){
            return new StudentDaoImpl();
        }if(studentDao == null){
            studentDao = new StudentDaoImpl();
        }

        return studentDao;
    }

    private static StudentDao getCourseDaoSQL(String scope) {
        if (scope.equals("prototype")){
           return new StudentDaoImpl();
        }if (courseDao == null){
           studentDao = new StudentDaoImpl();
        }

        return studentDao;
    }

    private static GroupDao getGroupDaoSQL(String scope) {
        if (scope.equals("prototype")){
            return new GroupDaoImpl();
        }if (groupDao == null){
            groupDao = new GroupDaoImpl();
        }
        return groupDao;
    }

    private static AddressDao getAddressDaoSQL(String scope) {
        if(scope.equals("prototype")){
            return new AddressDaoImpl();
        }if (addressDao == null){
            addressDao = new AddressDaoImpl();
        }

        return addressDao;
    }
}
