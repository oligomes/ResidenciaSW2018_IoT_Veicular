package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.authentication.User;
import com.example.demo.authentication.UserService;
import com.example.demo.model.Driver;
import com.example.demo.model.Vehicle;
import com.example.demo.service.DriveService;
import com.example.demo.service.DriverService;
import com.example.demo.service.VehicleService;
/**
 * @author José Suen
 */
@Controller
public class WEBApiController {
	
	/*Autowired wires beans seamlessly*/
	
	@Autowired
	private DriveService driveService;

	@Autowired
	private VehicleService vehicleService;
	
	
	@Autowired
	private DriverService driverService;
	
	@Autowired UserService userService;
	
	//LOPOC CONTROLLER
	@RequestMapping(value={"/lopoc/index"}, method = RequestMethod.GET)
	public ModelAndView lopoc(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("lopoc/index");
		return modelAndView;
	}	
	//HOME VIEW
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	//DRIVE VIEWS
	@GetMapping("/admin/vehicle/{vin}/drive/{driveid}")
    public String vehicleDetails(Model model, @PathVariable (value = "vin") String vin, @PathVariable (value = "driveid") int driveid) {
		model.addAttribute("drive", driveService.findById(driveid).get());
		model.addAttribute("readings", driveService.findById(driveid).get().getObdreadings());
        return "admin/drivedetails";
    }

	
	//VEHICLE VIEWS
	@RequestMapping(value = "/admin/vehicle", method = RequestMethod.GET)
    public String newVehicle(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		model.addAttribute("savedvehicles", vehicleService.getAllVehicles());
        return "admin/vehicles";
    }
	
	 @RequestMapping(value = "/admin/vehicle", method = RequestMethod.POST) 
	  public String createNewVehicle(@ModelAttribute Vehicle vehicle) { 
	        vehicleService.createNewVehicle(vehicle); 
	        return "redirect:/admin/vehicle";
	  } 
	
	@RequestMapping(value = "/admin/vehicle/{vin}", method = RequestMethod.GET)
    public String vehicleDetails(Model model, @PathVariable (value = "vin") String vin) {
		model.addAttribute("vehicle", vehicleService.findByVin(vin));
		model.addAttribute("drives", vehicleService.getAllDrives(vin));
        return "admin/vehicledetails";
    }
	
	//DRIVER VIEWS
	@RequestMapping(value = "/admin/driver", method = RequestMethod.GET)
    public String newDriver(Model model) {
		model.addAttribute("driver", new Driver());
		model.addAttribute("saveddrivers", driverService.getAllDrivers());
        return "admin/driver";
    }
	
	@RequestMapping(value = "/admin/driver", method = RequestMethod.POST)
	public String createNewDriver(@ModelAttribute Driver driver) {
        driverService.createNewDriver(driver);
        return "redirect:/admin/driver";
        
    }
	
	//AUTHENTICATION
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(Model model){
		
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		
		//Create modelandview object to pass both model and view :)
		ModelAndView modelAndView = new ModelAndView();
		
		//Verify if already exists
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		//If everything is ok, then create new user and return to registration page with success message
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home2(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
}
