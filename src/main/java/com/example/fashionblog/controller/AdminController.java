package com.example.fashionblog.controller;

import com.example.fashionblog.dto.requestDto.AdminLoginRequestDto;
import com.example.fashionblog.dto.requestDto.AdminRequestDto;
import com.example.fashionblog.services.AdminService;
import com.example.fashionblog.services.PostService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/v1/admin")
    public class AdminController {
        private final AdminService adminService;

        public AdminController(AdminService adminService, PostService postService) {
            this.adminService = adminService;

        }

        @PostMapping("/signup")
        public ResponseEntity<GenericResponses> signup(@RequestBody AdminRequestDto adminRequestDto) {
            GenericResponses genericResponses = adminService.createAdmin(adminRequestDto);
            return new ResponseEntity<>(genericResponses, HttpStatus.CREATED);
        }

        @PostMapping("/login")
        public ResponseEntity<GenericResponses> login(@RequestBody AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest httpServletRequest) {
            GenericResponses genericResponses = adminService.Login(adminLoginRequestDto, httpServletRequest);
            return new ResponseEntity<>(genericResponses, genericResponses.getHttpStatus());
        }

        @PutMapping("/updateAdmin")
        public ResponseEntity<GenericResponses> updateAdmin(@RequestBody AdminRequestDto adminRequestDto, Long adminId, HttpServletRequest request) {
                GenericResponses genericResponses = adminService.updateAdmin(adminId, request, adminRequestDto );
                return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
            }

    @GetMapping("/view_user")
    public ResponseEntity<GenericResponses> viewUser(Long id, HttpServletRequest request){
        GenericResponses genericResponse = adminService.fetchAdmin(id, request);
        return new  ResponseEntity<>(genericResponse,genericResponse.getHttpStatus());
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<GenericResponses>deleteUser(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
            GenericResponses genericResponses = adminService.deleteAdmin(Long.valueOf(id),request);
            return  new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }
}

