package HandballSkeleton.src.main.java.handball.entities.gameplay;

import HandballSkeleton.src.main.java.handball.entities.team.Team;
import HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;

import java.util.Collection;

public interface Gameplay {
    int allProtection();

    void addTeam(Team team);

    void removeTeam(Team team);

    void addEquipment(Equipment equipment);

    void teamsInGameplay();

    Collection<Team> getTeam();

    Collection<Equipment> getEquipments();

    String getName();




}
