package  ma.zyn.app.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.project.ProjectConverter;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.ws.converter.role.RoleConverter;
import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.ws.converter.member.MemberConverter;
import ma.zyn.app.bean.core.member.Member;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.ws.dto.project.ProjectTeamDto;

@Component
public class ProjectTeamConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    @Autowired
    private RoleConverter roleConverter ;
    @Autowired
    private MemberConverter memberConverter ;
    private boolean project;
    private boolean members;

    public  ProjectTeamConverter() {
        init(true);
    }

    public ProjectTeam toItem(ProjectTeamDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjectTeam item = new ProjectTeam();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.project && dto.getProject()!=null)
                item.setProject(projectConverter.toItem(dto.getProject())) ;


            if(this.members && ListUtil.isNotEmpty(dto.getMembers()))
                item.setMembers(memberConverter.toItem(dto.getMembers()));


        return item;
        }
    }


    public ProjectTeamDto toDto(ProjectTeam item) {
        if (item == null) {
            return null;
        } else {
            ProjectTeamDto dto = new ProjectTeamDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.project && item.getProject()!=null) {
                projectConverter.setProjectTeam(false);
                dto.setProject(projectConverter.toDto(item.getProject())) ;
                projectConverter.setProjectTeam(true);

            }
        if(this.members && ListUtil.isNotEmpty(item.getMembers())){
            memberConverter.init(true);
            memberConverter.setProjectTeam(false);
            dto.setMembers(memberConverter.toDto(item.getMembers()));
            memberConverter.setProjectTeam(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.members = value;
    }
    public void initObject(boolean value) {
        this.project = value;
    }
	
    public List<ProjectTeam> toItem(List<ProjectTeamDto> dtos) {
        List<ProjectTeam> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectTeamDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectTeamDto> toDto(List<ProjectTeam> items) {
        List<ProjectTeamDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjectTeam item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectTeamDto dto, ProjectTeam t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProject() == null  && dto.getProject() != null){
            t.setProject(new Project());
        }else if (t.getProject() != null  && dto.getProject() != null){
            t.setProject(null);
            t.setProject(new Project());
        }
        if (dto.getProject() != null)
        projectConverter.copy(dto.getProject(), t.getProject());
        if (dto.getMembers() != null)
            t.setMembers(memberConverter.copy(dto.getMembers()));
    }

    public List<ProjectTeam> copy(List<ProjectTeamDto> dtos) {
        List<ProjectTeam> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectTeamDto dto : dtos) {
                ProjectTeam instance = new ProjectTeam();
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
    public RoleConverter getRoleConverter(){
        return this.roleConverter;
    }
    public void setRoleConverter(RoleConverter roleConverter ){
        this.roleConverter = roleConverter;
    }
    public MemberConverter getMemberConverter(){
        return this.memberConverter;
    }
    public void setMemberConverter(MemberConverter memberConverter ){
        this.memberConverter = memberConverter;
    }
    public boolean  isProject(){
        return this.project;
    }
    public void  setProject(boolean project){
        this.project = project;
    }
    public boolean  isMembers(){
        return this.members ;
    }
    public void  setMembers(boolean members ){
        this.members  = members ;
    }
}
