package com.hbu.backend.service;

import com.hbu.backend.model.entity.Admin;
import com.hbu.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    /**
     * Create
     */
    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    /**
     * Read One
     */
    public Admin findAdmin(Long id){
        return adminRepository.findById(id).orElse(null);
    }

    /**
     * Read All
     */
    public List<Admin> findAllAdmin(){
        return adminRepository.findAll();
    }

    /**
     * Update
     */
    public Admin updateAdmin(Admin admin, Long id){
        Admin foundAdmin = adminRepository.findById(id).orElse(null);

        if(foundAdmin == null){
            return foundAdmin;
        }

        foundAdmin.setFirstName(admin.getFirstName());
        foundAdmin.setLastName(admin.getLastName());
        return adminRepository.save(foundAdmin);
    }

    /**
     * Delete
     */
    public void deleteAdmin(Admin admin){
        if(admin != null) {
            adminRepository.delete(admin);
        }
    }
}
