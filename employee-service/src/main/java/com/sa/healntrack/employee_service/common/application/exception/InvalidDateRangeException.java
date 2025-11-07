package com.sa.healntrack.employee_service.common.application.exception;

import java.time.LocalDate;

public class InvalidDateRangeException extends RuntimeException {

    public InvalidDateRangeException(LocalDate startDate, LocalDate endDate, String startDateName, String endDateName) {
        super(buildMessage(startDate, endDate, startDateName, endDateName));
    }

    public InvalidDateRangeException(LocalDate startDate, LocalDate endDate) {
        super(buildMessage(startDate, endDate, null, null));
    }

    private static String buildMessage(LocalDate startDate, LocalDate endDate, String startDateName,
            String endDateName) {
        String startName = startDateName != null ? startDateName : "fecha de inicio";
        String endName = endDateName != null ? endDateName : "fecha de fin";

        if (startDate == null && endDate == null) {
            return "El rango de fechas es inválido: ambas fechas son nulas";
        } else if (startDate == null) {
            return "El rango de fechas es inválido: " + startName + " es nula y " + endName + " es " + endDate;
        } else if (endDate == null) {
            return "El rango de fechas es inválido: " + startName + " es " + startDate + " y " + endName + " es nula";
        } else if (startDate.isAfter(endDate)) {
            return startName + " " + startDate + " no puede ser posterior a " + endName + " " + endDate;
        } else if (endDate.isBefore(startDate)) {
            return endName + " " + endDate + " no puede ser anterior a " + startName + " " + startDate;
        } else {
            return "Rango de fechas inválido: " + startName + " = " + startDate + ", " + endName + " = " + endDate;
        }
    }
}
