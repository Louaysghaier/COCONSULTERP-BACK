package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Pointage;

public interface PointageServiceInterface {
    public void addPointage(Pointage pointage);
    public void deletePointage(int idPointage);

    public Pointage updatePointage(Pointage pointage);
    public Pointage getPointageById(int idPoint);
}
