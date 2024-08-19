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
import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.ws.dto.project.ProjectCategoryDto;

@Component
public class ProjectCategoryConverter {



    public ProjectCategory toItem(ProjectCategoryDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectCategory item = new ProjectCategory();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public ProjectCategoryDto toDto(ProjectCategory item) {
        if (item == null) {
            return null;
        } else {
            ProjectCategoryDto dto = new ProjectCategoryDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<ProjectCategory> toItem(List<ProjectCategoryDto> dtos) {
        List<ProjectCategory> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectCategoryDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectCategoryDto> toDto(List<ProjectCategory> items) {
        List<ProjectCategoryDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectCategory item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectCategoryDto dto, ProjectCategory t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectCategory> copy(List<ProjectCategoryDto> dtos) {
        List<ProjectCategory> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectCategoryDto dto : dtos) {
                ProjectCategory instance = new ProjectCategory();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
