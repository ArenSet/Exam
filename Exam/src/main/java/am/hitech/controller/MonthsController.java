package am.hitech.controller;

import am.hitech.model.User;
import am.hitech.service.MonthsService;
import am.hitech.service.UserService;
import am.hitech.util.exception.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthsController {

    @Autowired
    private MonthsService monthsService;

    @Autowired
    private UserService userService;


    @GetMapping("/month-salary")
    public ResponseEntity<?> monthSalary(Authentication auth, @RequestParam int month){

        User user = userService.getByUserName(auth.getName());

        Integer hourSalary = (user.getSalary()/168);

        if (month <= 12 && month >=1){
            Integer monthSalary = (monthsService.checksCount(month, user.getId())) * hourSalary;
            return ResponseEntity.ok(monthSalary);
        }
        else return new ResponseEntity<>("How have to write month form 1 to 12", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/quarter-salary")
    public ResponseEntity<?> quarterSalary(Authentication auth, @RequestParam int quarter) throws AccessDeniedException {
        User user = userService.getByUserName(auth.getName());

        Integer hourSalary = (user.getSalary()/168);

        if (quarter == 1){
            Integer quarterSalary = (monthsService.quarterCount(1,2,3, user.getId())) * hourSalary;
            return ResponseEntity.ok(quarterSalary);
        }

        if (quarter == 2){
            Integer quarterSalary = (monthsService.quarterCount(4,5,6, user.getId())) * hourSalary;
            return ResponseEntity.ok(quarterSalary);
        }
        if (quarter == 3){
            Integer quarterSalary = (monthsService.quarterCount(7,8,9, user.getId())) * hourSalary;
            return ResponseEntity.ok(quarterSalary);
        }
        if (quarter == 4){
            Integer quarterSalary = (monthsService.quarterCount(10,11,12, user.getId())) * hourSalary;
            return ResponseEntity.ok(quarterSalary);
        }
        else return new ResponseEntity<>("We have only 4 quarters n a year",HttpStatus.NOT_FOUND);
    }
}
