package gabriel.fontes.br.quarkus.Model.Jpa;

import gabriel.fontes.br.quarkus.Model.Enums.StatusDepartamento;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusDepartamentoConverter implements AttributeConverter<StatusDepartamento, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusDepartamento status) {

        return (status == null) ? null : (long) status.getId();
    }

    @Override
    public StatusDepartamento convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        }
        return StatusDepartamento.getById(id.intValue());
    }
}