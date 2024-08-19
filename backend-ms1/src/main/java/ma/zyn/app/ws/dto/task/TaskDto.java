package  ma.zyn.app.ws.dto.task;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.project.ProjectDto;
import ma.zyn.app.ws.dto.member.MemberDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto  extends AuditBaseDto {

    private String name  ;
    private String description  ;
    private String startDate ;
    private String endDate ;

    private TaskStateDto taskState ;
    private MemberDto assignedTo ;
    private ProjectDto project ;

    private List<TaskDto> subTasks ;


    public TaskDto(){
        super();
    }




    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public TaskStateDto getTaskState(){
        return this.taskState;
    }

    public void setTaskState(TaskStateDto taskState){
        this.taskState = taskState;
    }
    public MemberDto getAssignedTo(){
        return this.assignedTo;
    }

    public void setAssignedTo(MemberDto assignedTo){
        this.assignedTo = assignedTo;
    }
    public ProjectDto getProject(){
        return this.project;
    }

    public void setProject(ProjectDto project){
        this.project = project;
    }



    public List<TaskDto> getSubTasks(){
        return this.subTasks;
    }

    public void setSubTasks(List<TaskDto> subTasks){
        this.subTasks = subTasks;
    }



}
