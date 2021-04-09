package domainapp.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/login"})
public class LoginController {

    @RequestMapping(
        produces = {"text/html"}
    )
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "redirect-immediately";
    }

}
