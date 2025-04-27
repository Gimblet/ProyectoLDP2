package com.cibertec.app.mapper.personal;

import com.cibertec.app.dto.personal.PersonalRequestDto;
import com.cibertec.app.dto.personal.PersonalResponseDto;
import com.cibertec.app.entity.Personal;

public class PersonalMapper {
        public static Personal toEntity(PersonalRequestDto dto) {
            return Personal.builder()
                    .id(dto.getId())
                    .nombre(dto.getNombre())
                    .monto(dto.getMonto())
                    .rol(dto.getRol())
                    .build();
        }

        public static PersonalResponseDto toDto(Personal entity) {
            return PersonalResponseDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .monto(entity.getMonto())
                    .rol(entity.getRol())
                    .build();
        }
    }


