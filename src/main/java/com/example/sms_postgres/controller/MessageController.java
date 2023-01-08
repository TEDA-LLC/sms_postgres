package com.example.sms_postgres.controller;

import com.example.sms_postgres.entity.ApiResponse;
import com.example.sms_postgres.entity.Message;
import com.example.sms_postgres.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse<List<Message>> response =  messageService.getAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getAllByStatus(@RequestParam int status){
        ApiResponse<List<Message>> response =  messageService.getAllByStatus(status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestParam List<Integer> list){
        return ResponseEntity.ok(messageService.editStatus(list));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOne(@PathVariable Long id){
//        ApiResponse<Message> response =  messageService.getOne(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> add(@RequestBody MessageDTO dto){
//        ApiResponse<Message> response =  messageService.add(dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody MessageDTO dto){
//        ApiResponse<Message> response =  messageService.edit( id,dto);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        ApiResponse<?> response =  messageService.delete(id);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}
