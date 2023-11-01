package FootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int dribble;
    private int passing;
    private int shooting;

    private int sprint;

    public Player(String name, int endurance,int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setSprint(int sprint) {
        checkStat(sprint,"Sprint");
        this.sprint=sprint;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    private void setDribble(int dribble) {
        checkStat(dribble,"Dribble");
        this.dribble = dribble;
    }

    private void setEndurance(int endurance) {
        checkStat(endurance,"Endurance");
        this.endurance = endurance;
    }

    private void setPassing(int passing) {
        checkStat(passing,"Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        checkStat(shooting,"Shooting");
        this.shooting = shooting;
    }

    private void checkStat(int num,String statName){
        if (num<0 || num>100){
            throw new IllegalArgumentException(statName+ " should be between 0 and 100.");
        }
    }
    public double overallSkillLevel(){
        return (endurance+shooting+passing+dribble+sprint)*1.0/5;
    }
}

