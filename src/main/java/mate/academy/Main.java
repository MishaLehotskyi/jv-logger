package mate.academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mate.academy.exception.AuthenticationException;
import mate.academy.model.User;
import mate.academy.service.AuthenticationService;
import mate.academy.service.AuthenticationServiceImpl;
import mate.academy.service.OrderService;
import mate.academy.service.OrderServiceImpl;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started");

        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        User user;

        try {
            logger.info("Attempting to authenticate user: bob");
            user = authenticationService.login("bob", "1234");
            logger.info("User authenticated successfully: {}", user.getLogin());
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: bob", e);
            return;
        }

        logger.info("Proceeding to order completion for userId={}", user.getUserId());
        OrderService orderService = new OrderServiceImpl();
        orderService.completeOrder(user.getUserId());

        logger.info("Application finished successfully");
    }
}
