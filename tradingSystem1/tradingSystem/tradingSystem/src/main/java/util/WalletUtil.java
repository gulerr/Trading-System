package util;

import entity.Wallet;

public class WalletUtil {
    public static void updateWalletBalance(Wallet wallet, double amount) {
        wallet.setAmount(wallet.getAmount() + amount);
    }
    public static boolean hasSufficientBalance(Wallet wallet, double amount){
        return wallet.getAmount()>=amount;
}
}
