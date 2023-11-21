package Exam_15August23.HandballSkeleton.src.main.java.handball.entities.team;

import Exam_15August23.HandballSkeleton.src.main.java.handball.common.ExceptionMessages;

public abstract class BaseTeam  implements Team{
    private String name;
    private String country;
    private int advantage;

    public BaseTeam(String name, String country, int advantage){
        setName(name);
        setCountry(country);
        setAdvantage(advantage);
    }

    @Override
    public void setName(String name) {
        if(name.isBlank()){
            throw new NullPointerException(ExceptionMessages.TEAM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setAdvantage(int advantage) {
        if(advantage<0){
            throw new IllegalArgumentException(ExceptionMessages.TEAM_ADVANTAGE_BELOW_OR_EQUAL_ZERO);
        }
        this.advantage = advantage;
    }

    public void setCountry(String country) {
        if(country.isBlank()){
            throw new NullPointerException(ExceptionMessages.TEAM_COUNTRY_NULL_OR_EMPTY);
        }
        this.country = country;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAdvantage() {
        return advantage;
    }

    @Override
    public void play() {
        this.advantage+=115;
    }
}
