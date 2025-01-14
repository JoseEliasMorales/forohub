package com.challengue.forohub.infra.settings;

import org.flywaydb.core.Flyway;

public class FlywayRepair {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mariadb://localhost/forohub", "root", "chester")
                .load();

        // Ejecuta repair
        flyway.repair();
        System.out.println("Repair ejecutado con éxito.");
    }
}
