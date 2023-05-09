package ru.mishenkoil.sd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.mishenkoil.sd.model.command.CreateSubscriptionCommand;
import ru.mishenkoil.sd.model.command.UpdateSubscriptionCommand;
import ru.mishenkoil.sd.model.query.GetSubscriptionInfoQuery;
import ru.mishenkoil.sd.service.command.SubscriptionCommandHandler;
import ru.mishenkoil.sd.service.query.SubscriptionsQueryHandler;

@RestController("/subscription")
public class SubscriptionEndpoint {

    private final SubscriptionCommandHandler subscriptionCommandHandler;
    private final SubscriptionsQueryHandler subscriptionsQueryHandler;

    @Autowired
    public SubscriptionEndpoint(
            SubscriptionCommandHandler subscriptionCommandHandler,
            SubscriptionsQueryHandler subscriptionsQueryHandler
    ) {
        this.subscriptionCommandHandler = subscriptionCommandHandler;
        this.subscriptionsQueryHandler = subscriptionsQueryHandler;
    }

    @GetMapping("/info")
    public String getSubscriptionInfo(
            @RequestParam long id
    ) {
        return subscriptionsQueryHandler.getSubscriptionInfo(
                new GetSubscriptionInfoQuery(id)
        );
    }

    @PostMapping("/create")
    public long createSubscription() {
        return subscriptionCommandHandler.createSubscription(
                new CreateSubscriptionCommand()
        );
    }

    @PostMapping("/update")
    public void updateSubscription(
            @RequestParam long id
    ) {
        subscriptionCommandHandler.updateSubscription(
                new UpdateSubscriptionCommand(id)
        );
    }
}
