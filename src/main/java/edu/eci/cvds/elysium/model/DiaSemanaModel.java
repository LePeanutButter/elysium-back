package edu.eci.cvds.elysium.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Enum representing the days of the week with associated schedules.
 * Each day has a predefined schedule stored in a TreeMap.
 */
public enum DiaSemanaModel {
    /**
     * Monday with predefined schedule.
     */
    LUNES(crearHorariosEntreSemana("LUNES")),
    
    /**
     * Tuesday with predefined schedule.
     */
    MARTES(crearHorariosEntreSemana("MARTES")),
    
    /**
     * Wednesday with predefined schedule.
     */
    MIERCOLES(crearHorariosEntreSemana("MIERCOLES")),
    
    /**
     * Thursday with predefined schedule.
     */
    JUEVES(crearHorariosEntreSemana("JUEVES")),
    
    /**
     * Friday with predefined schedule.
     */
    VIERNES(crearHorariosEntreSemana("VIERNES")),
    
    /**
     * Saturday with predefined schedule.
     */
    SABADO(crearHorariosSabado("SABADO"));

    /**
     * Attribute that stores the TreeMap with the schedule information for each day.
     */
    private final TreeMap<String, List<Double>> horarios;

    /**
     * Constructor for the enum.
     *
     * @param horarios TreeMap containing the schedule information for the day.
     */
    DiaSemanaModel(TreeMap<String, List<Double>> horarios) {
        this.horarios = horarios;
    }

    /**
     * Creates a TreeMap with the schedule for weekdays.
     *
     * @param dia The day of the week.
     * @return TreeMap with the schedule for the specified weekday.
     */
    private static TreeMap<String, List<Double>> crearHorariosEntreSemana(String dia) {
        TreeMap<String, List<Double>> map = new TreeMap<>();
        List<Double> horas = List.of(7.0, 8.5, 10.0, 11.5, 13.0, 14.5, 16.0, 17.5, 19.0);
        map.put("horas", horas);
        return new TreeMap<>(map);
    }

    /**
     * Creates a TreeMap with the schedule for Saturday.
     *
     * @param dia The day of the week.
     * @return TreeMap with the schedule for Saturday.
     */
    private static TreeMap<String, List<Double>> crearHorariosSabado(String dia) {
        TreeMap<String, List<Double>> map = new TreeMap<>();
        List<Double> horas = List.of(7.0, 8.5, 10.0, 11.5, 13.0);
        map.put("horas", horas);
        return new TreeMap<>(map);
    }
}
