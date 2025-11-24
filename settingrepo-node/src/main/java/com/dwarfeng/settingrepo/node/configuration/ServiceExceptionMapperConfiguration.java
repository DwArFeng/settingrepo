package com.dwarfeng.settingrepo.node.configuration;

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
        destination = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination = com.dwarfeng.datamark.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination.put(FormatterException.class, ServiceExceptionCodes.FORMATTER_FAILED);
        destination.put(FormatterExecutionException.class, ServiceExceptionCodes.FORMATTER_EXECUTION_FAILED);
        destination.put(FormatterMakeException.class, ServiceExceptionCodes.FORMATTER_MAKE_FAILED);
        destination.put(UnsupportedFormatterTypeException.class, ServiceExceptionCodes.UNSUPPORTED_FORMATTER_TYPE);
        destination.put(SettingCategoryNotExistsException.class, ServiceExceptionCodes.SETTING_CATEGORY_NOT_EXISTS);
        destination.put(SettingNodeExistsException.class, ServiceExceptionCodes.SETTING_NODE_EXISTS);
        destination.put(SettingNodeNotExistsException.class, ServiceExceptionCodes.SETTING_NODE_NOT_EXISTS);
        destination.put(ImageListNodeIndexOutOfBoundException.class, ServiceExceptionCodes.IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND);
        destination.put(InvalidIahnNodeLocaleException.class, ServiceExceptionCodes.INVALID_IAHN_NODE_LOCALE);
        destination.put(InvalidIahnNodeMekIdException.class, ServiceExceptionCodes.INVALID_IAHN_NODE_MEK_ID);
        destination.put(IahnNodeLocaleNotExistsException.class, ServiceExceptionCodes.IAHN_NODE_LOCALE_NOT_EXISTS);
        destination.put(IahnNodeMekNotExistsException.class, ServiceExceptionCodes.IAHN_NODE_MEK_NOT_EXISTS);
        destination.put(FileListNodeIndexOutOfBoundException.class, ServiceExceptionCodes.FILE_LIST_NODE_INDEX_OUT_OF_BOUND);
        destination.put(NavigationNodeItemIndexConflictException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_INDEX_CONFLICT);
        destination.put(NavigationNodeItemMismatchedException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_MISMATCHED);
        destination.put(NavigationNodeItemNotExistsException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_NOT_EXISTS);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
