package  ma.zyn.app.ws.converter.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.ws.dto.role.RoleDto;

@Component
public class RoleConverter {



    public Role toItem(RoleDto dto) {
        if (dto == null) {
            return null;
        } else {
        Role item = new Role();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public RoleDto toDto(Role item) {
        if (item == null) {
            return null;
        } else {
            RoleDto dto = new RoleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Role> toItem(List<RoleDto> dtos) {
        List<Role> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RoleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RoleDto> toDto(List<Role> items) {
        List<RoleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Role item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RoleDto dto, Role t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Role> copy(List<RoleDto> dtos) {
        List<Role> result = new ArrayList<>();
        if (dtos != null) {
            for (RoleDto dto : dtos) {
                Role instance = new Role();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
