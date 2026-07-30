package com.dwarfeng.settingrepo.node.all.he.configuration;

import com.dwarfeng.settingrepo.sdk.exception.ServiceExceptionCodeSuppliers;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset}")
    private int exceptionCodeOffset;
    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset.spring_telqos}")
    private int springTelqosExceptionCodeOffset;
    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset.spring_terminator}")
    private int springTerminatorExceptionCodeOffset;
    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset.dwarfeng_datamark}")
    private int dwarfengDatamarkExceptionCodeOffset;
    @Value("${com.dwarfeng.settingrepo.settingrepo.exception_code_offset.dwarfeng_ftp}")
    private int dwarfengFtpExceptionCodeOffset;

    @PostConstruct
    public void init() {
        ServiceExceptionCodeSuppliers.setExceptionCodeOffset(exceptionCodeOffset);
        com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionCodeSuppliers.setExceptionCodeOffset(
                subgradeExceptionCodeOffset
        );
        com.dwarfeng.springtelqos.sdk.exception.ServiceExceptionCodeSuppliers.setExceptionCodeOffset(
                springTelqosExceptionCodeOffset
        );
        com.dwarfeng.springterminator.sdk.exception.ServiceExceptionCodeSuppliers.setExceptionCodeOffset(
                springTerminatorExceptionCodeOffset
        );
        com.dwarfeng.datamark.sdk.exception.ServiceExceptionCodeSuppliers.setExceptionCodeOffset(
                dwarfengDatamarkExceptionCodeOffset
        );
        com.dwarfeng.ftp.sdk.exception.ServiceExceptionCodeSuppliers.setExceptionCodeOffset(
                dwarfengFtpExceptionCodeOffset
        );
    }
}
