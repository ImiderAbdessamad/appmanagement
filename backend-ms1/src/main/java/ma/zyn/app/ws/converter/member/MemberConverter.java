package  ma.zyn.app.ws.converter.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.project.ProjectConverter;
import ma.zyn.app.bean.core.project.Project;
import ma.zyn.app.ws.converter.project.ProjectTeamConverter;
import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.ws.converter.role.RoleConverter;
import ma.zyn.app.bean.core.role.Role;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.member.Member;
import ma.zyn.app.ws.dto.member.MemberDto;

@Component
public class MemberConverter {

    @Autowired
    private ProjectConverter projectConverter ;
    @Autowired
    private ProjectTeamConverter projectTeamConverter ;
    @Autowired
    private RoleConverter roleConverter ;
    private boolean roles;
    private boolean projectTeams;

    public  MemberConverter() {
        initList(true);
    }

    public Member toItem(MemberDto dto) {
        if (dto == null) {
            return null;
        } else {
        Member item = new Member();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPhone()))
                item.setPhone(dto.getPhone());

            if(this.roles && ListUtil.isNotEmpty(dto.getRoles()))
                item.setRoles(roleConverter.toItem(dto.getRoles()));
            if(this.projectTeams && ListUtil.isNotEmpty(dto.getProjectTeams()))
                item.setProjectTeams(projectTeamConverter.toItem(dto.getProjectTeams()));


        return item;
        }
    }


    public MemberDto toDto(Member item) {
        if (item == null) {
            return null;
        } else {
            MemberDto dto = new MemberDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getPhone()))
                dto.setPhone(item.getPhone());
        if(this.roles && ListUtil.isNotEmpty(item.getRoles())){
            roleConverter.init(true);
            roleConverter.setMember(false);
            dto.setRoles(roleConverter.toDto(item.getRoles()));
            roleConverter.setMember(true);

        }
        if(this.projectTeams && ListUtil.isNotEmpty(item.getProjectTeams())){
            projectTeamConverter.init(true);
            projectTeamConverter.setMember(false);
            dto.setProjectTeams(projectTeamConverter.toDto(item.getProjectTeams()));
            projectTeamConverter.setMember(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.roles = value;
        this.projectTeams = value;
    }
	
    public List<Member> toItem(List<MemberDto> dtos) {
        List<Member> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MemberDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MemberDto> toDto(List<Member> items) {
        List<MemberDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Member item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MemberDto dto, Member t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getRoles() != null)
            t.setRoles(roleConverter.copy(dto.getRoles()));
        if (dto.getProjectTeams() != null)
            t.setProjectTeams(projectTeamConverter.copy(dto.getProjectTeams()));
    }

    public List<Member> copy(List<MemberDto> dtos) {
        List<Member> result = new ArrayList<>();
        if (dtos != null) {
            for (MemberDto dto : dtos) {
                Member instance = new Member();
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
    public ProjectTeamConverter getProjectTeamConverter(){
        return this.projectTeamConverter;
    }
    public void setProjectTeamConverter(ProjectTeamConverter projectTeamConverter ){
        this.projectTeamConverter = projectTeamConverter;
    }
    public RoleConverter getRoleConverter(){
        return this.roleConverter;
    }
    public void setRoleConverter(RoleConverter roleConverter ){
        this.roleConverter = roleConverter;
    }
    public boolean  isRoles(){
        return this.roles ;
    }
    public void  setRoles(boolean roles ){
        this.roles  = roles ;
    }
    public boolean  isProjectTeams(){
        return this.projectTeams ;
    }
    public void  setProjectTeams(boolean projectTeams ){
        this.projectTeams  = projectTeams ;
    }
}
