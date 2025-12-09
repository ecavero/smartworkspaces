package pe.isil.smartworkspaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pe.isil.smartworkspaces.controllers.ReservaControlador;
import pe.isil.smartworkspaces.models.Reserva;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SmartworkspacesApplicationTests {
   @Autowired
   ReservaControlador reservaControlador;

	@Test
	void cruceDeHorariosTest() {
      List<Reserva> reservas = List.of(
            Reserva.builder()
               .fecha(LocalDate.of(2025, 12, 9))
               .horaInicio(LocalTime.of(13,0))
               .horaFin(LocalTime.of(14,0))
               .build(),
            Reserva.builder()
               .fecha(LocalDate.of(2025, 12, 10))
               .horaInicio(LocalTime.of(12,30))
               .horaFin(LocalTime.of(14,30))
               .build()
         );
      assertFalse(reservaControlador.isCruce(reservas.get(0), reservas.get(1)));
	}

}
