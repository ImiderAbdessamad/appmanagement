package  ma.zyn.app.ws.facade.admin.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.role.Role;
import ma.zyn.app.dao.criteria.core.role.RoleCriteria;
import ma.zyn.app.service.facade.admin.role.RoleAdminService;
import ma.zyn.app.ws.converter.role.RoleConverter;
import ma.zyn.app.ws.dto.role.RoleDto;
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
@RequestMapping("/api/admin/role/")
public class RoleRestAdmin {




    @Operation(summary = "Finds a list of all roles")
    @GetMapping("")
    public ResponseEntity<List<RoleDto>> findAll() throws Exception {
        ResponseEntity<List<RoleDto>> res = null;
        List<Role> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all roles")
    @GetMapping("optimized")
    public ResponseEntity<List<RoleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RoleDto>> res = null;
        List<Role> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a role by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        Role t = service.findById(id);
        if (t != null) {
            RoleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a role by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<RoleDto> findByLibelle(@PathVariable String libelle) {
	    Role t = service.findByReferenceEntity(new Role(libelle));
        if (t != null) {
            RoleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  role")
    @PostMapping("")
    public ResponseEntity<RoleDto> save(@RequestBody RoleDto dto) throws Exception {
        if(dto!=null){
            Role myT = converter.toItem(dto);
            Role t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RoleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  role")
    @PutMapping("")
    public ResponseEntity<RoleDto> update(@RequestBody RoleDto dto) throws Exception {
        ResponseEntity<RoleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Role t = service.findById(dto.getId());
            converter.copy(dto,t);
            Role updated = service.update(t);
            RoleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of role")
    @PostMapping("multiple")
    public ResponseEntity<List<RoleDto>> delete(@RequestBody List<RoleDto> dtos) throws Exception {
        ResponseEntity<List<RoleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Role> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified role")
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


    @Operation(summary = "Finds a role and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RoleDto> findWithAssociatedLists(@PathVariable Long id) {
        Role loaded =  service.findWithAssociatedLists(id);
        RoleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds roles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RoleDto>> findByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        ResponseEntity<List<RoleDto>> res = null;
        List<Role> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated roles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RoleCriteria criteria) throws Exception {
        List<Role> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RoleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets role data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RoleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RoleDto> findDtos(List<Role> list){
        List<RoleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RoleDto> getDtoResponseEntity(RoleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public RoleRestAdmin(RoleAdminService service, RoleConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RoleAdminService service;
    private final RoleConverter converter;





}
