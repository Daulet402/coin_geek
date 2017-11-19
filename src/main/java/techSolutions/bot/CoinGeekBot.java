package techSolutions.bot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import techSolutions.dto.CoinType;
import techSolutions.parser.ICoingeckoParser;
import techSolutions.utils.BotConstants;
import techSolutions.utils.CoinConstants;

import java.io.IOException;
import java.util.Objects;

@Component("coinGeekBot")
public class CoinGeekBot extends TelegramLongPollingBot {

    @Autowired
    private ICoingeckoParser parser;

    @Autowired
    private Logger log;

    @Override
    public String getBotToken() {
        return BotConstants.COIN_GEEK_BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message;
            switch (update.getMessage().getText()) {
                case BotConstants.BITCOIN_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.BITCOIN);
                    break;
                case BotConstants.ETHEREUM_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.ETHEREUM);
                    break;
                case BotConstants.BITCOIN_CASH_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.BITCOIN_CASH);
                    break;
                case BotConstants.LITECOIN_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.LITECOIN);
                    break;
                case BotConstants.ZCASH_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.ZCASH);
                    break;
                case BotConstants.DASH_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.DASH);
                    break;
                case BotConstants.MONERO_RATE_COMMAND:
                    message = getCoinRateInternal(parser, CoinType.MONERO);
                    break;
                default:
                    message = BotConstants.UNKNOWN_COMMAND_MESSAGE;
            }
            update.getMessage().getChatId();
            SendMessage messageToSend = new SendMessage();
            messageToSend.setChatId(update.getMessage().getChatId());
            log.info(String.format("Request: %s From: %s, Response message: %s",
                    update.getMessage().getText(),
                    update.getMessage().getFrom(),
                    message));
            messageToSend.setText(Objects.nonNull(message) ? message : BotConstants.ERROR_MESSAGE);
            this.sendMessage(messageToSend);
            log.info("Message sent successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String getCoinRateInternal(ICoingeckoParser parser, CoinType coinType) throws IOException {
        return parser.getCoinRateByCoinType(coinType);
    }

    @Override
    public String getBotUsername() {
        return BotConstants.COIN_GEEK_BOT_USERNAME;
    }
}