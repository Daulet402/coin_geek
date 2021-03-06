package techSolutions.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:bot.properties")
@Data
public class BotConstants {

    @Value("${bot.unknownCommand.message}")
    private String botUnknownCommand;

    @Value("${bot.error.message}")
    private String errorMessage;

    /**
     * All constants related to CoinGeekBot
     */

    public static final String COIN_GEEK_BOT_USERNAME = "CoinGeekBot";
    public static final String BITCOIN_RATE_COMMAND = "/btc";
    public static final String ETHEREUM_RATE_COMMAND = "/eth";
    public static final String BITCOIN_CASH_RATE_COMMAND = "/bch";
    public static final String ZCASH_RATE_COMMAND = "/zec";
    public static final String DASH_RATE_COMMAND = "/dash";
    public static final String LITECOIN_RATE_COMMAND = "/ltc";
    public static final String MONERO_RATE_COMMAND = "/xmr";
    public static final String CACHE_COMMAND = "cache";
}