package ma.zyn.app.bean.core.task;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.bean.core.member.Member;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="task_seq",sequenceName="task_seq",allocationSize=1, initialValue = 1)
public class Task  extends BaseEntity     {




    @Column(length = 500)
    private String name;

    @Column(length = 500)
    private String description;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    private TaskState taskState ;
    private Member assignedTo ;
    private Project project ;

    private List<Task> subTasks ;

    public Task(){
        super();
    }

    public Task(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="task_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_state")
    public TaskState getTaskState(){
        return this.taskState;
    }
    public void setTaskState(TaskState taskState){
        this.taskState = taskState;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    public Member getAssignedTo(){
        return this.assignedTo;
    }
    public void setAssignedTo(Member assignedTo){
        this.assignedTo = assignedTo;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    public Project getProject(){
        return this.project;
    }
    public void setProject(Project project){
        this.project = project;
    }
    @OneToMany(mappedBy = "task")
    public List<Task> getSubTasks(){
        return this.subTasks;
    }

    public void setSubTasks(List<Task> subTasks){
        this.subTasks = subTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id != null && id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

