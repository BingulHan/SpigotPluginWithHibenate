package tech.bingulhan.spigotpluginwithhibenate.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tech.bingulhan.spigotpluginwithhibenate.AccountManager;
import tech.bingulhan.spigotpluginwithhibenate.PlayerAccount;
import tech.bingulhan.spigotpluginwithhibenate.SpigotPluginWithHibenate;

import java.util.Optional;

public class PlayerListeners implements Listener {

    private SpigotPluginWithHibenate plugin;
    public PlayerListeners(SpigotPluginWithHibenate intance) {
        this.plugin = intance;
    }
    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {
        PlayerAccount account = plugin.getAccountController().getAccount(event.getPlayer());
        if (account == null) {
            account = plugin.getAccountController().createAccount(event.getPlayer());
        }
        plugin.getAccounts().add(account);
        event.getPlayer().sendMessage(ChatColor.GREEN+"Coin: "+account.getCoinAmount());
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event) {
        Optional<PlayerAccount> playerAccountOptional = plugin.getAccount(event.getPlayer().getName());

        playerAccountOptional.ifPresent(account ->  {
            plugin.getAccountController().updateAccount(account);
            plugin.getAccounts().remove(account);
        });

    }

    @EventHandler
    public void onDeathEntity(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Optional<PlayerAccount> playerAccountOptional = plugin.getAccount(event.getEntity().getKiller().getName());

            playerAccountOptional.ifPresent(account -> {
                new AccountManager().addCoin(account, 15);
                event.getEntity().getKiller().sendMessage(ChatColor.YELLOW+"+15 Coin");
            });
        }
    }

}
