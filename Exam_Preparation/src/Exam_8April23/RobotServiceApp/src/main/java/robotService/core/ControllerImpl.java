package Exam_8April23.RobotServiceApp.src.main.java.robotService.core;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.common.ConstantMessages;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot.FemaleRobot;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.repositories.SupplementRepository;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.common.ExceptionMessages;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot.MaleRobot;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.robot.Robot;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.services.MainService;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.services.SecondaryService;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.services.Service;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.MetalArmor;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.PlasticArmor;
import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.Supplement;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private SupplementRepository supplementRepository;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplementRepository =new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type){
            case "MainService":
                service= new MainService(name);
                break;
            case "SecondaryService":
                service= new SecondaryService(name);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        services.put(name,service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE,type);

    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type){
            case "PlasticArmor":
                supplement= new PlasticArmor();
                break;
            case "MetalArmor":
                supplement=new MetalArmor();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplementRepository.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement= supplementRepository.findFirst(supplementType);
        if(supplement==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,supplementType));
        }

        Service service = this.services.get(serviceName);
        service.addSupplement(supplement);
        this.supplementRepository.removeSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE,supplementType,serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price){
        Robot robot;
        Service service = services.get(serviceName);
        switch (robotType){
            case "MaleRobot":
                robot= new MaleRobot(robotName,robotKind,price);
                break;
            case "FemaleRobot":
                robot=new FemaleRobot(robotName,robotKind,price);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }
        if((robotType.equals("FemaleRobot") && service.getClass().getSimpleName().equals("MainService"))
            || (robotType.equals("MaleRobot") && service.getClass().getSimpleName().equals("SecondaryService"))){
            return ConstantMessages.UNSUITABLE_SERVICE;
        }
        services.get(serviceName).addRobot(robot);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE,robotType,serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        int size = services.get(serviceName).getRobots().size();
        services.get(serviceName).getRobots().forEach(Robot::eating);
        return String.format(ConstantMessages.FEEDING_ROBOT,size);
    }

    @Override
    public String sumOfAll(String serviceName) {
        double totalValue = 0;
        totalValue+=services.get(serviceName).getSupplements().stream().mapToDouble(Supplement::getPrice).sum();
        totalValue+=services.get(serviceName).getRobots().stream().mapToDouble(Robot::getPrice).sum();
        return String.format(ConstantMessages.VALUE_SERVICE,serviceName
        ,totalValue);
    }

    @Override
    public String getStatistics() {
        return services.values()
                .stream()
                .map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
