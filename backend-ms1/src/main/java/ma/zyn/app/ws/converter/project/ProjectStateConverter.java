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
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.ws.dto.project.ProjectStateDto;

@Component
public class ProjectStateConverter {



    public ProjectState toItem(ProjectStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectState item = new ProjectState();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public ProjectStateDto toDto(ProjectState item) {
        if (item == null) {
            return null;
        } else {
            ProjectStateDto dto = new ProjectStateDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<ProjectState> toItem(List<ProjectStateDto> dtos) {
        List<ProjectState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectStateDto> toDto(List<ProjectState> items) {
        List<ProjectStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectStateDto dto, ProjectState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProjectState> copy(List<ProjectStateDto> dtos) {
        List<ProjectState> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectStateDto dto : dtos) {
                ProjectState instance = new ProjectState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
