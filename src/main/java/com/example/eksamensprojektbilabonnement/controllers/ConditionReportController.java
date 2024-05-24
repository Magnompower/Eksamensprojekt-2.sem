package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.models.ConditionReport;
import com.example.eksamensprojektbilabonnement.models.Damage;
import com.example.eksamensprojektbilabonnement.models.LeaseAgreement;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.rmi.dgc.Lease;
import java.util.List;


/**
 * The  Condition report controller.
 */
@Controller
public class ConditionReportController {

    @Autowired
    private CarService carService;

    @Autowired
    private DamageService damageService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private ConditionReportService conditionReportService;


    /**
     * Car returned string.
     * author Otto
     *
     * @param chassisNumber      the chassis number
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @PostMapping("/car_returned")
    public String carReturned(@RequestParam String chassisNumber, RedirectAttributes redirectAttributes) {
        //Create the initial condition report with known data:
        int leaseId = leaseService.getActiveLease(chassisNumber).getLeaseId();
        double kmBeforeLease = carService.getCarByChassisNumber(chassisNumber).getKmDriven();
        conditionReportService.createConditionReport(chassisNumber, leaseId, kmBeforeLease);
        redirectAttributes.addAttribute("leaseId", leaseId);
        return "redirect:/add_damages_to_report";
    }


    /**
     * Add damages to report string.
     * author Hasan
     *
     * @param model   the model
     * @param leaseId the lease id
     * @return the string
     */
    @GetMapping("/add_damages_to_report")
    public String addDamagesToReport(Model model, @RequestParam int leaseId){
        //Get the lease and add it to the model:
        LeaseAgreement lease = leaseService.getLease(leaseId);
        model.addAttribute("lease", lease);

        //Get the created condition report and add it to the model:
        ConditionReport conditionReport = conditionReportService.getConditionReport(lease.getLeaseId());
        model.addAttribute(conditionReport);

        //Get the existing invoiced damages and add them to the model:
        List<Damage> invoicedDamages = damageService.getInvoicedDamages(conditionReport.getChassisNumber());
        model.addAttribute("invoicedDamages", invoicedDamages);

        //Get the new non-invoiced damages (if added):
        List<Damage> nonInvoicedDamages = damageService.getNonInvoicedDamages(conditionReport.getChassisNumber(), lease.getLeaseId());
        model.addAttribute("nonInvoicedDamages", nonInvoicedDamages);

        //Get the kilometers driven for the car and add to the model:
        double kmDriven = carService.getCarByChassisNumber(conditionReport.getChassisNumber()).getKmDriven();
        model.addAttribute("kmDriven", kmDriven);

        return "home/car_returned";
    }
}
