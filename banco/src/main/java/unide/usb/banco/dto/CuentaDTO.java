package unide.usb.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import unide.usb.banco.domain.Usuario;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CuentaDTO {
    private Integer id;
    private Integer usuarioid;
    private BigDecimal fondos;
    private Instant fechaApertura;
    private String tipoCuenta;

}