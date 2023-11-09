package am.hitech.controller;

import am.hitech.model.Check;
import am.hitech.model.User;
import am.hitech.service.CheckService;
import am.hitech.service.MonthsService;
import am.hitech.service.UserService;
import am.hitech.util.exception.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    CheckService checkService;

    @Autowired
    UserService userService;

    @Autowired
    MonthsService monthsService;

    @PutMapping
    public ResponseEntity<?> checks(Authentication auth) throws AccessDeniedException {
        User user = userService.getByUserName(auth.getName());

        if (!checkService.existByDateAndUserId(currentDate(), user.getId())){
            checkService.create(user.getId(), currentDate(), System.currentTimeMillis());
            return ResponseEntity.ok().build();
        }
        Check check = checkService.find(user.getId(), currentDate());

        if (check.getCheck() >= 8){
            return new ResponseEntity<>("You can't check more than 8 times in a day",HttpStatus.BAD_REQUEST);
        }
        if (System.currentTimeMillis() - check.getMilis() < 10000){
            return new ResponseEntity<>("You have to wait 50 minutes before another check", HttpStatus.BAD_REQUEST);
        }

        checkService.check(check.getId());
        checkService.date(currentDate(), check.getId());
        checkService.milliseconds(System.currentTimeMillis(), check.getId());

        int month = Integer.parseInt(currentDate().substring(5,7));
        System.out.println(month);
        monthsService.checksAdd(month, user.getId());

        return ResponseEntity.ok().build();
    }


    private String currentDate(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = form.format(date);

        return formattedDate;
    }
}
