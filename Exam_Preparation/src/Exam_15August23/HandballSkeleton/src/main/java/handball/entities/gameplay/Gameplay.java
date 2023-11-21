package Exam_15August23.HandballSkeleton.src.main.java.handball.entities.gameplay;

import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;
import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.team.Team;

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
