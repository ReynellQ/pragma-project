package com.pragma.messagingservice.application.mapper;

import com.pragma.messagingservice.application.dto.ReadyOrderMessageDto;
import com.pragma.messagingservice.domain.model.ReadyOrderMessage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MessagesDtoMapper {
    ReadyOrderMessage toMessage(ReadyOrderMessageDto readyOrderMessageDto);
}
