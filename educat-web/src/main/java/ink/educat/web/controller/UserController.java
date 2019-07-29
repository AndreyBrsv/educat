package ink.educat.web.controller;

//@Controller
public class UserController {

    /*private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    String signInPost(final HttpServletRequest request) {
        try {
            final User user = userService.findUserByEmail(request.getParameter("email"));
            if (user.getPassword().equals(request.getParameter("password"))) {
                final ShortDetailedUser shortDetailedUser = userService
                        .getShortDetailedUserById(user.getId());
                return shortDetailedUser.toString();
            } else {
                return "Try again";
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseBody
    String signUpPost(final HttpServletRequest request) {
        try {
            final User user = userService.findUserByEmail(request.getParameter("email"));
            return "Пользователь с таким email уже зарегестрирован!";
        } catch (final Exception ex) {
            User user = new User(
                    0 ,
                    request.getParameter("email"),
                    false,
                    request.getParameter("password"),
                    request.getParameter("first-name"),
                    request.getParameter("last-name"),
                    UserStatus.ACTIVE,
                    UserRole.USER,
                    LocalDateTime.now());

            userService.registrationUser(user);
            return "Пользователь успешно зарегестрировался!";
        }
    }*/
}
