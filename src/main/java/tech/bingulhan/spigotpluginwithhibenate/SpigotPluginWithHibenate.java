package tech.bingulhan.spigotpluginwithhibenate;

import org.bukkit.plugin.java.JavaPlugin;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tech.bingulhan.spigotpluginwithhibenate.listeners.PlayerListeners;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public final class SpigotPluginWithHibenate extends JavaPlugin {


    private SessionFactory sessionFactory;

    private String HIBERNATE_CONFIG_FILE_NAME;

    private AccountController accountController;

    private HashSet<PlayerAccount> accounts;

    public Optional<PlayerAccount> getAccount(String playerName) {
        return accounts.stream().filter(account -> account.getPlayerName().equals(playerName)).findAny();
    }


    @Override
    public void onEnable() {
        HIBERNATE_CONFIG_FILE_NAME = "hibernate.cfg.xml";

        saveResource(HIBERNATE_CONFIG_FILE_NAME, false);

        this.sessionFactory = new Configuration().configure(new File(getDataFolder().getAbsolutePath()+"/"+HIBERNATE_CONFIG_FILE_NAME))
                .addAnnotatedClass(PlayerAccount.class).buildSessionFactory();

        this.accountController = new AccountController(this.sessionFactory);

        this.accounts = new HashSet<>();

        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public AccountController getAccountController() {
        return accountController;
    }

    public void setAccountController(AccountController accountController) {
        this.accountController = accountController;
    }

    public HashSet<PlayerAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashSet<PlayerAccount> accounts) {
        this.accounts = accounts;
    }

}
