package solidLab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface Account {

    boolean getRequireUniqueEmail();

    int getMinRequiredPasswordLength();

    int getMaxRequiredPasswordLength();

    void register(User user);

    void login(String username, String password);
}
