package Exam_15August23.HandballSkeleton.src.main.java.handball.entities.gameplay;

import Exam_15August23.HandballSkeleton.src.main.java.handball.common.ExceptionMessages;
import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;
import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseGameplay implements Gameplay{
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private List<Team> teams;

    public BaseGameplay(String name, int capacity) {
       setName(name);
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void setName(String name) {
        if(name.isBlank()){
            throw new NullPointerException(ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<Team> getTeam() {
        return teams;
    }

    @Override
    public int allProtection() {
        return equipments.stream().mapToInt(Equipment::getProtection).sum();
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        teams.forEach(Team::play);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return equipments;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(" ").append(getClass().getSimpleName());
        String team = teams.isEmpty() ? "none" : teams.stream().map(Team::getName)
                .collect(Collectors.joining(" "));
        result.append(System.lineSeparator());
        result.append("Team: ").append(team);
        result.append(System.lineSeparator()).append("Equipment: ").append(equipments.size());
        result.append(", Protection: ").append(allProtection());

        return result.toString();
    }
}
