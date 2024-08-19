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

import ma.zyn.app.bean.core.project.ProjectCategory;
import ma.zyn.app.dao.criteria.core.project.ProjectCategoryCriteria;
import ma.zyn.app.service.facade.admin.project.ProjectCategoryAdminService;
import ma.zyn.app.ws.converter.project.ProjectCategoryConverter;
import ma.zyn.app.ws.dto.project.ProjectCategoryDto;
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
@RequestMapping("/api/admin/projectCategory/")
public class ProjectCategoryRestAdmin {




    @Operation(summary = "Finds a list of all projectCategorys")
    @GetMapping("")
    public ResponseEntity<List<ProjectCategoryDto>> findAll() throws Exception {
        ResponseEntity<List<ProjectCategoryDto>> res = null;
        List<ProjectCategory> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projectCategorys")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjectCategoryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjectCategoryDto>> res = null;
        List<ProjectCategory> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projectCategory by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjectCategoryDto> findById(@PathVariable Long id) {
        ProjectCategory t = service.findById(id);
        if (t != null) {
            ProjectCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projectCategory by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProjectCategoryDto> findByLibelle(@PathVariable String libelle) {
	    ProjectCategory t = service.findByReferenceEntity(new ProjectCategory(libelle));
        if (t != null) {
            ProjectCategoryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projectCategory")
    @PostMapping("")
    public ResponseEntity<ProjectCategoryDto> save(@RequestBody ProjectCategoryDto dto) throws Exception {
        if(dto!=null){
            ProjectCategory myT = converter.toItem(dto);
            ProjectCategory t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjectCategoryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projectCategory")
    @PutMapping("")
    public ResponseEntity<ProjectCategoryDto> update(@RequestBody ProjectCategoryDto dto) throws Exception {
        ResponseEntity<ProjectCategoryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjectCategory t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjectCategory updated = service.update(t);
            ProjectCategoryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projectCategory")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjectCategoryDto>> delete(@RequestBody List<ProjectCategoryDto> dtos) throws Exception {
        ResponseEntity<List<ProjectCategoryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProjectCategory> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified projectCategory")
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


    @Operation(summary = "Finds a projectCategory and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjectCategoryDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjectCategory loaded =  service.findWithAssociatedLists(id);
        ProjectCategoryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projectCategorys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjectCategoryDto>> findByCriteria(@RequestBody ProjectCategoryCriteria criteria) throws Exception {
        ResponseEntity<List<ProjectCategoryDto>> res = null;
        List<ProjectCategory> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProjectCategoryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projectCategorys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjectCategoryCriteria criteria) throws Exception {
        List<ProjectCategory> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProjectCategoryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projectCategory data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjectCategoryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjectCategoryDto> findDtos(List<ProjectCategory> list){
        List<ProjectCategoryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjectCategoryDto> getDtoResponseEntity(ProjectCategoryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ProjectCategoryRestAdmin(ProjectCategoryAdminService service, ProjectCategoryConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProjectCategoryAdminService service;
    private final ProjectCategoryConverter converter;





}
