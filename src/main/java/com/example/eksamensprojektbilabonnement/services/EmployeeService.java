package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Employee;
import com.example.eksamensprojektbilabonnement.repositories.EmployeeRepository;
import com.example.eksamensprojektbilabonnement.utilities.EmployeePageMapping;
import com.example.eksamensprojektbilabonnement.utilities.EmployeeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Employee service.
 */
@Service
public class EmployeeService {
    private final EmployeePageMapping employeePageMapping;
    //Reads from a HashMap (which is what employeePages is)
    // are thread-safe as long as no writes are happening simultaneously.
    // Since the common operations here (getPage) are read-only,
    // there are no threading issues.


    /**
     * The Employee repository.
     */
    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee service.
     *
     * @param employeePageMapping the employee page mapping
     */
    @Autowired
    public EmployeeService(EmployeePageMapping employeePageMapping) {
        this.employeePageMapping =  employeePageMapping;
    }

    /**
     * Check pass string.
     *
     * @param email    the email
     * @param password the password
     * @return the string
     * @author Hasan, Otto
     */
    public String checkPassword(String email, String password) {
        String dbPassword = employeeRepository.checkPass(email);
        if(password.equals(dbPassword)){
            return "UserApproved";
        } else if (dbPassword.equals("UserNotFound")){
            return "NoUserFound";
        } else {
            return "WrongPassWord";
        }
    }

    /**
     * Gets page for employee.
     *
     * @author Hasan
     * @param type the type
     * @return the page for employee
     *
     */
    public String getPageForEmployee(EmployeeType type) {
        return employeePageMapping.getPage(type);
    }

    /**
     * Get employee employee.
     *
     * @author Hasan
     * @param email the email
     * @return the employee
     */
    public Employee getEmployee(String email){
        return employeeRepository.getEmployee(email);
    }


}
