package com.dwarfeng.settingrepo.impl.configuration;

import com.dwarfeng.settingrepo.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.settingrepo.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(FormatterException.class, ServiceExceptionCodes.FORMATTER_FAILED);
        destination.put(FormatterExecutionException.class, ServiceExceptionCodes.FORMATTER_EXECUTION_FAILED);
        destination.put(FormatterMakeException.class, ServiceExceptionCodes.FORMATTER_MAKE_FAILED);
        destination.put(UnsupportedFormatterTypeException.class, ServiceExceptionCodes.UNSUPPORTED_FORMATTER_TYPE);
        destination.put(SettingCategoryNotExistsException.class, ServiceExceptionCodes.SETTING_CATEGORY_NOT_EXISTS);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
