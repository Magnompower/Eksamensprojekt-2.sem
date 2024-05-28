package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.CarService;
import com.example.eksamensprojektbilabonnement.services.DamageService;
import com.example.eksamensprojektbilabonnement.services.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


/**
 * The Lease controller.
 */
@Controller
public class LeaseController {
    @Autowired
    private CarService carService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private DamageService damageService;


    /**
     * Lease overview string.
     * @author Otto, Hasan, Anders, Magne
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/lease_overview")
    public  String leaseOverview(Model model) {
        model.addAttribute("leases", leaseService.getLeases());
        model.addAttribute("localDateTime", LocalDate.now());
        return "home/lease_registration/lease_overview";
    }

    /**
     * Create lease string.
     * @author Otto, Hasan
     *
     * @param chassisNumber      the chassis number
     * @param customerId         the customer id
     * @param startDate          the start date
     * @param endDate            the end date
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @PostMapping("/createLease")
    public String createLease(@RequestParam String chassisNumber,
                              @RequestParam int customerId,
                              @RequestParam LocalDate startDate,
                              @RequestParam LocalDate endDate,
                              RedirectAttributes redirectAttributes) {

        boolean isAvailable = leaseService.checkLeaseAvailability(chassisNumber, startDate, endDate);

        if (isAvailable) {
            try {
                leaseService.createLease(chassisNumber, customerId, startDate, endDate);
                return "redirect:/success.html";
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return "redirect:/view_car?chassisNumber=" + chassisNumber;
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Unknown Error. Try again.");
                return "redirect:/view_car?chassisNumber=" + chassisNumber;
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Those dates are not avaliable. Please choose valid dates");
            return "redirect:/view_car?chassisNumber=" + chassisNumber;
        }
    }

    /**
     * Conclude lease string.
     * @author Hasan
     *
     * @param leaseId       the lease id
     * @param chassisNumber the chassis number
     * @return the string
     */
    @PostMapping ("/conclude_lease")
    public String concludeLease(@RequestParam int leaseId, @RequestParam String chassisNumber, RedirectAttributes redirectAttributes){
        //Set lease to concluded:
        leaseService.concludeLease(leaseId);

        //Set the car to avaliable:
        String carTable = carService.getCarTable(chassisNumber);
        carService.updateCarState(chassisNumber, "AVAILABLE", carTable);

        //Set the damages to invoiced:
        damageService.setDamagesToInvoiced(leaseId, chassisNumber);

        //isActive should be set to false:
        leaseService.setLeaseInactive(leaseId);

        redirectAttributes.addAttribute("leaseId", leaseId);
        return "redirect:/display_condition_report";
    }





}
