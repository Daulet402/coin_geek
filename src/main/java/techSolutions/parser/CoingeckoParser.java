package techSolutions.parser;

import lombok.Data;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import techSolutions.dto.CoinType;
import techSolutions.utils.CoinConstants;
import techSolutions.utils.Constants;

import java.io.IOException;
import java.util.Objects;

@Data
@Component
public class CoingeckoParser implements ICoingeckoParser {

    private CoinType coinType;
    private String coinRate;

    @Autowired
    private Logger log;

    @Override
    public String getCoinRateByCoinType(CoinType coinType) throws IOException {
        this.setCoinType(coinType);
        this.parse();
        return this.getCoinRate();
    }

    @Override
    public void parse() throws IOException {
        String url = CoinConstants.COIN_PAGE_URL.concat(getCoinUri(coinType));
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByAttributeValue(Constants.CLASS_ATTR_NAME, CoinConstants.COIN_VALUE_CLASS_NAME);
        if (Objects.nonNull(elements)) {
            coinRate = elements.text();
        }
    }

    private String getCoinUri(CoinType coinType) {
        switch (coinType) {
            case DASH:
                return CoinConstants.DASH_URI;
            case ZCASH:
                return CoinConstants.ZCASH_URI;
            case MONERO:
                return CoinConstants.MONERO_URI;
            case BITCOIN:
                return CoinConstants.BITCOIN_URI;
            case ETHEREUM:
                return CoinConstants.ETHEREUM_URI;
            case LITECOIN:
                return CoinConstants.LITECOIN_URI;
            case BITCOIN_CASH:
                return CoinConstants.BITCOIN_CASH_URI;
            default:
                return null;
        }
    }
}