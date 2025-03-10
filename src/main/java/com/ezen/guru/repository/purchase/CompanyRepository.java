package com.ezen.guru.repository.purchase;

import com.ezen.guru.domain.Company;
import com.ezen.guru.dto.purchase.UpdateCompanyRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Integer>,CompanyCustomRepository {
    Company findByCompanyId(String companyId);

    @Modifying
    @Query("UPDATE Company " +
            "SET companyName = :#{#company.companyName}, " +
            "ceo = :#{#company.ceo}, " +
            "tel = :#{#company.tel}, " +
            "email = :#{#company.email}, " +
            "zipcode = :#{#company.zipcode}, " +
            "address = :#{#company.address} " +
            "WHERE companyId = :companyId")
    public void update(@Param("companyId") String companyId, @Param("company") UpdateCompanyRequest company);

    @Transactional
    @Modifying
    @Query("DELETE FROM Company " +
            "WHERE companyId = :companyId")
    public void remove(@Param("companyId") String companyId);

    boolean existsByCompanyId(String companyId);
}
