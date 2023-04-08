package tech.bingulhan.spigotpluginwithhibenate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class PlayerAccount {



    @Id
    @Column(name = "name")
    private String playerName;

    @Column(name = "coin")
    private int coinAmount;

    public PlayerAccount(String playerName, int coinAmount) {
        this.playerName = playerName;
        this.coinAmount = coinAmount;
    }



    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }
}
