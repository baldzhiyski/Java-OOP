package Exam_15August23.HandballSkeleton.src.main.java.handball.repositories;

import Exam_15August23.HandballSkeleton.src.main.java.handball.entities.equipment.Equipment;

public interface Repository {
    void add(Equipment equipment);
    boolean remove(Equipment equipment);
    Equipment findByType(String type);
}
