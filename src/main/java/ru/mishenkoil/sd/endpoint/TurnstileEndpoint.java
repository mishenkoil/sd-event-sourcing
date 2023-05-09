package ru.mishenkoil.sd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.mishenkoil.sd.model.command.EnterIfAvailableCommand;
import ru.mishenkoil.sd.model.command.ExitCommand;
import ru.mishenkoil.sd.service.command.TurnstileCommandHandler;

@RestController
@RequestMapping("/turnstile")
public class TurnstileEndpoint {

    private final TurnstileCommandHandler turnstileCommandHandler;

    @Autowired
    public TurnstileEndpoint(TurnstileCommandHandler turnstileCommandHandler) {
        this.turnstileCommandHandler = turnstileCommandHandler;
    }

    @PostMapping("/enter")
    public void enterIfAvailable(
            @RequestParam long id
    ) {
        // если нет ошибок == можно проходить
        turnstileCommandHandler.enterIfAvailable(
                new EnterIfAvailableCommand(id)
        );
    }

    @RequestMapping("/exit")
    public void exit(
            @RequestParam long id
    ) {
        turnstileCommandHandler.exit(new ExitCommand(id));
    }
}
