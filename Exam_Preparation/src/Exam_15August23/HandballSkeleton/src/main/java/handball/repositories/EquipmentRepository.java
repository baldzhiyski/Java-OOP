package Exam_15August23.HandballSkeleton.src.main.java.handball.repositories;

import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EquipmentRepository implements Repository{
    private Collection<Equipment> equipments;

    public EquipmentRepository() {
        this.equipments = new ArrayList<>();
    }

    @Override
    public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        for (Equipment equipment1 : equipments) {
            if(equipment1.equals(equipment)){
                equipments.remove(equipment);
                return true;
            }
        }
        return false;
    }

    @Override
    public Equipment findByType(String type) {
        Optional<Equipment> optionalEquipment = equipments.stream().filter(equipment -> equipment.getClass().getSimpleName().equals(type)).findFirst();

        return optionalEquipment.orElse(null);
    }
}
