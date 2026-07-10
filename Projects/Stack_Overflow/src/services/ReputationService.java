package services;

import events.Observer;
import events.ReputationEvent;
import models.User;

public class ReputationService implements Observer {

    private UserService userService;

    public ReputationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onEvent(ReputationEvent event) {
        User user = userService.getUserById(event.getUserId());

        if (event.getEventType().isPositive()) {
            user.addReputation(event.getPoints());
        } else {
            user.subtractReputation(event.getPoints());
        }
    }

    private void addReputation(Long userId, Long points) {
        User user = userService.getUserById(userId);
        user.addReputation(points);
    }

    private void removeReputation(Long userId, Long points) {
        User user = userService.getUserById(userId);
        user.subtractReputation(points);
    }
}