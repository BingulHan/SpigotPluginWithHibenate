package tech.bingulhan.spigotpluginwithhibenate;

public class AccountManager {

    public void addCoin(PlayerAccount account, int coin) {
        account.setCoinAmount(account.getCoinAmount()+coin);
    }
    public boolean takeCoin(PlayerAccount account, int coin) {
        if (account.getCoinAmount()>=coin) {
            account.setCoinAmount(account.getCoinAmount()-coin);
            return true;
        }

        return false;
    }
}
