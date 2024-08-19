package  ma.zyn.app.ws.facade.admin.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.task.TaskState;
import ma.zyn.app.dao.criteria.core.task.TaskStateCriteria;
import ma.zyn.app.service.facade.admin.task.TaskStateAdminService;
import ma.zyn.app.ws.converter.task.TaskStateConverter;
import ma.zyn.app.ws.dto.task.TaskStateDto;
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
@RequestMapping("/api/admin/taskState/")
public class TaskStateRestAdmin {




    @Operation(summary = "Finds a list of all taskStates")
    @GetMapping("")
    public ResponseEntity<List<TaskStateDto>> findAll() throws Exception {
        ResponseEntity<List<TaskStateDto>> res = null;
        List<TaskState> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TaskStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all taskStates")
    @GetMapping("optimized")
    public ResponseEntity<List<TaskStateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TaskStateDto>> res = null;
        List<TaskState> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TaskStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a taskState by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TaskStateDto> findById(@PathVariable Long id) {
        TaskState t = service.findById(id);
        if (t != null) {
            TaskStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a taskState by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TaskStateDto> findByLibelle(@PathVariable String libelle) {
	    TaskState t = service.findByReferenceEntity(new TaskState(libelle));
        if (t != null) {
            TaskStateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  taskState")
    @PostMapping("")
    public ResponseEntity<TaskStateDto> save(@RequestBody TaskStateDto dto) throws Exception {
        if(dto!=null){
            TaskState myT = converter.toItem(dto);
            TaskState t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TaskStateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  taskState")
    @PutMapping("")
    public ResponseEntity<TaskStateDto> update(@RequestBody TaskStateDto dto) throws Exception {
        ResponseEntity<TaskStateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TaskState t = service.findById(dto.getId());
            converter.copy(dto,t);
            TaskState updated = service.update(t);
            TaskStateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of taskState")
    @PostMapping("multiple")
    public ResponseEntity<List<TaskStateDto>> delete(@RequestBody List<TaskStateDto> dtos) throws Exception {
        ResponseEntity<List<TaskStateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TaskState> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified taskState")
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


    @Operation(summary = "Finds a taskState and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TaskStateDto> findWithAssociatedLists(@PathVariable Long id) {
        TaskState loaded =  service.findWithAssociatedLists(id);
        TaskStateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds taskStates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TaskStateDto>> findByCriteria(@RequestBody TaskStateCriteria criteria) throws Exception {
        ResponseEntity<List<TaskStateDto>> res = null;
        List<TaskState> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TaskStateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated taskStates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TaskStateCriteria criteria) throws Exception {
        List<TaskState> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TaskStateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets taskState data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TaskStateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TaskStateDto> findDtos(List<TaskState> list){
        List<TaskStateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TaskStateDto> getDtoResponseEntity(TaskStateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TaskStateRestAdmin(TaskStateAdminService service, TaskStateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TaskStateAdminService service;
    private final TaskStateConverter converter;





}
