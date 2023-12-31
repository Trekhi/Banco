package unide.usb.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unide.usb.banco.domain.Usuario;
//import unide.usb.banco.domain.Usuario;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CuentaDTO {
    private Integer id;
    private Integer usuarioId;
    private BigDecimal fondos;
    private Instant fechaapertura;
    private String tipocuenta;
    private boolean activo;

    // Constructor con parámetros (sin incluir el ID, ya que se generará automáticamente)
    public CuentaDTO(Integer usuarioId, BigDecimal fondos, Instant fechaApertura, String tipoCuenta, boolean activo) {
        this.usuarioId = usuarioId;
        this.fondos = fondos;
        this.fechaapertura = fechaApertura;
        this.tipocuenta = tipoCuenta;
        this.activo = activo;
    }
}
