package solidLab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface User {
    String getEmail();
    void setPassword(String password);
    String getPasswordHash();
}
