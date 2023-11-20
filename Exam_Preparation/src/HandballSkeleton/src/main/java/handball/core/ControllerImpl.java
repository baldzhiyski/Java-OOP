
package HandballSkeleton.src.main.java.handball.core;

import HandballSkeleton.src.main.java.handball.common.ExceptionMessages;
import HandballSkeleton.src.main.java.handball.entities.equipment.ElbowPad;
import HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;
import HandballSkeleton.src.main.java.handball.entities.equipment.Kneepad;
import HandballSkeleton.src.main.java.handball.entities.gameplay.Gameplay;
import HandballSkeleton.src.main.java.handball.entities.gameplay.Indoor;
import HandballSkeleton.src.main.java.handball.entities.gameplay.Outdoor;
import HandballSkeleton.src.main.java.handball.entities.team.Bulgaria;
import HandballSkeleton.src.main.java.handball.entities.team.Germany;
import HandballSkeleton.src.main.java.handball.entities.team.Team;
import HandballSkeleton.src.main.java.handball.repositories.EquipmentRepository;
import HandballSkeleton.src.main.java.handball.repositories.Repository;

import java.util.*;

import static HandballSkeleton.src.main.java.handball.common.ConstantMessages.GAMEPLAY_NOT_SUITABLE;
import static HandballSkeleton.src.main.java.handball.common.ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY;
import static HandballSkeleton.src.main.java.handball.common.ExceptionMessages.INVALID_TEAM_TYPE;

public  class ControllerImpl implements Controller{
    private Repository equipment;
    private Map<String,Gameplay> controllers;

    public ControllerImpl(){
        this.controllers= new LinkedHashMap<>();
        this.equipment=new EquipmentRepository();
    }
    public Equipment initialise(String equipmentType){
        Equipment equipment;
        if(equipmentType.equals("Kneepad")){
            equipment = new Kneepad();
        }else{
            equipment= new ElbowPad();
        }
        return equipment;
    }
    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        if(!gameplayType.equals("Outdoor") && !gameplayType.equals("Indoor")){
            throw new NullPointerException(ExceptionMessages.INVALID_GAMEPLAY_TYPE);
        }
        Gameplay gameplay;
        if(gameplayType.equals("Indoor")){
            gameplay = new Indoor(gameplayName);
        }else{
            gameplay= new Outdoor(gameplayName);
        }
        this.controllers.put(gameplayName,gameplay);
        return String.format("Successfully added %s.",gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        if(!equipmentType.equals("Kneepad") && !equipmentType.equals("ElbowPad")){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_EQUIPMENT_TYPE);
        }
        Equipment equipment = initialise(equipmentType);
        getEquipment().add(equipment);
        return String.format("Successfully added %s.",equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        if(!equipmentType.equals("Kneepad") && !equipmentType.equals("ElbowPad")){
            throw new IllegalArgumentException(String.format("There isn't an equipment of type %s.",equipmentType));
        }
        Equipment equipment= initialise(equipmentType);
        Gameplay gameplay = this.controllers.get(gameplayName);
        gameplay.addEquipment(equipment);
        getEquipment().remove(equipment);
        return String.format("Successfully added %s to %s.",equipmentType,gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Team currentTeam;
        switch (teamType) {
            case "Bulgaria":
                currentTeam = new Bulgaria(teamName, country, advantage);
                break;
            case "Germany":
                currentTeam = new Germany(teamName, country, advantage);
                break;
            default: throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }
        Gameplay gameplay = controllers.get(gameplayName);

        boolean isSuitable = gameplay.getClass().getSimpleName().equals("Outdoor") && teamType.equals("Bulgaria") ||
                gameplay.getClass().getSimpleName().equals("Indoor") && teamType.equals("Germany");

        if (!isSuitable) {
            return GAMEPLAY_NOT_SUITABLE;
        }
        this.controllers.get(gameplayName).addTeam(currentTeam);
        return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = this.controllers.get(gameplayName);
        int countTeams = gameplay.getTeam().size();
        gameplay.getTeam().forEach(Team::play);
        return "Teams that have played: " + countTeams;
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay1 = controllers.get(gameplayName);
        int advantage= gameplay1.getTeam().stream().mapToInt(Team::getAdvantage).sum();
        return String.format("The advantage of gameplay %s is %d.",gameplayName,advantage);
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        getControllers().entrySet().stream().forEach(gameplay -> result.append(gameplay.getValue().toString()).append(System.lineSeparator()));
        return result.toString();
    }

    public Repository getEquipment() {
        return equipment;
    }

    public Map<String, Gameplay> getControllers() {
        return controllers;
    }
}
