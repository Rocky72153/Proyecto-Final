package co.edu.elbosque.procureit.config;

import co.edu.elbosque.procureit.entity.CatalogoItem;
import co.edu.elbosque.procureit.entity.Empleado;
import co.edu.elbosque.procureit.entity.RolSistema;
import co.edu.elbosque.procureit.entity.Sistema;
import co.edu.elbosque.procureit.entity.Solicitud;
import co.edu.elbosque.procureit.repository.AprobacionRepository;
import co.edu.elbosque.procureit.repository.CatalogoItemRepository;
import co.edu.elbosque.procureit.repository.EmpleadoRepository;
import co.edu.elbosque.procureit.repository.RolSistemaRepository;
import co.edu.elbosque.procureit.repository.SistemaRepository;
import co.edu.elbosque.procureit.repository.SolicitudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DemoDataLoader {
	@Bean
	CommandLineRunner initData(EmpleadoRepository empRepo, CatalogoItemRepository catRepo, SistemaRepository sisRepo,
			RolSistemaRepository rolRepo, SolicitudRepository solRepo, AprobacionRepository aprRepo) {
		return args -> {
			if (empRepo.count() == 0) {
				Empleado admin = new Empleado();
				admin.setUsername("u_admin");
				admin.setNombre("Admin");
				admin.setEmail("admin@example.com");
				admin.setRolApp(Empleado.RolApp.ADMIN);
				empRepo.save(admin);

				Empleado ti = new Empleado();
				ti.setUsername("u_ti");
				ti.setNombre("Equipo TI");
				ti.setEmail("ti@example.com");
				ti.setRolApp(Empleado.RolApp.TI);
				empRepo.save(ti);

				Empleado gerente = new Empleado();
				gerente.setUsername("u_ger");
				gerente.setNombre("Gerente");
				gerente.setEmail("gerente@example.com");
				gerente.setRolApp(Empleado.RolApp.GERENTE);
				empRepo.save(gerente);

				Empleado director = new Empleado();
				director.setUsername("u_dir");
				director.setNombre("Director");
				director.setEmail("director@example.com");
				director.setRolApp(Empleado.RolApp.DIRECTOR);
				empRepo.save(director);

				Empleado sol = new Empleado();
				sol.setUsername("u_sol");
				sol.setNombre("Solicitante");
				sol.setEmail("sol@example.com");
				sol.setRolApp(Empleado.RolApp.SOLICITANTE);
				sol.setGerente(gerente);
				sol.setDirector(director);
				empRepo.save(sol);
			}

			if (catRepo.count() == 0) {
				CatalogoItem lap = new CatalogoItem();
				lap.setTipo(CatalogoItem.Tipo.HARDWARE);
				lap.setNombre("Laptop i5 16GB/512");
				lap.setSku("HW-LAP-001");
				lap.setCostoEstimado(new BigDecimal("850"));
				lap.setProveedor("PCDistrib");
				lap.setVigenteDesde(LocalDate.now().minusMonths(2));
				lap.setActivo(true);
				catRepo.save(lap);

				CatalogoItem lic = new CatalogoItem();
				lic.setTipo(CatalogoItem.Tipo.SOFTWARE);
				lic.setNombre("Licencia Office 365 E3");
				lic.setSku("SW-OFF-365");
				lic.setCostoEstimado(new BigDecimal("120"));
				lic.setProveedor("Microsoft");
				lic.setVigenteDesde(LocalDate.now().minusMonths(2));
				lic.setActivo(true);
				catRepo.save(lic);
			}

			if (sisRepo.count() == 0) {
				Sistema s = new Sistema();
				s.setNombre("ERP-NOVA");
				s.setActivo(true);
				sisRepo.save(s);

				RolSistema r1 = new RolSistema();
				r1.setSistema(s);
				r1.setNombre("Analista");
				r1.setActivo(true);
				rolRepo.save(r1);

				RolSistema r2 = new RolSistema();
				r2.setSistema(s);
				r2.setNombre("Administrador");
				r2.setActivo(true);
				rolRepo.save(r2);
			}

			if (solRepo.count() == 0) {
				Empleado solicitante = empRepo.findByUsername("u_sol").orElseThrow();
				CatalogoItem cat0 = catRepo.findAll().get(0);

				Solicitud shw = new Solicitud();
				shw.setSolicitudId(co.edu.elbosque.procureit.util.IdGenerator.generarSolicitudId());
				shw.setCodigoSeguimiento(co.edu.elbosque.procureit.util.IdGenerator.generarUUID());
				shw.setTipo(Solicitud.Tipo.HARDWARE);
				shw.setPrioridad(Solicitud.Prioridad.ALTA);
				shw.setEstado(Solicitud.Estado.NUEVA);
				shw.setCostoEstimado(new BigDecimal("850"));
				shw.setFechaRequerida(LocalDate.now().plusDays(10));
				shw.setFechaCreacion(LocalDateTime.now().minusDays(1));
				shw.setFechaActualizacion(LocalDateTime.now().minusHours(12));
				shw.setJustificacion("Renovación de equipo por bajo rendimiento.");
				shw.setSolicitante(solicitante);
				shw.setCatalogoItem(cat0);
				solRepo.save(shw);

				CatalogoItem cat1 = catRepo.findAll().get(1);

				Solicitud ssw = new Solicitud();
				ssw.setSolicitudId(co.edu.elbosque.procureit.util.IdGenerator.generarSolicitudId());
				ssw.setCodigoSeguimiento(co.edu.elbosque.procureit.util.IdGenerator.generarUUID());
				ssw.setTipo(Solicitud.Tipo.SOFTWARE);
				ssw.setPrioridad(Solicitud.Prioridad.MEDIA);
				ssw.setEstado(Solicitud.Estado.PENDIENTE_APROBACION);
				ssw.setCostoEstimado(new BigDecimal("120"));
				ssw.setFechaRequerida(LocalDate.now().plusDays(5));
				ssw.setFechaCreacion(LocalDateTime.now().minusDays(3));
				ssw.setFechaActualizacion(LocalDateTime.now().minusDays(2));
				ssw.setJustificacion("Licencia para nuevo colaborador.");
				ssw.setSolicitante(solicitante);
				ssw.setCatalogoItem(cat1);
				solRepo.save(ssw);
			}
		};
	}
}
