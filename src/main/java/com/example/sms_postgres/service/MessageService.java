package com.example.sms_postgres.service;

import com.example.sms_postgres.entity.ApiResponse;
import com.example.sms_postgres.entity.Message;
import com.example.sms_postgres.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;


    public ApiResponse<List<Message>> getAll() {
        List<Message> list = messageRepository.findAll();
        return ApiResponse.<List<Message>>builder().
                status(200).
                success(true).
                message("Here").
                data(list).
                build();
    }

//    public ApiResponse<Message> getOne(Long id) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        return ApiResponse.<Message>builder().
//                status(200).
//                success(true).
//                message("Here").
//                data(messageOptional.get()).
//                build();
//    }

//    public ApiResponse<Message> add(MessageDTO dto) {
//        Message message = new Message();
//        message.se(dto.getText());
//        message.setPhone(dto.getPhone());
//        try {
//            message.setStatusType(StatusType.valueOf(dto.getStatusType()));
//        }catch (Exception e){
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("This enum type not supported").
//                    build();
//        }
//
//        Message save = messageRepository.save(message);
//        return ApiResponse.<Message>builder().
//                status(201).
//                success(true).
//                message("Saved").
//                data(save).
//                build();
//    }
//
//    public ApiResponse<Message> edit(Long id, MessageDTO dto) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        Message message = messageOptional.get();
//        message.setText(dto.getText());
//        message.setPhone(dto.getPhone());
//        try {
//            message.setStatusType(StatusType.valueOf(dto.getStatusType()));
//        }catch (Exception e){
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("This enum type not supported").
//                    build();
//        }
//
//        Message save = messageRepository.save(message);
//        return ApiResponse.<Message>builder().
//                status(201).
//                success(true).
//                message("Saved").
//                data(save).
//                build();
//    }
//
//    public ApiResponse<?> delete(Long id) {
//        Optional<Message> messageOptional = messageRepository.findById(id);
//        if (messageOptional.isEmpty()) {
//            return ApiResponse.<Message>builder().
//                    status(400).
//                    success(false).
//                    message("Not found").
//                    build();
//        }
//        messageRepository.deleteById(id);
//        return ApiResponse.builder().
//                status(200).
//                success(true).
//                message("Deleted").
//                build();
//    }

    public ApiResponse<List<Message>> getAllByStatus(int status) {

        ApiResponse<List<Message>> response = new ApiResponse<>();
        if (status > 3 || status < 0) {
            response.setMessage("Flag not supported!!!");
            response.setStatus(400);
            response.setSuccess(false);
            return response;
        }
        List<Message> list = messageRepository.findAllByFlag(status);
        LocalDateTime dateTime = LocalDateTime.now();
        for (Message message : list) {
            if (message.getFlag() != 3) {
                message.setFlag(2);
                message.setSana(dateTime);
            }
        }
        messageRepository.saveAll(list);

        response.setMessage("Here!!!");
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(list);
        return response;
    }


    public boolean editStatus(List<Integer> list) {
        try {
            List<Message> messageList = messageRepository.findAllById(list);
            for (Message message : messageList) {
                message.setFlag(3);
            }
            messageRepository.saveAll(messageList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
