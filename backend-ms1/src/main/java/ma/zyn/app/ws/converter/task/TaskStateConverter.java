package  ma.zyn.app.ws.converter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.ws.dto.task.TaskStateDto;

@Component
public class TaskStateConverter {



    public TaskState toItem(TaskStateDto dto) {
        if (dto == null) {
            return null;
        } else {
        TaskState item = new TaskState();
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


    public TaskStateDto toDto(TaskState item) {
        if (item == null) {
            return null;
        } else {
            TaskStateDto dto = new TaskStateDto();
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


	
    public List<TaskState> toItem(List<TaskStateDto> dtos) {
        List<TaskState> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TaskStateDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TaskStateDto> toDto(List<TaskState> items) {
        List<TaskStateDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TaskState item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TaskStateDto dto, TaskState t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TaskState> copy(List<TaskStateDto> dtos) {
        List<TaskState> result = new ArrayList<>();
        if (dtos != null) {
            for (TaskStateDto dto : dtos) {
                TaskState instance = new TaskState();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
