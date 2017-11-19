package techSolutions.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import techSolutions.api.CoinService;
import techSolutions.dto.CoinType;
import techSolutions.dto.exceptions.CoinException;
import techSolutions.utils.BotConstants;

@Component("coinGeekBot")
@PropertySource("classpath:secret-info.properties")
public class CoinGeekBot extends TelegramLongPollingBot {

    @Autowired
    private Logger log;

    @Autowired
    private CoinService coinService;

    @Value("${bot.token:''}")
    private String botToken;

    @Autowired
    private BotConstants botConstants;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = null;
        try {
            switch (update.getMessage().getText()) {
                case BotConstants.BITCOIN_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.BITCOIN);
                    break;
                case BotConstants.ETHEREUM_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.ETHEREUM);
                    break;
                case BotConstants.BITCOIN_CASH_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.BITCOIN_CASH);
                    break;
                case BotConstants.LITECOIN_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.LITECOIN);
                    break;
                case BotConstants.ZCASH_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.ZCASH);
                    break;
                case BotConstants.DASH_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.DASH);
                    break;
                case BotConstants.MONERO_RATE_COMMAND:
                    message = getCoinRateInternal(CoinType.MONERO);
                    break;
                case BotConstants.CACHE_COMMAND:
                    message = String.valueOf(coinService.getCache());
                    break;
                default:
                    message = botConstants.getBotUnknownCommand();
            }
            log.info(String.format("Request: %s From: %s, Response message: %s",
                    update.getMessage().getText(),
                    update.getMessage().getFrom(),
                    message));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        this.sendMessage(update, StringUtils.isNotEmpty(message) ? message : botConstants.getErrorMessage());
    }

    public void sendMessage(Update update, String message) {
        try {
            SendMessage messageToSend = new SendMessage();
            messageToSend.setChatId(update.getMessage().getChatId());
            messageToSend.setText(message);
            this.sendMessage(messageToSend);
            log.info("Message sent successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String getCoinRateInternal(CoinType coinType) throws CoinException {
        return coinService.getCoinRateByCoinType(coinType);
    }

    @Override
    public String getBotUsername() {
        return BotConstants.COIN_GEEK_BOT_USERNAME;
    }
}