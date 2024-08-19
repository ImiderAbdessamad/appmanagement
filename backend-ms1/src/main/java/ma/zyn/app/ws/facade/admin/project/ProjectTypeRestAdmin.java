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

import ma.zyn.app.bean.core.project.ProjectType;
import ma.zyn.app.dao.criteria.core.project.ProjectTypeCriteria;
import ma.zyn.app.service.facade.admin.project.ProjectTypeAdminService;
import ma.zyn.app.ws.converter.project.ProjectTypeConverter;
import ma.zyn.app.ws.dto.project.ProjectTypeDto;
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
@RequestMapping("/api/admin/projectType/")
public class ProjectTypeRestAdmin {




    @Operation(summary = "Finds a list of all projectTypes")
    @GetMapping("")
    public ResponseEntity<List<ProjectTypeDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectTypeDto>> res = null;
        List<ProjectType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectTypeDto>> res = null;
        List<ProjectType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectTypeDto> findById(@PathVariable Long id) {
        ProjectType t = service.findById(id);
        if (t != null) {
            ProjectTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProjectTypeDto> findByLibelle(@PathVariable String libelle) {
	    ProjectType t = service.findByReferenceEntity(new ProjectType(libelle));
        if (t != null) {
            ProjectTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectType")
    @PostMapping("")
    public ResponseEntity<ProjectTypeDto> save(@RequestBody ProjectTypeDto dto) throws Exception {
        if(dto!=null){
            ProjectType myT = converter.toItem(dto);
            ProjectType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectType")
    @PutMapping("")
    public ResponseEntity<ProjectTypeDto> update(@RequestBody ProjectTypeDto dto) throws Exception {
        ResponseEntity<ProjectTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectType t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectType updated = service.update(t);
            ProjectTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectType")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectTypeDto>> delete(@RequestBody List<ProjectTypeDto> dtos) throws Exception {
        ResponseEntity<List<ProjectTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProjectType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectType")
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


    @Operation(summary = "Finds a projectType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectType loaded =  service.findWithAssociatedLists(id);
        ProjectTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectTypeDto>> findByCriteria(@RequestBody ProjectTypeCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectTypeDto>> res = null;
        List<ProjectType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectTypeCriteria criteria) throws Exception {
        List<ProjectType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProjectTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectTypeDto> findDtos(List<ProjectType> list){
        List<ProjectTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectTypeDto> getDtoResponseEntity(ProjectTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProjectTypeRestAdmin(ProjectTypeAdminService service, ProjectTypeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProjectTypeAdminService service;
    private final ProjectTypeConverter converter;





}
