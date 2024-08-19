package  ma.zyn.app.ws.converter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.project.ProjectConverter;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.ws.converter.task.TaskStateConverter;
import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.ws.converter.member.MemberConverter;
import ma.zyn.app.bean.core.member.Member;

import ma.zyn.app.bean.core.member.Member;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.task.Task;
import ma.zyn.app.ws.dto.task.TaskDto;

@Component
public class TaskConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    @Autowired
    private TaskStateConverter taskStateConverter ;
    @Autowired
    private MemberConverter memberConverter ;
    private boolean taskState;
    private boolean assignedTo;
    private boolean project;
    private boolean subTasks;

    public  TaskConverter() {
        init(true);
    }

    public Task toItem(TaskDto dto) {
        if (dto == null) {
            return null;
        } else {
        Task item = new Task();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(this.taskState && dto.getTaskState()!=null)
                item.setTaskState(taskStateConverter.toItem(dto.getTaskState())) ;

            if(dto.getAssignedTo() != null && dto.getAssignedTo().getId() != null){
                item.setAssignedTo(new Member());
                item.getAssignedTo().setId(dto.getAssignedTo().getId());
                item.getAssignedTo().setId(dto.getAssignedTo().getId());
            }

            if(this.project && dto.getProject()!=null)
                item.setProject(projectConverter.toItem(dto.getProject())) ;


            if(this.subTasks && ListUtil.isNotEmpty(dto.getSubTasks()))
                item.setSubTasks(toItem(dto.getSubTasks()));


        return item;
        }
    }


    public TaskDto toDto(Task item) {
        if (item == null) {
            return null;
        } else {
            TaskDto dto = new TaskDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(this.taskState && item.getTaskState()!=null) {
                dto.setTaskState(taskStateConverter.toDto(item.getTaskState())) ;

            }
            if(this.assignedTo && item.getAssignedTo()!=null) {
                dto.setAssignedTo(memberConverter.toDto(item.getAssignedTo())) ;

            }
            if(this.project && item.getProject()!=null) {
                dto.setProject(projectConverter.toDto(item.getProject())) ;

            }
        if(this.subTasks && ListUtil.isNotEmpty(item.getSubTasks())){
            taskConverter.init(true);
            taskConverter.setTask(false);
            dto.setSubTasks(taskConverter.toDto(item.getSubTasks()));
            taskConverter.setTask(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.subTasks = value;
    }
    public void initObject(boolean value) {
        this.taskState = value;
        this.assignedTo = value;
        this.project = value;
    }
	
    public List<Task> toItem(List<TaskDto> dtos) {
        List<Task> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TaskDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TaskDto> toDto(List<Task> items) {
        List<TaskDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Task item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TaskDto dto, Task t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTaskState() == null  && dto.getTaskState() != null){
            t.setTaskState(new TaskState());
        }else if (t.getTaskState() != null  && dto.getTaskState() != null){
            t.setTaskState(null);
            t.setTaskState(new TaskState());
        }
        if(t.getAssignedTo() == null  && dto.getAssignedTo() != null){
            t.setAssignedTo(new Member());
        }else if (t.getAssignedTo() != null  && dto.getAssignedTo() != null){
            t.setAssignedTo(null);
            t.setAssignedTo(new Member());
        }
        if(t.getProject() == null  && dto.getProject() != null){
            t.setProject(new Project());
        }else if (t.getProject() != null  && dto.getProject() != null){
            t.setProject(null);
            t.setProject(new Project());
        }
        if (dto.getTaskState() != null)
        taskStateConverter.copy(dto.getTaskState(), t.getTaskState());
        if (dto.getAssignedTo() != null)
        memberConverter.copy(dto.getAssignedTo(), t.getAssignedTo());
        if (dto.getProject() != null)
        projectConverter.copy(dto.getProject(), t.getProject());
        if (dto.getSubTasks() != null)
            t.setSubTasks(taskConverter.copy(dto.getSubTasks()));
    }

    public List<Task> copy(List<TaskDto> dtos) {
        List<Task> result = new ArrayList<>();
        if (dtos != null) {
            for (TaskDto dto : dtos) {
                Task instance = new Task();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectConverter getProjectConverter(){
        return this.projectConverter;
    }
    public void setProjectConverter(ProjectConverter projectConverter ){
        this.projectConverter = projectConverter;
    }
    public TaskStateConverter getTaskStateConverter(){
        return this.taskStateConverter;
    }
    public void setTaskStateConverter(TaskStateConverter taskStateConverter ){
        this.taskStateConverter = taskStateConverter;
    }
    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isTaskState(){
        return this.taskState;
    }
    public void  setTaskState(boolean taskState){
        this.taskState = taskState;
    }
    public boolean  isAssignedTo(){
        return this.assignedTo;
    }
    public void  setAssignedTo(boolean assignedTo){
        this.assignedTo = assignedTo;
    }
    public boolean  isProject(){
        return this.project;
    }
    public void  setProject(boolean project){
        this.project = project;
    }
    public boolean  isSubTasks(){
        return this.subTasks ;
    }
    public void  setSubTasks(boolean subTasks ){
        this.subTasks  = subTasks ;
    }
}
