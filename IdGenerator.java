package co.edu.elbosque.procureit.util;

import java.time.Year;
import java.util.UUID;

public class IdGenerator {
	public static String generarSolicitudId() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String prefix = uuid.substring(0, 7).toUpperCase();
		return prefix + "RQT" + Year.now().getValue();
	}

	public static String generarUUID() {
		return UUID.randomUUID().toString();
	}
}
