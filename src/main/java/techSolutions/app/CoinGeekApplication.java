package techSolutions.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import techSolutions.service.CoinGeekTelegramBot;

@SpringBootApplication(scanBasePackages = "techSolutions")
public class CoinGeekApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CoinGeekApplication.class, args);
        CoinGeekTelegramBot coinGeekTelegramBot = applicationContext.getBean(CoinGeekTelegramBot.class);
        coinGeekTelegramBot.run();
    }
}