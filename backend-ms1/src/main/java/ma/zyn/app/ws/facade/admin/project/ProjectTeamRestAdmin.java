package  ma.zyn.app.ws.facade.admin.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.project.ProjectTeam;
import ma.zyn.app.dao.criteria.core.project.ProjectTeamCriteria;
import ma.zyn.app.service.facade.admin.project.ProjectTeamAdminService;
import ma.zyn.app.ws.converter.project.ProjectTeamConverter;
import ma.zyn.app.ws.dto.project.ProjectTeamDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/projectTeam/")
public class ProjectTeamRestAdmin {




    @Operation(summary = "Finds a list of all projectTeams")
    @GetMapping("")
    public ResponseEntity<List<ProjectTeamDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTeamDto>> res = null;
        List<ProjectTeam> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ProjectTeamDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a projectTeam by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTeamDto> findById(@PathVariable Long id) {
        ProjectTeam t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjectTeamDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  projectTeam")
    @PostMapping("")
    public ResponseEntity<ProjectTeamDto> save(@RequestBody ProjectTeamDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ProjectTeam myT = converter.toItem(dto);
            ProjectTeam t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectTeamDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectTeam")
    @PutMapping("")
    public ResponseEntity<ProjectTeamDto> update(@RequestBody ProjectTeamDto dto) throws Exception {
        ResponseEntity<ProjectTeamDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectTeam t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectTeam updated = service.update(t);
            ProjectTeamDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectTeam")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTeamDto>> delete(@RequestBody List<ProjectTeamDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTeamDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjectTeam> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectTeam")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a projectTeam and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTeamDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectTeam loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjectTeamDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTeams by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTeamDto>> findByCriteria(@RequestBody ProjectTeamCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTeamDto>> res = null;
        List<ProjectTeam> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjectTeamDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTeams by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTeamCriteria criteria) throws Exception {
        List<ProjectTeam> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ProjectTeamDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectTeam data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTeamCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectTeamDto> findDtos(List<ProjectTeam> list){
        converter.initList(false);
        converter.initObject(true);
        List<ProjectTeamDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTeamDto> getDtoResponseEntity(ProjectTeamDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProjectTeamRestAdmin(ProjectTeamAdminService service, ProjectTeamConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProjectTeamAdminService service;
    private final ProjectTeamConverter converter;





}
