package Exam_8April23.RobotServiceApp.src.main.java.robotService.repositories;

import Exam_8April23.RobotServiceApp.src.main.java.robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepository implements Repository{
    private Collection<Supplement> supplements;

    public SupplementRepository() {
        this.supplements=new ArrayList<>();
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public boolean removeSupplement(Supplement supplement) {
        for (Supplement supplement1 : supplements) {
            if(supplement1.equals(supplement)){
                supplements.remove(supplement);
                return true;
            }
        }
        return false;
    }

    @Override
    public Supplement findFirst(String type) {
        return supplements.stream()
                .filter(s->s.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
