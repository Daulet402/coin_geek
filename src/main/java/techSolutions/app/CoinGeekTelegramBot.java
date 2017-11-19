package techSolutions.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
@Scope("singleton")
public class CoinGeekTelegramBot {

    @Autowired
    private Logger log;

    @Autowired
    private TelegramBotsApi telegramBotsApi;

    @Autowired
    @Qualifier("coinGeekBot")
    private TelegramLongPollingBot telegramLongPollingBot;


    public void run() {
        try {
            telegramBotsApi.registerBot(telegramLongPollingBot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}