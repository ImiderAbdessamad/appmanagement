package  ma.zyn.app.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.ws.dto.project.ProjectTypeDto;

@Component
public class ProjectTypeConverter {



    public ProjectType toItem(ProjectTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectType item = new ProjectType();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public ProjectTypeDto toDto(ProjectType item) {
        if (item == null) {
            return null;
        } else {
            ProjectTypeDto dto = new ProjectTypeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<ProjectType> toItem(List<ProjectTypeDto> dtos) {
        List<ProjectType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTypeDto> toDto(List<ProjectType> items) {
        List<ProjectTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTypeDto dto, ProjectType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectType> copy(List<ProjectTypeDto> dtos) {
        List<ProjectType> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTypeDto dto : dtos) {
                ProjectType instance = new ProjectType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
