package com.example.fashionblog.services.serviceImpl;

import com.example.fashionblog.dto.requestDto.AdminLoginRequestDto;
import com.example.fashionblog.dto.requestDto.AdminRequestDto;
import com.example.fashionblog.dto.responseDto.AdminResponseDto;
import com.example.fashionblog.entities.Admin;
import com.example.fashionblog.repository.AdminRepository;
import com.example.fashionblog.services.AdminService;
import com.example.fashionblog.utils.EntityMapper;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final EntityMapper entityMapper;


    public AdminServiceImpl(AdminRepository adminRepository, EntityMapper entityMapper, HttpSession httpSession) {
        this.adminRepository = adminRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public GenericResponses createAdmin(AdminRequestDto adminRequestDto) {
        Optional<Admin> admin = adminRepository.findAdminByEmail(adminRequestDto.getEmail());
        if (admin.isPresent()) {
            return new GenericResponses("Account already exist", "01");
        }
        Admin admin1 = entityMapper.dtoToAdminMapper(adminRequestDto);
        adminRepository.save(admin1);
        AdminResponseDto adminResponseDto = entityMapper.adminToDtoMapper(admin1);

        return new GenericResponses("Account created Successfully", "00", adminResponseDto);
    }

    @Override
    public GenericResponses Login(AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest request) {
        Optional<Admin> admin = adminRepository
                .findAdminByEmailAndPassword(adminLoginRequestDto.getEmail(), adminLoginRequestDto.getPassword());
        if (admin.isEmpty()) {
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        AdminResponseDto adminResponseDto = entityMapper.adminToDtoMapper(admin.get());
        HttpSession  session = request.getSession();
       session .setAttribute("adminResponseDto", adminResponseDto);
        return new GenericResponses("Request processed successfully", "00", HttpStatus.OK, adminResponseDto);

    }

    @Override
    public GenericResponses fetchAdmin(Long adminId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        AdminResponseDto adminSession = (AdminResponseDto) session.getAttribute("adminResponseDto");

        Optional<Admin> admin = adminRepository.findById(adminSession.getId());
        if (admin.isEmpty()) {
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
            AdminResponseDto adminResponseDto1 = new AdminResponseDto();
            adminResponseDto1.setId(admin.get().getId());
            adminResponseDto1.setName(admin.get().getName());
            adminResponseDto1.setEmail(admin.get().getEmail());
            return new GenericResponses("Request processed successfully", "00", HttpStatus.OK, adminResponseDto1);

    }
    @Override
    public GenericResponses updateAdmin(Long id, HttpServletRequest request, AdminRequestDto adminRequestDto) {
        HttpSession session = request.getSession();
        AdminResponseDto adminSession = (AdminResponseDto) session.getAttribute("adminResponseDto");

        Optional<Admin> admin = adminRepository.findById(adminSession.getId());
        if (admin.isEmpty()) {
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Admin admin1 = new Admin();
        admin1 = admin.get();
        admin1.setId(adminSession.getId());
        admin1.setName(adminRequestDto.getName());
        admin1.setEmail(adminRequestDto.getEmail());
        admin1.setPassword(adminRequestDto.getPassword());

        adminRepository.save(admin1);
        return new GenericResponses("Request processed successfully", "00", HttpStatus.OK, admin1);

    }
@Override
    public GenericResponses deleteAdmin(Long id, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    AdminResponseDto adminSession = (AdminResponseDto) session.getAttribute("adminResponseDto");
    if (!Objects.equals(adminSession.getEmail(), "favour@gmail.com")){
        throw new Exception("Cannot Perform this Operation");
    }
    Optional<Admin> admin = adminRepository.findById(id);
    if (admin.isEmpty()) {
        return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
    }
    adminRepository.deleteById(id);
    return new GenericResponses("Request processed successfully", "00", HttpStatus.GONE);
    }
}