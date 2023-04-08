package tech.bingulhan.spigotpluginwithhibenate;

import org.bukkit.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AccountController {

    private SessionFactory sessionFactory;
    public AccountController(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PlayerAccount createAccount(Player player) {
        PlayerAccount account = new PlayerAccount(player.getName(), 0);

        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();


        return account;
    }

    public PlayerAccount getAccount(Player player) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        PlayerAccount account = session.get(PlayerAccount.class, player.getName());
        session.getTransaction().commit();
        session.close();
        return account;
    }

    public void updateAccount(PlayerAccount account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.update(account);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAccount(PlayerAccount account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.delete(account);
        session.getTransaction().commit();
        session.close();
    }



}
