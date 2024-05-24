package com.example.eksamensprojektbilabonnement.services;

import com.example.eksamensprojektbilabonnement.models.Employee;
import com.example.eksamensprojektbilabonnement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Employee service.
 */
@Service
public class EmployeeService {
    /**
     * The Employee repository.
     */
    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * Check pass string.
     * @author Hasan, Otto
     *
     * @param email    the email
     * @param password the password
     * @return the string
     */
    public String checkPass(String email, String password) {
        String dbPassword = employeeRepository.checkPass(email);
        if(password.equals(dbPassword)){
            return "UserApproved";
        } else if (dbPassword.equals("UserNotFound")){
            return "NoUserFound";
        } else {
            return "WrongPassWord";
        }
    }
}
