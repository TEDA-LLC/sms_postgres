package com.example.sms_postgres.service;

import com.example.sms_postgres.entity.ApiResponse;
import com.example.sms_postgres.entity.Message;
import com.example.sms_postgres.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Message> messageList = messageRepository.findAllByFlag(status);
        return ApiResponse.<List<Message>>builder().
                status(200).
                success(true).
                message("Here").
                data(messageList).
                build();
    }

    public boolean editStatus(List<Integer> list) {
        List<Message> messageList = messageRepository.findAllById(list);
        for (Message message : messageList) {
            message.setFlag(3);
        }
        messageRepository.saveAll(messageList);
        return true;
    }
}
