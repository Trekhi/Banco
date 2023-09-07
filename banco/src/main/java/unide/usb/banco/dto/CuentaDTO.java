package unide.usb.banco.dto;

import unide.usb.banco.domain.Usuario;

import java.math.BigDecimal;
import java.time.Instant;

public class CuentaDTO {
    private Integer id;
    private Integer usuarioid;
    private BigDecimal fondos;
    private Instant fechaApertura;
    private String tipoCuenta;

}
