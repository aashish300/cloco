package artist_management_system.java.Utils;

import artist_management_system.java.Utils.Enum.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return (role == null) ? null : role.toString();
    }

    @Override
    public Role convertToEntityAttribute(String role) {
        return (role == null) ? null : Role.valueOf(role);
    }
}
