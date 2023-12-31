package unide.usb.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unide.usb.banco.domain.Cuenta;


import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TransaccionDTO {
    private Integer id;
    private Integer cuentaId;
    private BigDecimal consignacion;
    private Instant fechaenvio;

}
