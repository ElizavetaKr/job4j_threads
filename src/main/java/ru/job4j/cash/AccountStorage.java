package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (!(account == null)) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized boolean update(Account account) {
        if (!(account == null)) {
            accounts.replace(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> account = getById(fromId);
        if (account.isPresent()) {
            Account from = account.get();
            if (from.amount() >= amount) {
                Account to = getById(toId).orElseGet(() -> new Account(toId, 0));
                add(to);
                update(new Account(fromId, from.amount() - amount));
                update(new Account(toId, to.amount() + amount));
                return true;
            }
        }
        return false;
    }
}

