package  ma.zyn.app.ws.converter.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.project.ProjectTeamConverter;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.ws.converter.project.ProjectStateConverter;
import ma.zyn.app.bean.core.project.ProjectState;
import ma.zyn.app.ws.converter.project.ProjectCategoryConverter;
import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.ws.converter.project.ProjectTypeConverter;
import ma.zyn.app.bean.core.project.ProjectType;

import ma.zyn.app.bean.core.project.ProjectTeam;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.ws.dto.project.ProjectDto;

@Component
public class ProjectConverter {

    @Autowired
    private ProjectTeamConverter projectTeamConverter ;
    @Autowired
    private ProjectStateConverter projectStateConverter ;
    @Autowired
    private ProjectCategoryConverter projectCategoryConverter ;
    @Autowired
    private ProjectTypeConverter projectTypeConverter ;
    private boolean projectState;
    private boolean projectTeam;
    private boolean projectType;
    private boolean projectCategory;

    public  ProjectConverter() {
        initObject(true);
    }

    public Project toItem(ProjectDto dto) {
        if (dto == null) {
            return null;
        } else {
        Project item = new Project();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(this.projectState && dto.getProjectState()!=null)
                item.setProjectState(projectStateConverter.toItem(dto.getProjectState())) ;

            if(dto.getProjectTeam() != null && dto.getProjectTeam().getId() != null){
                item.setProjectTeam(new ProjectTeam());
                item.getProjectTeam().setId(dto.getProjectTeam().getId());
                item.getProjectTeam().setId(dto.getProjectTeam().getId());
            }

            if(this.projectType && dto.getProjectType()!=null)
                item.setProjectType(projectTypeConverter.toItem(dto.getProjectType())) ;

            if(this.projectCategory && dto.getProjectCategory()!=null)
                item.setProjectCategory(projectCategoryConverter.toItem(dto.getProjectCategory())) ;




        return item;
        }
    }


    public ProjectDto toDto(Project item) {
        if (item == null) {
            return null;
        } else {
            ProjectDto dto = new ProjectDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(this.projectState && item.getProjectState()!=null) {
                dto.setProjectState(projectStateConverter.toDto(item.getProjectState())) ;

            }
            if(this.projectTeam && item.getProjectTeam()!=null) {
                projectTeamConverter.setProject(false);
                dto.setProjectTeam(projectTeamConverter.toDto(item.getProjectTeam())) ;
                projectTeamConverter.setProject(true);

            }
            if(this.projectType && item.getProjectType()!=null) {
                dto.setProjectType(projectTypeConverter.toDto(item.getProjectType())) ;

            }
            if(this.projectCategory && item.getProjectCategory()!=null) {
                dto.setProjectCategory(projectCategoryConverter.toDto(item.getProjectCategory())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.projectState = value;
        this.projectTeam = value;
        this.projectType = value;
        this.projectCategory = value;
    }
	
    public List<Project> toItem(List<ProjectDto> dtos) {
        List<Project> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjectDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjectDto> toDto(List<Project> items) {
        List<ProjectDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Project item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjectDto dto, Project t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProjectState() == null  && dto.getProjectState() != null){
            t.setProjectState(new ProjectState());
        }else if (t.getProjectState() != null  && dto.getProjectState() != null){
            t.setProjectState(null);
            t.setProjectState(new ProjectState());
        }
        if(t.getProjectTeam() == null  && dto.getProjectTeam() != null){
            t.setProjectTeam(new ProjectTeam());
        }else if (t.getProjectTeam() != null  && dto.getProjectTeam() != null){
            t.setProjectTeam(null);
            t.setProjectTeam(new ProjectTeam());
        }
        if(t.getProjectType() == null  && dto.getProjectType() != null){
            t.setProjectType(new ProjectType());
        }else if (t.getProjectType() != null  && dto.getProjectType() != null){
            t.setProjectType(null);
            t.setProjectType(new ProjectType());
        }
        if(t.getProjectCategory() == null  && dto.getProjectCategory() != null){
            t.setProjectCategory(new ProjectCategory());
        }else if (t.getProjectCategory() != null  && dto.getProjectCategory() != null){
            t.setProjectCategory(null);
            t.setProjectCategory(new ProjectCategory());
        }
        if (dto.getProjectState() != null)
        projectStateConverter.copy(dto.getProjectState(), t.getProjectState());
        if (dto.getProjectTeam() != null)
        projectTeamConverter.copy(dto.getProjectTeam(), t.getProjectTeam());
        if (dto.getProjectType() != null)
        projectTypeConverter.copy(dto.getProjectType(), t.getProjectType());
        if (dto.getProjectCategory() != null)
        projectCategoryConverter.copy(dto.getProjectCategory(), t.getProjectCategory());
    }

    public List<Project> copy(List<ProjectDto> dtos) {
        List<Project> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjectDto dto : dtos) {
                Project instance = new Project();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjectTeamConverter getProjectTeamConverter(){
        return this.projectTeamConverter;
    }
    public void setProjectTeamConverter(ProjectTeamConverter projectTeamConverter ){
        this.projectTeamConverter = projectTeamConverter;
    }
    public ProjectStateConverter getProjectStateConverter(){
        return this.projectStateConverter;
    }
    public void setProjectStateConverter(ProjectStateConverter projectStateConverter ){
        this.projectStateConverter = projectStateConverter;
    }
    public ProjectCategoryConverter getProjectCategoryConverter(){
        return this.projectCategoryConverter;
    }
    public void setProjectCategoryConverter(ProjectCategoryConverter projectCategoryConverter ){
        this.projectCategoryConverter = projectCategoryConverter;
    }
    public ProjectTypeConverter getProjectTypeConverter(){
        return this.projectTypeConverter;
    }
    public void setProjectTypeConverter(ProjectTypeConverter projectTypeConverter ){
        this.projectTypeConverter = projectTypeConverter;
    }
    public boolean  isProjectState(){
        return this.projectState;
    }
    public void  setProjectState(boolean projectState){
        this.projectState = projectState;
    }
    public boolean  isProjectTeam(){
        return this.projectTeam;
    }
    public void  setProjectTeam(boolean projectTeam){
        this.projectTeam = projectTeam;
    }
    public boolean  isProjectType(){
        return this.projectType;
    }
    public void  setProjectType(boolean projectType){
        this.projectType = projectType;
    }
    public boolean  isProjectCategory(){
        return this.projectCategory;
    }
    public void  setProjectCategory(boolean projectCategory){
        this.projectCategory = projectCategory;
    }
}
