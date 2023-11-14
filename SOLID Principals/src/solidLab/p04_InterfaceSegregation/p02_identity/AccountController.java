package solidLab.p04_InterfaceSegregation.p02_identity;

import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.Account;
import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.User;
import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.UserRepository;

public class AccountController {
    private final Account manager;
    private final UserRepository userRepository;

    public AccountController(Account manager,UserRepository repository) {
        this.manager = manager;
        this.userRepository = repository;
    }
    public void changePassword(String id,String newPass){
        User userById = userRepository.getUserById(id);
        userById.setPassword(newPass);
        userRepository.update(userById);
    }
}
