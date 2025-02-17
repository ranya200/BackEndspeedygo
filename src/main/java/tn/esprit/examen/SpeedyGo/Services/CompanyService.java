package tn.esprit.examen.SpeedyGo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.CompanyRepository;
import tn.esprit.examen.SpeedyGo.entities.Company;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(String id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(String id, Company updatedCompany) {
        return companyRepository.findById(id).map(company -> {
            company.setAddress(updatedCompany.getAddress());
            company.setTelephone(updatedCompany.getTelephone());
            company.setEmail(updatedCompany.getEmail());
            return companyRepository.save(company);
        }).orElse(null);
    }

    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }
}
