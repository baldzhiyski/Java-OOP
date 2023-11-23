package Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.services;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.common.ConstantMessages;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.common.ExceptionMessages;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot.Robot;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class BaseService implements Service{
    private String name;
    private int capacity;
    private Collection<Supplement>supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements= new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name.isBlank()){
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name=name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if(robots.size()==capacity){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        robots.add(robot);
    }
    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(" ").append(getClass().getSimpleName()).append(":");
        result.append(System.lineSeparator());
        result.append("Robots: ");
        String resultFromRobots = robots.isEmpty()? "none" : robots.stream().map(Robot::getName)
                .collect(Collectors.joining(" "));
        result.append(resultFromRobots).append(System.lineSeparator());
        result.append("Supplements: ").append(supplements.size());
        result.append(" Hardness: ").append(sumHardness()).append(System.lineSeparator());
        return result.toString();
    }
}
