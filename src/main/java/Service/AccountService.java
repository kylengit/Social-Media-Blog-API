package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account registerAccount(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        if (password.length() < 4) {
            return null;
        }
        if (this.accountDAO.getAccountByUsername(username) != null) {
            return null;
        }
        return this.accountDAO.insertAccount(account);
    }
    
    public Account loginAccount(Account account){
        return this.accountDAO.getAccountByUsernamePassword(account.getUsername(),account.getPassword());
    }

}
