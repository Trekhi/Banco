package unide.usb.banco.dto;

import unide.usb.banco.domain.Cuenta;

import java.math.BigDecimal;
import java.time.Instant;

public class TransaccionDTO {
    private Integer id;
    private Integer cuentaid;
    private BigDecimal consignacion;
    private Instant fechaEnvio;

}
