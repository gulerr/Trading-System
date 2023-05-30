package util;

import entity.Coin;
import entity.Trade;
import entity.Wallet;

public class TradeDTOConverter {
    public static void performTrade(Trade trade) {
        Wallet wallet = trade.getWallet();
        wallet.setAmount(wallet.getAmount()-trade.getAmount());
        Coin coin = trade.getCoin();
        coin.setAmount(coin.getAmount() - trade.getAmount());
        System.out.println("Trade performed succesfully.");
    }
    public static double calculateProfit(Trade trade) {
        Wallet wallet = trade.getWallet();
        Coin coin = trade.getCoin();
        double purchasePrice= trade.getPrice();
        double currentPrice= coin.getPrice();
        double amount= trade.getAmount();
        double profit=(currentPrice-purchasePrice)*amount;
        return profit;
    }
}
