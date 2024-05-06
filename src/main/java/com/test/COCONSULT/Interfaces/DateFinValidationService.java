package com.test.COCONSULT.Interfaces;

import java.time.LocalDate;


public interface DateFinValidationService {
    boolean isDateFinSuperieure(LocalDate  dateDebut, LocalDate dateFin);

    }
